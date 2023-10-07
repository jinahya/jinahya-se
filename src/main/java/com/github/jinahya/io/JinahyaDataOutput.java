package com.github.jinahya.io;

import java.io.DataOutput;
import java.io.IOException;

/**
 * An extended data output defines methods for writing values in little endian byte order.
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
