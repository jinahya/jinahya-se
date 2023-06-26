package com.github.jinahya.util;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a bit mask for {@link BitFace face}s
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see BitFace
 */
public final class BitMask implements Serializable {

    private static final long serialVersionUID = 7457185557296234958L;

    /**
     * Represents a bit mask for {@link BitFace.OfLong face}s
     *
     * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
     */
    public static final class OfLong implements Serializable {

        private static final long serialVersionUID = 937865657727258530L;

        /**
         * The minimum value for an exponent of a mask. The value is {@value}.
         */
        public static final int MIN_EXPONENT = 0;

        /**
         * The maximum value for an exponent of a mask. The value is {@value}.
         */
        public static final int MAX_EXPONENT = Long.SIZE - 1;

        /**
         * Checks whether specified exponent value is between {@value #MIN_EXPONENT} and {@value #MAX_EXPONENT}, both
         * inclusive.
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

        private static final Map<Long, OfLong> INSTANCES = Collections.synchronizedMap(new HashMap<>());

        /**
         * Creates a new instance for specified exponent.
         *
         * @param exponent the exponent between {@value #MIN_EXPONENT} and {@value #MAX_EXPONENT}, both inclusive.
         * @return a new instance.
         */
        public static OfLong ofExponent(final int exponent) {
            return INSTANCES.computeIfAbsent(0x01L << requireValidExponent(exponent), OfLong::new);
        }

        static final List<OfLong> all;

        static {
            all = Collections.unmodifiableList(
                    IntStream.rangeClosed(MIN_EXPONENT, MAX_EXPONENT)
                            .mapToObj(OfLong::ofExponent)
                            .collect(Collectors.toList())
            );
        }

        private static long requireValidValue(final long value) {
            if (Long.bitCount(value) != 1) {
                throw new IllegalArgumentException("invalid value: " + Long.toBinaryString(value));
            }
            return value;
        }

        /**
         * Creates a new instance.
         */
        private OfLong(final long value) {
            super();
            if (INSTANCES.containsKey(value)) {
                throw new IllegalAccessError("");
            }
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
            if (!(obj instanceof OfLong)) return false;
            final OfLong that = (OfLong) obj;
            return value == that.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        private void readObject(final ObjectInputStream s) throws IOException, ClassNotFoundException {
            Objects.requireNonNull(s, "s is null");
            s.defaultReadObject();
            try {
                requireValidValue(value);
            } catch (final IllegalArgumentException iae) {
                throw new InvalidObjectException(iae.getMessage());
            }
        }

        private Object readResolve() {
            if (true) {
                return ofExponent(MAX_EXPONENT - Long.numberOfLeadingZeros(value));
            }
            if (value == Long.MIN_VALUE) {
                return ofExponent((int) (Math.log(value - 1L) / Math.log(2)) + 1);
            }
            return ofExponent((int) (Math.log(value) / Math.log(2)));
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

    /**
     * The minimum value for an exponent of a mask. The value is {@value}.
     */
    public static final int MIN_EXPONENT = 0;

    /**
     * The maximum value for an exponent of a mask. The value is {@value}.
     */
    public static final int MAX_EXPONENT = Integer.SIZE - 1;

    /**
     * Checks whether specified exponent value is between {@value #MIN_EXPONENT} and {@value #MAX_EXPONENT}, both
     * inclusive.
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

    private static final Map<Integer, BitMask> INSTANCES = Collections.synchronizedMap(new HashMap<>());

    /**
     * Creates a new instance for specified exponent.
     *
     * @param exponent the exponent between {@value #MIN_EXPONENT} and {@value #MAX_EXPONENT}, both inclusive.
     * @return a new instance.
     */
    public static BitMask ofExponent(final int exponent) {
        return INSTANCES.computeIfAbsent(0x01 << requireValidExponent(exponent), BitMask::new);
    }

    static final List<BitMask> all;

    static {
        all = Collections.unmodifiableList(
                IntStream.rangeClosed(MIN_EXPONENT, MAX_EXPONENT)
                        .mapToObj(BitMask::ofExponent)
                        .collect(Collectors.toList())
        );
    }

    private static int requireValidValue(final int value) {
        if (Integer.bitCount(value) != 1) {
            throw new IllegalArgumentException("invalid value: " + Integer.toBinaryString(value));
        }
        return value;
    }

    /**
     * Creates a new instance.
     */
    private BitMask(final int value) {
        super();
        if (INSTANCES.containsKey(value)) {
            throw new IllegalAccessError("");
        }
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
        if (!(obj instanceof BitMask)) return false;
        final BitMask that = (BitMask) obj;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    private void readObject(final ObjectInputStream s) throws IOException, ClassNotFoundException {
        Objects.requireNonNull(s, "s is null");
        s.defaultReadObject();
        try {
            requireValidValue(value);
        } catch (final IllegalArgumentException iae) {
            throw new InvalidObjectException(iae.getMessage());
        }
    }

    private Object readResolve() {
        return ofExponent(MAX_EXPONENT - Integer.numberOfLeadingZeros(value));
    }

    /**
     * Puts this mask on to specified face.
     *
     * @param face the face to which this mask is put on.
     * @return new face with this mask on.
     * @see com.github.jinahya.util.BitFace#putOn(BitMask)
     */
    public BitFace putOnTo(final BitFace face) {
        Objects.requireNonNull(face, "face is null");
        return face.putOn(this);
    }

    /**
     * Takes this mask off from specified face.
     *
     * @param face the face from which this mask is take off.
     * @return new face with this mask off.
     * @see com.github.jinahya.util.BitFace#takeOff(BitMask)
     */
    public BitFace takeOffFrom(final BitFace face) {
        Objects.requireNonNull(face, "face is null");
        return face.takeOff(this);
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
