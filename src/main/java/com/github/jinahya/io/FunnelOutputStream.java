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
import java.io.OutputStream;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class FunnelOutputStream extends OutputStream {


    /**
     * Creates a funnel output stream built on top of the specified underlying
     * output stream.
     *
     * @param output the underlying output stream
     */
    public FunnelOutputStream(final OutputStream output) {

        super();

        if (output == null) {
            throw new NullPointerException("output");
        }

        this.output = output;
    }


    @Override
    public void write(final int b) throws IOException {

        output.write(b);
    }


    @Override
    public void write(final byte[] b, int off, final int len)
        throws IOException {

        if (funnel) {
            super.write(b, off, len);
            return;
        }

        output.write(b, off, len);
    }


    @Override
    public void flush() throws IOException {

        output.flush();
    }


    @Override
    public void close() throws IOException {

        output.close();
    }


    public boolean getFunnel() {

        return funnel;
    }


    public void setFunnel(final boolean funnel) {

        this.funnel = funnel;
    }


    protected final OutputStream output;


    protected boolean funnel = true;


}

