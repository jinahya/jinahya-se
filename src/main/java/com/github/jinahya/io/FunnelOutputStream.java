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
import java.io.OutputStream;


/**
 * An output stream writes bytes only through {@link OutputStream#write(int) }.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class FunnelOutputStream extends OutputStream {


    /**
     * Creates a funnel output stream built on top of the specified underlying
     * output stream.
     *
     * @param output the underlying output stream
     */
    protected FunnelOutputStream(final OutputStream output) {

        super();

        this.output = output;
    }


    /**
     * Writes the specified byte to this output stream. The {@code write(int)}
     * method of {@code FunnelInputStream} calls {@link OutputStream#write(int)}
     * on {@link #output}.
     *
     * @param b {@inheritDoc }
     *
     * @throws IOException {@inheritDoc }
     */
    @Override
    public void write(final int b) throws IOException {

        output.write(b);
    }


    /**
     * Flushes this output stream and forces any buffered output bytes to be
     * written out. The {@code flush()} of {@code FunnelOutputStream} calls
     * {@link OutputStream#flush()} on {@link #output}.
     *
     * @throws IOException {@inheritDoc }
     */
    @Override
    public void flush() throws IOException {

        output.flush();
    }


    /**
     * Closes this output stream and releases any system resources associated
     * with this stream. The {@code close} method of {@code FunnelOutputStream}
     * calls {@link OutputStream#close()} on {@link #output}.
     *
     * @throws IOException {@inheritDoc }
     */
    @Override
    public void close() throws IOException {

        output.close();
    }


    /**
     * The underlying output stream.
     */
    protected final OutputStream output;

}

