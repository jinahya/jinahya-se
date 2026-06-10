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

import java.io.DataInput;
import java.io.IOException;

/**
 * An extended data input.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see JinahyaDataInput
 */
public interface JinahyaDataInput
        extends DataInput {

    /**
     * Reads {@value java.lang.Short#BYTES} bytes and decodes them as a {@code short} value in little endian byte
     * order.
     *
     * @return a {@code short} value.
     * @throws IOException          if an I/O error occurs.
     * @throws java.io.EOFException if this stream reaches the end before reading all the bytes.
     * @see JinahyaDataOutput#writeShortLe(int)
     */
    default short readShortLe() throws IOException {
        return (short) ((readByte() & 0xFF) | (readByte() << Byte.SIZE));
    }

    /**
     * Reads {@value java.lang.Integer#BYTES} input bytes and decodes them as a {@code int} value in little endian byte
     * order.
     *
     * @return the {@code int value} read.
     * @throws IOException          if an I/O error occurs.
     * @throws java.io.EOFException if this stream reaches the end before reading all the bytes.
     * @see JinahyaDataOutput#writeIntLe(int)
     */
    default int readIntLe() throws IOException {
        return (readShortLe() & 0xFFFF) | (readShortLe() << Short.SIZE);
    }

    /**
     * Reads {@value java.lang.Long#BYTES} bytes and decodes them as a {@code long} value in little endian byte order.
     *
     * @return a {@code long} value.
     * @throws IOException          if an I/O error occurs.
     * @throws java.io.EOFException if this stream reaches the end before reading all the bytes.
     * @see JinahyaDataOutput#writeLongLe(long)
     */
    default long readLongLe() throws IOException {
        return (readIntLe() & 0xFFFFFFFFL) | (((long) readIntLe()) << Integer.SIZE);
    }
}
