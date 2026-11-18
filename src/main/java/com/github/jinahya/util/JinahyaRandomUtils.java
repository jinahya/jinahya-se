/*
 * Copyright 2011 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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
package com.github.jinahya.util;

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

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Utilities for {@link Random}.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class JinahyaRandomUtils {

    /**
     * Returns a new array of specified length filled with random bytes.
     *
     * @param random the random to use; {@link ThreadLocalRandom#current()} is used when {@code null}.
     * @param length the length of the array; must not be negative.
     * @return a new array of random bytes.
     * @throws IllegalArgumentException if {@code length} is negative.
     */
    public static byte[] newRandomBytesOfFixedLength(Random random, final int length) {
        if (length < 0) {
            throw new IllegalArgumentException("length(" + length + ") < 0");
        }
        if (random == null) {
            random = ThreadLocalRandom.current();
        }
        final var bytes = new byte[length];
        random.nextBytes(bytes);
        return bytes;
    }

    /**
     * Returns a new array of random bytes whose length is randomly chosen between specified origin (inclusive) and
     * specified bound (exclusive).
     *
     * @param random the random to use; {@link ThreadLocalRandom#current()} is used when {@code null}.
     * @param origin the least length (inclusive).
     * @param bound  the upper bound of the length (exclusive).
     * @return a new array of random bytes.
     * @throws IllegalArgumentException if {@code origin} is negative, or is greater than or equal to {@code bound}.
     */
    public static byte[] newRandomBytesOfRandomLength(Random random, final int origin, final int bound) {
        if (random == null) {
            random = ThreadLocalRandom.current();
        }
        final var length = random.nextInt(origin, bound);
        return newRandomBytesOfFixedLength(random, length);
    }

    /**
     * Returns a new array of random bytes whose length is randomly chosen between {@code 0} (inclusive) and specified
     * bound (exclusive).
     *
     * @param random the random to use; {@link ThreadLocalRandom#current()} is used when {@code null}.
     * @param bound  the upper bound of the length (exclusive); must be positive.
     * @return a new array of random bytes.
     * @throws IllegalArgumentException if {@code bound} is not positive.
     */
    public static byte[] newRandomBytesOfRandomLength(Random random, final int bound) {
        if (random == null) {
            random = ThreadLocalRandom.current();
        }
        final var length = random.nextInt(bound);
        return newRandomBytesOfFixedLength(random, length);
    }

    private JinahyaRandomUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
