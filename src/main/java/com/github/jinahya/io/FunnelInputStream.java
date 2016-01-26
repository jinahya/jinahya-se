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


import java.io.IOException;
import java.io.InputStream;


/**
 * An input stream reads bytes only through {@link InputStream#read()}.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class FunnelInputStream extends SafelyCloseableFilterInputStream {


    /**
     * Creates a funnel input stream built on top of the specified underlying
     * input stream.
     *
     * @param in the underlying input stream
     */
    public FunnelInputStream(final InputStream in) {

        super(in);
    }


    @Override
    public int read(final byte[] b, final int off, final int len)
        throws IOException {

        if (b == null) {
            throw new NullPointerException();
        }

        if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        }

        int count = 0;

        for (; count < len; count++) {
            final int read = read();
            if (read == -1) {
                if (count == 0) {
                    return -1;
                }
                break;
            }
            b[off + count] = (byte) read;
        }

        return count;
    }

}

