/*
 * Copyright 2011 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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
package com.github.jinahya.util;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class JinahyaRandomTest {

    private static final Logger LOGGER
            = LoggerFactory.getLogger(JinahyaRandomTest.class);

    private static void testNextIntMinMax(final JinahyaRandom random,
                                          final int minimum,
                                          final int maximum) {

        LOGGER.debug("testNextIntMinMax({}, {}, {})", random, minimum, maximum);

        boolean min = false;
        boolean max = false;
        while (!min || !max) {
            final int nextInt = random.nextInt(minimum, maximum);
            if (!min && nextInt == minimum) {
                LOGGER.debug("minimum generated: {}", nextInt);
                min = true;
            }
            if (!max && nextInt == maximum - 1) {
                LOGGER.debug("maximum generated: {}", nextInt);
                max = true;
            }
        }
    }

    private static void testNextBytes(final JinahyaRandom random,
                                      final int minimumLength,
                                      final int maximumLength) {

        final byte[] bytes = random.nextBytes(minimumLength, maximumLength);

        assertTrue(bytes.length >= minimumLength);
        assertTrue(bytes.length < maximumLength);
    }

    @Test
    public void testConstructor() throws NoSuchAlgorithmException {

        try {
            new JinahyaRandom(null);
            fail("passed: JinahyaRandom(null)");
        } catch (NullPointerException npe) {
            // expected
        }

        JinahyaRandom random;
        random = new JinahyaRandom(new Random());
        random = new JinahyaRandom(new SecureRandom());
        random = new JinahyaRandom(ThreadLocalRandom.current());
    }

    @Test
    public void testNextIntWithToWideRange() {

        final JinahyaRandom random
                = new JinahyaRandom(ThreadLocalRandom.current());

        random.nextInt(Integer.MIN_VALUE, -1); // ok

        try {
            random.nextInt(Integer.MIN_VALUE, 0);
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            random.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
        } catch (IllegalArgumentException iae) {
            // expected
        }

        random.nextInt(0, Integer.MAX_VALUE);

        try {
            random.nextInt(-1, Integer.MAX_VALUE);
        } catch (IllegalArgumentException iae) {
            // expected
        }
    }

    @Test
    public void testNextInt() {

        final JinahyaRandom random
                = new JinahyaRandom(ThreadLocalRandom.current());

        try {
            random.nextInt(0, -1);
            fail("passed: nextInt(0, -1)");
        } catch (IllegalArgumentException iae) {
        }

        random.nextInt(Integer.MIN_VALUE, Integer.MIN_VALUE);
        random.nextInt(-1, -1);
        random.nextInt(0, 0);
        random.nextInt(1, 1);
        random.nextInt(Integer.MAX_VALUE, Integer.MAX_VALUE);

        final int length
                = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);

        random.nextInt(length, length);

        testNextIntMinMax(random, Integer.MIN_VALUE, Integer.MIN_VALUE + 200);
        testNextIntMinMax(random, -100, 100);
        testNextIntMinMax(random, Integer.MAX_VALUE - 200, Integer.MAX_VALUE);
    }

    @Test
    public void testNextUnsignedIntWithWrongArguments() {

        final JinahyaRandom random
                = new JinahyaRandom(ThreadLocalRandom.current());

        try {
            random.nextUnsignedInt(0, 1);
            fail("passed: nextUnsignedInt(0, 0)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            random.nextUnsignedInt(1, 0);
            fail("passed: nextUnsignedInt(1, 0)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            random.nextUnsignedInt(1, Integer.SIZE);
            fail("passed: nextUnsignedInt(1, Integer.SIZE");
        } catch (IllegalArgumentException iae) {
            // expected
        }
    }

    @Test
    public void testNextUnsignedInt() {

        final JinahyaRandom random
                = new JinahyaRandom(ThreadLocalRandom.current());

        random.nextUnsignedInt(1, 1);
        random.nextUnsignedInt(1, Integer.SIZE - 1);
        random.nextUnsignedInt(Integer.SIZE - 1, Integer.SIZE - 1);

        final int minimumBitLength
                = ThreadLocalRandom.current().nextInt(31) + 1;
        assertTrue(minimumBitLength > 0);
        assertTrue(minimumBitLength < Integer.SIZE);

        final int maximumBitLength
                = ThreadLocalRandom.current().nextInt(
                Integer.SIZE - minimumBitLength) + minimumBitLength;
        assertTrue(maximumBitLength >= minimumBitLength);
        assertTrue(maximumBitLength < Integer.SIZE);

        final int nextUnsignedInt
                = random.nextUnsignedInt(minimumBitLength, maximumBitLength);
        assertTrue(nextUnsignedInt >= 0);
        assertTrue(nextUnsignedInt >> maximumBitLength == 0);
    }

    @Test
    public void testNextSignedIntWithWrongArguments() {

        final JinahyaRandom random
                = new JinahyaRandom(ThreadLocalRandom.current());

        try {
            random.nextSignedInt(1, 1);
            fail("passed: nextSignedInt(1, 1)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            random.nextSignedInt(2, 1);
            fail("passed: nextSignedInt(2, 1)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            random.nextSignedInt(2, Integer.SIZE + 1);
            fail("passed: nextSignedInt(2, Integer.SIZE + 1)");
        } catch (IllegalArgumentException iae) {
            // expected
        }
    }

    @Test
    public void testNextSignedInt() {

        final JinahyaRandom random
                = new JinahyaRandom(ThreadLocalRandom.current());

        random.nextSignedInt(2, 2);
        random.nextSignedInt(2, Integer.SIZE);
        random.nextSignedInt(Integer.SIZE, Integer.SIZE);

        final int minimumBitLength
                = ThreadLocalRandom.current().nextInt(Integer.SIZE - 1) + 2;
        assertTrue(minimumBitLength > 1);
        assertTrue(minimumBitLength <= Integer.SIZE);

        final int maximumBitLength
                = ThreadLocalRandom.current().nextInt(
                Integer.SIZE + 1 - minimumBitLength) + minimumBitLength;
        assertTrue(maximumBitLength >= minimumBitLength);
        assertTrue(maximumBitLength <= Integer.SIZE);

        final int signedInt
                = random.nextSignedInt(minimumBitLength, maximumBitLength);
        if (maximumBitLength < Integer.SIZE) {
            if (signedInt >= 0) {
                assertTrue(signedInt >> maximumBitLength == 0);
            } else {
                assertTrue(signedInt >> maximumBitLength == -1);
            }
        }
    }

    @Test
    public void testNextSignedLongWithWrongArguments() {

        final JinahyaRandom random
                = new JinahyaRandom(ThreadLocalRandom.current());

        try {
            random.nextSignedLong(1, 1);
            fail("passed: nextSignedLong(1, 1)");
        } catch (IllegalArgumentException iae) {
        }

        try {
            random.nextSignedLong(2, 1);
            fail("passed: nextSignedLong(2, 1)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            random.nextSignedLong(2, Long.SIZE + 1);
            fail("passed: nextSignedLong(2, Long.SIZE + 1)");
        } catch (IllegalArgumentException iae) {
            // expected
        }
    }

    @Test
    public void testNextSignedLong() {

        final JinahyaRandom random
                = new JinahyaRandom(ThreadLocalRandom.current());

        random.nextSignedLong(2, 2);
        random.nextSignedLong(2, Long.SIZE);
        random.nextSignedLong(Long.SIZE, Long.SIZE);

        final int minimumBitLength
                = ThreadLocalRandom.current().nextInt(Long.SIZE - 1) + 2;
        assertTrue(minimumBitLength > 1);
        assertTrue(minimumBitLength <= Long.SIZE);

        final int maximumBitLength
                = ThreadLocalRandom.current().nextInt(
                Long.SIZE + 1 - minimumBitLength) + minimumBitLength;
        assertTrue(maximumBitLength >= minimumBitLength);
        assertTrue(maximumBitLength <= Long.SIZE);

        final long signedLong
                = random.nextSignedLong(minimumBitLength, maximumBitLength);
        if (maximumBitLength < Long.SIZE) {
            if (signedLong >= 0L) {
                assertTrue(signedLong >> maximumBitLength == 0L);
            } else {
                assertTrue(signedLong >> maximumBitLength == -1L);
            }
        }
    }

    @Test
    public void testNextUnsignedLongWithWrongArguments() {

        final JinahyaRandom random
                = new JinahyaRandom(ThreadLocalRandom.current());

        try {
            random.nextUnsignedLong(0, 0);
            fail("passed: nextUnsignedLong(0, 0)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            random.nextUnsignedLong(1, 0);
            fail("passed: nextUnsignedLong(1, 0)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            random.nextUnsignedLong(1, Long.SIZE);
            fail("passed: nextUnsignedLong(0, Long.SIZE");
        } catch (IllegalArgumentException iae) {
            // expected
        }
    }

    @Test
    public void testNextUnsignedLong() {

        final JinahyaRandom random
                = new JinahyaRandom(ThreadLocalRandom.current());

        random.nextUnsignedLong(1, 1);
        random.nextUnsignedLong(1, Long.SIZE - 1);
        random.nextUnsignedLong(Long.SIZE - 1, Long.SIZE - 1);

        final int minimumBitLength
                = ThreadLocalRandom.current().nextInt(Long.SIZE - 1) + 1;
        assertTrue(minimumBitLength >= 1);
        assertTrue(minimumBitLength < Long.SIZE);

        final int maximumBitLength
                = ThreadLocalRandom.current().nextInt(Long.SIZE - minimumBitLength)
                  + minimumBitLength;
        assertTrue(maximumBitLength >= minimumBitLength);
        assertTrue(maximumBitLength < Long.SIZE);

        final long unsignedLong
                = random.nextUnsignedLong(minimumBitLength, maximumBitLength);

        assertTrue(unsignedLong >= 0L);
        assertTrue(unsignedLong >> maximumBitLength == 0L);
    }
}
