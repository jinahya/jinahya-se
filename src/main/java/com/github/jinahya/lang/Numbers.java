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


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class Numbers {


    /**
     * The number of bytes used to represent a {@code byte} value in two's
     * complement binary form.
     */
    public static final int BYTE_BYTES = Byte.SIZE / Byte.SIZE;


    /**
     * The number of bytes used to represent a {@code short} value in two's
     * complement binary form.
     */
    public static final int SHORT_BYTES = Short.SIZE / Byte.SIZE;


    /**
     * The number of bytes used to represent a {@code int} value in two's
     * complement binary form.
     */
    public static final int INTEGER_BYTES = Integer.SIZE / Byte.SIZE;


    /**
     * The number of bytes used to represent a {@code long} value in two's
     * complement binary form.
     */
    public static final int LONG_BYTES = Long.SIZE / Byte.SIZE;


    /**
     * The number of bytes used to represent a {@code float} value.
     */
    public static final int FLOAT_BYTES = Float.SIZE / Byte.SIZE;


    /**
     * The number of bytes used to represent a {@code double} value.
     */
    public static final int DOUBLE_BYTES = Double.SIZE / Byte.SIZE;


    /**
     *
     * @param value
     *
     * @return
     */
    public static byte[] toBytes(short value) {

        final byte[] bytes = new byte[SHORT_BYTES];

        for (int i = bytes.length - 1; i >= 0; i--) {
            bytes[i] = (byte) (value & 0xFF);
            value >>= Byte.SIZE;
        }

        return bytes;
    }


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


    public static short toShortBegins(final byte[] bytes) {

        return toShortBegins(bytes, 0);
    }


    /**
     *
     * @param bytes the byte array to convert
     * @param index
     *
     * @return
     *
     * @throws NullPointerException if {@code bytes} is {@code null}.
     * @throws IllegalArgumentException if {@code bytes.length} is zero.
     * @throws IllegalArgumentException {@code limit} is less than zero, or
     * greater than or equals to {@code bytes.length}.
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
     *
     * @param bytes
     *
     * @return
     */
    public static short toShortEnds(final byte[] bytes) {

        return toShortEnds(bytes, bytes.length - 1);
    }


    /**
     * Converts given int value to an array of {@value #INTEGER_BYTES} bytes.
     *
     * @param value the int value to convert.
     *
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
     *
     * @param bytes
     * @param index the starting index in {@code bytes} between 0 (inclusive)
     * and {@code bytes.length} (exclusive).
     *
     * @return
     *
     * @throws NullPointerException if {@code bytes} is {@code null}.
     * @throws IllegalArgumentException if {@code bytes.length} is zero.
     * @throws IllegalArgumentException if {@code position} is less than zero,
     * or greater than or equals to {@code bytes.length}.
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


    public static int toIntBegins(final byte[] bytes) {

        return toIntBegins(bytes, 0);
    }


    /**
     *
     * @param bytes
     * @param index
     *
     * @return
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


    public static int toIntEnds(final byte[] bytes) {

        return toIntEnds(bytes, bytes.length - 1);
    }


    public static byte[] toBytes(final float value) {

        return toBytes(Float.floatToRawIntBits(value));
    }


    public static float toFloatBegins(final byte[] bytes, final int index) {

        return Float.intBitsToFloat(toIntBegins(bytes, index));
    }


    public static float toFloatBegins(final byte[] bytes) {

        return toFloatBegins(bytes, bytes.length - 1);
    }


    public static float toFloatEnds(final byte[] bytes, final int index) {

        return Float.intBitsToFloat(toIntEnds(bytes, index));
    }


    public static float toFloatEnds(final byte[] bytes) {

        return toFloatEnds(bytes, bytes.length - 1);
    }


    public static byte[] toBytes(long value) {

        final byte[] bytes = new byte[LONG_BYTES];

        for (int i = bytes.length - 1; i >= 0; i--) {
            bytes[i] = (byte) (value & 0xFF);
            value >>= Byte.SIZE;
        }

        return bytes;
    }


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


    public static long toLongBegins(final byte[] bytes) {

        return toLongBegins(bytes, 0);
    }


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


    public static long toLongEnds(final byte[] bytes) {

        return toLongEnds(bytes, bytes.length - 1);
    }


    public static byte[] toBytes(final double value) {

        return toBytes(Double.doubleToRawLongBits(value));
    }


    public static double toDoubleBegins(final byte[] bytes, final int index) {

        return Double.longBitsToDouble(toLongBegins(bytes, index));
    }


    public static double toDoubleBegin(final byte[] bytes) {

        return toDoubleBegins(bytes, bytes.length - 1);
    }


    public static double toDoubleEnds(final byte[] bytes, final int index) {

        return Double.longBitsToDouble(toLongEnds(bytes, index));
    }


    public static double toDoubleEnds(final byte[] bytes) {

        return toDoubleEnds(bytes, bytes.length - 1);
    }


    private Numbers() {

        super();
    }


}

