/*
 * Copyright 2014 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
 *
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
 */
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

/**
 * Utilities for converting between numbers and bytes.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class Numbers {

    /**
     * The number of bytes used to represent a {@code byte} value in two's complement binary form.
     *
     * @deprecated Use {@link Byte#BYTES}
     */
    @Deprecated
    public static final int BYTE_BYTES = Byte.SIZE / Byte.SIZE;

    /**
     * The number of bytes used to represent a {@code short} value in two's complement binary form.
     *
     * @deprecated Use {@link Short#BYTES}
     */
    @Deprecated
    public static final int SHORT_BYTES = Short.SIZE / Byte.SIZE;

    /**
     * The number of bytes used to represent a {@code int} value in two's complement binary form.
     *
     * @deprecated Use {@link Integer#BYTES}
     */
    @Deprecated
    public static final int INTEGER_BYTES = Integer.SIZE / Byte.SIZE;

    /**
     * The number of bytes used to represent a {@code long} value in two's complement binary form.
     *
     * @deprecated Use {@link Long#BYTES}
     */
    @Deprecated
    public static final int LONG_BYTES = Long.SIZE / Byte.SIZE;

    /**
     * The number of bytes used to represent a {@code float} value.
     *
     * @deprecated Use {@link Float#BYTES}
     */
    @Deprecated
    public static final int FLOAT_BYTES = Float.SIZE / Byte.SIZE;

    /**
     * The number of bytes used to represent a {@code double} value.
     *
     * @deprecated Use {@link Double#BYTES}
     */
    @Deprecated
    public static final int DOUBLE_BYTES = Double.SIZE / Byte.SIZE;

    /**
     * Converts given {@code short} value to an array of {@value #SHORT_BYTES} bytes in big-endian order.
     *
     * @param value the {@code short} value to convert.
     * @return an array of {@value #SHORT_BYTES} bytes.
     */
    public static byte[] toBytes(short value) {
        final byte[] bytes = new byte[SHORT_BYTES];
        for (int i = bytes.length - 1; i >= 0; i--) {
            bytes[i] = (byte) (value & 0xFF);
            value >>= Byte.SIZE;
        }
        return bytes;
    }

    /**
     * Converts, in big-endian order, {@value #SHORT_BYTES} bytes of specified array beginning at specified index to a {@code short}
     * value. Bytes beyond the end of the array are treated as zeros.
     *
     * @param bytes the byte array to convert.
     * @param index the starting index in {@code bytes} between 0 (inclusive) and {@code bytes.length} (exclusive).
     * @return a {@code short} value converted from {@code bytes}.
     * @throws NullPointerException     if {@code bytes} is {@code null}.
     * @throws IllegalArgumentException if {@code bytes.length} is zero.
     * @throws IllegalArgumentException if {@code index} is less than zero, or greater than or equals to
     *                                  {@code bytes.length}.
     */
    public static short toShortBegins(final byte[] bytes, final int index) {
        if (bytes == null) {
            throw new NullPointerException("null bytes");
        }
        if (bytes.length == 0) {
            throw new IllegalArgumentException("empty bytes");
        }
        if (index < 0) {
            throw new IllegalArgumentException("index(" + index + ") < 0");
        }
        if (index >= bytes.length) {
            throw new IllegalArgumentException(
                    "index(" + index + ") >= bytes.length(" + bytes.length + ")");
        }
        short value = 0;
        final int end = index + SHORT_BYTES;
        for (int i = index; i < end; i++) {
            value <<= Byte.SIZE;
            if (i >= bytes.length) {
                continue;
            }
            value |= (bytes[i] & 0xFF);
        }
        return value;
    }

    /**
     * Converts bytes of specified array beginning at index {@code 0} to a {@code short} value.
     *
     * @param bytes the byte array to convert.
     * @return a {@code short} value converted from {@code bytes}.
     * @throws NullPointerException     if {@code bytes} is {@code null}.
     * @throws IllegalArgumentException if {@code bytes.length} is zero.
     * @see #toShortBegins(byte[], int)
     */
    public static short toShortBegins(final byte[] bytes) {
        return toShortBegins(bytes, 0);
    }

    /**
     * Converts, in big-endian order, {@value #SHORT_BYTES} bytes of specified array ending at specified index
     * (inclusive) to a {@code short} value. Bytes before the beginning of the array are treated as zeros.
     *
     * @param bytes the byte array to convert
     * @param index the ending index in {@code bytes} between 0 (inclusive) and {@code bytes.length} (exclusive).
     * @return a {@code short} value converted from {@code bytes}.
     * @throws NullPointerException     if {@code bytes} is {@code null}.
     * @throws IllegalArgumentException if {@code bytes.length} is zero.
     * @throws IllegalArgumentException if {@code index} is less than zero, or greater than or equals to
     *                                  {@code bytes.length}.
     */
    public static short toShortEnds(final byte[] bytes, final int index) {
        if (bytes == null) {
            throw new NullPointerException("null bytes");
        }
        if (bytes.length == 0) {
            throw new IllegalArgumentException("empty bytes");
        }
        if (index < 0) {
            throw new IllegalArgumentException("index(" + index + ") <= 0");
        }
        if (index >= bytes.length) {
            throw new IllegalArgumentException(
                    "index(" + index + ") >= bytes.length(" + bytes.length + ")");
        }
        short value = 0;
        for (int i = index - SHORT_BYTES + 1; i <= index; i++) {
            value <<= Byte.SIZE;
            if (i < 0) {
                continue;
            }
            value |= (bytes[i] & 0xFF);
        }
        return value;
    }

    /**
     * Converts bytes of specified array ending at the last index, {@code bytes.length - 1}, to a {@code short}
     * value.
     *
     * @param bytes the byte array to convert.
     * @return a {@code short} value converted from {@code bytes}.
     * @throws NullPointerException     if {@code bytes} is {@code null}.
     * @throws IllegalArgumentException if {@code bytes.length} is zero.
     * @see #toShortEnds(byte[], int)
     */
    public static short toShortEnds(final byte[] bytes) {

        return toShortEnds(bytes, bytes.length - 1);
    }

    /**
     * Converts given int value to an array of {@value #INTEGER_BYTES} bytes.
     *
     * @param value the int value to convert.
     * @return an array of {@value #INTEGER_BYTES} bytes.
     */
    public static byte[] toBytes(int value) {

        final byte[] bytes = new byte[INTEGER_BYTES];

        for (int i = bytes.length - 1; i >= 0; i--) {
            bytes[i] = (byte) (value & 0xFF);
            value >>= Byte.SIZE;
        }

        return bytes;
    }

    /**
     * Converts, in big-endian order, {@value #INTEGER_BYTES} bytes of specified array beginning at specified index to
     * an {@code int} value. Bytes beyond the end of the array are treated as zeros.
     *
     * @param bytes the byte array to convert.
     * @param index the starting index in {@code bytes} between 0 (inclusive) and {@code bytes.length} (exclusive).
     * @return an {@code int} value converted from {@code bytes}.
     * @throws NullPointerException     if {@code bytes} is {@code null}.
     * @throws IllegalArgumentException if {@code bytes.length} is zero.
     * @throws IllegalArgumentException if {@code index} is less than zero, or greater than or equals to
     *                                  {@code bytes.length}.
     */
    public static int toIntBegins(final byte[] bytes, final int index) {

        if (bytes == null) {
            throw new NullPointerException("null bytes");
        }

        if (bytes.length == 0) {
            throw new IllegalArgumentException("empty bytes");
        }

        if (index < 0) {
            throw new IllegalArgumentException("index(" + index + ") < 0");
        }

        if (index >= bytes.length) {
            throw new IllegalArgumentException(
                    "index(" + index + ") >= bytes.length(" + bytes.length + ")");
        }

        int value = 0;

        final int end = index + INTEGER_BYTES;
        for (int i = index; i < end; i++) {
            value <<= Byte.SIZE;
            if (i >= bytes.length) {
                continue;
            }
            value |= (bytes[i] & 0xFF);
        }

        return value;
    }

    /**
     * Converts bytes of specified array beginning at index {@code 0} to an {@code int} value.
     *
     * @param bytes the byte array to convert.
     * @return an {@code int} value converted from {@code bytes}.
     * @throws NullPointerException     if {@code bytes} is {@code null}.
     * @throws IllegalArgumentException if {@code bytes.length} is zero.
     * @see #toIntBegins(byte[], int)
     */
    public static int toIntBegins(final byte[] bytes) {

        return toIntBegins(bytes, 0);
    }

    /**
     * Converts, in big-endian order, {@value #INTEGER_BYTES} bytes of specified array ending at specified index
     * (inclusive) to an {@code int} value. Bytes before the beginning of the array are treated as zeros.
     *
     * @param bytes the byte array to convert.
     * @param index the ending index in {@code bytes} between 0 (inclusive) and {@code bytes.length} (exclusive).
     * @return an {@code int} value converted from {@code bytes}.
     * @throws NullPointerException     if {@code bytes} is {@code null}.
     * @throws IllegalArgumentException if {@code bytes.length} is zero.
     * @throws IllegalArgumentException if {@code index} is less than zero, or greater than or equals to
     *                                  {@code bytes.length}.
     */
    public static int toIntEnds(final byte[] bytes, final int index) {

        if (bytes == null) {
            throw new NullPointerException("null bytes");
        }

        if (bytes.length == 0) {
            throw new IllegalArgumentException("empty bytes");
        }

        if (index < 0) {
            throw new IllegalArgumentException("index(" + index + ") < 0");
        }

        if (index >= bytes.length) {
            throw new IllegalArgumentException(
                    "index(" + index + ") >= bytes.length(" + bytes.length + ")");
        }

        int value = 0;

        for (int i = index - INTEGER_BYTES + 1; i <= index; i++) {
            value <<= Byte.SIZE;
            if (i < 0) {
                continue;
            }
            value |= (bytes[i] & 0xFF);
        }

        return value;
    }

    /**
     * Converts bytes of specified array ending at the last index, {@code bytes.length - 1}, to an {@code int}
     * value.
     *
     * @param bytes the byte array to convert.
     * @return an {@code int} value converted from {@code bytes}.
     * @throws NullPointerException     if {@code bytes} is {@code null}.
     * @throws IllegalArgumentException if {@code bytes.length} is zero.
     * @see #toIntEnds(byte[], int)
     */
    public static int toIntEnds(final byte[] bytes) {

        return toIntEnds(bytes, bytes.length - 1);
    }

    /**
     * Converts given {@code float} value to an array of {@value #FLOAT_BYTES} bytes in big-endian order, using its raw int bits.
     *
     * @param value the {@code float} value to convert.
     * @return an array of {@value #FLOAT_BYTES} bytes.
     */
    public static byte[] toBytes(final float value) {

        return toBytes(Float.floatToRawIntBits(value));
    }

    /**
     * Converts, in big-endian order, {@value #FLOAT_BYTES} bytes of specified array beginning at specified index to a {@code float}
     * value. Bytes beyond the end of the array are treated as zeros. The result is converted from the raw int bits.
     *
     * @param bytes the byte array to convert.
     * @param index the starting index in {@code bytes} between 0 (inclusive) and {@code bytes.length} (exclusive).
     * @return a {@code float} value converted from {@code bytes}.
     * @throws NullPointerException     if {@code bytes} is {@code null}.
     * @throws IllegalArgumentException if {@code bytes.length} is zero.
     * @throws IllegalArgumentException if {@code index} is less than zero, or greater than or equals to
     *                                  {@code bytes.length}.
     */
    public static float toFloatBegins(final byte[] bytes, final int index) {

        return Float.intBitsToFloat(toIntBegins(bytes, index));
    }

    /**
     * Converts bytes of specified array beginning at the last index, {@code bytes.length - 1}, to a {@code float}
     * value.
     *
     * @param bytes the byte array to convert.
     * @return a {@code float} value converted from {@code bytes}.
     * @throws NullPointerException     if {@code bytes} is {@code null}.
     * @throws IllegalArgumentException if {@code bytes.length} is zero.
     * @see #toFloatBegins(byte[], int)
     */
    public static float toFloatBegins(final byte[] bytes) {

        return toFloatBegins(bytes, bytes.length - 1);
    }

    /**
     * Converts, in big-endian order, {@value #FLOAT_BYTES} bytes of specified array ending at specified index (inclusive) to a
     * {@code float} value. Bytes before the beginning of the array are treated as zeros.
     *
     * @param bytes the byte array to convert.
     * @param index the ending index in {@code bytes} between 0 (inclusive) and {@code bytes.length} (exclusive).
     * @return a {@code float} value converted from {@code bytes}.
     * @throws NullPointerException     if {@code bytes} is {@code null}.
     * @throws IllegalArgumentException if {@code bytes.length} is zero.
     * @throws IllegalArgumentException if {@code index} is less than zero, or greater than or equals to
     *                                  {@code bytes.length}.
     */
    public static float toFloatEnds(final byte[] bytes, final int index) {

        return Float.intBitsToFloat(toIntEnds(bytes, index));
    }

    /**
     * Converts bytes of specified array ending at the last index, {@code bytes.length - 1}, to a {@code float}
     * value.
     *
     * @param bytes the byte array to convert.
     * @return a {@code float} value converted from {@code bytes}.
     * @throws NullPointerException     if {@code bytes} is {@code null}.
     * @throws IllegalArgumentException if {@code bytes.length} is zero.
     * @see #toFloatEnds(byte[], int)
     */
    public static float toFloatEnds(final byte[] bytes) {

        return toFloatEnds(bytes, bytes.length - 1);
    }

    /**
     * Converts given {@code long} value to an array of {@value #LONG_BYTES} bytes in big-endian order.
     *
     * @param value the {@code long} value to convert.
     * @return an array of {@value #LONG_BYTES} bytes.
     */
    public static byte[] toBytes(long value) {

        final byte[] bytes = new byte[LONG_BYTES];

        for (int i = bytes.length - 1; i >= 0; i--) {
            bytes[i] = (byte) (value & 0xFF);
            value >>= Byte.SIZE;
        }

        return bytes;
    }

    /**
     * Converts, in big-endian order, {@value #LONG_BYTES} bytes of specified array beginning at specified index to a {@code long}
     * value. Bytes beyond the end of the array are treated as zeros.
     *
     * @param bytes the byte array to convert.
     * @param index the starting index in {@code bytes} between 0 (inclusive) and {@code bytes.length} (exclusive).
     * @return a {@code long} value converted from {@code bytes}.
     * @throws NullPointerException     if {@code bytes} is {@code null}.
     * @throws IllegalArgumentException if {@code bytes.length} is zero.
     * @throws IllegalArgumentException if {@code index} is less than zero, or greater than or equals to
     *                                  {@code bytes.length}.
     */
    public static long toLongBegins(final byte[] bytes, final int index) {

        if (bytes == null) {
            throw new NullPointerException("null bytes");
        }

        if (bytes.length == 0) {
            throw new IllegalArgumentException("empty bytes");
        }

        if (index < 0) {
            throw new IllegalArgumentException("index(" + index + ") < 0");
        }

        if (index >= bytes.length) {
            throw new IllegalArgumentException(
                    "index(" + index + ") >= bytes.length(" + bytes.length + ")");
        }

        long value = 0;

        final int end = index + LONG_BYTES;
        for (int i = index; i < end; i++) {
            value <<= Byte.SIZE;
            if (i >= bytes.length) {
                continue;
            }
            value |= (bytes[i] & 0xFF);
        }

        return value;
    }

    /**
     * Converts bytes of specified array beginning at index {@code 0} to a {@code long} value.
     *
     * @param bytes the byte array to convert.
     * @return a {@code long} value converted from {@code bytes}.
     * @throws NullPointerException     if {@code bytes} is {@code null}.
     * @throws IllegalArgumentException if {@code bytes.length} is zero.
     * @see #toLongBegins(byte[], int)
     */
    public static long toLongBegins(final byte[] bytes) {

        return toLongBegins(bytes, 0);
    }

    /**
     * Converts, in big-endian order, {@value #LONG_BYTES} bytes of specified array ending at specified index (inclusive) to a
     * {@code long} value. Bytes before the beginning of the array are treated as zeros.
     *
     * @param bytes the byte array to convert.
     * @param index the ending index in {@code bytes} between 0 (inclusive) and {@code bytes.length} (exclusive).
     * @return a {@code long} value converted from {@code bytes}.
     * @throws NullPointerException     if {@code bytes} is {@code null}.
     * @throws IllegalArgumentException if {@code bytes.length} is zero.
     * @throws IllegalArgumentException if {@code index} is less than zero, or greater than or equals to
     *                                  {@code bytes.length}.
     */
    public static long toLongEnds(final byte[] bytes, final int index) {

        if (bytes == null) {
            throw new NullPointerException("null bytes");
        }

        if (bytes.length == 0) {
            throw new IllegalArgumentException("empty bytes");
        }

        if (index < 0) {
            throw new IllegalArgumentException("index(" + index + ") < 0");
        }

        if (index >= bytes.length) {
            throw new IllegalArgumentException(
                    "index(" + index + ") >= bytes.length(" + bytes.length + ")");
        }

        long value = 0;

        for (int i = index - LONG_BYTES + 1; i <= index; i++) {
            value <<= Byte.SIZE;
            if (i < 0) {
                continue;
            }
            value |= (bytes[i] & 0xFF);
        }

        return value;
    }

    /**
     * Converts bytes of specified array ending at the last index, {@code bytes.length - 1}, to a {@code long}
     * value.
     *
     * @param bytes the byte array to convert.
     * @return a {@code long} value converted from {@code bytes}.
     * @throws NullPointerException     if {@code bytes} is {@code null}.
     * @throws IllegalArgumentException if {@code bytes.length} is zero.
     * @see #toLongEnds(byte[], int)
     */
    public static long toLongEnds(final byte[] bytes) {

        return toLongEnds(bytes, bytes.length - 1);
    }

    /**
     * Converts given {@code double} value to an array of {@value #DOUBLE_BYTES} bytes in big-endian order, using its raw long bits.
     *
     * @param value the {@code double} value to convert.
     * @return an array of {@value #DOUBLE_BYTES} bytes.
     */
    public static byte[] toBytes(final double value) {

        return toBytes(Double.doubleToRawLongBits(value));
    }

    /**
     * Converts, in big-endian order, {@value #DOUBLE_BYTES} bytes of specified array beginning at specified index to a {@code double}
     * value. Bytes beyond the end of the array are treated as zeros. The result is converted from the raw long bits.
     *
     * @param bytes the byte array to convert.
     * @param index the starting index in {@code bytes} between 0 (inclusive) and {@code bytes.length} (exclusive).
     * @return a {@code double} value converted from {@code bytes}.
     * @throws NullPointerException     if {@code bytes} is {@code null}.
     * @throws IllegalArgumentException if {@code bytes.length} is zero.
     * @throws IllegalArgumentException if {@code index} is less than zero, or greater than or equals to
     *                                  {@code bytes.length}.
     */
    public static double toDoubleBegins(final byte[] bytes, final int index) {

        return Double.longBitsToDouble(toLongBegins(bytes, index));
    }

    /**
     * Converts bytes of specified array beginning at the last index, {@code bytes.length - 1}, to a {@code double}
     * value.
     *
     * @param bytes the byte array to convert.
     * @return a {@code double} value converted from {@code bytes}.
     * @throws NullPointerException     if {@code bytes} is {@code null}.
     * @throws IllegalArgumentException if {@code bytes.length} is zero.
     * @see #toDoubleBegins(byte[], int)
     */
    public static double toDoubleBegin(final byte[] bytes) {

        return toDoubleBegins(bytes, bytes.length - 1);
    }

    /**
     * Converts, in big-endian order, {@value #DOUBLE_BYTES} bytes of specified array ending at specified index (inclusive) to a
     * {@code double} value. Bytes before the beginning of the array are treated as zeros.
     *
     * @param bytes the byte array to convert.
     * @param index the ending index in {@code bytes} between 0 (inclusive) and {@code bytes.length} (exclusive).
     * @return a {@code double} value converted from {@code bytes}.
     * @throws NullPointerException     if {@code bytes} is {@code null}.
     * @throws IllegalArgumentException if {@code bytes.length} is zero.
     * @throws IllegalArgumentException if {@code index} is less than zero, or greater than or equals to
     *                                  {@code bytes.length}.
     */
    public static double toDoubleEnds(final byte[] bytes, final int index) {

        return Double.longBitsToDouble(toLongEnds(bytes, index));
    }

    /**
     * Converts bytes of specified array ending at the last index, {@code bytes.length - 1}, to a {@code double}
     * value.
     *
     * @param bytes the byte array to convert.
     * @return a {@code double} value converted from {@code bytes}.
     * @throws NullPointerException     if {@code bytes} is {@code null}.
     * @throws IllegalArgumentException if {@code bytes.length} is zero.
     * @see #toDoubleEnds(byte[], int)
     */
    public static double toDoubleEnds(final byte[] bytes) {

        return toDoubleEnds(bytes, bytes.length - 1);
    }

    private Numbers() {
        super();
    }
}
