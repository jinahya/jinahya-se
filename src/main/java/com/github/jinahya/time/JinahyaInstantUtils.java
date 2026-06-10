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
