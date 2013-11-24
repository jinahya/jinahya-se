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


import java.io.IOException;
import java.io.InputStream;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class FunnelInputStream extends InputStream {


    /**
     * Creates a funnel input stream built on top of the specified underlying
     * input stream.
     *
     * @param input the underlying input stream
     */
    public FunnelInputStream(final InputStream input) {

        super();

        if (input == null) {
            throw new NullPointerException("null input");
        }

        this.input = input;
    }


    /**
     * Reads the next byte of data from the input stream. The {@code read()}
     * method for class {@code FunnelInputStream} performs {@code input.read()}
     * and returns the result.
     *
     * @return {@inheritDoc }
     *
     * @throws IOException {@inheritDoc }
     */
    @Override
    public int read() throws IOException {

        return input.read();
    }


    @Override
    public int read(final byte[] b, int off, final int len) throws IOException {

        if (funnel) {
            return super.read(b, off, len);
        }

        return input.read(b, off, len);

//        for (int i = 0, read; i < len; i++) {
//            if ((read = read()) == -1) {
//                return i == 0 ? -1 : i;
//            }
//            b[off++] = (byte) read;
//        }
//
//        return len;
    }


    @Override
    public long skip(final long n) throws IOException {

        if (funnel) {
            return super.skip(n);
        }

        return input.skip(n);
    }


    @Override
    public int available() throws IOException {

        if (funnel) {
            return super.available(); // ... always returns 0.
        }

        return input.available();
    }


    @Override
    public void close() throws IOException {

        input.close();
    }


    @Override
    public void mark(final int readLimit) {

        if (funnel) {
            super.mark(readLimit); // ... does nothing.
        }

        input.mark(readLimit);
    }


    @Override
    public void reset() throws IOException {

        if (funnel) {
            super.reset(); // ... does nothing except throw an IOException.
        }

        input.reset();
    }


    public boolean getFunnel() {

        return funnel;
    }


    public void setFunnel(final boolean funnel) {

        this.funnel = funnel;
    }


    protected final InputStream input;


    protected boolean funnel = true;


}

