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

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;

/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class Iterators {

    public static <T> void requireSortedAscending(
            final Iterator<? extends T> iterator,
            final Comparator<? super T> comparator, final boolean requireUnique) {

        Objects.requireNonNull(iterator, "null iterator");

        Objects.requireNonNull(comparator, "null comparator");

        T previous = null;
        if (iterator.hasNext()) {
            previous = iterator.next();
        }
        while (iterator.hasNext()) {
            final T next = iterator.next();
            final int compared = comparator.compare(previous, next);
            if (compared > 0) {
                throw new IllegalArgumentException(
                        "not sorted in ascending order");
            }
            if (compared == 0 && requireUnique) {
                throw new IllegalArgumentException("not unique");
            }
            assert compared < 0;
        }
    }

    public static <T> void requireSortedAscending(
            final Collection<? extends T> collection,
            final Comparator<? super T> comparator, final boolean requireUnique) {

        Objects.requireNonNull(collection, "null collection");

        requireSortedAscending(collection.iterator(), comparator,
                               requireUnique);
    }

    public static <T> void requireSortedDescending(
            final Iterator<? extends T> iterator,
            final Comparator<? super T> comparator, final boolean requireUnique) {

        Objects.requireNonNull(iterator, "null iterator");

        Objects.requireNonNull(comparator, "null comparator");

        T previous = null;
        if (iterator.hasNext()) {
            previous = iterator.next();
        }
        while (iterator.hasNext()) {
            final T current = iterator.next();
            final int compared = comparator.compare(previous, current);
            if (compared < 0) {
                throw new IllegalArgumentException(
                        "not sorted in descending order");
            }
            if (compared == 0 && requireUnique) {
                throw new IllegalArgumentException("not unique");
            }
            assert compared > 0;
        }
    }

    public static <T> void requireSortedDescending(
            final Collection<? extends T> collection,
            final Comparator<? super T> comparator, final boolean requireUnique) {

        Objects.requireNonNull(collection, "null collection");

        requireSortedDescending(collection.iterator(), comparator,
                                requireUnique);
    }

    /**
     * private constructor.
     */
    private Iterators() {

        super();
    }

}
