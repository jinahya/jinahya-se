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

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.util.BitFaceTestUtils.randomBitFace;
import static com.github.jinahya.util.BitMask_TestUtils.bitMaskStreamOfAllExponents;
import static com.github.jinahya.util.BitMask_TestUtils.randomBitMask;
import static org.assertj.core.api.Assertions.assertThat;

class BitFace_Test {

    @Test
    void toString__() {
        final var face = randomBitFace();
        assertThat(face.toString()).isNotBlank();
    }

    @Test
    void equals__() {
        EqualsVerifier.forClass(BitFace.class).verify();
    }

    @Test
    void isWearing__() {
        var face = BitFace.ofNone();
        final var mask = randomBitMask();
        assertThat(face.isWearing(mask)).isFalse();
        face = face.putOn(mask);
        assertThat(face.isWearing(mask)).isTrue();
    }

    @Test
    void putOn__() {
        final var face = bitMaskStreamOfAllExponents().reduce(
                BitFace.ofNone(),
                BitFace::putOn,
                BitFace::merge
        );
        assertThat(face)
                .isEqualTo(BitFace.ofAll());
    }

    @Test
    void takeOff__() {
        final var face = bitMaskStreamOfAllExponents().reduce(
                BitFace.ofAll(),
                BitFace::takeOff,
                BitFace::merge
        );
        assertThat(face)
                .isEqualTo(BitFace.ofNone());
    }

    @DisplayName("toMaskSet()")
    @Nested
    class ToMaskSetTest {

        @Test
        void _Empty_OfNone() {
            final var face = BitFace.ofNone();
            final var set = face.toMaskSet();
            assertThat(set).isEmpty();
        }

        @Test
        void _NotEmpty_OfAll() {
            final var face = BitFace.ofAll();
            final var set = face.toMaskSet();
            assertThat(set).isNotEmpty();
        }
    }
}
