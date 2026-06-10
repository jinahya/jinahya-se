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

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.jinahya.util.BitMask.OfLong.MAX_EXPONENT;
import static com.github.jinahya.util.BitMask.OfLong.MIN_EXPONENT;
import static java.util.concurrent.ThreadLocalRandom.current;
import static java.util.stream.IntStream.rangeClosed;

final class BitMask_OfLong_TestUtils {

    static int randomExponent() {
        return current().nextInt(MIN_EXPONENT, MAX_EXPONENT + 1);
    }

    static BitMask.OfLong randomBitMask() {
        return BitMask.OfLong.ofExponent(randomExponent());
    }

    static Stream<BitMask.OfLong> randomBitMaskStream() {
        return IntStream.range(0, 64).mapToObj(i -> randomBitMask());
    }

    static Stream<BitMask.OfLong> bitMaskStreamOfAllExponents() {
        return rangeClosed(MIN_EXPONENT, MAX_EXPONENT)
                .mapToObj(BitMask.OfLong::ofExponent);
    }

    private BitMask_OfLong_TestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
