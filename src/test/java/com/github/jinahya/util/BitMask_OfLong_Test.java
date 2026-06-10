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
import org.junit.jupiter.api.Test;

import static com.github.jinahya.util.BitMask_OfLong_TestUtils.randomBitMask;
import static org.assertj.core.api.Assertions.assertThat;

class BitMask_OfLong_Test {

    @Test
    void equals__() {
        EqualsVerifier.forClass(BitMask.OfLong.class).verify();
    }

    @Test
    void toString__() {
        final var mask = randomBitMask();
        assertThat(mask.toString()).isNotBlank();
    }

    @Test
    void putOnTo__() {
        final var mask = randomBitMask();
        final var face = mask.putOnTo(BitFaceTestUtils.OfLong.randomBitFaceOfLong());
        assertThat(face).isNotNull();
    }

    @Test
    void takeOffFrom__() {
        final var mask = randomBitMask();
        final var face = mask.takeOffFrom(BitFaceTestUtils.OfLong.randomBitFaceOfLong());
        assertThat(face).isNotNull();
    }
}
