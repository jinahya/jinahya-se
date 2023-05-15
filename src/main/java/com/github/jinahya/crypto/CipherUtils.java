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
import java.util.Objects;

/**
 * Utilities for {@link Cipher}s.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class CipherUtils {

    /**
     * Updates and optionally finishes a multipart encryption or description operation.
     *
     * @param cipher the cipher
     * @param input  the input
     * @param output the output
     * @param inbuf  an input buffer.
     * @param outbuf an output buffer.
     * @param limit  the maximum number of bytes to process; {@code -1L} for all available bytes in {@code input}.
     * @param finish a flag for calling {@link Cipher#doFinal()}.
     * @return the actual number of bytes processed
     * @throws IOException               if an I/O error occurs.
     * @throws IllegalBlockSizeException if this cipher is a block cipher, no padding has been requested (only in
     *                                   encryption mode), and the total input length of the data processed by this
     *                                   cipher is not a multiple of block size; or if this encryption algorithm is
     *                                   unable to process the input data provided. <i>Description copied from
     *                                   {@link Cipher#doFinal(byte[], int)}.</i>
     * @throws BadPaddingException       if this cipher is in decryption mode, and (un)padding has been requested, but
     *                                   the decrypted data is not bounded by the appropriate padding bytes.
     *                                   <i>Description copied from {@link Cipher#doFinal(byte[], int)}.</i>
     * @see Cipher#update(byte[], int, int, byte[], int)
     */
    public static long update(final Cipher cipher, final InputStream input, final OutputStream output,
                              final byte[] inbuf, final byte[] outbuf, long limit, final boolean finish)
            throws IOException, IllegalBlockSizeException, BadPaddingException, ShortBufferException {
        Objects.requireNonNull(cipher, "cipher is null");
        Objects.requireNonNull(input, "input is null");
        Objects.requireNonNull(output, "output is null");
        if (Objects.requireNonNull(inbuf, "inbuf is null").length == 0) {
            throw new IllegalArgumentException("inbuf.length == 0");
        }
        if (Objects.requireNonNull(outbuf, "outbuf is null").length == 0) {
            throw new IllegalArgumentException("outbuf.length == 0");
        }
        if (limit <= 0L) {
            throw new IllegalArgumentException("limit(" + limit + ") is not positive");
        }
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
        if (finish) {
            outlen = cipher.doFinal(outbuf, 0);
            output.write(outbuf, 0, outlen);
        }
        return count;
    }

    public static long update(final Cipher cipher, final InputStream input, final OutputStream output,
                              final byte[] inbuf, final long limit, final boolean finish)
            throws IOException, IllegalBlockSizeException, BadPaddingException {
        if (Objects.requireNonNull(inbuf, "inbuf is null").length == 0) {
            throw new IllegalArgumentException("inbuf.length == 0");
        }
        final byte[] outbuf = new byte[cipher.getOutputSize(inbuf.length) + cipher.getBlockSize()];
        try {
            return update(cipher, input, output, inbuf, outbuf, limit, finish);
        } catch (final ShortBufferException sbe) {
            throw new RuntimeException(sbe);
        }
    }

    public static long update(final Cipher cipher, final ReadableByteChannel readable,
                              final WritableByteChannel writable, final ByteBuffer inbuf, final ByteBuffer outbuf,
                              long limit, final boolean finish)
            throws IOException, IllegalBlockSizeException, BadPaddingException, ShortBufferException {
        Objects.requireNonNull(cipher, "cipher is null");
        Objects.requireNonNull(readable, "readable is null");
        Objects.requireNonNull(writable, "writable is null");
        if (Objects.requireNonNull(inbuf, "inbuf is null").capacity() == 0) {
            throw new IllegalArgumentException("inbuf.capacity is zero");
        }
        if (Objects.requireNonNull(outbuf, "outbuf is null").capacity() == 0) {
            throw new IllegalArgumentException("outbuf.capacity is zero");
        }
        if (limit <= 0L) {
            throw new IllegalArgumentException("limit(" + limit + ") is not positive");
        }
        long count = 0L;
        inbuf.clear();
        outbuf.clear();
        for (int r; limit > 0L; count += r) {
            if (limit < inbuf.remaining()) {
                inbuf.limit(inbuf.position() + (int) limit);
            }
            if ((r = readable.read(inbuf)) == -1) {
                break;
            }
            inbuf.flip(); // limit -> position, position -> zero
            final int updated = cipher.update(inbuf, outbuf);
            if (updated > 0) {
                for (outbuf.flip(); outbuf.hasRemaining(); ) {
                    writable.write(outbuf);
                }
            }
            inbuf.clear(); // position -> zero, limit -> capacity
            outbuf.clear(); // position -> zero, limit -> capacity
            limit -= r;
        }
        if (finish) {
            assert inbuf.position() == 0;
            assert inbuf.limit() == inbuf.capacity();
            assert outbuf.position() == 0;
            assert outbuf.limit() == outbuf.capacity();
            inbuf.position(inbuf.limit());
            final int finalized = cipher.doFinal(inbuf, outbuf);
            if (finalized > 0) {
                for (outbuf.flip(); outbuf.hasRemaining(); ) {
                    writable.write(outbuf);
                }
            }
        }
        return count;
    }

    public static long update(final Cipher cipher, final ReadableByteChannel readable,
                              final WritableByteChannel writable, final ByteBuffer inbuf, long limit,
                              final boolean finish)
            throws IOException, IllegalBlockSizeException, BadPaddingException {
        if (Objects.requireNonNull(inbuf, "inbuf is null").capacity() == 0) {
            throw new IllegalArgumentException("inbuf.capacity is zero");
        }
        final ByteBuffer outbuf = ByteBuffer.allocate(cipher.getOutputSize(inbuf.capacity()) + cipher.getBlockSize());
        try {
            return update(cipher, readable, writable, inbuf, outbuf, limit, finish);
        } catch (final ShortBufferException sbe) {
            throw new RuntimeException(sbe);
        }
    }

    private CipherUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
