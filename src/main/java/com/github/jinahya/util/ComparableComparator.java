/*
 * Copyright 2013 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Comparator;


/**
 * A {@link java.util.Comparator} implementation for
 * {@link java.lang.Comparable}s.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 * @param <T> comparable type parameter
 */
public class ComparableComparator<T extends Comparable<? super T>>
    implements Comparator<T> {


    /**
     * An enum constants for ordering {@code null} references.
     */
    public enum Nulls {

        /**
         * Constant for preceding {@code null} references.
         */
        PRECEDE_NONNULLS {

            @Override
            <T extends Comparable<? super T>> int compare(final T o1, final T o2) {
                return o1 == null ? (o2 == null ? 0 : -1) : (o2 == null ? 1 : o1.compareTo(o2));
            }

        },
        /**
         * Constant for succeeding {@code null} references.
         */
        SUCCEED_NONNULLS {

            @Override
            <T extends Comparable<? super T>> int compare(final T o1, final T o2) {
                return o1 == null ? (o2 == null ? 0 : 1) : (o2 == null ? -1 : o1.compareTo(o2));
            }

        };


        /**
         * Compares given object references.
         *
         * @param <T> object reference type parameter
         * @param o1 first object
         * @param o2 second object
         *
         * @return a negative integer, zero, or a positive integer as {@code o1}
         * is less than, equal to, or greater than {@code o2).
         */
        abstract <T extends Comparable<? super T>> int compare(T o1, T o2);


        /**
         *
         * @param <T>
         * @param <U>
         * @param comparator
         *
         * @return
         */
        @SuppressWarnings(value = "unchecked")
        <T extends ComparableComparator<U>, U extends Comparable<? super U>> T nulls(final T comparator) {
            return (T) comparator.nulls(this);
        }

    }


    public static Comparator<Byte> byteComparator(final Nulls nulls) {

        return new ComparableComparator<Byte>().nulls(nulls);
    }


    public static Comparator<Short> shortComparator(final Nulls nulls) {

        return new ComparableComparator<Short>().nulls(nulls);
    }


    public static Comparator<Integer> integerComparator(final Nulls nulls) {

        return new ComparableComparator<Integer>().nulls(nulls);
    }


    public static Comparator<Long> longComparator(final Nulls nulls) {

        return new ComparableComparator<Long>().nulls(nulls);
    }


    public static Comparator<BigInteger> bigIntegerComparator(final Nulls nulls) {

        return new ComparableComparator<BigInteger>().nulls(nulls);
    }


    public static Comparator<Float> floatComparator(final Nulls nulls) {

        return new ComparableComparator<Float>().nulls(nulls);
    }


    public static Comparator<Double> doubleComparator(final Nulls nulls) {

        return new ComparableComparator<Double>().nulls(nulls);
    }


    public static Comparator<BigDecimal> bigDecimalComparator(final Nulls nulls) {

        return new ComparableComparator<BigDecimal>().nulls(nulls);
    }


    @Override
    public int compare(final T o1, final T o2) {

        if (nulls != null) {
            return nulls.compare(o1, o2);
        }

        return o1.compareTo(o2);
    }


    /**
     * Replaces the value of {@code nulls} with given value.
     *
     * @param nulls new value of {@code nulls}
     *
     * @return this comparator
     */
    public ComparableComparator<T> nulls(final Nulls nulls) {

        this.nulls = nulls;

        return this;
    }


    /**
     * nulls.
     */
    private Nulls nulls;

}

