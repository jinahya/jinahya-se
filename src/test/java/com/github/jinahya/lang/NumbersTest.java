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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class NumbersTest {

    private static final Logger logger
            = LoggerFactory.getLogger(NumbersTest.class);

    private static short newShort() {

        return (short) (ThreadLocalRandom.current().nextInt() & 0xFFFF);
    }

    private static int newInt() {

        return ThreadLocalRandom.current().nextInt();
    }

    private static float newFloat() {

        final float value = 2.f * ThreadLocalRandom.current().nextFloat() - 1.f;
        return value * (value < .0f ? Float.MIN_VALUE : Float.MAX_VALUE);
    }

    private static long newLong() {

        return ThreadLocalRandom.current().nextLong();
    }

    private static double newDouble() {

        final double value = 2d * ThreadLocalRandom.current().nextDouble() - 1d;
        return value * (value < .0d ? Double.MIN_VALUE : Double.MAX_VALUE);
    }

    private static String hex(final byte[] bytes) {

        final StringBuilder builder = new StringBuilder(bytes.length * 2);

        for (final byte b : bytes) {
            builder.append(String.format("%1$02x", b));
        }

        return builder.toString();
    }

    private static void copyBegins(final byte[] src, final int index,
                                   final byte[] dest) {

        final int srcPos = index;
        final int destPos = 0;
        final int length = Math.min(dest.length, src.length - index);
        System.arraycopy(src, srcPos, dest, destPos, length);
    }

    private static void copyEnds(final byte[] src, final int index,
                                 final byte[] dest) {

        final int srcPos = Math.max(index - dest.length + 1, 0);
        final int destPos = Math.max(dest.length - index - 1, 0);
        final int length = Math.min(dest.length, index + 1);
        System.arraycopy(src, srcPos, dest, destPos, length);
    }

    @Test(invocationCount = 1)
    public static void toBytes_short_() {

        final short value = newShort();
        final byte[] bytes = Numbers.toBytes(value);

        final String actual = hex(bytes);
        final String expected = String.format("%1$04x", value);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public static void toShortBegins_() {

        final byte[] bytes = new byte[128];
        ThreadLocalRandom.current().nextBytes(bytes);

        for (int index = 0; index < bytes.length; index++) {
            final short value = Numbers.toShortBegins(bytes, index);
            final byte[] actual = Numbers.toBytes(value);
            final byte[] expected = new byte[Numbers.SHORT_BYTES];
            copyBegins(bytes, index, expected);
            Assert.assertEquals(actual, expected);
        }
    }

    @Test
    public static void toShortEnds_() {

        final byte[] bytes = new byte[128];
        ThreadLocalRandom.current().nextBytes(bytes);

        for (int index = 0; index < bytes.length; index++) {
            final short value = Numbers.toShortEnds(bytes, index);
            final byte[] actual = Numbers.toBytes(value);
            final byte[] expected = new byte[Numbers.SHORT_BYTES];
            copyEnds(bytes, index, expected);
            Assert.assertEquals(actual, expected);
        }
    }

    @Test(invocationCount = 1)
    public static void toBytes_int_() {

        final int value = newInt();
        final byte[] bytes = Numbers.toBytes(value);

        final String actual = hex(bytes);
        final String expected = String.format("%1$08x", value);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public static void toIntBegins_() {

        final byte[] bytes = new byte[128];
        ThreadLocalRandom.current().nextBytes(bytes);

        for (int index = 0; index < bytes.length; index++) {
            final int value = Numbers.toIntBegins(bytes, index);
            final byte[] actual = Numbers.toBytes(value);
            final byte[] expected = new byte[Numbers.INTEGER_BYTES];
            copyBegins(bytes, index, expected);
            Assert.assertEquals(actual, expected);
        }
    }

    @Test
    public static void toIntEnds_() {

        final byte[] bytes = new byte[128];
        ThreadLocalRandom.current().nextBytes(bytes);

        for (int index = 0; index < bytes.length; index++) {
            final int value = Numbers.toIntEnds(bytes, index);
            final byte[] actual = Numbers.toBytes(value);
            final byte[] expected = new byte[Numbers.INTEGER_BYTES];
            copyEnds(bytes, index, expected);
            Assert.assertEquals(actual, expected);
        }
    }

    @Test(enabled = true, invocationCount = 1)
    public static void toBytes_float_() {

        final float value = newFloat();
        final byte[] bytes = Numbers.toBytes(value);

        final String actual = hex(bytes);
        final String expected
                = String.format("%1$08x", Float.floatToRawIntBits(value));
        Assert.assertEquals(actual, expected);
    }

    @Test(enabled = true)
    public static void toFloat_() {

        final byte[] bytes = new byte[128];
        ThreadLocalRandom.current().nextBytes(bytes);

        for (int index = 0; index < bytes.length; index++) {
            final float value = Numbers.toFloatEnds(bytes, index);
            final byte[] actual = Numbers.toBytes(value);
            final byte[] expected = new byte[Numbers.FLOAT_BYTES];
            final int srcPos = Math.max(index - expected.length + 1, 0);
            final int destPos = Math.max(expected.length - index - 1, 0);
            final int length = Math.min(expected.length, index + 1);
            System.arraycopy(bytes, srcPos, expected, destPos, length);
            Assert.assertEquals(actual, expected);
        }
    }

    @Test(invocationCount = 1)
    public static void toBytes_long_() {

        final long value = newLong();
        final byte[] bytes = Numbers.toBytes(value);
        Assert.assertEquals(bytes.length, Numbers.LONG_BYTES);

        final String actual = hex(bytes);
        final String expected = String.format("%1$016x", value);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public static void toLongBegins_() {

        final byte[] bytes = new byte[128];
        ThreadLocalRandom.current().nextBytes(bytes);

        for (int index = 0; index < bytes.length; index++) {
            final long value = Numbers.toLongBegins(bytes, index);
            final byte[] actual = Numbers.toBytes(value);
            final byte[] expected = new byte[Numbers.LONG_BYTES];
            copyBegins(bytes, index, expected);
            Assert.assertEquals(actual, expected);
        }
    }

    @Test
    public static void toLongEnds_() {

        final byte[] bytes = new byte[128];
        ThreadLocalRandom.current().nextBytes(bytes);

        for (int index = 0; index < bytes.length; index++) {
            final long value = Numbers.toLongEnds(bytes, index);
            final byte[] actual = Numbers.toBytes(value);
            final byte[] expected = new byte[Numbers.LONG_BYTES];
            copyEnds(bytes, index, expected);
            Assert.assertEquals(actual, expected);
        }
    }

    @Test(invocationCount = 1)
    public static void toBytes_double_() {

        final double value = newDouble();
        final byte[] bytes = Numbers.toBytes(value);
        Assert.assertEquals(bytes.length, Numbers.DOUBLE_BYTES);

        final String actual = hex(bytes);
        final String expected
                = String.format("%1$016x", Double.doubleToRawLongBits(value));
        Assert.assertEquals(actual, expected);
    }

    @Test
    public static void toDoubleBegins_() {

        final byte[] bytes = new byte[128];
        ThreadLocalRandom.current().nextBytes(bytes);

        for (int index = 0; index < bytes.length; index++) {
            final double value = Numbers.toDoubleBegins(bytes, index);
            final byte[] actual = Numbers.toBytes(value);
            final byte[] expected = new byte[Numbers.DOUBLE_BYTES];
            copyBegins(bytes, index, expected);
            Assert.assertEquals(actual, expected);
        }
    }

    @Test
    public static void toDoubleEnds_() {

        final byte[] bytes = new byte[128];
        ThreadLocalRandom.current().nextBytes(bytes);

        for (int index = 0; index < bytes.length; index++) {
            final double value = Numbers.toDoubleEnds(bytes, index);
            final byte[] actual = Numbers.toBytes(value);
            final byte[] expected = new byte[Numbers.DOUBLE_BYTES];
            copyEnds(bytes, index, expected);
            Assert.assertEquals(actual, expected);
        }
    }
}
