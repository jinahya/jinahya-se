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


import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class FunnelOutputStream extends FilterOutputStream {


    /**
     * Creates a funnel output stream built on top of the specified underlying
     * output stream.
     *
     * @param out the underlying output stream, or {@code null} if this instance
     * is to be created without an underlying stream.
     */
    public FunnelOutputStream(final OutputStream out) {

        super(out);
    }


    /**
     * {@inheritDoc} Overridden to write every byte via {@link #write(int)}.
     *
     * @param b {@inheritDoc }
     * @param off {@inheritDoc }
     * @param len {@inheritDoc }
     *
     * @throws IOException {@inheritDoc }
     */
    @Override
    public void write(final byte[] b, int off, final int len)
        throws IOException {

        for (int i = 0; i < len; i++) {
            write(b[off++]);
        }
    }


}
