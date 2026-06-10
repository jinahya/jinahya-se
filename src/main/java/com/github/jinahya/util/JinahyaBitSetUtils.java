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

import java.util.BitSet;
import java.util.Objects;

/**
 * Utilities for writing/reading multi-byte integer values into/from a {@link BitSet}.
 *
 * <p>Values are encoded in <strong>little-endian</strong> order at every level:</p>
 * <ul>
 *   <li>Within each byte, the <em>least</em>-significant bit is stored at the <em>lower</em> bit index.
 *       Bit {@code 0} of the value goes to {@code set.get(index)}, bit {@code 7} to {@code set.get(index + 7)}.</li>
 *   <li>Across bytes, the <em>lower</em> byte is stored at the <em>lower</em> bit index. The low byte of a short
 *       occupies bits {@code index..index+7}, the high byte occupies bits {@code index+8..index+15}; analogous
 *       layout for {@code int} (low/high halves) and {@code long} (low/high halves).</li>
 * </ul>
 *
 * <p>This convention matches {@link BitSet#toByteArray()} and {@link BitSet#toLongArray()}, so a value written
 * with {@link #setLong(BitSet, int, long) setLong(set, 0, v)} can be recovered as {@code set.toLongArray()[0]}
 * (modulo {@code toLongArray}'s trailing-zero trimming).</p>
 *
 * <p>Every {@code setX}/{@code getX} pair round-trips: for any valid {@code index} and any {@code v} in the
 * type's representable range, {@code getX(setX(set, index, v), index) == v}.</p>
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public final class JinahyaBitSetUtils {

    /**
     * Writes the low {@value Byte#SIZE} bits of specified value into specified bit set, starting at specified bit
     * index, in <strong>LSB-first</strong> order.
     *
     * <p>After this call, {@code set.get(index + k)} returns {@code ((value >>> k) & 1) != 0} for
     * {@code k} in {@code [0, 8)}. Bits above the low {@value Byte#SIZE} of {@code value} are ignored.</p>
     *
     * @param set   the bit set to write into.
     * @param index the starting bit index; must be non-negative.
     * @param value the value whose low {@value Byte#SIZE} bits are written.
     * @param <T>   the bit set type parameter.
     * @return given {@code set} for chaining.
     * @throws NullPointerException      when {@code set} is {@code null}.
     * @throws IndexOutOfBoundsException when {@code index} is negative.
     * @see #getByte(BitSet, int)
     */
    public static <T extends BitSet> T setByte(final T set, int index, int value) {
        Objects.requireNonNull(set, "set is null");
        if (index < 0) {
            throw new IndexOutOfBoundsException("index is negative: " + index);
        }
        for (int i = 0; i < Byte.SIZE; i++) {
            set.set(index++, (value & 0x01) != 0);
            value >>>= 1;
        }
        return set;
    }

    /**
     * Reads {@value Byte#SIZE} bits from specified bit set starting at specified bit index, in
     * <strong>LSB-first</strong> order, and returns them as an unsigned byte value.
     *
     * <p>Inverse of {@link #setByte(BitSet, int, int)}: for any non-negative {@code index} and any
     * {@code v} in {@code [0, 256)}, {@code getByte(setByte(set, index, v), index) == v}.</p>
     *
     * @param set   the bit set to read from.
     * @param index the starting bit index; must be non-negative.
     * @return an {@code int} value in {@code [0, 256)} representing the {@value Byte#SIZE} bits read.
     * @throws NullPointerException      when {@code set} is {@code null}.
     * @throws IndexOutOfBoundsException when {@code index} is negative.
     * @see #setByte(BitSet, int, int)
     */
    public static int getByte(final BitSet set, final int index) {
        Objects.requireNonNull(set, "set is null");
        if (index < 0) {
            throw new IndexOutOfBoundsException("index is negative: " + index);
        }
        int value = 0;
        for (int i = 0; i < Byte.SIZE; i++) {
            if (set.get(index + i)) {
                value |= 1 << i;
            }
        }
        return value;
    }

    /**
     * Writes the low {@value Short#SIZE} bits of specified value into specified bit set, starting at specified
     * bit index, in little-endian byte order.
     *
     * <p>The low byte occupies bits {@code index..index+7}; the high byte occupies bits
     * {@code index+8..index+15}. Bits above the low {@value Short#SIZE} of {@code value} are ignored.</p>
     *
     * @param set   the bit set to write into.
     * @param index the starting bit index; must be non-negative.
     * @param value the value whose low {@value Short#SIZE} bits are written.
     * @param <T>   the bit set type parameter.
     * @return given {@code set} for chaining.
     * @throws NullPointerException      when {@code set} is {@code null}.
     * @throws IndexOutOfBoundsException when {@code index} is negative.
     * @see #getShort(BitSet, int)
     */
    public static <T extends BitSet> T setShort(final T set, final int index, final int value) {
        return setByte(
                setByte(set, index, value),
                index + Byte.SIZE,
                value >>> Byte.SIZE
        );
    }

    /**
     * Reads {@value Short#SIZE} bits from specified bit set starting at specified bit index, in little-endian
     * byte order, and returns them as an unsigned short value.
     *
     * <p>Inverse of {@link #setShort(BitSet, int, int)}: for any non-negative {@code index} and any
     * {@code v} in {@code [0, 65536)}, {@code getShort(setShort(set, index, v), index) == v}.</p>
     *
     * @param set   the bit set to read from.
     * @param index the starting bit index; must be non-negative.
     * @return an {@code int} value in {@code [0, 65536)} representing the {@value Short#SIZE} bits read.
     * @throws NullPointerException      when {@code set} is {@code null}.
     * @throws IndexOutOfBoundsException when {@code index} is negative.
     * @see #setShort(BitSet, int, int)
     */
    public static int getShort(final BitSet set, final int index) {
        return (getByte(set, index + Byte.SIZE) << Byte.SIZE)
               | getByte(set, index);
    }

    /**
     * Writes all {@value Integer#SIZE} bits of specified value into specified bit set, starting at specified
     * bit index, in little-endian byte order.
     *
     * <p>The low short occupies bits {@code index..index+15}; the high short occupies bits
     * {@code index+16..index+31}.</p>
     *
     * @param set   the bit set to write into.
     * @param index the starting bit index; must be non-negative.
     * @param value the value whose {@value Integer#SIZE} bits are written.
     * @param <T>   the bit set type parameter.
     * @return given {@code set} for chaining.
     * @throws NullPointerException      when {@code set} is {@code null}.
     * @throws IndexOutOfBoundsException when {@code index} is negative.
     * @see #getInt(BitSet, int)
     */
    public static <T extends BitSet> T setInt(final T set, final int index, final int value) {
        return setShort(
                setShort(set, index, value),
                index + Short.SIZE,
                value >>> Short.SIZE
        );
    }

    /**
     * Reads {@value Integer#SIZE} bits from specified bit set starting at specified bit index, in little-endian
     * byte order, and returns them as a (signed) {@code int}.
     *
     * <p>Inverse of {@link #setInt(BitSet, int, int)}: for any non-negative {@code index} and any {@code int}
     * value {@code v}, {@code getInt(setInt(set, index, v), index) == v}.</p>
     *
     * @param set   the bit set to read from.
     * @param index the starting bit index; must be non-negative.
     * @return the {@code int} value composed of the {@value Integer#SIZE} bits read.
     * @throws NullPointerException      when {@code set} is {@code null}.
     * @throws IndexOutOfBoundsException when {@code index} is negative.
     * @see #setInt(BitSet, int, int)
     */
    public static int getInt(final BitSet set, final int index) {
        return (getShort(set, index + Short.SIZE) << Short.SIZE)
               | getShort(set, index);
    }

    /**
     * Writes all {@value Long#SIZE} bits of specified value into specified bit set, starting at specified bit
     * index, in little-endian byte order.
     *
     * <p>The low int occupies bits {@code index..index+31}; the high int occupies bits
     * {@code index+32..index+63}.</p>
     *
     * @param set   the bit set to write into.
     * @param index the starting bit index; must be non-negative.
     * @param value the value whose {@value Long#SIZE} bits are written.
     * @param <T>   the bit set type parameter.
     * @return given {@code set} for chaining.
     * @throws NullPointerException      when {@code set} is {@code null}.
     * @throws IndexOutOfBoundsException when {@code index} is negative.
     * @see #getLong(BitSet, int)
     */
    public static <T extends BitSet> T setLong(final T set, final int index, final long value) {
        return setInt(
                setInt(set, index, (int) value),
                index + Integer.SIZE,
                (int) (value >>> Integer.SIZE)
        );
    }

    /**
     * Reads {@value Long#SIZE} bits from specified bit set starting at specified bit index, in little-endian
     * byte order, and returns them as a (signed) {@code long}.
     *
     * <p>Inverse of {@link #setLong(BitSet, int, long)}: for any non-negative {@code index} and any
     * {@code long} value {@code v}, {@code getLong(setLong(set, index, v), index) == v}.</p>
     *
     * @param set   the bit set to read from.
     * @param index the starting bit index; must be non-negative.
     * @return the {@code long} value composed of the {@value Long#SIZE} bits read.
     * @throws NullPointerException      when {@code set} is {@code null}.
     * @throws IndexOutOfBoundsException when {@code index} is negative.
     * @see #setLong(BitSet, int, long)
     */
    public static long getLong(final BitSet set, final int index) {
        return ((long) getInt(set, index + Integer.SIZE) << Integer.SIZE)
               | Integer.toUnsignedLong(getInt(set, index));
    }

    private JinahyaBitSetUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
