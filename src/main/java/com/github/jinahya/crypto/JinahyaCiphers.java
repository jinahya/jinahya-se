/*
 * Copyright 2012 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jinahya.crypto;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;

/**
 * A utility class for {@link Cipher}s.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class JinahyaCiphers {

    /**
     * An immutable map of transformations and lists of available key sizes that
     * every implementation of the Java platform is required to support.
     *
     * @see Cipher
     */
    public static final Map<String, List<Integer>> SUPPORTED_TRANSFORMATIONS;

    static {
        final Map<String, List<Integer>> m = new HashMap<>();
        m.put("AES/CBC/NoPadding", asList(128));
        m.put("AES/CBC/PKCS5Padding", asList(128));
        m.put("AES/ECB/NoPadding", asList(128));
        m.put("AES/ECB/PKCS5Padding", asList(128));
        m.put("DES/CBC/NoPadding", asList(56));
        m.put("DES/CBC/PKCS5Padding", asList(56));
        m.put("DES/ECB/NoPadding", asList(56));
        m.put("DES/ECB/PKCS5Padding", asList(56));
        m.put("DESede/CBC/NoPadding", asList(168));
        m.put("DESede/CBC/PKCS5Padding", asList(168));
        m.put("DESede/ECB/NoPadding", asList(168));
        m.put("DESede/ECB/PKCS5Padding", asList(168));
        m.put("RSA/ECB/PKCS1Padding", asList(1024, 2048));
        m.put("RSA/ECB/OAEPWithSHA-1AndMGF1Padding", asList(1024, 2048));
        m.put("RSA/ECB/OAEPWithSHA-256AndMGF1Padding", asList(1024, 2048));
        SUPPORTED_TRANSFORMATIONS = unmodifiableMap(m);
    }

    /**
     * Updates and optionally finishes a multi-part encryption or description
     * operation.
     *
     * @param cipher the cipher
     * @param input the input
     * @param output the output
     * @param length buffer size
     * @param limit the maximum number of bytes to process; {@code -1L} for all
     * available bytes in {@code input}.
     * @param finalize
     *
     * @return the actual number of bytes processed
     *
     * @throws IOException if an I/O error occurs.
     * @throws IllegalBlockSizeException if this cipher is a block cipher, no
     * padding has been requested (only in encryption mode), and the total input
     * length of the data processed by this cipher is not a multiple of block
     * size; or if this encryption algorithm is unable to process the input data
     * provided. <i>Description copied from
     * {@link Cipher#doFinal(byte[], int)}.</i>
     * @throws BadPaddingException if this cipher is in decryption mode, and
     * (un)padding has been requested, but the decrypted data is not bounded by
     * the appropriate padding bytes. <i>Description copied from
     * {@link Cipher#doFinal(byte[], int)}.</i>
     *
     * @see Cipher#update(byte[], int, int, byte[], int)
     */
    public static long update(final Cipher cipher, final InputStream input,
                              final OutputStream output, final int length,
                              final long limit, final boolean finalize)
            throws IOException, IllegalBlockSizeException, BadPaddingException {

        if (cipher == null) {
            throw new NullPointerException("cipher == null");
        }

        if (input == null) {
            throw new NullPointerException("input == null");
        }

        if (output == null) {
            throw new NullPointerException("output == null");
        }

        if (length <= 0) {
            throw new IllegalArgumentException("size(" + length + ") <= 0");
        }

        long count = 0L;

        final byte[] inbuf = new byte[length];
        byte[] outbuf = new byte[cipher.getOutputSize(inbuf.length)];

        for (int read; limit < 0L || count < limit; count += read) {
            int inlen = inbuf.length;
            if (limit != -1L) {
                final long remained = limit - count;
                if (inlen > remained) {
                    inlen = (int) remained;
                }
            }
            read = input.read(inbuf, 0, inlen);
            if (read == -1) {
                break;
            }
            while (true) {
                try {
                    final int outlen = cipher.update(inbuf, 0, read, outbuf);
                    output.write(outbuf, 0, outlen);
                    break;
                } catch (final ShortBufferException sbe) {
                    outbuf = new byte[outbuf.length * 2];
                }
            }
        }

        if (finalize) {
            while (true) {
                try {
                    final int outlen = cipher.doFinal(outbuf, 0);
                    output.write(outbuf, 0, outlen);
                    break;
                } catch (final ShortBufferException sbe) {
                    outbuf = new byte[outbuf.length * 2];
                }
            }
        }

        return count;
    }

    private static ByteBuffer enlarge(final ByteBuffer source) {

        final ByteBuffer target = ByteBuffer.allocate(source.capacity() * 2);

        final int previousPosition = source.position();
        final int previousLimit = source.limit();
        source.position(0);
        source.limit(source.capacity());
        for (int i = 0; i < source.capacity(); i++) {
            target.put(source.get(i));
        }
        source.position(previousPosition);
        source.limit(previousLimit);

        target.position(source.position());

        return target;
    }

    public static long update(final Cipher cipher,
                              final ReadableByteChannel input,
                              final WritableByteChannel output,
                              final int capacity, final long limit,
                              final boolean finalize)
            throws IOException, IllegalBlockSizeException, BadPaddingException {

        if (cipher == null) {
            throw new IllegalArgumentException("cipher");
        }

        if (input == null) {
            throw new IllegalArgumentException("input");
        }

        if (output == null) {
            throw new IllegalArgumentException("output");
        }

        if (capacity <= 0) {
            throw new IllegalArgumentException("inlen(" + capacity + ") <= 0");
        }

        final int blockSize = cipher.getBlockSize();
        if (blockSize != 0 && capacity < blockSize) {
            throw new IllegalArgumentException(
                    "capacity(" + capacity + ") < blockSize(" + blockSize + ")");
        }

        long count = 0L;

        final ByteBuffer inbuf = ByteBuffer.allocate(capacity);
        ByteBuffer outbuf = ByteBuffer.allocate(cipher.getOutputSize(capacity));

        for (int read; limit < 0L || count < limit; count += read) {
            if (limit >= 0L) {
                final long remained = limit - count;
                if (remained < inbuf.remaining()) {
                    inbuf.limit(inbuf.position() + (int) remained);
                }
            }
            read = input.read(inbuf);
            if (read == -1) {
                break;
            }
            inbuf.flip(); // limit -> position, position -> 0
            for (final int previous = inbuf.limit(); true;) {
//                System.out.println("inbuf.remaining: " + inbuf.remaining());
//                System.out.println("outbuf.remaining: " + outbuf.remaining());
                try {
                    final int updated = cipher.update(inbuf, outbuf);
                    inbuf.limit(previous);
                    break;
                } catch (ShortBufferException sbe) {
                    if (inbuf.remaining() > 1) {
                        inbuf.limit(inbuf.position() + (inbuf.remaining() / 2));
                    } else {
                        outbuf = enlarge(outbuf);
                    }
                }
            }
            inbuf.compact();
            outbuf.flip(); // limit -> position, position -> 0
            final int written = output.write(outbuf);
            outbuf.compact();
        }

        // update all remaining input
        inbuf.flip();
        for (final int previous = inbuf.limit(); inbuf.hasRemaining();) {
            try {
                cipher.update(inbuf, outbuf);
                inbuf.limit(previous);
            } catch (ShortBufferException sbe) {
                if (inbuf.remaining() > 1) {
                    inbuf.limit(inbuf.position() + (inbuf.remaining() / 2));
                } else {
                    outbuf = enlarge(outbuf);
                }
            }
            outbuf.flip(); // limit -> position, position -> 0
            output.write(outbuf);
            outbuf.compact();
        }
        assert inbuf.position() == inbuf.limit(); // fully drained

        // writes all remaining output
        outbuf.flip();
        while (outbuf.hasRemaining()) {
            output.write(outbuf);
        }
        outbuf.compact();
        assert outbuf.remaining() == outbuf.capacity(); // fully available

        if (finalize) {
            while (true) {
                try {
                    final int finalized = cipher.doFinal(inbuf, outbuf);
                    break;
                } catch (final ShortBufferException sbe) {
                    outbuf = ByteBuffer.allocate(outbuf.capacity() * 2);
                }
            }
            outbuf.flip();
            while (outbuf.hasRemaining()) {
                output.write(outbuf);
            }
        }

        return count;
    }

    private JinahyaCiphers() {
        super();
    }
}
