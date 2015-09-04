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


package com.github.jinahya.util;


import static com.github.jinahya.util.JinahyaArrays.reverse;
import static java.lang.invoke.MethodHandles.lookup;
import java.util.concurrent.ThreadLocalRandom;
import static java.util.concurrent.ThreadLocalRandom.current;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class JinahyaArraysTest {


    private static final Logger logger = getLogger(lookup().lookupClass());


    @Test
    public static void reverseByteArrayEmpty() {

        final byte[] a = new byte[0];

        JinahyaArrays.reverse(a);
    }


    @Test
    public static void reverseByteArrayOne() {

        final byte[] a = new byte[1];
        current().nextBytes(a);

        JinahyaArrays.reverse(a);
    }


    @Test(invocationCount = 1)
    public static void reverseByteArray() {

        final ThreadLocalRandom random = ThreadLocalRandom.current();

        final byte[] a = new byte[current().nextInt(2, 1024)];
        do {
            current().nextBytes(a);
        } while (a[0] == a[a.length - 1]);
        final byte expected = a[0];

        reverse(a);
        final byte actual = a[a.length - 1];

        assertEquals(actual, expected);
    }


}

