package com.github.jinahya.time.temporal;

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

import java.time.DateTimeException;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

/**
 * A utility class for slicing a temporal range into {@link TemporalSlice}s.
 */
public final class TemporalSlicer {

    /**
     * Slices specified range and accepts each slice to specified consumer.
     *
     * @param <T>            temporal type parameter
     * @param startInclusive the start of the range, inclusive.
     * @param endExclusive   the end of the range, exclusive; must be after {@code startInclusive}.
     * @param startAdjuster  an operator for adjusting {@code startInclusive} to the start of the first slice; must
     *                       not result a value after {@code startInclusive}.
     * @param amountAdder    an operator for adding an amount to the start of each slice.
     * @param endAdjuster    an operator for adjusting the result of {@code amountAdder} to the end of each slice.
     * @param sliceConsumer  the consumer accepts each slice.
     * @throws IllegalArgumentException if {@code startInclusive} is not before {@code endExclusive}.
     * @throws DateTimeException        if any of the operators results an invalid value.
     */
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

    /**
     * Slices specified range by specified amount and accepts each slice to specified consumer.
     *
     * @param <T>            temporal type parameter
     * @param startInclusive the start of the range, inclusive.
     * @param endExclusive   the end of the range, exclusive; must be after {@code startInclusive}.
     * @param startAdjuster  an operator for adjusting {@code startInclusive} to the start of the first slice; must
     *                       not result a value after {@code startInclusive}.
     * @param sliceAmount    the amount of each slice.
     * @param sliceConsumer  the consumer accepts each slice.
     * @throws IllegalArgumentException if {@code startInclusive} is not before {@code endExclusive}.
     * @throws DateTimeException        if {@code startAdjuster} results an invalid value.
     */
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
