package com.github.jinahya.util;

import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Represents a mask for {@link BitFace face}s
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
final class BitMask {

    /**
     * Represents a mask for {@link BitFace.OfLong face}s
     *
     * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
     */
    public static final class OfLong {

        public static final int MIN_EXPONENT = 0;

        public static final int MAX_EXPONENT = Long.SIZE - 2;

        static final long MIN_VALUE = 0x00_00_00_00_00_00_00_01L;

        static final long MAX_VALUE = 0x40_00_00_00_00_00_00_00L; // 0b0100_0000_...

        /**
         * Checks whether specified exponent value is valid.
         *
         * @param exponent the exponent value to test.
         * @return given {@code exponent} when it's between {@value #MIN_EXPONENT} and {@value #MAX_EXPONENT}, both
         * inclusive.
         * @throws IllegalArgumentException when {@code exponent} does not reside in the valid range.
         */
        public static int requireValidExponent(final int exponent) {
            if (exponent < MIN_EXPONENT) {
                throw new IllegalArgumentException("exponent(" + exponent + ") < " + MIN_EXPONENT);
            }
            if (exponent > MAX_EXPONENT) {
                throw new IllegalArgumentException("exponent(" + exponent + ") > " + MAX_EXPONENT);
            }
            return exponent;
        }

        /**
         * Checks whether specified value is valid.
         *
         * @param value the value to test.
         * @return given {@code value} when it's between {@value #MIN_VALUE} and {@value #MAX_VALUE}, both inclusive.
         * @throws IllegalArgumentException when {@code value} does not reside in the valid range.
         */
        static long requireValidValue(final long value) {
            if (value < MIN_VALUE) {
                throw new IllegalArgumentException("value(" + value + ") < " + MIN_VALUE);
            }
            if (value > MAX_VALUE) {
                throw new IllegalArgumentException("value(" + value + ") > " + MAX_VALUE);
            }
            return value;
        }

        private static final Map<Long, OfLong> CACHE = new ConcurrentHashMap<>(new WeakHashMap<>());

        static OfLong of(final long value) {
            return CACHE.computeIfAbsent(requireValidValue(value), OfLong::new);
        }

        public static OfLong ofExponent(final int exponent) {
            return of(0x01L << requireValidExponent(exponent));
        }

//        public static long valueOfAll(final Stream<OfLong> stream) {
//            Objects.requireNonNull(stream, "stream is null");
//            return stream
//                    .mapToLong(m -> m.value)
//                    .reduce(BitFace.OfLong.MIN_VALUE, (v1, v2) -> v1 | v2);
//        }
//
//        public static long valueOfAll(final Iterable<OfLong> iterable) {
//            Objects.requireNonNull(iterable, "iterable is null");
//            return valueOfAll(StreamSupport.stream(iterable.spliterator(), false));
//        }

        /**
         * Creates a new instance.
         */
        private OfLong(final long value) {
            super();
            this.value = requireValidValue(value);
        }

        @Override
        public String toString() {
            return super.toString() + '{' +
                   "value=" + value +
                   '}';
        }

        @Override
        public boolean equals(final Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof OfLong that)) return false;
            return value == that.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        /**
         * Puts this mask on to specified face.
         *
         * @param face the face to which this mask is put on.
         * @return new face with this mask on.
         */
        public BitFace.OfLong putOnTo(final BitFace.OfLong face) {
            return Objects.requireNonNull(face, "face is null").putOn(this);
        }

        /**
         * Takes this mask off from specified face.
         *
         * @param face the face from which this mask is take off.
         * @return new face with this mask off.
         */
        public BitFace.OfLong takeOffFrom(final BitFace.OfLong face) {
            return Objects.requireNonNull(face, "face is null").takeOff(this);
        }

        /**
         * Returns value of this mask.
         *
         * @return value of this mask.
         */
        public long getValue() {
            return value;
        }

        private final long value;
    }

    public static final int MIN_EXPONENT = 0;

    public static final int MAX_EXPONENT = Integer.SIZE - 2;

    static final int MIN_VALUE = 0x00_00_00_01;

    static final int MAX_VALUE = 0x40_00_00_00; // 0b0100_0000_...

    /**
     * Checks whether specified exponent value is valid.
     *
     * @param exponent the exponent value to test.
     * @return given {@code exponent} when it's between {@value #MIN_EXPONENT} and {@value #MAX_EXPONENT}, both
     * inclusive.
     * @throws IllegalArgumentException when {@code exponent} does not reside in the valid range.
     */
    public static int requireValidExponent(final int exponent) {
        if (exponent < MIN_EXPONENT) {
            throw new IllegalArgumentException("exponent(" + exponent + ") < " + MIN_EXPONENT);
        }
        if (exponent > MAX_EXPONENT) {
            throw new IllegalArgumentException("exponent(" + exponent + ") > " + MAX_EXPONENT);
        }
        return exponent;
    }

    /**
     * Checks whether specified value is valid.
     *
     * @param value the value to test.
     * @return given {@code value} when it's between {@value #MIN_VALUE} and {@value #MAX_VALUE}, both inclusive.
     * @throws IllegalArgumentException when {@code value} does not reside in the valid range.
     */
    static int requireValidValue(final int value) {
        if (value < MIN_VALUE) {
            throw new IllegalArgumentException("value(" + value + ") < " + MIN_VALUE);
        }
        if (value > MAX_VALUE) {
            throw new IllegalArgumentException("value(" + value + ") > " + MAX_VALUE);
        }
        return value;
    }

    private static final Map<Integer, BitMask> CACHE = new ConcurrentHashMap<>(new WeakHashMap<>());

    static BitMask of(final int value) {
        return CACHE.computeIfAbsent(requireValidValue(value), BitMask::new);
    }

    public static BitMask ofExponent(final int exponent) {
        return of(0x01 << requireValidExponent(exponent));
    }

    /**
     * Creates a new instance.
     */
    private BitMask(final int value) {
        super();
        this.value = requireValidValue(value);
    }

    @Override
    public String toString() {
        return super.toString() + '{' +
               "value=" + value +
               '}';
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BitMask that)) return false;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    /**
     * Puts this mask on to specified face.
     *
     * @param face the face to which this mask is put on.
     * @return new face with this mask on.
     */
    public BitFace putOnTo(final BitFace face) {
        return Objects.requireNonNull(face, "face is null").putOn(this);
    }

    /**
     * Takes this mask off from specified face.
     *
     * @param face the face from which this mask is take off.
     * @return new face with this mask off.
     */
    public BitFace takeOffFrom(final BitFace face) {
        return Objects.requireNonNull(face, "face is null").takeOff(this);
    }

    /**
     * Returns the value of this mask.
     *
     * @return the value of this mask.
     */
    public int getValue() {
        return value;
    }

    private final int value;
}
