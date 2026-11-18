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

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Utilities for primitive types.
 */
public final class JinahyaPrimitives {

    /**
     * An unmodifiable set of primitive types.
     */
    public static final Set<Class<?>> TYPES = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
            Void.TYPE,
            Boolean.TYPE,
            Byte.TYPE,
            Short.TYPE,
            Integer.TYPE,
            Long.TYPE,
            Character.TYPE,
            Float.TYPE,
            Double.TYPE
    )));

    /**
     * Utilities for {@code int} values.
     */
    public static final class Ints {

        /**
         * Checks that specified value is greater than or equal to specified bound.
         *
         * @param value   the value to check.
         * @param against the bound to check against.
         * @return given {@code value}.
         * @throws IllegalArgumentException if {@code value} is less than {@code against}.
         */
        public static int requireGreaterThanOrEqualTo(final int value, final int against) {
            if (value < against) {
                throw new IllegalArgumentException(
                        "value(" + value + ") expected to be greater than or equal to " + against);
            }
            return value;
        }

        /**
         * Checks that specified value is greater than specified bound.
         *
         * @param value   the value to check.
         * @param against the bound to check against.
         * @return given {@code value}.
         * @throws IllegalArgumentException if {@code value} is less than or equal to {@code against}.
         */
        public static int requireGreaterThan(final int value, final int against) {
            if (requireGreaterThanOrEqualTo(value, against) == against) {
                throw new IllegalArgumentException("value(" + value + ") expected to be greater than " + against);
            }
            return value;
        }

        /**
         * Checks that specified value is less than or equal to specified bound.
         *
         * @param value   the value to check.
         * @param against the bound to check against.
         * @return given {@code value}.
         * @throws IllegalArgumentException if {@code value} is greater than {@code against}.
         */
        public static int requireLessThanOrEqualTo(final int value, final int against) {
            if (value > against) {
                throw new IllegalArgumentException(
                        "value(" + value + ") expected to be less than or equal to " + against);
            }
            return value;
        }

        /**
         * Checks that specified value is less than specified bound.
         *
         * @param value   the value to check.
         * @param against the bound to check against.
         * @return given {@code value}.
         * @throws IllegalArgumentException if {@code value} is greater than or equal to {@code against}.
         */
        public static int requireLessThan(final int value, final int against) {
            if (requireLessThanOrEqualTo(value, against) == against) {
                throw new IllegalArgumentException("value(" + value + ") expected to be less than " + against);
            }
            return value;
        }

        /**
         * Checks that specified value is not positive.
         *
         * @param value the value to check.
         * @return given {@code value}.
         * @throws IllegalArgumentException if {@code value} is positive.
         */
        public static int requireNotPositive(final int value) {
            return requireLessThanOrEqualTo(value, 0);
        }

        /**
         * Checks that specified value is not negative.
         *
         * @param value the value to check.
         * @return given {@code value}.
         * @throws IllegalArgumentException if {@code value} is negative.
         */
        public static int requireNotNegative(final int value) {
            return requireGreaterThanOrEqualTo(value, 0);
        }

        /**
         * Checks that specified value is negative.
         *
         * @param value the value to check.
         * @return given {@code value}.
         * @throws IllegalArgumentException if {@code value} is not negative.
         */
        public static int requireNegative(final int value) {
            return requireLessThan(value, 0);
        }

        /**
         * Checks that specified value is positive.
         *
         * @param value the value to check.
         * @return given {@code value}.
         * @throws IllegalArgumentException if {@code value} is not positive.
         */
        public static int requirePositive(final int value) {
            return requireGreaterThan(value, 0);
        }

        private Ints() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    private JinahyaPrimitives() {
        throw new AssertionError("instantiation is not allowed");
    }
}
