/*
 * Copyright 2013 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * A filter input stream reads bytes only through {@link #read()} method.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 * @see FunnelOutputStream
 */
public class FunnelInputStream
        extends FilterInputStream {

    /**
     * Creates a funnel input stream built on top of the specified underlying input stream.
     *
     * @param in the underlying input stream
     */
    public FunnelInputStream(final InputStream in) {
        super(in);
    }

    @Override
    public final int read(final byte[] b, int off, final int len) throws IOException {
        Objects.requireNonNull(b, "b is null");
        if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        }
        int c = 0;
        for (; c < len; c++) {
            final int r = read();
            if (r == -1) {
                if (c == 0) {
                    return -1;
                }
                break;
            }
            b[off++] = (byte) r;
        }
        return c;
    }

    @Override
    public final int read(final byte[] b) throws IOException {
        return super.read(b); // -> return read(b, 0, b.length)
    }

    @Override
    public final long skip(final long n) throws IOException {
        return super.skip(n); // in.skip(n) -> read(b, off, len)
    }
}
