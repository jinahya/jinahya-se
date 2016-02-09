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
public class LimitedWhiteInputStream extends FunnelInputStream {


    public LimitedWhiteInputStream(final long limit) {

        super(null);

        this.limit = limit;
    }


    @Override
    public int read() throws IOException {

        if (limit >= 0L && limit <= count) {
            return -1;
        }

        count++;

        return 0;
    }


    @Override
    public boolean markSupported() {

        return false;
    }


    @Override
    public synchronized void reset() throws IOException {

        // does nothing
    }


    @Override
    public synchronized void mark(int readlimit) {

        // does nothing
    }


    @Override
    public void close() throws IOException {

        // does nothing
    }


    @Override
    public int available() throws IOException {

        if (limit <= 0L) {
            return Integer.MAX_VALUE;
        }

        if (count >= 0 && count <= limit) {
            final long available = limit - count;
            if (available <= Integer.MAX_VALUE) {
                return (int) available;
            } else {
                return Integer.MAX_VALUE;
            }
        }

        return 0;
    }


    @Override
    public long skip(final long n) throws IOException {

        for (int i = 0; i < n; i++) {
            read();
        }

        return n;
    }


    /**
     * Returns the maximum number of bytes can be read.
     *
     * @return the maximum number of bytes can be read or {@code -1} if there is
     * no limit.
     */
    public long getLimit() {

        return limit;
    }


    /**
     * Returns the number of bytes read so far.
     *
     * @return the number of bytes read so far.
     */
    public long getCount() {

        return count;
    }


    /**
     * the maximum number of bytes can be read. {@code -1L} for unlimited.
     */
    protected long limit;


    /**
     * the total number of byte read so far.
     */
    protected transient long count;

}

