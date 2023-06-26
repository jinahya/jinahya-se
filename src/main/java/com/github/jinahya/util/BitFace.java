package com.github.jinahya.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Represents a face with zero or more {@link BitMask mask}s on it.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see BitMask
 */
public final class BitFace implements Serializable {

    private static final long serialVersionUID = -8446641798336537758L;

    /**
     * Represents a face with zero or more {@link BitMask.OfLong mask}s on it.
     *
     * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
     * @see BitMask.OfLong
     */
    public static final class OfLong implements Serializable {

        private static final long serialVersionUID = -5541074193547352154L;

        private static final Map<Long, OfLong> CACHE = Collections.synchronizedMap(new WeakHashMap<>());

        /**
         * Returns an instance with specified value.
         *
         * @param value the value.
         * @return an instance with {@code value}.
         */
        static OfLong of(final long value) {
            return CACHE.computeIfAbsent(value, OfLong::new);
        }

        static OfLong merge(final OfLong f1, final OfLong f2) {
            Objects.requireNonNull(f1, "f1 is null");
            Objects.requireNonNull(f2, "f2 is null");
            return of(f1.getValue() | f2.getValue());
        }

        static OfLong of(final Stream<BitMask.OfLong> masks) {
            Objects.requireNonNull(masks, "masks is null");
            return of(masks.mapToLong(BitMask.OfLong::getValue).sum());
        }

        /**
         * Returns an instance wearing specified masks
         *
         * @param masks the masks to wear.
         * @return an instance wearing {@code masks}.
         */
        public static OfLong of(final Iterable<BitMask.OfLong> masks) {
            Objects.requireNonNull(masks, "masks is null");
            return of(StreamSupport.stream(masks.spliterator(), false));
        }

        /**
         * Returns an instance wearing specified mask.
         *
         * @param mask the mask to wear.
         * @return an instance wearing {@code mask}.
         * @see #ofNone()
         */
        public static OfLong of(final BitMask.OfLong mask) {
            return of(mask.getValue());
        }

        /**
         * Returns an instance wearing specified masks.
         *
         * @param mask1 a mask to wear.
         * @param mask2 another mask to wear.
         * @return an instance wearing all specified masks.
         */
        public static OfLong of(final BitMask.OfLong mask1, final BitMask.OfLong mask2) {
            return of(mask1).putOn(mask2);
        }

        /**
         * Returns an instance wearing specified masks.
         *
         * @param mask1 the 1st mask to wear.
         * @param mask2 the 2nd mask to wear.
         * @param mask3 the 3rd mask to wear.
         * @return an instance wearing {@code otherMasks}.
         */
        public static OfLong of(final BitMask.OfLong mask1, final BitMask.OfLong mask2, final BitMask.OfLong mask3) {
            return of(mask1, mask2).putOn(mask3);
        }

        /**
         * Returns an instance wearing specified masks.
         *
         * @param mask1 the 1st mask to wear.
         * @param mask2 the 2nd mask to wear.
         * @param mask3 the 3rd mask to wear.
         * @param mask4 the 4th mask to wear.
         * @return an instance wearing {@code otherMasks}.
         */
        public static OfLong of(final BitMask.OfLong mask1, final BitMask.OfLong mask2, final BitMask.OfLong mask3,
                                final BitMask.OfLong mask4) {
            return of(mask1, mask2, mask3).putOn(mask4);
        }

        /**
         * Returns an instance wearing specified masks.
         *
         * @param mask1      the 1st mask to wear.
         * @param mask2      the 2nd mask to wear.
         * @param mask3      the 3rd mask to wear.
         * @param mask4      the 4th mask to wear.
         * @param otherMasks other masks to wear.
         * @return an instance wearing {@code otherMasks}.
         */
        public static OfLong of(final BitMask.OfLong mask1, final BitMask.OfLong mask2, final BitMask.OfLong mask3,
                                final BitMask.OfLong mask4, final BitMask.OfLong... otherMasks) {
            Objects.requireNonNull(otherMasks, "otherMasks is null");
            return merge(of(mask1, mask2, mask3, mask4), of(Arrays.stream(otherMasks)));
        }

        /**
         * Returns an instance with no mask.
         *
         * @return an instance with no mask.
         */
        public static OfLong ofNone() {
            return of(0L);
        }

        /**
         * Returns an instance wearing all possible masks.
         *
         * @return an instance wearing all possible masks.
         */
        public static OfLong ofAll() {
            return of(-1L);
        }

        /**
         * Creates a new instance with specified value.
         *
         * @param value the value.
         */
        private OfLong(final long value) {
            super();
            this.value = value;
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

        private Object readResolve() {
            return of(value);
        }

        /**
         * Checks whether this face is wearing specified mask.
         *
         * @param mask the mask to check.
         * @return {@code true} if this face is wearing the {@code mask}; {@code false} otherwise.
         */
        public boolean isWearing(final BitMask.OfLong mask) {
            Objects.requireNonNull(mask, "mask is null");
            return (value & mask.getValue()) == mask.getValue();
        }

        /**
         * Puts specified mask on to this face.
         *
         * @param mask the mask to put on.
         * @return a new face with {@code mask} on.
         */
        public OfLong putOn(final BitMask.OfLong mask) {
            Objects.requireNonNull(mask, "mask is null");
            return of(value | mask.getValue());
        }

        /**
         * Takes specified mask off from this face.
         *
         * @param mask the mask to take off.
         * @return a new face with {@code mask} off.
         */
        public OfLong takeOff(final BitMask.OfLong mask) {
            Objects.requireNonNull(mask, "mask is null");
            return of(value & ~mask.getValue());
        }

        /**
         * Returns a set of masks that this face is wearing.
         *
         * @return a set of masks that this face is wearing.
         * @apiNote Modifying result set does not modify this face.
         */
        public Set<BitMask.OfLong> toMaskSet() {
            return BitMask.OfLong.all.stream()
                    .filter(this::isWearing)
                    .collect(Collectors.toSet());
        }

        /**
         * Returns current value of this face.
         *
         * @return current value of this face.
         */
        public long getValue() {
            return value;
        }

        private final long value;
    }

