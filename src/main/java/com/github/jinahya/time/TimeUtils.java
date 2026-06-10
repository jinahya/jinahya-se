package com.github.jinahya.time;

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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.temporal.Temporal;
import java.util.function.BiFunction;
import java.util.function.Function;

import static java.time.temporal.TemporalAdjusters.lastDayOfYear;
import static java.util.Objects.requireNonNull;

public final class TimeUtils {

    private static <T1 extends Temporal, T2 extends Temporal, R> R applyStartEndOf(
            final T1 baseTemporal, final Function<? super T1, ? extends T2> startMapper,
            final BiFunction<? super T1, ? super T2, ? extends T2> endMapper,
            final BiFunction<? super T2, ? super T2, ? extends R> resultMapper) {
        requireNonNull(baseTemporal, "baseTemporal is null");
        requireNonNull(startMapper, "startMapper is null");
        requireNonNull(endMapper, "endMapper is null");
        requireNonNull(resultMapper, "resultMapper is null");
        final T2 startInclusive = startMapper.apply(baseTemporal);
        final T2 endInclusive = endMapper.apply(baseTemporal, startInclusive);
        return resultMapper.apply(startInclusive, endInclusive);
    }

    /**
     * Applies the first day and the last day, of specified year, to specified function, and returns the result.
     *
     * @param year     the year.
     * @param function the function which applies with the first and last day of {@code year}.
     * @param <R>      result type parameter
     * @return the result of the {@code function}.
     */
    public static <R> R applyStartEndDateOf(
            final Year year, final BiFunction<? super LocalDate, ? super LocalDate, ? extends R> function) {
        return applyStartEndOf(
                year,
                y -> y.atDay(1),
                (y, s) -> s.with(lastDayOfYear()),
                function
        );
    }

    /**
     * Applies the first day and the last day, of specified month, to specified function, and returns the result.
     *
     * @param month    the month.
     * @param function the function which applies with the first and last day of {@code month}.
     * @param <R>      result type parameter
     * @return the result of the {@code function}.
     */
    public static <R> R applyStartEndDateOf(
            final YearMonth month, final BiFunction<? super LocalDate, ? super LocalDate, ? extends R> function) {
        return applyStartEndOf(
                month,
                m -> m.atDay(1),
                (m, s) -> m.atEndOfMonth(),
                function
        );
    }

    /**
     * Applies the <em>inclusive</em> start of the first day of specified year and the <em>exclusive</em> start of the
     * first day of the next year, to specified function, and returns the result.
     *
     * @param year     the year.
     * @param function the function which applies with both ends.
     * @param <R>      result type parameter
     * @return the result of the {@code function}.
     */
    public static <R> R applyStartEndTimeOf(
            final Year year, final BiFunction<? super LocalDateTime, ? super LocalDateTime, ? extends R> function) {
        return applyStartEndDateOf(
                year,
                (s, e) -> function.apply(s.atStartOfDay(), e.plusDays(1L).atStartOfDay())
        );
    }

    /**
     * Applies the <em>inclusive</em> start of the first day of specified month and the <em>exclusive</em> start of the
     * first day of the next month, to specified function, and returns the result.
     *
     * @param month    the month.
     * @param function the function which applies with both ends.
     * @param <R>      result type parameter
     * @return the result of the {@code function}.
     */
    public static <R> R applyStartEndTimeOf(
            final YearMonth month,
            final BiFunction<? super LocalDateTime, ? super LocalDateTime, ? extends R> function) {
        return applyStartEndDateOf(
                month,
                (s, e) -> function.apply(s.atStartOfDay(), e.plusDays(1L).atStartOfDay())
        );
    }

    /**
     * Applies the <em>inclusive</em> start of specified date and the <em>exclusive</em> start of the next date, to
     * specified function, and returns the result.
     *
     * @param date     the date.
     * @param function the function which applies with both ends.
     * @param <R>      result type parameter
     * @return the result of the {@code function}.
     */
    public static <R> R applyStartEndTimeOf(
            final LocalDate date,
            final BiFunction<? super LocalDateTime, ? super LocalDateTime, ? extends R> function) {
        return applyStartEndOf(
                date,
                LocalDate::atStartOfDay,
                (d, s) -> s.plusDays(1L),
                function
        );
    }

    private TimeUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
