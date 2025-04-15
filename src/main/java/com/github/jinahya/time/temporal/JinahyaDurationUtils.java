package com.github.jinahya.time.temporal;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.LongFunction;

public class JinahyaDurationUtils {

    public static <R> R applyDays(final Duration duration,
                                  final LongFunction<? extends Function<? super Duration, ? extends R>> function) {
        if (Objects.requireNonNull(duration, "duration is null").isNegative()) {
            throw new IllegalArgumentException("negative duration: " + duration);
        }
        Objects.requireNonNull(function, "function is null");
        final var days = duration.toDays();
        final var remaining = Duration.ofNanos(duration.toNanos() - TimeUnit.DAYS.toNanos(days));
        return function.apply(days).apply(remaining);
    }

    public static <R> R applyHours(final Duration duration,
                                   final LongFunction<? extends Function<? super Duration, ? extends R>> function) {
        if (Objects.requireNonNull(duration, "duration is null").isNegative()) {
            throw new IllegalArgumentException("negative duration: " + duration);
        }
        Objects.requireNonNull(function, "function is null");
        final var hours = duration.toHours();
        final var remaining = Duration.ofNanos(duration.toNanos() - TimeUnit.HOURS.toNanos(hours));
        return function.apply(hours).apply(remaining);
    }

    public static <R> R applyMinutes(final Duration duration,
                                     final LongFunction<? extends Function<? super Duration, ? extends R>> function) {
        if (Objects.requireNonNull(duration, "duration is null").isNegative()) {
            throw new IllegalArgumentException("negative duration: " + duration);
        }
        Objects.requireNonNull(function, "function is null");
        final var minutes = duration.toMinutes();
        final var remaining = Duration.ofNanos(duration.toNanos() - TimeUnit.MINUTES.toNanos(minutes));
        return function.apply(minutes).apply(remaining);
    }

    public static <R> R applySeconds(final Duration duration,
                                     final LongFunction<? extends Function<? super Duration, ? extends R>> function) {
        if (Objects.requireNonNull(duration, "duration is null").isNegative()) {
            throw new IllegalArgumentException("negative duration: " + duration);
        }
        Objects.requireNonNull(function, "function is null");
        final var seconds = duration.toSeconds();
        final var remaining = Duration.ofNanos(duration.toNanos() - TimeUnit.SECONDS.toNanos(seconds));
        return function.apply(seconds).apply(remaining);
    }

    /**
     * Applies values of {@link java.time.temporal.ChronoUnit#DAYS},, {@link java.time.temporal.ChronoUnit#HOURS},
     * {@link java.time.temporal.ChronoUnit#MINUTES}, {@link java.time.temporal.ChronoUnit#SECONDS},
     * {@link java.time.temporal.ChronoUnit#NANOS}, in chain, to specified function, and returns the result.
     *
     * @param duration the duration.
     * @param function the curried function.
     * @param <R>      result type parameter.
     * @return result of the {@code function}.
     */
    static <R> R applyUnitValues(final Duration duration,
                                 final LongFunction<
                                         ? extends LongFunction<
                                                 ? extends LongFunction<
                                                         ? extends LongFunction<
                                                                 ? extends LongFunction<? extends R>>>>> function) {
        return applyDays(
                duration,
                qd -> rd -> applyHours(
                        rd,
                        qh -> rh -> applyMinutes(
                                rh,
                                qm -> rm -> applySeconds(
                                        rm,
                                        qs -> rs -> function.apply(qd).apply(qh).apply(qm).apply(qs).apply(rs.toNanos())
                                )
                        )
                )
        );
    }

    private JinahyaDurationUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
