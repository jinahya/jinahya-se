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
import java.io.OutputStream;

/**
 * An output stream discards written bytes.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 * @see WhiteInputStream
 */
@SuppressWarnings({"java:S4349"})
public final class BlackOutputStream extends OutputStream {

    private static final class InstanceHolder {

        private static final OutputStream INSTANCE = new BlackOutputStream();

        private InstanceHolder() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    /**
     * Returns the instance. {@code BlackOutputStream} class is singleton.
     *
     * @return the instance.
     */
    public static OutputStream getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private BlackOutputStream() {
        super();
    }

    /**
     * Does nothing.
     *
     * @param b a byte.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public void write(final int b) throws IOException {
        // does nothing
    }
}
