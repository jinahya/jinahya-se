package com.github.jinahya.io;

import java.io.DataOutput;
import java.io.IOException;

public interface JinahyaDataOutput extends DataOutput {

    // -----------------------------------------------------------------------------------------------------------------
    default void writeShortLe(final int v) throws IOException {
        write((byte) (v & 0xFF));
        write((byte) (v >> 8 & 0xFF));
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Writes an {@code int} value, which is comprised of four bytes, to the output stream in little endian byte order.
     * The byte values to be written, in the order shown, are:
     * <blockquote><pre>{@code
     * (byte)(0xff & v)
     * (byte)(0xff & (v >> 8))
     * (byte)(0xff & (v >> 16))
     * (byte)(0xff & (v >> 24))
     * }</pre></blockquote>
     * The bytes written by this method may be read by the {@link JinahyaDataInput#readIntLe() readIntLe()} method of
     * interface {@link JinahyaDataInput}, which will then return an {@code int} equal to {@code v}.
     *
     * @param v the {@code int} value to be written.
     * @throws IOException if an I/O error occurs.
     * @see #writeInt(int)
     * @see JinahyaDataInput#readIntLe()
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
