package com.github.jinahya.time.temporal;

import java.time.DateTimeException;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public final class TemporalSlicer {

    public static <T extends Temporal & Comparable<? super T>> void slice(
            final T startInclusive, final T endExclusive, final UnaryOperator<T> startAdjuster,
            final UnaryOperator<T> amountAdder, final UnaryOperator<T> endAdjuster,
            final Consumer<? super TemporalSlice<T>> sliceConsumer) {
        Objects.requireNonNull(startInclusive, "startInclusive is null");
        Objects.requireNonNull(endExclusive, "endExclusive is null");
        if (startInclusive.compareTo(endExclusive) >= 0) {
            throw new IllegalArgumentException("startInclusive(" + startInclusive
                                               + ") >= endExclusive(" + endExclusive + ")");
        }
        Objects.requireNonNull(startAdjuster, "startAdjuster is null");
        Objects.requireNonNull(amountAdder, "amountAdder is null");
        Objects.requireNonNull(endAdjuster, "endAdjuster is null");
        Objects.requireNonNull(sliceConsumer, "sliceConsumer is null");
        T startAdjusted = startAdjuster.apply(startInclusive);
        if (startAdjusted == null) {
            throw new DateTimeException("startAdjusted is null");
        }
        if (startAdjusted.compareTo(startInclusive) > 0) {
            throw new DateTimeException("startAdjusted(" + startAdjusted
                                        + ") is greater than startInclusive(" + startInclusive + ")");
        }
        while (true) {
            final T amountAdded = amountAdder.apply(startAdjusted);
            if (amountAdded == null) {
                throw new DateTimeException("amountAdded is null");
            }
            if (amountAdded.compareTo(startAdjusted) <= 0) {
                throw new DateTimeException("amountAdded(" + amountAdded
                                            + ") is less than or equal to startAdjusted(" + startAdjusted + ")");
            }
            T endAdjusted = endAdjuster.apply(amountAdder.apply(startAdjusted));
            if (endAdjusted == null) {
                throw new DateTimeException("endAdjusted is null");
            }
            if (endAdjusted.compareTo(startInclusive) <= 0) {
                throw new DateTimeException("endAdjusted(" + endAdjusted
                                            + ") is less than or equals to startInclusive(" + startInclusive + ")");
            }
            final TemporalSlice<T> slice = TemporalSlice.of(startInclusive, endExclusive, startAdjusted, endAdjusted);
            sliceConsumer.accept(slice);
            startAdjusted = endAdjusted;
            if (startAdjusted.compareTo(endExclusive) >= 0) {
                break;
            }
        }
    }

    public static <T extends Temporal & Comparable<? super T>> void slice(
            final T startInclusive, final T endExclusive,
            final UnaryOperator<T> startAdjuster, final TemporalAmount sliceAmount,
            final Consumer<? super TemporalSlice<T>> sliceConsumer) {
        Objects.requireNonNull(startInclusive, "startInclusive is null");
        Objects.requireNonNull(endExclusive, "endExclusive is null");
        if (startInclusive.compareTo(endExclusive) >= 0) {
            throw new IllegalArgumentException("startInclusive(" + startInclusive
                                               + ") >= endExclusive(" + endExclusive + ")");
        }
        Objects.requireNonNull(startAdjuster, "startAdjuster is null");
        Objects.requireNonNull(sliceAmount, "sliceAmount is null");
        Objects.requireNonNull(sliceConsumer, "sliceConsumer is null");
        T startAdjusted = startAdjuster.apply(startInclusive);
        if (startAdjusted == null) {
            throw new DateTimeException("startAdjusted is null");
        }
        if (startAdjusted.compareTo(startInclusive) > 0) {
            throw new DateTimeException("startAdjusted(" + startAdjusted
                                        + ") is greater than startInclusive(" + startInclusive + ")");
        }
        while (true) {
            @SuppressWarnings({"unchecked"})
            final T endAdjusted = (T) sliceAmount.addTo(startAdjusted);
            final TemporalSlice<T> slice = TemporalSlice.of(startInclusive, endExclusive, startAdjusted, endAdjusted);
            sliceConsumer.accept(slice);
            startAdjusted = endAdjusted;
            if (startAdjusted.compareTo(endExclusive) >= 0) {
                break;
            }
        }
    }

    private TemporalSlicer() {
        throw new AssertionError("instantiation is not allowed");
    }
}
