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


    /**
     * Copies bytes from given input stream to given output stream.
     *
     * @param input the input stream
     * @param output the output stream
     * @param buffer a buffer to use
     * @param length the maximum number of bytes to copy; {@code -1L} for all
     * available bytes in {@code input}.
     *
     * @return the actual number of bytes copied.
     *
     * @throws IOException if an I/O error occurs
     */
    public static long copy(final InputStream input, final OutputStream output,
                            final byte[] buffer, final long length)
        throws IOException {

        if (input == null) {
            throw new NullPointerException("input == null");
        }

        if (output == null) {
            throw new NullPointerException("output == null");
        }

        if (buffer == null) {
            throw new NullPointerException("buffer == null");
        }

        if (buffer.length == 0) {
            throw new IllegalArgumentException(
                "buffer.length(" + buffer.length + ") == 0");
        }

        if (length < -1L) {
            throw new IllegalArgumentException("length(" + length + ") < -1L");
        }

        long count = 0L;

        long remained = length - count;
        for (int read; length == -1L || remained > 0; count += read) {
            int limit = buffer.length;
            if (length != -1L && remained < buffer.length) {
                limit = (int) remained;
            }
            read = input.read(buffer, 0, limit);
            if (read == -1) {
                break;
            }
            output.write(buffer, 0, read);
            remained -= read;
        }

        return count;
    }


    /**
     * Copies bytes from given input file to given output stream.
     *
     * @param input the input file
     * @param output the output stream
     * @param buffer a buffer
     * @param length the maximum number of bytes to copy; {@code -1L} for all
     * available bytes in {@code input}.
     *
     * @return the actual number of bytes copied.
     *
     * @throws IOException if an I/O error occurs
     */
    public static long copy(final File input, final OutputStream output,
                            final byte[] buffer, final long length)
        throws IOException {

        if (input == null) {
            throw new NullPointerException("input == null");
        }

        final InputStream finput = new FileInputStream(input);
        try {
            return copy(finput, output, buffer, length);
        } finally {
            finput.close();
        }
    }


    /**
     * Copies bytes from given input stream to given output file.
     *
     * @param input the input stream
     * @param output the output file
     * @param buffer a buffer to use
     * @param length the maximum number of bytes to copy; {@code -1L} for all
     * available bytes in {@code input}.
     *
     * @return the actual number of bytes copied.
     *
     * @throws IOException if an I/O error occurs
     */
    public static long copy(final InputStream input, final File output,
                            final byte[] buffer, final long length)
        throws IOException {

        if (output == null) {
            throw new NullPointerException("output");
        }

        final OutputStream foutput = new FileOutputStream(output);
        try {
            try {
                return copy(input, foutput, buffer, length);
            } finally {
                foutput.flush();
            }
        } finally {
            foutput.close();
        }
    }


    /**
     * Copies bytes from given input file to given output file.
     *
     * @param input the input file
     * @param output the output file
     * @param buffer a buffer
     * @param length the maximum number of bytes to copy; {@code -1L} for all
     * available bytes in {@code input}.
     *
     * @return the actual number of bytes copied.
     *
     * @throws IOException if an I/O error occurs
     */
    public static long copy(final File input, final File output,
                            final byte[] buffer, final long length)
        throws IOException {

        if (output == null) {
            throw new NullPointerException("output");
        }

        final InputStream finput = new FileInputStream(input);
        try {
            final OutputStream foutput = new FileOutputStream(output);
            try {
                try {
                    return copy(finput, foutput, buffer, length);
                } finally {
                    foutput.flush();
                }
            } finally {
                foutput.close();
            }
        } finally {
            finput.close();
        }
    }


    /**
     * Creates a new instance.
     */
    private JinahyaByteStreams() {

        super();
    }

}

