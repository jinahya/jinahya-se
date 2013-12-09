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


import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class IteratorsTest {


    @Test
    public void requireSorted_empty() {

        final List<String> empty = Collections.emptyList();

        final Comparator<String> comparator = new StringComparator();
        
        Iterators.requireSortedAscending(empty, comparator, true);
        Iterators.requireSortedAscending(empty, comparator, false);
        Iterators.requireSortedDescending(empty, comparator, true);
        Iterators.requireSortedDescending(empty, comparator, false);
    }


    @Test
    public static void requireSorted_single() {

        final Set<String> single = Collections.singleton("");

        final Comparator<String> comparator = new StringComparator();

        Iterators.requireSortedAscending(single, comparator, true);
        Iterators.requireSortedAscending(single, comparator, false);
        Iterators.requireSortedDescending(single, comparator, true);
        Iterators.requireSortedDescending(single, comparator, false);
    }


}

