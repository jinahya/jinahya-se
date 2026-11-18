/*
 * Copyright 2015 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
 *
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
 */
package com.github.jinahya.util.concurrent;

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

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * A utility class for generating streams of pseudorandom values using {@link ThreadLocalRandom}.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class JinahyaThreadLocalRandoms {

    /**
     * Returns an effectively unlimited stream of pseudorandom {@code float} values, each between zero (inclusive) and
     * one (exclusive).
     *
     * @return a stream of pseudorandom {@code float} values.
     * @see ThreadLocalRandom#doubles()
     */
    public static DoubleStream floats() {
        return ThreadLocalRandom.current()
                .doubles()
                .map(d -> (float) d);
    }

    /**
     * Returns an effectively unlimited stream of pseudorandom {@code float} values, each conforming to specified origin
     * (inclusive) and bound (exclusive).
     *
     * @param randomNumberOrigin the origin (inclusive) of each random value.
     * @param randomNumberBound  the bound (exclusive) of each random value.
     * @return a stream of pseudorandom {@code float} values.
     * @see ThreadLocalRandom#doubles(double, double)
     */
    public static DoubleStream floats(final float randomNumberOrigin,
                                      final float randomNumberBound) {

        return ThreadLocalRandom.current()
                .doubles(randomNumberOrigin, randomNumberBound)
                .map(d -> (float) d);
    }

    /**
     * Returns a stream producing specified number of pseudorandom {@code float} values, each between zero (inclusive)
     * and one (exclusive).
     *
     * @param streamSize the number of values to generate.
     * @return a stream of pseudorandom {@code float} values.
     * @see ThreadLocalRandom#doubles(long)
     */
    public static DoubleStream floats(final long streamSize) {

        return ThreadLocalRandom.current()
                .doubles(streamSize)
                .map(d -> (float) d);
    }

    /**
     * Returns a stream producing specified number of pseudorandom {@code float} values, each conforming to specified
     * origin (inclusive) and bound (exclusive).
     *
     * @param streamSize         the number of values to generate.
     * @param randomNumberOrigin the origin (inclusive) of each random value.
     * @param randomNumberBound  the bound (exclusive) of each random value.
     * @return a stream of pseudorandom {@code float} values.
     * @see ThreadLocalRandom#doubles(long, double, double)
     */
    public static DoubleStream floats(final long streamSize,
                                      final float randomNumberOrigin,
                                      final float randomNumberBound) {

        return ThreadLocalRandom.current()
                .doubles(streamSize, randomNumberOrigin, randomNumberBound)
                .map(d -> (float) d);
    }

    /**
     * Returns an effectively unlimited stream of pseudorandom {@code byte} values.
     *
     * @return a stream of pseudorandom byte values
     * @see ThreadLocalRandom#ints()
     */
    public static IntStream bytes() {

        return ThreadLocalRandom.current()
                .ints()
                .map(i -> (byte) i);
    }

    /**
     * Returns an effectively unlimited stream of pseudorandom {@code byte} values, each conforming to specified origin
     * (inclusive) and bound (exclusive).
     *
     * @param randomNumberOrigin the origin (inclusive) of each random value.
     * @param randomNumberBound  the bound (exclusive) of each random value.
     * @return a stream of pseudorandom {@code byte} values.
     * @see ThreadLocalRandom#ints(int, int)
     */
    public static IntStream bytes(final byte randomNumberOrigin,
                                  final byte randomNumberBound) {

        return ThreadLocalRandom.current()
                .ints(randomNumberOrigin, randomNumberBound)
                .map(i -> (byte) i);
    }

    /**
     * Returns a stream producing specified number of pseudorandom {@code byte} values.
     *
     * @param streamSize the number of values to generate.
     * @return a stream of pseudorandom {@code byte} values.
     * @see ThreadLocalRandom#ints(long)
     */
    public static IntStream bytes(final long streamSize) {

        return ThreadLocalRandom.current()
                .ints(streamSize)
                .map(i -> (byte) i);
    }

    /**
     * Returns a stream producing specified number of pseudorandom {@code byte} values, each conforming to specified
     * origin (inclusive) and bound (exclusive).
     *
     * @param streamSize         the number of values to generate.
     * @param randomNumberOrigin the origin (inclusive) of each random value.
     * @param randomNumberBound  the bound (exclusive) of each random value.
     * @return a stream of pseudorandom {@code byte} values.
     * @see ThreadLocalRandom#ints(long, int, int)
     */
    public static IntStream bytes(final long streamSize,
                                  final byte randomNumberOrigin,
                                  final byte randomNumberBound) {

        return ThreadLocalRandom.current()
                .ints(streamSize, randomNumberOrigin, randomNumberBound)
                .map(i -> (byte) i);
    }

    private JinahyaThreadLocalRandoms() {
        super();
    }
}
