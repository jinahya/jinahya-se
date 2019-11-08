/*
 * Copyright 2011 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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
package com.github.jinahya.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Utilities for InputStreams and OutputStreams.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class JinahyaByteStreams {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Copies specified number of bytes from specified input stream to specified output stream using specified array of
     * bytes as a buffer.
     *
     * @param input  the input stream from which bytes are read.
     * @param output the output stream to which bytes are written.
     * @param buffer the array of bytes to be used as a buffer whose {@code length} must be positive.
     * @param length the number of bytes to copy.
     * @return the actual number of bytes copied.
     * @throws IOException if an I/O error occurs
     */
    public static long copy(final InputStream input, final OutputStream output, final byte[] buffer, long length)
            throws IOException {
        if (input == null) {
            throw new NullPointerException("input is null");
        }
        if (output == null) {
            throw new NullPointerException("output is null");
        }
        if (buffer == null) {
            throw new NullPointerException("buffer is null");
        }
        if (buffer.length == 0) {
            throw new IllegalArgumentException("buffer.length(" + buffer.length + ") == 0");
        }
        if (length < 0L) {
            throw new IllegalArgumentException("length(" + length + ") < 0L");
        }
        long count = 0L;
        int limit = buffer.length;
        for (int read; length > 0; count += read) {
            if (length < limit) {
                limit = (int) length;
            }
            read = input.read(buffer, 0, limit);
            if (read == -1) {
                break;
            }
            output.write(buffer, 0, read);
            length -= read;
        }
        return count;
    }

    /**
     * Copies specified number of bytes from specified file to specified output stream using specified array of bytes as
     * a buffer.
     *
     * @param file   the input file from which bytes are read.
     * @param output the output stream to which bytes are written.
     * @param buffer the array of bytes to used as a buffer.
     * @param length the maximum number of bytes to copy.
     * @return the actual number of bytes copied.
     * @throws IOException if an I/O error occurs
     */
    public static long copy(final File file, final OutputStream output, final byte[] buffer, final long length)
            throws IOException {
        if (file == null) {
            throw new NullPointerException("file is null");
        }
        try (InputStream input = new FileInputStream(file)) {
            return copy(input, output, buffer, length);
        }
    }

    /**
     * Copies bytes from given input stream to given output file.
     *
     * @param input  the input stream
     * @param file   the output file
     * @param buffer a buffer to use
     * @param length the maximum number of bytes to copy; {@code -1L} for all available bytes in {@code input}.
     * @return the actual number of bytes copied.
     * @throws IOException if an I/O error occurs
     */
    public static long copy(final InputStream input, final File file, final byte[] buffer, final long length)
            throws IOException {
        if (file == null) {
            throw new NullPointerException("file is null");
        }
        final long copied;
        try (OutputStream output = new FileOutputStream(file)) {
            copied = copy(input, output, buffer, length);
            output.flush();
        }
        return copied;
    }

    /**
     * Copies bytes from given input file to given output file.
     *
     * @param source      the input file
     * @param destination the output file
     * @param buffer      a buffer
     * @param length      the maximum number of bytes to copy; {@code -1L} for all available bytes in {@code input}.
     * @return the actual number of bytes copied.
     * @throws IOException if an I/O error occurs
     */
    public static long copy(final File source, final File destination, final byte[] buffer, final long length)
            throws IOException {
        if (source == null) {
            throw new NullPointerException("source is null");
        }
        if (destination == null) {
            throw new NullPointerException("destination is null");
        }
        final long copied;
        try (InputStream input = new FileInputStream(source);
             OutputStream output = new FileOutputStream(destination)) {
            copied = copy(input, output, buffer, length);
            output.flush();
        }
        return copied;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    private JinahyaByteStreams() {
        super();
    }
}
