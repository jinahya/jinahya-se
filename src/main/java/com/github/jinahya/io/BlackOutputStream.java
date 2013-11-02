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
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;


/**
 * An {@code OutputStream} which blacks out written values.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class BlackOutputStream extends OutputStream {


    /**
     * Creates a new instance with specified limit.
     *
     * @param limit the maximum number of bytes can be written; {@code -1L} for
     * no limit.
     */
    public BlackOutputStream(final long limit) {

        super();

        if (limit < -1L) {
            throw new IllegalArgumentException("limit(" + limit + ") < -1L");
        }

        this.limit = limit;
    }


    /**
     * Creates a new instance.
     */
    public BlackOutputStream() {

        this(-1L);
    }


    /**
     * Writes the specified byte to this output stream.
     *
     * @param b the byte to write
     *
     * @throws IOException if {@code limit} is set and the number of bytes
     * written so far exceeds it.
     */
    @Override
    public void write(final int b) throws IOException {

        if (limit != -1L && count++ >= limit) {
            throw new IOException("limit(" + limit + ") exceeded");
        }

        //count++;
    }


    /**
     * Constructs a channel that writes bytes to this stream.
     *
     * @return a new writable byte channel
     *
     * @see Channels#newChannel(OutputStream)
     */
    public WritableByteChannel newChannel() {

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
     * Returns the number of bytes written so far.
     *
     * @return the number of bytes written so far.
     */
    public long getCount() {

        return count;
    }


    /**
     * the maximum number of bytes can be written. {@code -1L} for unlimited.
     */
    protected long limit;


    /**
     * the number of bytes written so far.
     */
    private long count = 0L;


}

