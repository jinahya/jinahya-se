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
package com.github.jinahya.lang;

import java.util.Arrays;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class StringsTest {

    @Test
    public static void indexOf_ch() {

        //                     0123456
        final String string = "_a__b_c"; // 0, 2, 3, 5

        Assert.assertEquals(Strings.indexOf(string, '_', -2, 0), 0);
        Assert.assertEquals(Strings.indexOf(string, '_', -2, 0),
                string.indexOf('_', -2));
        Assert.assertEquals(Strings.indexOf(string, '_', -1, 0), 0);
        Assert.assertEquals(Strings.indexOf(string, '_', -1, 0),
                string.indexOf('_', -1));
        Assert.assertEquals(Strings.indexOf(string, '_', 7, 0), -1);
        Assert.assertEquals(Strings.indexOf(string, '_', 7, 0),
                string.indexOf('_', 7));
        Assert.assertEquals(Strings.indexOf(string, '_', 8, 0), -1);
        Assert.assertEquals(Strings.indexOf(string, '_', 8, 0),
                string.indexOf('_', 8));

        Assert.assertEquals(Strings.indexOf(string, '_', 0, 0), 0);
        Assert.assertEquals(Strings.indexOf(string, '_', 1, 0), 2);
        Assert.assertEquals(Strings.indexOf(string, '_', 2, 0), 2);
        Assert.assertEquals(Strings.indexOf(string, '_', 3, 0), 3);
        Assert.assertEquals(Strings.indexOf(string, '_', 4, 0), 5);
        Assert.assertEquals(Strings.indexOf(string, '_', 5, 0), 5);
        Assert.assertEquals(Strings.indexOf(string, '_', 6, 0), -1);

        Assert.assertEquals(Strings.indexOf(string, '_', 0, 1), 2);
        Assert.assertEquals(Strings.indexOf(string, '_', 1, 1), 3);
        Assert.assertEquals(Strings.indexOf(string, '_', 2, 1), 3);
        Assert.assertEquals(Strings.indexOf(string, '_', 3, 1), 5);
        Assert.assertEquals(Strings.indexOf(string, '_', 4, 1), -1);
        Assert.assertEquals(Strings.indexOf(string, '_', 5, 1), -1);
        Assert.assertEquals(Strings.indexOf(string, '_', 6, 1), -1);

        Assert.assertEquals(Strings.indexOf(string, '_', 0, 2), 3);
        Assert.assertEquals(Strings.indexOf(string, '_', 1, 2), 5);
        Assert.assertEquals(Strings.indexOf(string, '_', 2, 2), 5);
        Assert.assertEquals(Strings.indexOf(string, '_', 3, 2), -1);
        Assert.assertEquals(Strings.indexOf(string, '_', 4, 2), -1);
        Assert.assertEquals(Strings.indexOf(string, '_', 5, 2), -1);
        Assert.assertEquals(Strings.indexOf(string, '_', 6, 2), -1);

        Assert.assertEquals(Strings.indexOf(string, '_', 0, 3), 5);
        Assert.assertEquals(Strings.indexOf(string, '_', 1, 3), -1);
        Assert.assertEquals(Strings.indexOf(string, '_', 2, 3), -1);
        Assert.assertEquals(Strings.indexOf(string, '_', 3, 3), -1);
        Assert.assertEquals(Strings.indexOf(string, '_', 4, 3), -1);
        Assert.assertEquals(Strings.indexOf(string, '_', 5, 3), -1);
        Assert.assertEquals(Strings.indexOf(string, '_', 6, 3), -1);

        Assert.assertEquals(Strings.indexOf(string, '_', 0, 4), -1);
        Assert.assertEquals(Strings.indexOf(string, '_', 1, 4), -1);
        Assert.assertEquals(Strings.indexOf(string, '_', 2, 4), -1);
        Assert.assertEquals(Strings.indexOf(string, '_', 3, 4), -1);
        Assert.assertEquals(Strings.indexOf(string, '_', 4, 4), -1);
        Assert.assertEquals(Strings.indexOf(string, '_', 5, 4), -1);
        Assert.assertEquals(Strings.indexOf(string, '_', 6, 4), -1);
    }

    @Test
    public static void indicesOf_ch() {

        //                     0123456
        final String string = "_a__b_c"; // 0, 2, 3, 5

        Assert.assertEquals(Strings.indicesOf(string, '_', -1),
                Arrays.asList(0, 2, 3, 5));

        Assert.assertEquals(Strings.indicesOf(string, '_', 0),
                Arrays.asList(0, 2, 3, 5));

        Assert.assertEquals(Strings.indicesOf(string, '_', 1),
                Arrays.asList(2, 3, 5));

        Assert.assertEquals(Strings.indicesOf(string, '_', 2),
                Arrays.asList(2, 3, 5));

        Assert.assertEquals(Strings.indicesOf(string, '_', 3),
                Arrays.asList(3, 5));

        Assert.assertEquals(Strings.indicesOf(string, '_', 4),
                Arrays.asList(5));

        Assert.assertEquals(Strings.indicesOf(string, '_', 5),
                Arrays.asList(5));

        Assert.assertTrue(Strings.indicesOf(string, '_', 6).isEmpty());

        Assert.assertTrue(Strings.indicesOf(string, '_', 7).isEmpty());
    }

    @Test
    public static void indexOf_str() {

        //                     0123456789
        final String string = "__a___b__c"; // 0, 3, 4, 7

        Assert.assertEquals(Strings.indexOf(string, "__", -2, 0), 0);
        Assert.assertEquals(Strings.indexOf(string, "__", -2, 0),
                string.indexOf("__", -2));
        Assert.assertEquals(Strings.indexOf(string, "__", -1, 0), 0);
        Assert.assertEquals(Strings.indexOf(string, "__", -1, 0),
                string.indexOf("__", -1));
        Assert.assertEquals(Strings.indexOf(string, "__", 10, 0), -1);
        Assert.assertEquals(Strings.indexOf(string, "__", 10, 0),
                string.indexOf("__", 10));
        Assert.assertEquals(Strings.indexOf(string, "__", 11, 0), -1);
        Assert.assertEquals(Strings.indexOf(string, "__", 11, 0),
                string.indexOf("__", 11));

        Assert.assertEquals(Strings.indexOf(string, "__", 0, 0), 0);
        Assert.assertEquals(Strings.indexOf(string, "__", 1, 0), 3);
        Assert.assertEquals(Strings.indexOf(string, "__", 2, 0), 3);
        Assert.assertEquals(Strings.indexOf(string, "__", 3, 0), 3);
        Assert.assertEquals(Strings.indexOf(string, "__", 4, 0), 4);
        Assert.assertEquals(Strings.indexOf(string, "__", 5, 0), 7);
        Assert.assertEquals(Strings.indexOf(string, "__", 6, 0), 7);
        Assert.assertEquals(Strings.indexOf(string, "__", 7, 0), 7);
        Assert.assertEquals(Strings.indexOf(string, "__", 8, 0), -1);
        Assert.assertEquals(Strings.indexOf(string, "__", 9, 0), -1);

        Assert.assertEquals(Strings.indexOf(string, "__", 0, 1), 3);
        Assert.assertEquals(Strings.indexOf(string, "__", 1, 1), 7);
        Assert.assertEquals(Strings.indexOf(string, "__", 2, 1), 7);
        Assert.assertEquals(Strings.indexOf(string, "__", 3, 1), 7);
        Assert.assertEquals(Strings.indexOf(string, "__", 4, 1), 7);
        Assert.assertEquals(Strings.indexOf(string, "__", 5, 1), -1);
        Assert.assertEquals(Strings.indexOf(string, "__", 6, 1), -1);
        Assert.assertEquals(Strings.indexOf(string, "__", 7, 1), -1);
        Assert.assertEquals(Strings.indexOf(string, "__", 8, 1), -1);
        Assert.assertEquals(Strings.indexOf(string, "__", 9, 1), -1);

        Assert.assertEquals(Strings.indexOf(string, "__", 0, 2), 7);
        Assert.assertEquals(Strings.indexOf(string, "__", 1, 2), -1);
        Assert.assertEquals(Strings.indexOf(string, "__", 2, 2), -1);
        Assert.assertEquals(Strings.indexOf(string, "__", 3, 2), -1);
        Assert.assertEquals(Strings.indexOf(string, "__", 4, 2), -1);
        Assert.assertEquals(Strings.indexOf(string, "__", 5, 2), -1);
        Assert.assertEquals(Strings.indexOf(string, "__", 6, 2), -1);
        Assert.assertEquals(Strings.indexOf(string, "__", 7, 2), -1);
        Assert.assertEquals(Strings.indexOf(string, "__", 8, 2), -1);
        Assert.assertEquals(Strings.indexOf(string, "__", 9, 2), -1);

        Assert.assertEquals(Strings.indexOf(string, "__", 0, 3), -1);
        Assert.assertEquals(Strings.indexOf(string, "__", 1, 3), -1);
        Assert.assertEquals(Strings.indexOf(string, "__", 2, 3), -1);
        Assert.assertEquals(Strings.indexOf(string, "__", 3, 3), -1);
        Assert.assertEquals(Strings.indexOf(string, "__", 4, 3), -1);
        Assert.assertEquals(Strings.indexOf(string, "__", 5, 3), -1);
        Assert.assertEquals(Strings.indexOf(string, "__", 6, 3), -1);
        Assert.assertEquals(Strings.indexOf(string, "__", 7, 3), -1);
        Assert.assertEquals(Strings.indexOf(string, "__", 8, 3), -1);
        Assert.assertEquals(Strings.indexOf(string, "__", 9, 3), -1);

    }

    @Test
    public static void indicesOf_str() {

        //                     0123456789
        final String string = "__a___b__c"; // 0, 3, 7

        Assert.assertEquals(Strings.indicesOf(string, "__", -1),
                Arrays.asList(0, 3, 7));

        Assert.assertEquals(Strings.indicesOf(string, "__", 0),
                Arrays.asList(0, 3, 7));

        Assert.assertEquals(Strings.indicesOf(string, "__", 1),
                Arrays.asList(3, 7));

        Assert.assertEquals(Strings.indicesOf(string, "__", 2),
                Arrays.asList(3, 7));

        Assert.assertEquals(Strings.indicesOf(string, "__", 3),
                Arrays.asList(3, 7));

        Assert.assertEquals(Strings.indicesOf(string, "__", 4),
                Arrays.asList(4, 7));

        Assert.assertEquals(Strings.indicesOf(string, "__", 5),
                Arrays.asList(7));

        Assert.assertEquals(Strings.indicesOf(string, "__", 6),
                Arrays.asList(7));

        Assert.assertEquals(Strings.indicesOf(string, "__", 7),
                Arrays.asList(7));

        Assert.assertTrue(Strings.indicesOf(string, "__", 8).isEmpty());

        Assert.assertTrue(Strings.indicesOf(string, "__", 9).isEmpty());

        Assert.assertTrue(Strings.indicesOf(string, "__", 10).isEmpty());
    }

}
