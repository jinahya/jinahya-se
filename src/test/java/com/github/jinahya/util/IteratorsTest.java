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

        Iterators.requireSortedAscending(empty, true);
        Iterators.requireSortedAscending(empty, false);
        Iterators.requireSortedDescending(empty, true);
        Iterators.requireSortedDescending(empty, false);
    }


    @Test
    public static void requireSorted_single() {

        final Set<String> single = Collections.singleton("");

        Iterators.requireSortedAscending(single, true);
        Iterators.requireSortedAscending(single, false);
        Iterators.requireSortedDescending(single, true);
        Iterators.requireSortedDescending(single, false);
    }


}

