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
import java.nio.channels.ReadableByteChannel;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A readable byte channel read random values.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class WhiteByteChannel implements ReadableByteChannel {

    private static final class InstanceHolder {

        private static final ReadableByteChannel INSTANCE = new WhiteByteChannel();

        private InstanceHolder() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    /**
     * Returns the instance. The {@code WhiteByteChannel} class is singleton.
     *
     * @return the instance.
     */
    public static ReadableByteChannel getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private WhiteByteChannel() {
        super();
    }

    /**
     * Puts some number of random values into specified buffer.
     *
     * @param dst the buffer.
     * @return the number of random values put on {@code dst}.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public int read(final ByteBuffer dst) throws IOException {
        Objects.requireNonNull(dst, "dst is null");
        int bytes = 0;
        for (; dst.hasRemaining() && ThreadLocalRandom.current().nextBoolean(); bytes++) {
            dst.put((byte) ThreadLocalRandom.current().nextInt(255));
        }
        return bytes;
    }

    /**
     * Returns {@code true}.
     *
     * @return {@code true}.
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
