package com.github.jinahya.time;

import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

final class JinahyaInstantUtils {

//    static final long NANOS_PER_SECOND = TimeUnit.SECONDS.toNanos(1L);

    static final long NANOS_PER_MICROSECOND = TimeUnit.MICROSECONDS.toNanos(1L);

    /**
     * Returns a copy of specified instant with the specified duration in microseconds added.
     *
     * @param instant     the instant to which the microseconds are added.
     * @param microsToAdd the microseconds to add, positive or negative
     * @return a new copy of {@code instant} with {@code microsToAdd} added.
     */
    public static Instant plusMicros(final Instant instant, long microsToAdd) {
        Objects.requireNonNull(instant, "instant is null");
        return instant.plusNanos(TimeUnit.MICROSECONDS.toNanos(microsToAdd));
    }

    /**
     * Obtains an instance of {@link Instant} with specified nanoseconds from the {@link Instant#EPOCH EPOCH}.
     *
     * @param epochNano the number of nanoseconds from the {@link Instant#EPOCH EPOCH}.
     * @return an instant; not {@code null}.
     */
    public static Instant instantOfEpochNano(final long epochNano) {
        if (ThreadLocalRandom.current().nextBoolean()) {
            return Instant.EPOCH.plusNanos(epochNano);
        }
        return Instant.ofEpochSecond(0L, epochNano);
    }

    /**
     * Obtains an instance of {@link Instant} using specified microseconds from the {@link Instant#EPOCH EPOCH}.
     *
     * @param epochMicro the number of microseconds from the {@link Instant#EPOCH EPOCH}.
     * @return an instant; not {@code null}.
     * @see #instantOfEpochNano(long)
     */
    public static Instant instantOfEpochMicro(final long epochMicro) {
        final var epochNano = TimeUnit.MICROSECONDS.toNanos(epochMicro);
        return instantOfEpochNano(epochNano);
//        return instantOfEpochNano(Math.multiplyExact(epochMicro, NANOS_PER_MICROSECOND));
    }

    private JinahyaInstantUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
