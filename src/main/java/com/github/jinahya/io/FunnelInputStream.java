/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
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


import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * An input stream reads bytes only through {@link InputStream#read()}.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class FunnelInputStream extends FilterInputStream {


    /**
     * Creates a funnel input stream built on top of the specified underlying
     * input stream.
     *
     * @param in the underlying input stream
     *
     * @throws NullPointerException if {@code input} is {@code null}
     */
    public FunnelInputStream(final InputStream in) {

        super(in);
    }


    @Override
    public int read(final byte[] b, int off, final int len) throws IOException {

        if (b == null) {
            throw new NullPointerException("null b");
        }

        if (off < 0) {
            throw new IndexOutOfBoundsException("off(" + off + ") < 0");
        }

        if (len < 0) {
            throw new IndexOutOfBoundsException("len(" + len + ") < 0");
        }

        if (len > b.length - off) {
            throw new IndexOutOfBoundsException(
                "len(" + len + ") > b.length(" + b.length + ") - off(" + off
                + ")");
        }

        for (int i = 0; i < len; i++) {
            if ((b[off++] = (byte) read()) == -1) {
                return i;
            }
        }

        return len;
    }


}

