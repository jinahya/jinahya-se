package com.github.jinahya.lang;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Objects;

/**
 * Utilities for {@link Appendable}s.
 *
 * @see Appendable
 */
public final class JinahyaAppendableUtils {

    /**
     * Appends the specified character to the specified appendable, and returns the appendable.
     *
     * @param <T>        appendable type parameter
     * @param appendable the appendable to which the character is appended.
     * @param c          the character to append.
     * @return given {@code appendable}.
     * @throws NullPointerException if {@code appendable} is {@code null}.
     * @throws IOException          if an I/O error occurs.
     * @see Appendable#append(char)
     */
    @SuppressWarnings({"unchecked"})
    public static <T extends Appendable> T append(final T appendable, final char c) throws IOException {
        Objects.requireNonNull(appendable, "appendable is null");
        return (T) appendable.append(c);
    }

    /**
     * Appends the specified character sequence to the specified appendable, and returns the appendable.
     *
     * @param <T>        appendable type parameter
     * @param appendable the appendable to which the character sequence is appended.
     * @param csq        the character sequence to append; {@code "null"} is appended if {@code null}.
     * @return given {@code appendable}.
     * @throws NullPointerException if {@code appendable} is {@code null}.
     * @throws IOException          if an I/O error occurs.
     * @see Appendable#append(CharSequence)
     */
    public static <T extends Appendable> T append(final T appendable, final CharSequence csq) throws IOException {
        Objects.requireNonNull(appendable, "appendable is null");
        appendable.append(csq);
        return appendable;
    }

    /**
     * Appends a subsequence of the specified character sequence to the specified appendable, and returns the
     * appendable.
     *
     * @param <T>        appendable type parameter
     * @param appendable the appendable to which the subsequence is appended.
     * @param csq        the character sequence from which a subsequence is appended; characters of {@code "null"} are
     *                   appended if {@code null}.
     * @param start      the index of the first character in the subsequence.
     * @param end        the index of the character following the last character in the subsequence.
     * @return given {@code appendable}.
     * @throws NullPointerException      if {@code appendable} is {@code null}.
     * @throws IndexOutOfBoundsException if {@code start} or {@code end} are negative, {@code start} is greater than
     *                                   {@code end}, or {@code end} is greater than {@code csq.length()}.
     * @throws IOException               if an I/O error occurs.
     * @see Appendable#append(CharSequence, int, int)
     */
    public static <T extends Appendable> T append(final T appendable, final CharSequence csq, final int start,
                                                  final int end)
            throws IOException {
        Objects.requireNonNull(appendable, "appendable is null");
        appendable.append(csq, start, end);
        return appendable;
    }

    /**
     * Appends all characters in the specified array to the specified appendable, and returns the appendable.
     *
     * @param <T>        appendable type parameter
     * @param appendable the appendable to which the characters are appended.
     * @param chars      the array of characters to append.
     * @return given {@code appendable}.
     * @throws NullPointerException if either {@code appendable} or {@code chars} is {@code null}.
     * @throws IOException          if an I/O error occurs.
     */
    public static <T extends Appendable> T append(final T appendable, final char[] chars) throws IOException {
        Objects.requireNonNull(appendable, "appendable is null");
        Objects.requireNonNull(chars, "chars is null");
        return append(appendable, CharBuffer.wrap(chars));
    }

    /**
     * Appends the character(s) of the specified Unicode code point to the specified appendable, and returns the
     * appendable.
     *
     * @param <T>        appendable type parameter
     * @param appendable the appendable to which the code point is appended.
     * @param codePoint  the code point to append.
     * @return given {@code appendable}.
     * @throws NullPointerException     if {@code appendable} is {@code null}.
     * @throws IllegalArgumentException if {@code codePoint} is not a valid Unicode code point.
     * @throws IOException              if an I/O error occurs.
     * @see Character#toChars(int)
     */
    public static <T extends Appendable> T appendCodePoint(final T appendable, final int codePoint)
            throws IOException {
        Objects.requireNonNull(appendable, "appendable is null");
        return append(appendable, Character.toChars(codePoint));
    }

    /**
     * Appends the specified character to the specified appendable, the specified number of times, and returns the
     * appendable.
     *
     * @param <T>        appendable type parameter
     * @param appendable the appendable to which the character is appended.
     * @param c          the character to append.
     * @param count      the number of times to append; may be {@code 0}.
     * @return given {@code appendable}.
     * @throws NullPointerException     if {@code appendable} is {@code null}.
     * @throws IllegalArgumentException if {@code count} is negative.
     * @throws IOException              if an I/O error occurs.
     */
    public static <T extends Appendable> T repeat(final T appendable, final char c, final int count)
            throws IOException {
        Objects.requireNonNull(appendable, "appendable is null");
        if (count < 0) {
            throw new IllegalArgumentException("count(" + count + ") is negative");
        }
        for (int i = 0; i < count; i++) {
            appendable.append(c);
        }
        return appendable;
    }

    /**
     * Appends the specified character sequence to the specified appendable, the specified number of times, and
     * returns the appendable.
     *
     * @param <T>        appendable type parameter
     * @param appendable the appendable to which the character sequence is appended.
     * @param csq        the character sequence to append; {@code "null"} is appended if {@code null}.
     * @param count      the number of times to append; may be {@code 0}.
     * @return given {@code appendable}.
     * @throws NullPointerException     if {@code appendable} is {@code null}.
     * @throws IllegalArgumentException if {@code count} is negative.
     * @throws IOException              if an I/O error occurs.
     */
    public static <T extends Appendable> T repeat(final T appendable, final CharSequence csq, final int count)
            throws IOException {
        Objects.requireNonNull(appendable, "appendable is null");
        if (count < 0) {
            throw new IllegalArgumentException("count(" + count + ") is negative");
        }
        for (int i = 0; i < count; i++) {
            appendable.append(csq);
        }
        return appendable;
    }

    /**
     * Appends the {@link System#lineSeparator() line separator} to the specified appendable, and returns the
     * appendable.
     *
     * @param <T>        appendable type parameter
     * @param appendable the appendable to which the line separator is appended.
     * @return given {@code appendable}.
     * @throws NullPointerException if {@code appendable} is {@code null}.
     * @throws IOException          if an I/O error occurs.
     * @see System#lineSeparator()
     */
    public static <T extends Appendable> T appendLineSeparator(final T appendable) throws IOException {
        return append(appendable, System.lineSeparator());
    }

    /**
     * Appends the specified elements, separated by the specified delimiter, to the specified appendable, and returns
     * the appendable.
     *
     * @param <T>        appendable type parameter
     * @param appendable the appendable to which the elements are appended.
     * @param delimiter  the delimiter appended between each element; {@code "null"} is appended if {@code null}.
     * @param elements   the elements to append; each {@code null} element is appended as {@code "null"}.
     * @return given {@code appendable}.
     * @throws NullPointerException if either {@code appendable} or {@code elements} is {@code null}.
     * @throws IOException          if an I/O error occurs.
     * @see String#join(CharSequence, Iterable)
     */
    public static <T extends Appendable> T join(final T appendable, final CharSequence delimiter,
                                                final Iterable<? extends CharSequence> elements)
            throws IOException {
        Objects.requireNonNull(appendable, "appendable is null");
        Objects.requireNonNull(elements, "elements is null");
        boolean first = true;
        for (final CharSequence element : elements) {
            if (!first) {
                appendable.append(delimiter);
            }
            appendable.append(element);
            first = false;
        }
        return appendable;
    }

    /**
     * Creates a new instance which is not allowed.
     *
     * @throws AssertionError always.
     */
    private JinahyaAppendableUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
