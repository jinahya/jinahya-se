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
 * An output stream which shallows written bytes.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class BlackOutputStream extends OutputStream {


    /**
     * Creates a new instance with specified limit.
     *
     * @param limit the maximum number of bytes can be written; {@code -1} for
     * no limit.
     */
    public BlackOutputStream(final long limit) {

        super();

        if (limit < -1) {
            throw new IllegalArgumentException("limit(" + limit + ") < -1");
        }

        this.limit = limit;
    }


    /**
     * Writes given byte. This method does nothing. An {@link IOException} will
     * be thrown if {@code limit} is set and the number of bytes written so far
     * exceeds it.
     *
     * @param b the byte to write.
     *
     * @throws IOException {@inheritDoc }
     */
    @Override
    public void write(final int b) throws IOException {

        if (limit != -1 && count >= limit) {
            throw new IOException("limit(" + limit + ") exceeded");
        }

        count++;
    }


    /**
     * Constructs a channel that writes bytes to this stream.
     *
     * @return a new writable byte channel
     *
     * @see Channels#newChannel(OutputStream)
     * @deprecated Use {@link Channels#newChannel(java.io.OutputStream) }
     */
    @Deprecated
    public WritableByteChannel newChannel() {

        return Channels.newChannel(this);
    }


    /**
     * Returns the maximum number of bytes can be written.
     *
     * @return the maximum number of bytes can be written or {@code -1} if there
     * is no limit.
     */
    public long getLimit() {

        return limit;
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
     * the maximum number of bytes can be written.
     */
    private final long limit;


    /**
     * the total number of bytes written so far.
     */
    private long count = 0L;


}

