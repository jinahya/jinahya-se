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

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class JinahyaInstantUtils_InstantOfEpochMicro_Test {

    private static LongStream epochNanoStream() {
        return LongStream.concat(
                LongStream.of(
                        -1L,
                        0L,
                        +1L,
                        Long.MIN_VALUE,
                        Long.MAX_VALUE
                ),
                IntStream.range(0, 8).mapToLong(i -> {
                    return ThreadLocalRandom.current().nextLong(-1000000000, 1000000000);
                })
        );
    }

    @MethodSource({"epochNanoStream"})
    @ParameterizedTest
    void __(final long epochNano) {
    }

    @Test
    void __() {
        try (var mockStatic = Mockito.mockStatic(JinahyaInstantUtils.class, Mockito.CALLS_REAL_METHODS)) {
            // ------------------------------------------------------------------------------------------------------- given
            final var epochMicro = ThreadLocalRandom.current().nextLong();
            // -------------------------------------------------------------------------------------------------------- when
            final var instant = JinahyaInstantUtils.instantOfEpochMicro(epochMicro);
            // -------------------------------------------------------------------------------------------------------- then
            mockStatic.verify(() -> JinahyaInstantUtils.instantOfEpochNano(TimeUnit.MICROSECONDS.toNanos(epochMicro)));
        }
    }

    @Test
    void __0() {
        final var instant = JinahyaInstantUtils.instantOfEpochMicro(0L);
        assertThat(instant).isEqualTo(Instant.EPOCH);
    }

    @Test
    void __Positive1() {
        final var epochMicro = +1L;
        final var instant = JinahyaInstantUtils.instantOfEpochMicro(epochMicro);
        assertThat(instant)
                .isNotNull()
                .satisfies(v -> {
                    assertThat(v.getNano()).isEqualTo(TimeUnit.MICROSECONDS.toNanos(epochMicro));
                });
    }

    @Test
    void __Negative1() {
        // ------------------------------------------------------------------------------------------------------- given
        final var epochMicro = -1L;
        // -------------------------------------------------------------------------------------------------------- when
        final var instant = JinahyaInstantUtils.instantOfEpochMicro(epochMicro);
        // -------------------------------------------------------------------------------------------------------- then
        assertThat(instant)
                .isNotNull()
                .satisfies(v -> {
                });
    }
}
