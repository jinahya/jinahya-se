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


import java.io.EOFException;
import java.io.IOException;


/**
 * An output stream which shallows written bytes.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class LimitedBlackOutputStream extends FunnelOutputStream {


    public LimitedBlackOutputStream(final long limit) {

        super(null);

        this.limit = limit;
    }


    @Override
    public void write(final int b) throws IOException {

        if (limit >= 0L && limit <= count++) {
            throw new EOFException("limit exceeded");
        }
    }


    @Override
    public void flush() throws IOException {

        // does nothing
    }


    @Override
    public void close() throws IOException {

        // does nothing
    }


    protected long limit;


    protected transient long count;

}

