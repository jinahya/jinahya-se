package com.github.jinahya.time.temporal;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class JinahyaTemporalAmountUtils {

    /**
     * .
     *
     * @param amount .
     * @param unit   .
     * @return .
     * @see <a href="https://docs.oracle.com/en/java/javase//21/docs/api/java.base/java/util/concurrent/TimeUnit.html">
     * java.util.concurrent.TimeUnit</a>
     * @see <a
     * href="https://docs.oracle.com/en/java/javase//21/docs/api/java.base/java/time/temporal/TemporalUnit.html">java.time.temporal.TemporalUnit</a>
     * @see <a
     * href="https://docs.oracle.com/en/java/javase//21/docs/api/java.base/java/time/temporal/ChronoUnit.html">java.time.temporal.ChronoUnit</a>
     */
    public static long get(final TemporalAmount amount, final TimeUnit unit) {
        Objects.requireNonNull(amount, "amount is null");
        Objects.requireNonNull(unit, "unit is null");
        return amount.getUnits()
                .stream()
                .filter(ChronoUnit.class::isInstance)
                .map(u -> {
                    try {
                        return unit.convert(
                                amount.get(u),
                                TimeUnit.of((ChronoUnit) u) // IllegalArgumentException
                        );
                    } catch (final IllegalArgumentException iae) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("no supported; amount: " + amount + "; unit: " + unit));
    }

    public static long get(final TemporalAmount amount, final ChronoUnit unit) {
        Objects.requireNonNull(amount, "amount is null");
        Objects.requireNonNull(unit, "unit is null");
        return get(amount, TimeUnit.of(unit));
    }

    private JinahyaTemporalAmountUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
