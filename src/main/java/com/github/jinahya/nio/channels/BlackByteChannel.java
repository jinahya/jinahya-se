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
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A byte channel which discards all remaining bytes of given buffer.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class BlackByteChannel implements WritableByteChannel {

    private static final class InstanceHolder {

        private static final WritableByteChannel INSTANCE = new BlackByteChannel();

        private InstanceHolder() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    /**
     * Returns the instance. The {@code BlackByteChannel} class is singleton.
     *
     * @return the instance.
     */
    public static WritableByteChannel getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private BlackByteChannel() {
        super();
    }

    /**
     * Discards some number of byte from specified buffer.
     *
     * @param src the buffer.
     * @return the number of bytes discarded.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public int write(final ByteBuffer src) throws IOException {
        Objects.requireNonNull(src, "src is null");
        int bytes = 0;
        for (; src.hasRemaining() && ThreadLocalRandom.current().nextBoolean(); bytes++) {
            src.get();
        }
        return bytes;
    }

    /**
     * Returns {@code true}.
     *
     * @return {@code true}
     */
    @Override
    public boolean isOpen() {
        return true;
    }

    /**
     * Does nothing.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public void close() throws IOException {
        // does nothing
    }
}