    private static final Map<Integer, BitFace> CACHE = Collections.synchronizedMap(new WeakHashMap<>());

    /**
     * Returns an instance with specified value.
     *
     * @param value the value.
     * @return an instance.
     */
    static BitFace of(final int value) {
        return CACHE.computeIfAbsent(value, BitFace::new);
    }

    static BitFace merge(final BitFace f1, final BitFace f2) {
        Objects.requireNonNull(f1, "f1 is null");
        Objects.requireNonNull(f2, "f2 is null");
        return of(f1.getValue() | f2.getValue());
    }

    static BitFace of(final Stream<BitMask> masks) {
        Objects.requireNonNull(masks, "masks is null");
        return of(masks.mapToInt(BitMask::getValue).sum());
    }

    /**
     * Returns an instance wearing specified masks
     *
     * @param masks the masks to wear.
     * @return an instance wearing {@code mask}.
     */
    public static BitFace of(final Iterable<BitMask> masks) {
        Objects.requireNonNull(masks, "masks is null");
        return of(StreamSupport.stream(masks.spliterator(), false));
    }

    /**
     * Returns an instance wearing specified mask.
     *
     * @param mask the mask to wear.
     * @return an instance wearing specified masks.
     */
    public static BitFace of(final BitMask mask) {
        Objects.requireNonNull(mask, "mask is null");
        return of(mask.getValue());
    }

    /**
     * Returns an instance wearing specified masks
     *
     * @param mask1 a mask to wear.
     * @param mask2 another mask to wear.
     * @return an instance wearing specified masks.
     */
    public static BitFace of(final BitMask mask1, final BitMask mask2) {
        return of(mask1).putOn(mask2);
    }

    /**
     * Returns an instance wearing specified masks
     *
     * @param mask1 the 1st mask to wear.
     * @param mask2 the 2nd mask to wear.
     * @param mask3 the 3rd mask to wear.
     * @return an instance wearing specified masks.
     */
    public static BitFace of(final BitMask mask1, final BitMask mask2, final BitMask mask3) {
        return of(mask1, mask2).putOn(mask3);
    }

    /**
     * Returns an instance wearing specified masks
     *
     * @param mask1 the 1st mask to wear.
     * @param mask2 the 2nd mask to wear.
     * @param mask3 the 3rd mask to wear.
     * @param mask4 the 4th mask to wear.
     * @return an instance wearing specified masks.
     */
    public static BitFace of(final BitMask mask1, final BitMask mask2, final BitMask mask3, final BitMask mask4) {
        return of(mask1, mask2, mask3).putOn(mask4);
    }

    /**
     * Returns an instance wearing specified masks
     *
     * @param mask1      the 1st mask to wear.
     * @param mask2      the 2nd mask to wear.
     * @param mask3      the 3rd mask to wear.
     * @param mask4      the 4th mask to wear.
     * @param otherMasks other masks to wear.
     * @return an instance wearing specified masks.
     */
    public static BitFace of(final BitMask mask1, final BitMask mask2, final BitMask mask3, final BitMask mask4,
                             final BitMask... otherMasks) {
        return merge(of(mask1, mask2, mask3, mask4), of(Arrays.stream(otherMasks)));
    }

    /**
     * Returns an instance with no mask.
     *
     * @return an instance with no mask.
     */
    public static BitFace ofNone() {
        return of(0);
    }

    /**
     * Returns an instance wearing all possible masks.
     *
     * @return an instance wearing all possible masks.
     */
    public static BitFace ofAll() {
        return of(-1);
    }

    /**
     * Creates a new instance with specified value.
     *
     * @param value the value.
     */
    private BitFace(final int value) {
        super();
        this.value = value;
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
        if (!(obj instanceof BitFace)) return false;
        final BitFace that = (BitFace) obj;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    private Object readResolve() {
        return of(value);
    }

    /**
     * Checks whether this face is wearing specified mask.
     *
     * @param mask the mask to check.
     * @return {@code true} if this face is wearing {@code mask}; {@code false} otherwise.
     */
    public boolean isWearing(final BitMask mask) {
        Objects.requireNonNull(mask, "mask is null");
        return (value & mask.getValue()) == mask.getValue();
    }

    /**
     * Puts specified mask onto this face, and returns a new instance with the mask on.
     *
     * @param mask the mask to put onto this face.
     * @return a new face instance with {@code mask} on.
     */
    public BitFace putOn(final BitMask mask) {
        Objects.requireNonNull(mask, "mask is null");
        return of(value | mask.getValue());
    }

    /**
     * Takes specified mask off from this face, and returns a new instance with the mask off.
     *
     * @param mask the mask to take off from this face.
     * @return a new face instance with {@code mask} off.
     */
    public BitFace takeOff(final BitMask mask) {
        Objects.requireNonNull(mask, "mask is null");
        return of(value & ~mask.getValue());
    }

    /**
     * Returns a set of masks that this face is wearing.
     *
     * @return a set of masks that this face is wearing.
     * @apiNote Modifying result set does not modify this face.
     */
    public Set<BitMask> toMaskSet() {
        return BitMask.all.stream()
                .filter(this::isWearing)
                .collect(Collectors.toSet());
    }

    /**
     * Returns current value of this face.
     *
     * @return current value of this face.
     */
    public int getValue() {
        return value;
    }

    private final int value;
}
