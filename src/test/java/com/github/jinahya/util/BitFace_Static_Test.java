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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.github.jinahya.util.BitMask_TestUtils.bitMaskStreamOfAllExponents;
import static com.github.jinahya.util.BitMask_TestUtils.randomBitMask;
import static com.github.jinahya.util.BitMask_TestUtils.randomBitMaskStream;
import static org.assertj.core.api.Assertions.assertThat;

class BitFace_Static_Test {

    @Nested
    class OfNoneTest {

        @Test
        void __() {
            final var face = BitFace.ofNone();
            assertThat(face)
                    .isNotNull();
        }
    }

    @DisplayName("of(Iterable)")
    @Test
    void of__Iterable() {
        assertThat(BitFace.of(bitMaskStreamOfAllExponents().toList()))
                .isEqualTo(BitFace.ofAll());
    }

    @DisplayName("of(mask)")
    @Nested
    class OfOneTest {

        @Test
        void __() {
            final var mask = randomBitMask();
            assertThat(BitFace.of(mask))
                    .isNotNull()
                    .isSameAs(BitFace.of(mask, mask));
        }
    }

    @DisplayName("of(mask1, mask2)")
    @Nested
    class OfTwoTest {

        @Test
        void __() {
            final var mask1 = randomBitMask();
            final var mask2 = randomBitMask();
            assertThat(BitFace.of(mask1, mask2))
                    .isNotNull()
                    .extracting(BitFace::getValue)
                    .isEqualTo(mask1.getValue() | mask2.getValue());
        }
    }

    @DisplayName("of(mask1, mask2, mask3)")
    @Nested
    class OfThreeMasksTest {

        @Test
        void __() {
            final var mask1 = randomBitMask();
            final var mask2 = randomBitMask();
            final var mask3 = randomBitMask();
            assertThat(BitFace.of(mask1, mask2, mask3))
                    .isNotNull()
                    .extracting(BitFace::getValue)
                    .isEqualTo(mask1.getValue() | mask2.getValue() | mask3.getValue());
        }
    }

    @DisplayName("of(mask1, mask2, mask3, mask4)")
    @Nested
    class OfFourMasksTest {

        @Test
        void __() {
            final var mask1 = randomBitMask();
            final var mask2 = randomBitMask();
            final var mask3 = randomBitMask();
            final var mask4 = randomBitMask();
            assertThat(BitFace.of(mask1, mask2, mask3, mask4))
                    .isNotNull()
                    .extracting(BitFace::getValue)
                    .isEqualTo(mask1.getValue() | mask2.getValue() | mask3.getValue() | mask4.getValue());
        }
    }

    @DisplayName("of(mask1, mask2, mask3, mask4, otherMasks)")
    @Nested
    class OfFourMasksWithOtherMasksTest {

        @Test
        void __() {
            final var mask1 = randomBitMask();
            final var mask2 = randomBitMask();
            final var mask3 = randomBitMask();
            final var mask4 = randomBitMask();
            final var otherMasks = randomBitMaskStream().toArray(BitMask[]::new);
            assertThat(BitFace.of(mask1, mask2, mask3, mask4, otherMasks))
                    .isNotNull()
                    .extracting(BitFace::getValue)
                    .isEqualTo(mask1.getValue() | mask2.getValue() | mask3.getValue() | mask4.getValue()
                               | Arrays.stream(otherMasks).mapToInt(BitMask::getValue).sum());
        }
    }
}
