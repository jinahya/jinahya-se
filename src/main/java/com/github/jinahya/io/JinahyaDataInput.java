package com.github.jinahya.io;

import java.io.DataInput;
import java.io.IOException;

public interface JinahyaDataInput extends DataInput {

    // -----------------------------------------------------------------------------------------------------------------
    default short readShortLe() throws IOException {
        int value = 0;
        for (int i = 0; i < Short.SIZE; i += Byte.SIZE) {
            value |= (readByte() & 0xFF) << i;
        }
        return (short) value;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Reads four input bytes written in little endian byte order and returns an {@code int} value. Let {@code a-d} be
     * the first through fourth bytes read. The value returned is:
     * <blockquote><pre>{@code
     * (((d & 0xff) << 24) |
     * ((c & 0xff) << 16) |
     * ((b & 0xff) << 8) |
     * (a & 0xff))
     * }</pre></blockquote>
     * This method is suitable for reading bytes written by the {@link JinahyaDataOutput#writeIntLe(int) writeIntLe}
     * method of interface {@link JinahyaDataOutput}.
     *
     * @return the {@code int value} read.
     * @throws IOException if an I/O error occurs.
     * @see #readInt()
     * @see JinahyaDataOutput#writeIntLe(int)
     */
    default int readIntLe() throws IOException {
        int value = 0;
        for (int i = 0; i < Integer.SIZE; i += Byte.SIZE) {
            value |= (readByte() & 0xFF) << i;
        }
        return value;
    }

    // -----------------------------------------------------------------------------------------------------------------
    default long readLongLe() throws IOException {
        return readIntLe() & 0xFFFFFFFFL | (long) readIntLe() << Integer.SIZE;
    }
}
