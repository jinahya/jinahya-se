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

import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class MasksTest {

    @Test
    public static void testPutOnWithSingleMask() {

        final int mask = ThreadLocalRandom.current().nextInt();

        assertEquals(Masks.putOn(0x00, mask), mask);
    }

    @Test
    public static void testPutOnWithNullMasks() {

        Masks.putOn(0, (int[]) null);
    }

    @Test
    public void testTakeOffWithSingleMask() {
        final int mask = ThreadLocalRandom.current().nextInt();
        assertEquals(Masks.takeOff(mask, mask), 0x00);
    }

    @Test
    public static void testTakeOffWithNullMasks() {
        Masks.takeOff(0, (int[]) null);
    }
}
