package com.github.jinahya.time.temporal;

import java.time.temporal.Temporal;
import java.util.Objects;

public final class Interval<T extends Temporal & Comparable<? super T>> {

    public Interval(final T start, final T end) {
        super();
        if (Objects.requireNonNull(start, "start is null").compareTo(Objects.requireNonNull(end, "end is null")) >= 0) {
            throw new IllegalArgumentException("start(" + start + ") must not be after the end(" + end + ")");
        }
        this.start = start;
        this.end = end;
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return super.toString() + '{' +
               "start=" + start +
               ",end=" + end +
               '}';
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final var that = (Interval<?>) obj;
        return Objects.equals(start, that.start) &&
               Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final T start;

    private final T end;
}
