package com.github.jinahya.lang;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UncheckedIOException;
import java.util.Objects;

public final class JinahyaThrowables {

    /**
     * Appends specified object's stack traces to specified appendable, and returns the appendable.
     *
     * @param thrown     the object whose stack traces are appended.
     * @param appendable the appendable to which {@code thrown}'s stack traces are appended.
     * @param <A>        appendable type parameter
     * @return given {@code appendable}.
     * @throws IOException if an I/O error occurs.
     */
    public static <A extends Appendable> A printStackTrace(final Throwable thrown, final A appendable)
            throws IOException {
        Objects.requireNonNull(thrown, "thrown is null");
        Objects.requireNonNull(appendable, "appendable is null");
        if (appendable instanceof PrintStream stream) {
            thrown.printStackTrace(stream);
            return appendable;
        }
        if (appendable instanceof PrintWriter writer) {
            thrown.printStackTrace(writer);
            return appendable;
        }
        appendable.append(thrown.toString());
        for (final var element : thrown.getStackTrace()) {
            appendable.append(System.lineSeparator()).append('\t').append(element.toString());
        }
        final var cause = thrown.getCause();
        if (cause != null) {
            printStackTrace(cause, appendable.append(System.lineSeparator()));
        }
        return appendable;
    }

    public static String printStackTrace(final Throwable throwable) {
        try {
            return printStackTrace(throwable, new StringWriter()).toString();
        } catch (final IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    private JinahyaThrowables() {
        throw new AssertionError("instantiation is not allowed");
    }
}
