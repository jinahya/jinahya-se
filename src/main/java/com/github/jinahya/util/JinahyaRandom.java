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

import java.io.Serializable;
import java.util.Random;

/**
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class JinahyaRandom implements Serializable {

    /**
     * GENERATED.
     */
    private static final long serialVersionUID = 4093243768555129124L;

    /**
     * Returns a pseudorandom, uniformly distributed {@code int} value between {@code minimum} (inclusive) and
     * {@code maximum} (exclusive), drawn from the given random number generator's sequence. An instance of
     * {@code IllegalArgumentException} will thrown if the range is greater than {@value Integer#MAX_VALUE}.
     *
     * @param random  random
     * @param minimum the lower bound on the random number to returned
     * @param maximum the upper bound on the random number to returned; must be greater than or equal to
     *                {@code minimum}.
     * @return the next pseudorandom, uniformly distributed {@code int} value between {@code minimum} (inclusive) and
     * {@code maximum} (exclusive) from the wrapped random number generator's sequence.
     */
    public static int nextInt(final Random random, final int minimum,
                              final int maximum) {

        if (random == null) {
            throw new NullPointerException("random");
        }

        if (maximum < minimum) {
            throw new IllegalArgumentException(
                    "maximum(" + maximum + ") < minimum(" + minimum + ")");
        }

        final long range = (long) maximum - (long) minimum;
        if (range > (long) Integer.MAX_VALUE) {
            throw new IllegalArgumentException(
                    "maximum(" + maximum + ") - minimum(" + minimum + ") = "
                    + range + " > " + Integer.MAX_VALUE);
        }

        if (maximum == minimum) {
            return minimum;
        }

        return random.nextInt(maximum - minimum) + minimum;
    }

    /**
     * Generates a random byte array. The length of result byte array is between {@code minimumLength} (inclusive) and
     * {@code maximumLength} (exclusive).
     *
     * @param random        random
     * @param minimumLength minimum array length; must be greater than or equal to 0.
     * @param maximumLength maximum array length; must be greater than or equal to {@code minimumLength}.
     * @return the generated byte array
     */
    public static byte[] nextBytes(final Random random, final int minimumLength,
                                   final int maximumLength) {

        if (random == null) {
            throw new NullPointerException("random");
        }

        if (minimumLength < 0) {
            throw new IllegalArgumentException(
                    "minimumLength(" + minimumLength + ") < 0");
        }

        if (maximumLength < minimumLength) {
            throw new IllegalArgumentException(
                    "maximumLength(" + maximumLength + ") < minimumLength("
                    + minimumLength + ")");
        }

        final int length = nextInt(random, minimumLength, maximumLength);

        final byte[] bytes = new byte[length];

        random.nextBytes(bytes);

        return bytes;
    }

    /**
     * Creates a new instance.
     *
     * @param random the random to be wrapped.
     */
    public JinahyaRandom(final Random random) {

        super();

        if (random == null) {
            throw new NullPointerException("random");
        }

        this.random = random;
    }

    /**
     * Returns a pseudorandom, uniformly distributed {@code int} value between {@code minimum} (inclusive) and
     * {@code maximum} (exclusive), drawn from the wrapped random number generator's sequence. An instance of
     * {@code IllegalArgumentException} will thrown if the range is greater than {@value Integer#MAX_VALUE}.
     *
     * @param minimum the lower bound on the random number to returned
     * @param maximum the upper bound on the random number to returned; must be greater than or equal to
     *                {@code minimum}.
     * @return the next pseudorandom, uniformly distributed {@code int} value between {@code minimum} (inclusive) and
     * {@code maximum} (exclusive) from the wrapped random number generator's sequence.
     */
    protected int nextInt(final int minimum, final int maximum) {

        if (maximum < minimum) {
            throw new IllegalArgumentException(
                    "maximum(" + maximum + ") < minimum(" + minimum + ")");
        }

        final long range = (long) maximum - (long) minimum;
        if (range > (long) Integer.MAX_VALUE) {
            throw new IllegalArgumentException(
                    "range(" + range + ") > " + Integer.MAX_VALUE);
        }

        if (maximum == minimum) {
            return minimum;
        }

        return random.nextInt(maximum - minimum) + minimum;
    }

    /**
     * Generates a random byte array. The length of result byte array is between {@code minimumLength} (inclusive) and
     * {@code maximumLength} (exclusive).
     *
     * @param minimumLength minimum array length; must be greater than or equal to 0.
     * @param maximumLength maximum array length; must be greater than or equal to {@code minimumLength}.
     * @return the generated byte array
     */
    public byte[] nextBytes(final int minimumLength, final int maximumLength) {

        if (minimumLength < 0) {
            throw new IllegalArgumentException(
                    "minimumLength(" + minimumLength + ") < 0");
        }

        if (maximumLength < minimumLength) {
            throw new IllegalArgumentException(
                    "maximumLength(" + maximumLength + ") < minimumLength("
                    + minimumLength + ")");
        }

        final byte[] bytes = new byte[nextInt(minimumLength, maximumLength)];

        random.nextBytes(bytes);

        return bytes;
    }

    /**
     * Generates an unsigned integer in arbitrary bit length.
     * {@code 1 &lt;= minimumBitLength &lt;= maximumBitLength &lt;= 32}.
     *
     * @param minimumBitLength the minimum number of bits; must be greater than or equal to 1.
     * @param maximumBitLength the maximum number of bits; must be between {@code minimumBitLength} (inclusive) and
     *                         {@value java.lang.Integer#SIZE} (exclusive).
     * @return the generated value.
     */
    public int nextUnsignedInt(final int minimumBitLength,
                               final int maximumBitLength) {

        if (minimumBitLength < 1) {
            throw new IllegalArgumentException(
                    "minimumBitLength(" + minimumBitLength + ") < 1");
        }

        if (maximumBitLength < minimumBitLength) {
            throw new IllegalArgumentException(
                    "maximumBitLength(" + maximumBitLength
                    + ") < minimumBitLength(" + minimumBitLength + ")");
        }

        if (maximumBitLength >= Integer.SIZE) {
            throw new IllegalArgumentException(
                    "maximumBitLength(" + maximumBitLength + ") >= "
                    + Integer.SIZE);
        }

        final int bitLength = nextInt(minimumBitLength, maximumBitLength);

        int value = random.nextInt();

        if (value < 0) {
            value >>>= (Integer.SIZE - bitLength);
        } else {
            value >>= (Integer.SIZE - bitLength - 1);
        }

        return value;
    }

    /**
     * Generates a signed integer in arbitrary bit length.
     * {@code 1 &lt;= minimumBitLength &lt;= maximumBitLength &lt; 32}.
     *
     * @param minimumBitLength the minimum number of bits; must be greater than 1.
     * @param maximumBitLength the maximum number of bits; must be between {@code minimumBitLength} (inclusive) and
     *                         {@value java.lang.Integer#SIZE} (inclusive).
     * @return the generated value.
     */
    public int nextSignedInt(final int minimumBitLength,
                             final int maximumBitLength) {

        if (minimumBitLength <= 1) {
            throw new IllegalArgumentException(
                    "minimumBitLength(" + minimumBitLength + ") <= 1");
        }

        if (maximumBitLength < minimumBitLength) {
            throw new IllegalArgumentException(
                    "maximumBitLength(" + maximumBitLength
                    + ") < miinimumBitLength(" + minimumBitLength + ")");
        }

        if (maximumBitLength > Integer.SIZE) {
            throw new IllegalArgumentException(
                    "maximumBitLength(" + maximumBitLength + ") > " + Integer.SIZE);
        }

        final int bitLength = nextInt(minimumBitLength, maximumBitLength);

        int nextInt = random.nextInt();

        if (nextInt > 0L) {
            nextInt >>= (Integer.SIZE - bitLength - 1);
        } else {
            nextInt >>= (Integer.SIZE - bitLength);
        }

        return nextInt;
    }

    /**
     * Generates an unsigned long in arbitrary bits. {@code 1 &lt;= minimumBitLength &lt;= maximumBitLength &lt; 64}.
     *
     * @param minimumBitLength the minimum number of bits; must be greater than or equal to 1.
     * @param maximumBitLength the maximum number of bits; must be between {@code minimumBitLength} (inclusive) and
     *                         {@value java.lang.Long#SIZE} (exclusive).
     * @return the generated value.
     */
    public long nextUnsignedLong(final int minimumBitLength,
                                 final int maximumBitLength) {

        if (minimumBitLength < 1) {
            throw new IllegalArgumentException(
                    "minimumBitLength(" + minimumBitLength + ") < 1");
        }

        if (maximumBitLength < minimumBitLength) {
            throw new IllegalArgumentException(
                    "maximumBitLength(" + maximumBitLength + ") < minimumBitLength("
                    + minimumBitLength + ")");
        }

        if (maximumBitLength >= Long.SIZE) {
            throw new IllegalArgumentException(
                    "maximumBitLength(" + maximumBitLength + ") >= " + Long.SIZE);
        }

        final int bitLength = nextInt(minimumBitLength, maximumBitLength);

        long value = random.nextLong();

        if (value < 0L) {
            value >>>= (Long.SIZE - bitLength);
        } else {
            value >>= (Long.SIZE - bitLength - 1);
        }

        return value;
    }

    /**
     * Generates a signed long in arbitrary bit length.
     * {@code 1 &lt; minimumBitLength &lt;= maximumBitLength &lt;= 64}.
     *
     * @param minimumBitLength the minimum number of bits; must be greater than 1.
     * @param maximumBitLength the maximum number of bits; must be between {@code minimumBitLength} (inclusive) and
     *                         {@value java.lang.Long#SIZE} (inclusive).
     * @return generated value.
     */
    public long nextSignedLong(final int minimumBitLength,
                               final int maximumBitLength) {

        if (minimumBitLength <= 1) {
            throw new IllegalArgumentException(
                    "minimumBitLength(" + minimumBitLength + ") <= 1");
        }

        if (maximumBitLength < minimumBitLength) {
            throw new IllegalArgumentException(
                    "maximumBitLength(" + maximumBitLength
                    + ") < minimumBitLength(" + minimumBitLength + ")");
        }

        if (maximumBitLength > Long.SIZE) {
            throw new IllegalArgumentException(
                    "maximumBitLength(" + maximumBitLength + ") > " + Long.SIZE);
        }

        final int bitLength = nextInt(minimumBitLength, maximumBitLength);

        long value = random.nextLong();

        if (value > 0L) {
            value >>= (Long.SIZE - bitLength - 1);
        } else {
            value >>= (Long.SIZE - bitLength);
        }

        return value;
    }

    /**
     * wrapped random.
     */
    private final Random random;
}
