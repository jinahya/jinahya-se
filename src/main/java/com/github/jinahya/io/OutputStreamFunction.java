package com.github.jinahya.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.util.function.IntConsumer;

@FunctionalInterface
public interface OutputStreamFunction extends IntConsumer {

    /**
     * Writes the specified byte to this output stream. The general contract for {@code write} is that one byte is
     * written to the output stream. The byte to be written is the eight low-order bits of the argument {@code b}. The
     * 24 high-order bits of {@code b} are ignored.
     *
     * @param b the byte.
     * @throws IOException if an I/O error occurs. In particular, an IOException may be thrown if the output stream has
     *                     been closed.
     */
    void write(int b) throws IOException;

    /**
     * Accepts specified value. The {@code accept(int)} method of {@code OutputStreamFunction} interface invokes {@link
     * #write(int)} method with specified value.
     *
     * @param value the value.
     */
    @Override
    default void accept(final int value) {
        try {
            write(value);
        } catch (final IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    /**
     * Returns a new instance of {@link OutputStream} whose {@link OutputStream#write(int)} method returns the value of
     * {@link #write(int)} method.
     *
     * @return a new instance of {@link OutputStream}.
     */
    default OutputStream toOutputStream() {
        return new OutputStream() {
            @Override
            public void write(final int b) throws IOException {
                OutputStreamFunction.this.write(b);
            }
        };
    }
}
