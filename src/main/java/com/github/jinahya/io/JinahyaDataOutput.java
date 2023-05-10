package com.github.jinahya.io;

import java.io.DataOutput;
import java.io.IOException;

public interface JinahyaDataOutput extends DataOutput {

    default void writeShortLe(final int v) throws IOException {
        write((byte) (v & 0xFF));
        write((byte) ((v >> Byte.SIZE) & 0xFF));
    }

    /**
     * Writes specified {@code int} value in little endian byte order.
     *
     * @param v the {@code int} value to write.
     * @throws IOException if an I/O error occurs.
     */
    default void writeIntLe(final int v) throws IOException {
        writeShortLe(v);
        writeShortLe(v >> Short.SIZE);
    }

    default void writeLongLe(final long v) throws IOException {
        writeIntLe((int) v);
        writeIntLe((int) (v >> Integer.SIZE));
    }
}
