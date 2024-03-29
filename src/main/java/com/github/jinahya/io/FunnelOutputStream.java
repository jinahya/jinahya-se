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

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * A filer output stream writes bytes only through {@link #write(int)} method.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 * @see FunnelInputStream
 */
public class FunnelOutputStream extends FilterOutputStream {

    /**
     * Creates a funnel output stream built on top of the specified underlying output stream.
     *
     * @param out the underlying output stream
     */
    public FunnelOutputStream(final OutputStream out) {
        super(out);
    }

    @Override
    public final void write(byte[] b) throws IOException {
        super.write(b);
    }

    @Override
    public final void write(byte[] b, int off, int len) throws IOException {
        super.write(b, off, len);
    }
}
