/*
 * Copyright 2012 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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
import java.util.concurrent.ThreadLocalRandom;

/**
 * An input stream reads random bytes.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 * @see BlackOutputStream
 */
@SuppressWarnings({"java:S4929"})
public final class WhiteInputStream extends InputStream {

    private static final class InstanceHolder {

        private static final InputStream INSTANCE = new WhiteInputStream();

        private InstanceHolder() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    /**
     * Returns the instance. {@code WhiteInputStream} class is singleton.
     *
     * @return the instance.
     */
    public static InputStream getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private WhiteInputStream() {
        super();
    }

    /**
     * Returns a random value.
     *
     * @return a random value between {@code 0} and {@code 255}, both inclusive.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public int read() throws IOException {
        return ThreadLocalRandom.current().nextInt(256);
    }
}
