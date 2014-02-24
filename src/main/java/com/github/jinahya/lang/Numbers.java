/*
 * Copyright 2014 Jin Kwon <onacit at gmail.com>.
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
 * @author Jin Kwon <onacit at gmail.com>
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


    public static byte[] toBytes(short value) {

        final byte[] bytes = new byte[SHORT_BYTES];

        for (int i = bytes.length - 1; i >= 0; i--) {
            bytes[i] = (byte) (value & 0xFF);
            value >>= Byte.SIZE;
        }

        return bytes;
    }


    /**
     *
     * @param bytes the byte array to convert
     * @param limit the left most byte index; must be greater than {@code 0} and
     * less than or equals to {@code bytes.length}.
     *
     * @return
     *
     * @throws NullPointerException if {@code bytes} is {@code null}.
     * @throws IllegalArgumentException if {@code bytes} is empty
     * @throws IllegalArgumentException {@code limit} is less than or equals to
     * {@code 0} or greater than {@code bytes.length}.
     */
    public static short toShort(final byte[] bytes, final int limit) {

        if (bytes == null) {
            throw new NullPointerException("null bytes");
        }

        if (bytes.length == 0) {
            throw new IllegalArgumentException("empty bytes");
        }

        if (limit <= 0) {
            throw new IllegalArgumentException("limit(" + limit + ") <= 0");
        }

        if (limit > bytes.length) {
            throw new IllegalArgumentException(
                "limit(" + limit + ") > bytes.length(" + bytes.length + ")");
        }

        short value = 0;

        final int size = SHORT_BYTES;
        assert size == 2;
        for (int i = Math.max(limit - size, 0); i < limit; i++) {
            value <<= Byte.SIZE;
            value |= (bytes[i] & 0xFF);
        }

        return value;
    }


    public static short toShort(final byte[] bytes) {

        return toShort(bytes, bytes.length);
    }


    public static byte[] toBytes(int value) {

        final byte[] bytes = new byte[INTEGER_BYTES];
        assert bytes.length == 4;

        for (int i = bytes.length - 1; i >= 0; i--) {
            bytes[i] = (byte) (value & 0xFF);
            value >>= Byte.SIZE;
        }

        return bytes;
    }


    public static int toInt(final byte[] bytes, final int limit) {

        if (bytes == null) {
            throw new NullPointerException("null bytes");
        }

        if (bytes.length == 0) {
            throw new IllegalArgumentException("empty bytes");
        }

        if (limit <= 0) {
            throw new IllegalArgumentException("limit(" + limit + ") <= 0");
        }

        if (limit > bytes.length) {
            throw new IllegalArgumentException(
                "limit(" + limit + ") > bytes.length(" + bytes.length + ")");
        }

        int value = 0;

        final int size = INTEGER_BYTES;
        assert size == 4;
        for (int i = Math.max(limit - size, 0); i < limit; i++) {
            value <<= Byte.SIZE;
            value |= (bytes[i] & 0xFF);
        }

        return value;
    }


    public static int toInt(final byte[] bytes) {

        return toInt(bytes, bytes.length);
    }


    public static byte[] toBytes(long value) {

        final byte[] bytes = new byte[LONG_BYTES];

        for (int i = bytes.length - 1; i >= 0; i--) {
            bytes[i] = (byte) (value & 0xFF);
            value >>= Byte.SIZE;
        }

        return bytes;
    }


    public static long toLong(final byte[] bytes, final int limit) {

        if (bytes == null) {
            throw new NullPointerException("null bytes");
        }

        if (bytes.length == 0) {
            throw new IllegalArgumentException("empty bytes");
        }

        if (limit <= 0) {
            throw new IllegalArgumentException("limit(" + limit + ") <= 0");
        }

        if (limit > bytes.length) {
            throw new IllegalArgumentException(
                "limit(" + limit + ") > bytes.length(" + bytes.length + ")");
        }

        long value = 0;

        final int size = LONG_BYTES;
        assert size == 8;
        for (int i = Math.max(limit - size, 0); i < limit; i++) {
            value <<= Byte.SIZE;
            value |= (bytes[i] & 0xFF);
        }

        return value;
    }


    public static long toLong(final byte[] bytes) {

        return toLong(bytes, bytes.length);
    }


    private Numbers() {

        super();
    }


}

