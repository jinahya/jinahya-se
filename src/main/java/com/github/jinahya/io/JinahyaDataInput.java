package com.github.jinahya.io;

import java.io.DataInput;
import java.io.IOException;

public interface JinahyaDataInput extends DataInput {

    /**
     * Reads {@value java.lang.Short#BYTES} bytes and decodes them as a {@code short} value in reversed byte order.
     *
     * @return a {@code short} value.
     * @throws IOException if an I/O error occurs.
     * @see JinahyaDataOutput#writeShortLe(int)
     */
    default short readShortLe() throws IOException {
        return (short) ((readByte() & 0xFF) | (readByte() << Byte.SIZE));
    }

    /**
     * Reads {@value java.lang.Integer#BYTES} input bytes and decodes them as a {@code int} value in reversed byte
     * order.
     *
     * @return the {@code int value} read.
     * @throws IOException if an I/O error occurs.
     * @see JinahyaDataOutput#writeIntLe(int)
     */
    default int readIntLe() throws IOException {
        return (readShortLe() & 0xFFFF) | (readShortLe() << Short.SIZE);
    }

    /**
     * Reads {@value java.lang.Long#BYTES} bytes and decodes them as a {@code long} value in reversed byte order.
     *
     * @return a {@code long} value.
     * @throws IOException if an I/O error occurs.
     * @see JinahyaDataOutput#writeLongLe(long)
     */
    default long readLongLe() throws IOException {
        return (readIntLe() & 0xFFFFFFFFL) | (((long) readIntLe()) << Integer.SIZE);
    }
}
