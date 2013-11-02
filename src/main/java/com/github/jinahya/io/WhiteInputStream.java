/*
 * Copyright 2012 Jin Kwon <jinahya at gmail.com>.
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
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;


/**
 * An {@code InputStream} which generates random values.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class WhiteInputStream extends InputStream {


    /**
     * Creates a new instance with given {@code limit}.
     *
     * @param limit the maximum number of bytes can be read; {@code -1L} for no
     * limit.
     *
     * @throws IllegalArgumentException if {@code limit} is less than
     * {@code -1L}
     */
    public WhiteInputStream(final long limit) {

        super();

        if (limit < -1L) {
            throw new IllegalArgumentException("limit(" + limit + ") < -1L");
        }

        this.limit = limit;
    }


    /**
     * Creates a new instance.
     */
    public WhiteInputStream() {

        this(-1L);
    }


    /**
     * Reads the next byte of data from the input stream.
     *
     * @return the next byte of data, or -1 if {@code limit} is set and the
     * number of bytes read so far exceeds it.
     *
     * @throws IOException {@inheritDoc}
     */
    @Override
    public int read() throws IOException {

        if (limit != -1L && count++ >= limit) {
            return -1;
        }

        //count++;
        return (int) (System.currentTimeMillis() & 0xFF);
    }


    /**
     * Constructs a channel that read bytes from this stream.
     *
     * @return a new readable byte channel
     *
     * @see Channels#newChannel(InputStream)
     */
    public ReadableByteChannel newChannel() {

        return Channels.newChannel(this);
    }


    /**
     * Returns the current value of {@link #limit}.
     *
     * @return the current value of {@link #limit}.
     */
    public long getLimit() {

        return limit;
    }


    /**
     * Sets the value of {@link #limit}.
     *
     * @param limit new value for {@link #limit}.
     */
    public void setLimit(final long limit) {

        if (limit < -1L) {
            throw new IllegalArgumentException("limit(" + limit + ") < -1L");
        }

        this.limit = limit;
    }


    /**
     * Returns the number of bytes read so far.
     *
     * @return the number of bytes read so far.
     */
    public long getCount() {

        return count;
    }


    /**
     * the maximum number of bytes can be read. {@code -1L} for unlimited.
     */
    protected long limit;


    /**
     * the number of byte read so far.
     */
    private long count = 0x00L;


}

