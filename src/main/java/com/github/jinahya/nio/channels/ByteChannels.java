/*
 * Copyright 2013 Jin Kwon <onacit at gmail.com>.
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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A utility class for channels.
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public final class ByteChannels {


    /**
     * logger.
     */
    private static final Logger LOGGER
        = LoggerFactory.getLogger(ByteChannels.class);


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

        while (length == -1L || count < length) {

            long remained = input.size() - input.position();
            if (remained == 0L) {
                break;
            }
            if (length != -1L) {
                final long required = length - count;
                if (remained > required) {
                    remained = required;
                }
            }

            final long transferred
                = input.transferTo(input.position(), remained, output);
            input.position(input.position() + transferred);
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

        LOGGER.debug("copy({}, {}, {})", input, output, length);

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

        while (length == -1L || count < length) {

            LOGGER.debug("output.size: {}", output.size());
            LOGGER.debug("output.position: {}", output.position());
            long available = output.size() - output.position();
            LOGGER.debug("available: {}", available);
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
                = output.transferFrom(input, output.position(), available);
            LOGGER.debug("transferred: {}", transferred);
            output.position(output.position() + transferred);
            LOGGER.debug("output.position: {}", output.position());
            count += transferred;
            LOGGER.debug("count: {}", count);
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

        LOGGER.debug("copy1({}, {}, {}, {})", input, output, buffer, length);

        if (input instanceof FileChannel) {
            return copy((FileChannel) input, output, length);
        }

        if (output instanceof FileChannel) {
            return copy(input, (FileChannel) output, length);
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
            return copy(input, (FileChannel) output, length);
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
     * @param input the input
     * @param output the output
     * @param buffer the buffer
     * @param length the maximum number of bytes to copy; any negative value for
     * all available bytes.
     *
     * @return the actual number of byte copied.
     *
     * @throws IOException if an I/O error occurs.
     */
    public static long copy(final File input, final WritableByteChannel output,
                            final ByteBuffer buffer, final long length)
        throws IOException {

        if (input == null) {
            throw new NullPointerException("input");
        }

        final FileChannel input_ = new FileInputStream(input).getChannel();
        try {
            try {
                return copy(input_, output, buffer, length);
            } finally {
                input_.force(false);
            }
        } finally {
            input_.close();
        }
    }


    /**
     * Copies bytes from {@code input} to {@code output} using specified
     * {@code buffer}.
     *
     * @param input the input
     * @param output the output
     * @param buffer the buffer
     * @param length the maximum number of bytes to copy; any negative value for
     * all available bytes.
     *
     * @return the actual number of byte copied.
     *
     * @throws IOException if an I/O error occurs.
     */
    public static long copy(final ReadableByteChannel input, final File output,
                            final ByteBuffer buffer, final long length)
        throws IOException {

        if (output == null) {
            throw new NullPointerException("output");
        }

        final FileChannel foutput = new FileOutputStream(output).getChannel();
        try {
            try {
                return copy(input, foutput, buffer, length);
            } finally {
                foutput.force(false);
            }
        } finally {
            foutput.close();
        }
    }


    /**
     * Copies bytes from {@code input} to {@code output} using specified
     * {@code buffer}.
     *
     * @param input the input
     * @param output the output
     * @param buffer the buffer
     * @param length the maximum number of bytes to copy; any negative value for
     * all available bytes.
     *
     * @return the actual number of byte copied.
     *
     * @throws IOException if an I/O error occurs.
     */
    public static long copy(final File input, final File output,
                            final ByteBuffer buffer, final long length)
        throws IOException {

        if (input == null) {
            throw new NullPointerException("input == null");
        }

        if (output == null) {
            throw new NullPointerException("output == null");
        }

        final ReadableByteChannel finput
            = new FileInputStream(input).getChannel();
        try {
            final FileChannel foutput
                = new FileOutputStream(output).getChannel();
            try {
                try {
                    return copy(finput, foutput, buffer, length);
                } finally {
                    foutput.force(false);
                }
            } finally {
                foutput.close();
            }
        } finally {
            finput.close();
        }
    }


    /**
     * private constructor.
     */
    private ByteChannels() {

        super();
    }


}

