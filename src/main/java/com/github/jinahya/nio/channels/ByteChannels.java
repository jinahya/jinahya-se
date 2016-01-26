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


package com.github.jinahya.nio.channels;


import java.io.File;
import java.io.IOException;
import static java.lang.invoke.MethodHandles.lookup;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;


/**
 * A utility class for channels.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class ByteChannels {


    /**
     * logger.
     */
    private static final Logger logger = getLogger(lookup().lookupClass());


    /**
     *
     * @param input
     * @param output
     * @param length
     *
     * @return the actual number of bytes copied.
     *
     * @throws IOException if an I/O error occurs.
     *
     * @see FileChannel#transferTo(long, long, WritableByteChannel)
     */
    public static long copy(final FileChannel input,
                            final WritableByteChannel output,
                            final long length)
        throws IOException {

        if (input == null) {
            throw new NullPointerException("input == null");
        }

        if (output == null) {
            throw new NullPointerException("output == null");
        }

        if (length < -1L) {
            throw new IllegalArgumentException("length(" + length + ") < -1L");
        }

        long count = 0L;

        long position = input.position();
        while (length == -1L || count < length) {

            long available = input.size() - position;
            if (available == 0L) {
                break;
            }
            if (length != -1L) {
                final long required = length - count;
                if (available > required) {
                    available = required;
                }
            }

            final long transferred
                = input.transferTo(position, available, output);
            position += transferred;
            count += transferred;
        }

        return count;
    }


    /**
     *
     * @param input
     * @param output
     * @param length
     *
     * @return the actual number of bytes copied.
     *
     * @throws IOException if an I/O error occurs.
     *
     * @see FileChannel#transferFrom(ReadableByteChannel, long, long)
     */
    public static long copy(final ReadableByteChannel input,
                            final FileChannel output, final long length)
        throws IOException {

        logger.trace("copy({}, {}, {})", input, output, length);

        if (input == null) {
            throw new NullPointerException("input == null");
        }

        if (output == null) {
            throw new NullPointerException("output == null");
        }

        if (length < -1L) {
            throw new IllegalArgumentException("length(" + length + ") < -1L");
        }

        long count = 0L;

        long position = output.position();
        while (length == -1L || count < length) {

            long available = output.size() - position;
            if (available == 0L) {
                break;
            }
            if (length != -1L) {
                final long required = length - count;
                if (available > required) {
                    available = required;
                }
            }

            final long transferred
                = output.transferFrom(input, position, available);
            if (transferred == 0) {
                final ByteBuffer buffer = ByteBuffer.allocate(1);
                if (input.read(buffer) == -1) {
                    break;
                }
                buffer.flip();
                while (buffer.hasRemaining()) {
                    output.write(buffer);
                }
            }
            position += transferred;
            count += transferred;
        }

        return count;
    }


    /**
     * Copies bytes from {@code input} to {@code output} using specified
     * {@code buffer}.
     *
     * @param input the input channel
     * @param output the output channel
     * @param buffer the buffer to use
     * @param length the maximum number of bytes to copy; {@code -1L} for all
     * available bytes from {@code input}.
     *
     * @return the actual number of bytes copied.
     *
     * @throws IOException if an I/O error occurs.
     */
    public static long copy1(final ReadableByteChannel input,
                             final WritableByteChannel output,
                             final ByteBuffer buffer, final long length)
        throws IOException {

        logger.debug("copy1({}, {}, {}, {})", input, output, buffer, length);

        if (input instanceof FileChannel) {
            return copy((FileChannel) input, output, length);
        }

        if (output instanceof FileChannel) {
            return copy(input, (FileChannel) output, buffer, length);
        }

        if (input == null) {
            throw new NullPointerException("input == null");
        }

        if (output == null) {
            throw new NullPointerException("output == null");
        }

        if (buffer == null) {
            throw new NullPointerException("buffer == null");
        }

        if (buffer.capacity() == 0) {
            throw new IllegalArgumentException(
                "buffer.capacity(" + buffer.capacity() + ") == 0");
        }

        if (length < -1L) {
            throw new IllegalArgumentException("length(" + length + ") < -1L");
        }

        if (buffer.position() > 0 || buffer.limit() < buffer.capacity()) {
            buffer.clear(); // position -> 0, limit -> capacity
        }

        long count = 0L;

        for (int read; length == -1L || count < length; count += read) {

            if (length != -1L) {
                final long remained = length - count;
                if (remained < buffer.remaining()) {
                    buffer.limit(buffer.position() + (int) remained);
                }
            }

            read = input.read(buffer);
            if (read == -1) {
                break;
            }

            buffer.flip(); // limit -> position, position -> 0
            output.write(buffer);
            buffer.compact(); // position -> n + 1, limit -> capacity
        }

        buffer.flip();
        while (buffer.hasRemaining()) {
            output.write(buffer);
        }

        return count;
    }


    /**
     * Copies bytes from {@code input} to {@code output} using specified
     * {@code buffer}.
     *
     * @param input the input channel
     * @param output the output channel
     * @param buffer the buffer to use
     * @param length the maximum number of bytes to copy; {@code -1L} for all
     * available bytes from {@code input}.
     *
     * @return the actual number of bytes copied.
     *
     * @throws IOException if an I/O error occurs.
     */
    public static long copy2(final ReadableByteChannel input,
                             final WritableByteChannel output,
                             final ByteBuffer buffer, final long length)
        throws IOException {

        if (input instanceof FileChannel) {
            return copy((FileChannel) input, output, length);
        }

        if (output instanceof FileChannel) {
            return copy(input, (FileChannel) output, buffer, length);
        }

        if (input == null) {
            throw new NullPointerException("input == null");
        }

        if (output == null) {
            throw new NullPointerException("output == null");
        }

        if (buffer == null) {
            throw new NullPointerException("buffer == null");
        }

        if (buffer.capacity() == 0) {
            throw new IllegalArgumentException(
                "buffer.capacity(" + buffer.capacity() + ") == 0");
        }

        if (length < -1L) {
            throw new IllegalArgumentException("length(" + length + ") < -1L");
        }

        if (buffer.position() > 0 || buffer.limit() < buffer.capacity()) {
            buffer.clear(); // position -> 0, limit -> capacity
        }

        long count = 0L;

        for (int read; length == -1L || count < length; count += read) {

            if (length >= 0L) {
                final long remained = length - count;
                if (remained < buffer.capacity()) {
                    buffer.limit((int) remained);
                }
            }

            read = input.read(buffer);
            if (read == -1) {
                break;
            }

            buffer.flip(); // limit -> position, position -> 0
            while (buffer.hasRemaining()) {
                output.write(buffer);
            }
            buffer.clear(); // position -> 0, limit -> capacity
        }

        for (int read; length == -1L || count < length; count += read) {
            buffer.clear(); // position -> 0, limit -> capacity
            if (length >= 0L) {
                final long remained = length - count;
                if (remained < buffer.capacity()) {
                    buffer.limit((int) remained);
                }
            }
            read = input.read(buffer);
            if (read == -1) {
                break;
            }
            buffer.flip(); // limit -> position, position -> 0
            while (buffer.hasRemaining()) {
                output.write(buffer);
            }
        }

        return count;
    }


    private static long copy(final ReadableByteChannel input,
                             final WritableByteChannel output,
                             final ByteBuffer buffer, final long length)
        throws IOException {

        return copy1(input, output, buffer, length);
    }


    /**
     * Copies bytes from {@code input} to {@code output} using specified
     * {@code buffer}.
     *
     * @param input the input path
     * @param output the output channel
     * @param length the maximum number of bytes to copy; {@code -1L} for all
     * available bytes in {@code input}.
     *
     * @return the actual number of bytes copied.
     *
     * @throws IOException if an I/O error occurs.
     *
     * @see #copy(FileChannel, WritableByteChannel, long)
     */
    public static long copy(final Path input, final WritableByteChannel output,
                            final long length)
        throws IOException {

        if (input == null) {
            throw new NullPointerException("input = null");
        }

        final FileChannel finput
            = FileChannel.open(input, StandardOpenOption.READ);
        try {
            return copy(finput, output, length);
        } finally {
            finput.close();
        }
    }


    /**
     * Copies bytes from {@code input} to {@code output} using specified
     * {@code buffer}.
     *
     * @param input the input file
     * @param output the output channel
     * @param length the maximum number of bytes to copy; {@code -1L} for all
     * available bytes in {@code input}.
     *
     * @return the actual number of bytes copied.
     *
     * @throws NullPointerException if {@code input} is {@code null}
     * @throws IOException if an I/O error occurs.
     *
     * @see #copy(Path, WritableByteChannel, ByteBuffer, long)
     */
    public static long copy(final File input, final WritableByteChannel output,
                            final long length)
        throws IOException {

        if (input == null) {
            throw new NullPointerException("input == null");
        }

        if (!input.isFile()) {
            throw new IllegalArgumentException("input file doesn't exist");
        }

        return copy(input.toPath(), output, length);
    }


    /**
     * Copies all or specified number of bytes from input channel to output
     * path.
     *
     * @param input the input channel
     * @param output the output path
     * @param length the maximum number of bytes to copy; {@code -1L} for for
     * all available bytes in {@code input}.
     *
     * @return the actual number of bytes copied.
     *
     * @throws NullPointerException if {@code output} is {@code null}.
     * @throws IOException if an I/O error occurs.
     */
    public static long copy(final ReadableByteChannel input, final Path output,
                            final long length)
        throws IOException {

        if (output == null) {
            throw new NullPointerException("output == null");
        }

        final FileChannel foutput
            = FileChannel.open(output, StandardOpenOption.WRITE);
        try {
            try {
                final long count = copy(input, foutput, length);
                foutput.truncate(foutput.position() + count);
                return count;
            } finally {
                foutput.force(false);
            }
        } finally {
            foutput.close();
        }
    }


    /**
     * Copies all or specified number of bytes from input channel to output file
     * using given buffer.
     *
     * @param input the input channel
     * @param output the output file
     * @param length the maximum number of bytes to copy; {@code -1L} for all
     * available bytes in {@code input}.
     *
     * @return the actual number of bytes copied.
     *
     * @throws NullPointerException if {@code output} is {@code null}.
     * @throws IOException if an I/O error occurs.
     */
    public static long copy(final ReadableByteChannel input, final File output,
                            final long length)
        throws IOException {

        if (output == null) {
            throw new NullPointerException("output == null");
        }

        return copy(input, output.toPath(), length);
    }


    /**
     * Copies all or specified number of bytes from input file to output file.
     *
     * @param input the input file
     * @param output the output file
     * @param length the maximum number of bytes to copy; {@code -1L} for all
     * available bytes in {@code input}.
     *
     * @return the actual number of bytes copied.
     *
     * @throws IOException if an I/O error occurs.
     */
    public static long copy(final File input, final File output,
                            final long length)
        throws IOException {

        if (output == null) {
            throw new NullPointerException("output == null");
        }

        final FileChannel foutput
            = FileChannel.open(output.toPath(), StandardOpenOption.WRITE);
        try {
            final long count = copy(input, foutput, length);
            foutput.truncate(foutput.position());
            foutput.force(false);
            return count;
        } finally {
            foutput.close();
        }
    }


    /**
     * private constructor.
     */
    private ByteChannels() {

        super();
    }


}

