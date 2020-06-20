package com.github.jinahya.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.function.IntSupplier;

@FunctionalInterface
public interface InputStreamFunction extends IntSupplier {

    /**
     * Reads the next byte of data from the input stream. The value byte is returned as an {@code int} in the range
     * {@code 0} to {@code 255}. If no byte is available because the end of the stream has been reached, the value
     * {@code -1} is returned. This method blocks until input data is available, the end of the stream is detected, or
     * an exception is thrown.
     *
     * @return the next byte of data, or {@code -1} if the end of the stream is reached.
     * @throws IOException if an I/O error occurs.
     */
    int read() throws IOException;

    /**
     * Returns an {@code int} value. The {@code getAsInt()} method of {@code InputStreamFunction} interface returns the
     * result of {@link #read()} method.
     *
     * @return an {@code int} value.
     */
    @Override
    default int getAsInt() {
        try {
            final int value = read();
            if (value == -1) {
                throw new EOFException("reached to a end-of-file");
            }
            return value;
        } catch (final IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    /**
     * Returns a new instance of {@link InputStream} whose {@link InputStream#read()} method returns the result of
     * {@link #read()} method.
     *
     * @return a new instance of {@link InputStream}.
     */
    default InputStream toInputStream() {
        return new InputStream() {
            @Override
            public int read() throws IOException {
                return InputStreamFunction.this.read();
            }
        };
    }
}
