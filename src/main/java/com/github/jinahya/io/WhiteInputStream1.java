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
public class WhiteInputStream1 extends FunnelInputStream {


    public WhiteInputStream1() {

        super(null);
    }


    @Override
    public int read() throws IOException {

        return 0;
    }


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
    public void close() throws IOException {

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

