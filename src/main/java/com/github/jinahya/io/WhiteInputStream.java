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
 * An input stream generates random bytes.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class WhiteInputStream extends InputStream {


    /**
     * Creates a new instance with given {@code limit}.
     *
     * @param limit the maximum number of bytes can be read; {@code -1} for no
     * limit.
     */
    public WhiteInputStream(final long limit) {

        super();

        if (limit < -1) {
            throw new IllegalArgumentException("limit(" + limit + ") < -1");
        }

        this.limit = limit;
    }


    /**
     * Returns next byte.
     *
     * @return next byte or {@code -1} if {@code limit} is set and the number of
     * byte read so far exceeds it.
     *
     * @throws IOException {@inheritDoc }
     */
    @Override
    public int read() throws IOException {

        if (limit != -1 && count >= limit) {
            return -1;
        }

        count++;

        return (int) (System.currentTimeMillis() & 0xFFL);
    }


    /**
     * Constructs a channel that read bytes from this stream.
     *
     * @return a new readable byte channel
     *
     * @see Channels#newChannel(InputStream)
     * @deprecated Use {@link Channels#newChannel(java.io.InputStream)}
     */
    @Deprecated
    public ReadableByteChannel newChannel() {

        return Channels.newChannel(this);
    }


    /**
     * Returns the maximum number of bytes can be read.
     *
     * @return the maximum number of bytes can be read or {@code -1} if there is
     * no limit.
     */
    public long getLimit() {

        return limit;
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
    private final long limit;


    /**
     * the total number of byte read so far.
     */
    private long count = 0L;


}

