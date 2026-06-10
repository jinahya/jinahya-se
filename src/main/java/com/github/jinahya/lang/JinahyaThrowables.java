package com.github.jinahya.lang;

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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * A utilities for {@link Throwable} class.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
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
            appendable.append(System.lineSeparator())
                    .append('\t')
                    .append(element.toString());
        }
        final var cause = thrown.getCause();
        if (cause != null) {
            printStackTrace(
                    cause,
                    appendable.append(System.lineSeparator())
            );
        }
        return appendable;
    }

    public static <S extends OutputStream, R> R printStackTraceToStream(final Throwable throwable,
                                                                        final Supplier<? extends S> supplier,
                                                                        final Charset charset,
                                                                        final Function<? super S, ? extends R> mapper) {
        Objects.requireNonNull(supplier, "supplier is null");
        Objects.requireNonNull(charset, "charset is null");
        Objects.requireNonNull(mapper, "mapper is null");
        final var stream = Objects.requireNonNull(supplier.get(), "null supplied from " + supplier);
        final var printer = new PrintStream(stream, true, charset);
        try {
            printStackTrace(throwable, printer);
        } catch (final IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
        return mapper.apply(stream);
    }

    public static byte[] getStackTraceBytes(final Throwable throwable, final Charset charset) {
        return printStackTraceToStream(
                throwable,
                ByteArrayOutputStream::new,
                charset,
                ByteArrayOutputStream::toByteArray
        );
    }

    public static <S extends Writer, R> R printStackTraceToWriter(final Throwable throwable,
                                                                  final Supplier<? extends S> supplier,
                                                                  final Function<? super S, ? extends R> mapper) {
        Objects.requireNonNull(supplier, "supplier is null");
        Objects.requireNonNull(mapper, "mapper is null");
        final var writer = Objects.requireNonNull(supplier.get(), "null supplied from " + supplier);
        final var printer = new PrintWriter(writer, true);
        try {
            printStackTrace(throwable, printer);
        } catch (final IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
        return mapper.apply(writer);
    }

    public static String getStackTraceString(final Throwable throwable) {
        return printStackTraceToWriter(
                throwable,
                StringWriter::new,
                StringWriter::toString
        );
    }

    private JinahyaThrowables() {
        throw new AssertionError("instantiation is not allowed");
    }
}
