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


package com.github.jinahya.util;


import com.github.jinahya.util.ComparableComparator.Nulls;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.testng.Assert;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 * @param <T>
 * @param <U>
 */
public class ComparableComparatorTest<T extends ComparableComparator<U>, U extends Comparable<? super U>> {


    protected static <T extends ComparableComparator<U>, U extends Comparable<? super U>> void sort_(
            final List<U> list, final T comparator) {

        Objects.requireNonNull(list, "null list");

        Objects.requireNonNull(comparator, "null comparator");

        if (!list.contains(null)) {
            final List<U> list1 = new ArrayList<>(list);
            Collections.sort(list1, comparator);
            final List<U> list2 = new ArrayList<>(list);
            list2.sort((u1, u2) -> u1.compareTo(u2));
            Assert.assertEquals(list1, list2);
        }

        for (final Nulls nulls : Nulls.values()) {
            comparator.nulls(nulls);
            Collections.sort(new ArrayList<>(list), comparator);
        }
    }


}

