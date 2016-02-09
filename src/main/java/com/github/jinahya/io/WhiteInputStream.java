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


/**
 * An input stream generates random bytes.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class WhiteInputStream extends FunnelInputStream {


    /**
     * Creates a new instance.
     */
    public WhiteInputStream() {

        super(null);
    }


    /**
     * Reads the next byte of data from the input stream. The {@code read()}
     * method of {@code WhiteInputStream} class returns {@code 0}.
     *
     * @return the next byte of data, or -1 if the end of the stream is reached.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public int read() throws IOException {

        return 0;
    }


    /**
     * Tests if this input stream supports the mark and reset methods. The
     * {@code markSupported()} method of {@code WhiteInputStream} class returns
     * {@code true}.
     *
     * @return true if this stream instance supports the mark and reset methods;
     * false otherwise.
     */
    @Override
    public boolean markSupported() {

        return true;
    }


    @Override
    public synchronized void mark(final int readlimit) {

        // does nothing
    }


    @Override
    public synchronized void reset() throws IOException {

        // does nothing
    }


    @Override
    public int available() throws IOException {

        return Integer.MAX_VALUE;
    }


    @Override
    public long skip(final long n) throws IOException {

        return n;
    }

}

