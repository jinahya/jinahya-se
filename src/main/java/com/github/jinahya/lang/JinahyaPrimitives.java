package com.github.jinahya.lang;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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

    public static final class Ints {

        public static int requireGreaterThanOrEqualTo(final int value, final int against) {
            if (value < against) {
                throw new IllegalArgumentException(
                        "value(" + value + ") expected to be greater than or equal to " + against);
            }
            return value;
        }

        public static int requireGreaterThan(final int value, final int against) {
            if (requireGreaterThanOrEqualTo(value, against) == against) {
                throw new IllegalArgumentException("value(" + value + ") expected to be greater than " + against);
            }
            return value;
        }

        public static int requireLessThanOrEqualTo(final int value, final int against) {
            if (value > against) {
                throw new IllegalArgumentException(
                        "value(" + value + ") expected to be less than or equal to " + against);
            }
            return value;
        }

        public static int requireLessThan(final int value, final int against) {
            if (requireLessThanOrEqualTo(value, against) == against) {
                throw new IllegalArgumentException("value(" + value + ") expected to be less than " + against);
            }
            return value;
        }

        public static int requireNotPositive(final int value) {
            return requireLessThanOrEqualTo(value, 0);
        }

        public static int requireNotNegative(final int value) {
            return requireGreaterThanOrEqualTo(value, 0);
        }

        public static int requireNegative(final int value) {
            return requireLessThan(value, 0);
        }

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
