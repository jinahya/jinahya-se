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


import java.util.concurrent.ThreadLocalRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
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


    private static long newLong() {

        return ThreadLocalRandom.current().nextLong();
    }


    private static String hex(final byte[] bytes) {

        final StringBuilder builder = new StringBuilder(bytes.length * 2);

        for (final byte b : bytes) {
            builder.append(String.format("%1$02x", b));
        }

        return builder.toString();
    }


    @Test(invocationCount = 128)
    public static void toBytes_short_() {

        final short value = newShort();
        final byte[] bytes = Numbers.toBytes(value);

        final String actual = hex(bytes);
        final String expected = String.format("%1$04x", value);
        Assert.assertEquals(actual, expected);
    }


    @Test
    public static void toShort_() {

        final byte[] bytes = new byte[128];
        ThreadLocalRandom.current().nextBytes(bytes);

        for (int limit = 1; limit <= bytes.length; limit++) {
            final short value = Numbers.toShort(bytes, limit);
            final byte[] actual = Numbers.toBytes(value);
            final byte[] expected = new byte[Numbers.SHORT_BYTES];
            final int srcPos = Math.max(limit - expected.length, 0);
            final int destPos = Math.max(expected.length - limit, 0);
            final int length = Math.min(expected.length, limit);
            System.arraycopy(bytes, srcPos, expected, destPos, length);
            Assert.assertEquals(actual, expected);
        }
    }


    @Test(invocationCount = 128)
    public static void toBytes_int_() {

        final int value = newInt();
        final byte[] bytes = Numbers.toBytes(value);

        final String actual = hex(bytes);
        final String expected = String.format("%1$08x", value);
        Assert.assertEquals(actual, expected);
    }


    @Test
    public static void toInt_() {

        final byte[] bytes = new byte[128];
        ThreadLocalRandom.current().nextBytes(bytes);

        for (int limit = 1; limit <= bytes.length; limit++) {
            final int value = Numbers.toInt(bytes, limit);
            final byte[] actual = Numbers.toBytes(value);
            final byte[] expected = new byte[Integer.SIZE / Byte.SIZE];
            final int srcPos = Math.max(limit - expected.length, 0);
            final int destPos = Math.max(expected.length - limit, 0);
            final int length = Math.min(expected.length, limit);
            System.arraycopy(bytes, srcPos, expected, destPos, length);
            Assert.assertEquals(actual, expected);
        }
    }


    @Test(invocationCount = 128)
    public static void toBytes_long_() {

        final long value = newLong();
        final byte[] bytes = Numbers.toBytes(value);
        Assert.assertEquals(bytes.length, Numbers.LONG_BYTES);

        final String actual = hex(bytes);
        final String expected = String.format("%1$016x", value);
        Assert.assertEquals(actual, expected);
    }


    @Test
    public static void toLong_() {

        final byte[] bytes = new byte[128];
        ThreadLocalRandom.current().nextBytes(bytes);

        for (int limit = 1; limit <= bytes.length; limit++) {
            final long value = Numbers.toLong(bytes, limit);
            final byte[] actual = Numbers.toBytes(value);
            final byte[] expected = new byte[Numbers.LONG_BYTES];
            logger.debug("actual: {}", actual);
            final int srcPos = Math.max(limit - expected.length, 0);
            final int destPos = Math.max(expected.length - limit, 0);
            final int length = Math.min(expected.length, limit);
            System.arraycopy(bytes, srcPos, expected, destPos, length);
            logger.debug("expected: {}", expected);
            Assert.assertEquals(actual, expected);
        }
    }


}

