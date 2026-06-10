package com.github.jinahya.io;

/*-
 * #%L
 * jinahya-se
 * %%
 * Copyright (C) 2025 - 2026 Jinahya
 * %%
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
 * #L%
 */

import java.io.DataOutput;
import java.io.IOException;

/**
 * An extended data output.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see JinahyaDataInput
 */
public interface JinahyaDataOutput
        extends DataOutput {

    /**
     * Writes specified {@code int} value's lower {@value Short#BYTES} bytes in little endian byte order.
     *
     * @param v the {@code short} value to write.
     * @throws IOException if an I/O error occurs.
     * @see JinahyaDataInput#readShortLe()
     */
    default void writeShortLe(final int v) throws IOException {
        writeByte(v);
        writeByte((v >> Byte.SIZE));
    }

    /**
     * Writes specified {@code int} value in little endian byte order.
     *
     * @param v the {@code int} value to write.
     * @throws IOException if an I/O error occurs.
     * @see JinahyaDataInput#readIntLe()
     */
    default void writeIntLe(final int v) throws IOException {
        writeShortLe(v);
        writeShortLe(v >> Short.SIZE);
    }

    /**
     * Writes specified {@code long} value in little endian byte order.
     *
     * @param v the {@code long} value to write.
     * @throws IOException if an I/O error occurs.
     * @see JinahyaDataInput#readLongLe()
     */
    default void writeLongLe(final long v) throws IOException {
        writeIntLe((int) v);
        writeIntLe((int) (v >> Integer.SIZE));
    }
}
