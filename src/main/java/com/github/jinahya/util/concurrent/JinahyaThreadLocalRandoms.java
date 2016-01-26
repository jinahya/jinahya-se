/*
 * Copyright 2015 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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


package com.github.jinahya.util.concurrent;


import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class JinahyaThreadLocalRandoms {


    public static DoubleStream floats() {
        return ThreadLocalRandom.current()
            .doubles()
            .map(d -> (float) d);
    }


    public static DoubleStream floats(final float randomNumberOrigin,
                                      final float randomNumberBound) {

        return ThreadLocalRandom.current()
            .doubles(randomNumberOrigin, randomNumberBound)
            .map(d -> (float) d);
    }


    public static DoubleStream floats(final long streamSize) {

        return ThreadLocalRandom.current()
            .doubles(streamSize)
            .map(d -> (float) d);
    }


    public static DoubleStream floats(final long streamSize,
                                      final float randomNumberOrigin,
                                      final float randomNumberBound) {

        return ThreadLocalRandom.current()
            .doubles(streamSize, randomNumberOrigin, randomNumberBound)
            .map(d -> (float) d);
    }


    /**
     *
     * @return a stream of pseudorandom byte values
     *
     * @see ThreadLocalRandom#ints()
     */
    public static IntStream bytes() {

        return ThreadLocalRandom.current()
            .ints()
            .map(i -> (byte) i);
    }


    /**
     *
     * @param randomNumberOrigin
     * @param randomNumberBound
     *
     * @return
     *
     * @see ThreadLocalRandom#ints(int, int)
     */
    public static IntStream bytes(final byte randomNumberOrigin,
                                  final byte randomNumberBound) {

        return ThreadLocalRandom.current()
            .ints(randomNumberOrigin, randomNumberBound)
            .map(i -> (byte) i);
    }


    public static IntStream bytes(final long streamSize) {

        return ThreadLocalRandom.current()
            .ints(streamSize)
            .map(i -> (byte) i);
    }


    public static IntStream bytes(final long streamSize,
                                  final byte randomNumberOrigin,
                                  final byte randomNumberBound) {

        return ThreadLocalRandom.current()
            .ints(streamSize, randomNumberOrigin, randomNumberBound)
            .map(i -> (byte) i);
    }


    private JinahyaThreadLocalRandoms() {
        super();
    }


}

