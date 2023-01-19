package com.github.jinahya.time.temporal;

import java.time.temporal.Temporal;
import java.util.Objects;

public final class TemporalSlice<T extends Temporal & Comparable<? super T>> {

    static <T extends Temporal & Comparable<? super T>> TemporalSlice<T> of(
            final T unslicedStartInclusive, final T unslicedEndExclusive, final T slicedStartInclusive,
            final T slicedEndExclusive) {
        Objects.requireNonNull(unslicedStartInclusive, "unslicedStartInclusive is null");
        Objects.requireNonNull(unslicedEndExclusive, "unslicedEndExclusive is null");
        if (unslicedStartInclusive.compareTo(unslicedEndExclusive) >= 0) {
            throw new IllegalArgumentException("unslicedStartInclusive(" + unslicedStartInclusive
                                               + ") >= unslicedEndExclusive(" + unslicedEndExclusive + ")");
        }
        Objects.requireNonNull(slicedStartInclusive, "startInclusive is null");
        Objects.requireNonNull(slicedEndExclusive, "endExclusive is null");
        if (slicedStartInclusive.compareTo(slicedEndExclusive) >= 0) {
            throw new IllegalArgumentException(
                    "startInclusive(" + slicedStartInclusive + ") is greater than or equals to endExclusive("
                    + slicedEndExclusive + ")");
        }
        final T startInclusive = unslicedStartInclusive.compareTo(slicedEndExclusive) > 0
                                 ? unslicedStartInclusive : slicedStartInclusive;
        final T endExclusive = unslicedEndExclusive.compareTo(slicedEndExclusive) < 0
                               ? unslicedEndExclusive : slicedEndExclusive;
        return new TemporalSlice<>(startInclusive, endExclusive);
    }

    private TemporalSlice(final T startInclusive, final T endExclusive) {
        super();
        Objects.requireNonNull(startInclusive, "startInclusive is null");
        Objects.requireNonNull(endExclusive, "endExclusive is null");
        if (startInclusive.compareTo(endExclusive) >= 0) {
            throw new IllegalArgumentException(
                    "startInclusive(" + startInclusive + ") >= endExclusive(" + endExclusive + ")");
        }
        this.startInclusive = startInclusive;
        this.endExclusive = endExclusive;
    }

    @Override
    public String toString() {
        return super.toString() + '{'
               + "startInclusive=" + startInclusive
               + ", endExclusive=" + endExclusive
               + '}';
    }

    public T getStartInclusive() {
        return startInclusive;
    }

    public T getEndExclusive() {
        return endExclusive;
    }

    private final T startInclusive;

    private final T endExclusive;
}
