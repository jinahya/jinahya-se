package com.github.jinahya.lang;

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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.jinahya.lang.JinahyaPrimitives.Ints.requireLessThanOrEqualTo;
import static java.util.concurrent.ThreadLocalRandom.current;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class JinahyaPrimitivesIntsTest {

    @DisplayName("requireLessThan(value, against)")
    @Nested
    class RequireLessThanTest {

        @Test
        void __() {
            final var against = current().nextInt() << 1;
        }
    }

    @Nested
    class RequireLessThatOrEqualToTest {

        private static Stream<Arguments> getArguments1() {
            return Stream.of(
                    arguments(-1, 0),
                    arguments(-1, 1),
                    arguments(0, 1),
                    arguments(0, 2)
            );
        }

        private static Stream<Arguments> getArguments2() {
            return IntStream.range(0, 1024).mapToObj(i -> {
                final var against = current().nextInt() | 1;
                assert against > Integer.MIN_VALUE;
                final var value = against - 1;
                return arguments(value, against);
            });
        }

        @MethodSource({
                "getArguments1",
                "getArguments2"
        })
        @ParameterizedTest
        void __(final int value, final int against) {
            assertThat(requireLessThanOrEqualTo(value, against)).isEqualTo(value);
        }

        private static Stream<Arguments> getThrowingArguments1() {
            return Stream.of(
                    arguments(0, -1),
                    arguments(1, -1),
                    arguments(1, 0),
                    arguments(2, 0)
            );
        }

        private static Stream<Arguments> getThrowingArguments2() {
            return IntStream.range(0, 1024).mapToObj(i -> {
                final var against = current().nextInt() & 0xFFFFFE;
                assert against < Integer.MAX_VALUE;
                final var value = against + 1;
                return arguments(value, against);
            });
        }

        @MethodSource({
                "getThrowingArguments1",
                "getThrowingArguments2"
        })
        @ParameterizedTest
        void _Fail_(final int value, final int against) {
            assertThatThrownBy(() -> requireLessThanOrEqualTo(value, against))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
