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

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.LongFunction;

/**
 * A utility class for {@link Duration}.
 */
public class JinahyaDurationUtils {

    /**
     * Applies the number of days, of specified duration, and the remaining duration to specified function, and returns
     * the result.
     *
     * @param <R>      result type parameter
     * @param duration the duration; must not be negative.
     * @param function the function to be applied with the number of days and the remaining duration.
     * @return the result of the {@code function}.
     * @throws IllegalArgumentException if {@code duration} is negative.
     */
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

    /**
     * Applies the number of hours, of specified duration, and the remaining duration to specified function, and returns
     * the result.
     *
     * @param <R>      result type parameter
     * @param duration the duration; must not be negative.
     * @param function the function to be applied with the number of hours and the remaining duration.
     * @return the result of the {@code function}.
     * @throws IllegalArgumentException if {@code duration} is negative.
     */
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

    /**
     * Applies the number of minutes, of specified duration, and the remaining duration to specified function, and returns
     * the result.
     *
     * @param <R>      result type parameter
     * @param duration the duration; must not be negative.
     * @param function the function to be applied with the number of minutes and the remaining duration.
     * @return the result of the {@code function}.
     * @throws IllegalArgumentException if {@code duration} is negative.
     */
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

    /**
     * Applies the number of seconds, of specified duration, and the remaining duration to specified function, and returns
     * the result.
     *
     * @param <R>      result type parameter
     * @param duration the duration; must not be negative.
     * @param function the function to be applied with the number of seconds and the remaining duration.
     * @return the result of the {@code function}.
     * @throws IllegalArgumentException if {@code duration} is negative.
     */
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
