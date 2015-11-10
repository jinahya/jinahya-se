/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
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


import java.util.Comparator;


/**
 * A {@link java.util.Comparator} implementation for
 * {@link java.lang.Comparable}s.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <T> comparable type parameter
 */
public class ComparableComparator<T extends Comparable<T>>
    implements Comparator<T> {


    /**
     * An enum constants for ordering {@code null} references.
     */
    public enum NullPolicy {


        /**
         * Constant for preceding {@code null} references.
         */
        NULLS_PRECEDE_NONNULLS() {


                @Override
                <T extends Comparable<T>> int compare(final T o1, final T o2) {

                    return o1 == null
                           ? o2 == null ? 0 : -1
                           : o2 == null ? 1 : o1.compareTo(o2);
                }


            },
        /**
         * Constant for succeeding {@code null} references.
         */
        NULLS_SUCCEED_NONNULLS() {


                @Override
                <T extends Comparable<T>> int compare(final T o1, final T o2) {

                    return o1 == null
                           ? o2 == null ? 0 : 1
                           : o2 == null ? -1 : o1.compareTo(o2);
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
        abstract <T extends Comparable<T>> int compare(T o1, T o2);


    }


    @Override
    public int compare(final T o1, final T o2) {

        if (nullPolicy != null) {
            nullPolicy.compare(o1, o2);
        }

        return o1.compareTo(o2);
    }


    /**
     * Returns the current value of {@code nullPolicty}. The default value of
     * {@code nullPolicy} is {@code null}.
     *
     * @return the current value of {@code nullPolicy}
     *
     * @see NullPolicy
     */
    public NullPolicy getNullPolicy() {

        return nullPolicy;
    }


    /**
     * Replaces the value of {@code nullPolicy} with given value.
     *
     * @param nullPolicy new value of {@code nullPolicy}
     *
     * @see NullPolicy
     */
    public void setNullPolicy(final NullPolicy nullPolicy) {

        this.nullPolicy = nullPolicy;
    }


    /**
     * nullPolicy.
     */
    private NullPolicy nullPolicy;


}

