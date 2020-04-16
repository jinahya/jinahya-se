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

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class StringComparatorTest
        extends ComparableComparatorTest<StringComparator, String> {

    @Test
    public void sort_() {

        final Random random = ThreadLocalRandom.current();

        final List<String> list = new ArrayList<>();

        for (int i = 0; i < 128; i++) {
            if (random.nextBoolean()) {
                list.add(null);
            } else {
                list.add(RandomStringUtils.random(random.nextInt(128)));
            }
        }
        sort_(list, new StringComparator());

        do {
        } while (list.remove(null));
        assert !list.contains(null);

        sort_(list, new StringComparator());
    }
}
