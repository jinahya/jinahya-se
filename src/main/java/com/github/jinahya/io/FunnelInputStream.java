/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
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


import java.io.IOException;
import java.io.InputStream;


/**
 * An input stream reads bytes only through {@link InputStream#read()}.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class FunnelInputStream extends InputStream {


    /**
     * Creates a funnel input stream built on top of the specified underlying
     * input stream.
     *
     * @param input the underlying input stream
     *
     * @throws NullPointerException if {@code input} is {@code null}
     */
    protected FunnelInputStream(final InputStream input) {

        super();

        if (input == null) {
            throw new NullPointerException("null input");
        }

        this.input = input;
    }


    /**
     * Reads the next byte of data from the input stream.
     * <p/>
     * The {@code read()} method of {@code FunnelInputStream} calls
     * {@link InputStream#read()} on {@link #input}.
     *
     * @return {@inheritDoc }
     *
     * @throws IOException {@inheritDoc }
     */
    @Override
    public int read() throws IOException {

        return input.read();
    }


    /**
     * Closes this input stream and releases any system resources associated
     * with the stream.
     * <p/>
     * The {@code close()} method of {@code FunnelInputStream} calls
     * {@link InputStream#close()} on {@link #input}.
     *
     * @throws IOException {@inheritDoc }
     */
    @Override
    public void close() throws IOException {

        input.close();
    }


    /**
     * Marks the current position in this input stream.
     * <p/>
     * The {@code mark(int)} method of {@code FunnelInputStream} calls
     * {@link InputStream#mark(int)} on {@link #input}.
     *
     * @param readLimit {@inheritDoc }
     */
    @Override
    public void mark(final int readLimit) {

        input.mark(readLimit);
    }


    /**
     * Repositions this stream to the position at the time the {@code mark}
     * method was last called on this input stream.
     * <p/>
     * The {@code reset()} method of {@code FunnelInputStream} calls
     * {@link InputStream#reset()} on {@link #input}.
     *
     * @throws IOException {@inheritDoc }
     */
    @Override
    public void reset() throws IOException {

        input.reset();
    }


    /**
     * The underlying input stream.
     */
    protected final InputStream input;


}

