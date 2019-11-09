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

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.ByteBuffer.allocate;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.Collections.unmodifiableMap;

/**
 * A utility class for {@link Cipher}s.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class JinahyaCiphers {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * An unmodifiable map of transformations and lists of available key sizes that every implementation of the Java
     * platform is required to support.
     *
     * @see Cipher
     */
    public static final Map<String, List<Integer>> SUPPORTED_TRANSFORMATIONS;

    static {
        final Map<String, List<Integer>> m = new HashMap<>();
        m.put("AES/CBC/NoPadding", singletonList(128));
        m.put("AES/CBC/PKCS5Padding", singletonList(128));
        m.put("AES/ECB/NoPadding", singletonList(128));
        m.put("AES/ECB/PKCS5Padding", singletonList(128));
        m.put("DES/CBC/NoPadding", singletonList(56));
        m.put("DES/CBC/PKCS5Padding", singletonList(56));
        m.put("DES/ECB/NoPadding", singletonList(56));
        m.put("DES/ECB/PKCS5Padding", singletonList(56));
        m.put("DESede/CBC/NoPadding", singletonList(168));
        m.put("DESede/CBC/PKCS5Padding", singletonList(168));
        m.put("DESede/ECB/NoPadding", singletonList(168));
        m.put("DESede/ECB/PKCS5Padding", singletonList(168));
        m.put("RSA/ECB/PKCS1Padding", asList(1024, 2048));
        m.put("RSA/ECB/OAEPWithSHA-1AndMGF1Padding", asList(1024, 2048));
        m.put("RSA/ECB/OAEPWithSHA-256AndMGF1Padding", asList(1024, 2048));
        SUPPORTED_TRANSFORMATIONS = unmodifiableMap(m);
    }

    /**
     * Updates and optionally finishes a multi-part encryption or description operation.
     *
     * @param cipher   the cipher
     * @param input    the input
     * @param output   the output
     * @param inbuf    buffer size
     * @param limit    the maximum number of bytes to process; {@code -1L} for all available bytes in {@code input}.
     * @param finalize a flag for calling {@link Cipher#doFinal()}.
     * @return the actual number of bytes processed
     * @throws IOException               if an I/O error occurs.
     * @throws IllegalBlockSizeException if this cipher is a block cipher, no padding has been requested (only in
     *                                   encryption mode), and the total input length of the data processed by this
     *                                   cipher is not a multiple of block size; or if this encryption algorithm is
     *                                   unable to process the input data provided. <i>Description copied from {@link
     *                                   Cipher#doFinal(byte[], int)}.</i>
     * @throws BadPaddingException       if this cipher is in decryption mode, and (un)padding has been requested, but
     *                                   the decrypted data is not bounded by the appropriate padding bytes.
     *                                   <i>Description copied from {@link Cipher#doFinal(byte[], int)}.</i>
     * @see Cipher#update(byte[], int, int, byte[], int)
     */
    public static long update(final Cipher cipher, final InputStream input, final OutputStream output,
                              final byte[] inbuf, final byte[] outbuf, long limit, final boolean finalize)
            throws IOException, IllegalBlockSizeException, BadPaddingException, ShortBufferException {
        long count = 0L;
        int inlen = inbuf.length;
        int outlen;
        for (int r; limit > 0; count += r) {
            if (limit < inbuf.length) {
                inlen = (int) limit;
            }
            if ((r = input.read(inbuf, 0, inlen)) == -1) {
                break;
            }
            outlen = cipher.update(inbuf, 0, r, outbuf);
            output.write(outbuf, 0, outlen);
            limit -= r;
        }
        if (finalize) {
            outlen = cipher.doFinal(outbuf, 0);
            output.write(outbuf, 0, outlen);
        }
        return count;
    }

    public static long update(final Cipher cipher, final InputStream input, final OutputStream output,
                              final byte[] inbuf, long limit, final boolean finalize)
            throws IOException, IllegalBlockSizeException, BadPaddingException {
        final byte[] outbuf = new byte[cipher.getOutputSize(inbuf.length) + cipher.getBlockSize()];
        try {
            return update(cipher, input, output, inbuf, outbuf, limit, finalize);
        } catch (final ShortBufferException sbe) {
            throw new RuntimeException(sbe);
        }
    }

    public static long update(final Cipher cipher, final ReadableByteChannel readable,
                              final WritableByteChannel writable, final ByteBuffer inbuf,
                              final ByteBuffer outbuf, long limit, final boolean finalize)
            throws IOException, IllegalBlockSizeException, BadPaddingException, ShortBufferException {
        long count = 0L;
        for (int r; limit > 0L; count += r) {
            if (limit < inbuf.remaining()) {
                inbuf.limit(inbuf.position() + (int) limit);
            }
            if ((r = readable.read(inbuf)) == -1) {
                break;
            }
            inbuf.flip(); // limit -> position, position -> zero
            final int updated = cipher.update(inbuf, outbuf);
            inbuf.clear(); // position -> zero, limit -> capacity
            for (outbuf.flip(); outbuf.hasRemaining(); ) {
                final int written = writable.write(outbuf);
            }
            outbuf.clear(); // position -> zero, limit -> capacity
            limit -= r;
        }
        if (finalize) {
            inbuf.flip();
            final int finalized = cipher.doFinal(inbuf, outbuf);
            for (outbuf.flip(); outbuf.hasRemaining(); ) {
                final int written = writable.write(outbuf);
            }
        }
        return count;
    }

    public static long update(final Cipher cipher, final ReadableByteChannel readable,
                              final WritableByteChannel writable, final ByteBuffer inbuf,
                              long limit, final boolean finalize)
            throws IOException, IllegalBlockSizeException, BadPaddingException {
        final ByteBuffer outbuf = allocate(cipher.getOutputSize(inbuf.capacity()) + cipher.getBlockSize());
        try {
            return update(cipher, readable, writable, inbuf, outbuf, limit, finalize);
        } catch (final ShortBufferException sbe) {
            throw new RuntimeException(sbe);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private JinahyaCiphers() {
        super();
    }
}
