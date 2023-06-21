package com.github.jinahya.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.temporal.Temporal;
import java.util.function.BiFunction;
import java.util.function.Function;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;
import static java.util.Objects.requireNonNull;

public final class TimeUtils {

    private static <T1 extends Temporal, T2 extends Temporal, R> R applyStartEndOf(
            final T1 baseTemporal, final Function<? super T1, ? extends T2> startMapper,
            final Function<? super T1, ? extends T2> endMapper,
            final BiFunction<? super T2, ? super T2, ? extends R> resultMapper) {
        requireNonNull(baseTemporal, "baseTemporal is null");
        requireNonNull(startMapper, "startMapper is null");
        requireNonNull(endMapper, "endMapper is null");
        requireNonNull(resultMapper, "resultMapper is null");
        final T2 startInclusive = startMapper.apply(baseTemporal);
        final T2 endInclusive = endMapper.apply(baseTemporal);
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
                y -> LocalDate.from(year.atDay(1).with(firstDayOfYear())),
                y -> LocalDate.from(year.atDay(1).with(lastDayOfYear())),
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
                m -> LocalDate.from(m.atDay(1).with(firstDayOfMonth())),
                m -> LocalDate.from(m.atDay(1).with(lastDayOfMonth())),
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
                (s, e) -> function.apply(s.atStartOfDay(), e.atStartOfDay().plusDays(1L))
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
                (s, e) -> function.apply(s.atStartOfDay(), e.atStartOfDay().plusDays(1L))
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
                d -> d.atStartOfDay().plusDays(1L),
                function
        );
    }

    private TimeUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
