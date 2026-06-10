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
