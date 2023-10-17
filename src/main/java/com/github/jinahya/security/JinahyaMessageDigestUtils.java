/*
 * Copyright 2013 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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
package com.github.jinahya.security;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.security.MessageDigest;
import java.util.Objects;

/**
 * Constants for {@link MessageDigest}s.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 * @see MessageDigest
 * @see <a
 * href="https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/security/MessageDigest.html">java.security.MessageDigest</a>
 * (JDK 21)
 * @see <a href="https://docs.oracle.com/en/java/javase/21/docs/specs/security/standard-names.html">Java Security
 * Standard Algorithm Names</a> (Java SE 21)
 * @see <a
 * href="https://docs.oracle.com/en/java/javase/21/docs/specs/security/standard-names.html#messagedigest-algorithms">MessageDigest
 * Algorithms</> (Java SE 21)
 */
public final class JinahyaMessageDigestUtils {

    /**
     * Updates specified message digest with bytes read from specified byte channel.
     *
     * @param digest  the digest to be updated.
     * @param channel the byte channel from which bytes are read.
     * @param buffer  a buffer whose {@code capacity} should be positive.
     * @param length  the maximum number of bytes to (read and) update; {@code -1} for all bytes read from
     *                {@code channel}.
     * @return actual number of bytes (read from {@code channel} and) updated to the {@code digest}.
     * @throws IOException if an I/O error occurs.
     */
    public static long updateLong(final MessageDigest digest, final ReadableByteChannel channel, final ByteBuffer buffer,
                                  long length)
            throws IOException {
        Objects.requireNonNull(digest, "digest is null");
        Objects.requireNonNull(channel, "input is null");
        if (Objects.requireNonNull(buffer, "buffer is null").capacity() == 0) {
            throw new IllegalArgumentException("buffer.capacity == 0");
        }
        if (length < 0L) {
            length = Long.MAX_VALUE;
        }
        long count = 0L;
        for (int r; length > 0L; count += r) {
            r = channel.read(buffer.clear().limit((int) Math.min(buffer.remaining(), length)));
            if (r == -1) {
                break;
            }
            digest.update(buffer.flip());
            length -= r;
        }
        return count;
    }

    /**
     * Updates specified message digest with bytes read from specified byte channel.
     *
     * @param digest  the digest to be updated.
     * @param channel the byte channel from which bytes are read.
     * @param buffer  a buffer whose {@code capacity} should be positive.
     * @param length  the maximum number of bytes to (read and) update; {@code -1} for all bytes read from
     *                {@code channel}.
     * @return actual number of bytes (read from {@code channel} and) updated to the {@code digest}.
     * @throws IOException if an I/O error occurs.
     */
    public static int update(final MessageDigest digest, final ReadableByteChannel channel, final ByteBuffer buffer,
                             final int length)
            throws IOException {
        return (int) updateLong(digest, channel, buffer, length);
    }

    /**
     * Updates specified message digest with bytes read from specified input stream.
     *
     * @param digest the digest to be updated.
     * @param input  the input stream from which bytes are read.
     * @param buffer a buffer whose {@code length} should be positive.
     * @param length the maximum number of bytes to (read and) update; {@code -1} for all bytes read from
     *               {@code stream}.
     * @return actual number of bytes (read from {@code stream} and) updated to the {@code digest}.
     * @throws IOException if an I/O error occurs.
     */
    public static long updateLong(final MessageDigest digest, final InputStream input, final byte[] buffer,
                                  long length)
            throws IOException {
        Objects.requireNonNull(input, "input is null");
        if (Objects.requireNonNull(buffer, "buffer is null").length == 0) {
            throw new IllegalArgumentException("buffer.length == 0");
        }
        return updateLong(digest, Channels.newChannel(input), ByteBuffer.wrap(buffer), length);
    }

    /**
     * Updates specified message digest with bytes read from specified input stream.
     *
     * @param digest the digest to be updated.
     * @param input  the input stream from which bytes are read.
     * @param buffer a buffer whose {@code length} should be positive.
     * @param length the maximum number of bytes to (read and) update; {@code -1} for all bytes read from
     *               {@code stream}.
     * @return actual number of bytes (read from {@code stream} and) updated to the {@code digest}.
     * @throws IOException if an I/O error occurs.
     */
    public static int update(final MessageDigest digest, final InputStream input, final byte[] buffer,
                             final int length)
            throws IOException {
        return (int) updateLong(digest, input, buffer, length);
    }

    private JinahyaMessageDigestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
