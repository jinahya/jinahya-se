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

import java.util.concurrent.ThreadLocalRandom;

final class BitFaceTestUtils {

    static final class OfLong {

        static long randomBitFaceOfLongValue() {
            return ThreadLocalRandom.current().nextLong() >>> 1;
        }

        static BitFace.OfLong randomBitFaceOfLong() {
            return BitFace.OfLong.of(randomBitFaceOfLongValue());
        }

        private OfLong() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    static int randomBitFaceValue() {
        return ThreadLocalRandom.current().nextInt() >>> 1;
    }

    static BitFace randomBitFace() {
        return BitFace.of(randomBitFaceValue());
    }

    private BitFaceTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
