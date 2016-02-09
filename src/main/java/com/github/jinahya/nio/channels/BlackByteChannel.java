/*
 * Copyright 2015 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;


/**
 * A byte channel which consumes all remaining bytes.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class BlackByteChannel implements WritableByteChannel {


    /**
     * The {@code #write(java.nio.ByteBuffer)} method of
     * {@code BlackByteChannel} class just set given buffer's {@code position}
     * as its {@code limit} and returns previous value of {@code remaining}.
     *
     * @param src {@inheritDoc}
     *
     * @return the value of {@code remainging} of specified byte buffer.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public int write(final ByteBuffer src) throws IOException {

        final int remainging = src.remaining();

        src.position(src.limit());

        return remainging;
    }


    /**
     * The {@code isOpen} method of {@code BlackByteChannel} class always
     * returns {@code true}.
     *
     * @return {@code true}
     */
    @Override
    public boolean isOpen() {

        return true;
    }


    /**
     * The {@code close} method of {@code BlackByteChannel} class does nothing.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public void close() throws IOException {

        // does nothing
    }

}

