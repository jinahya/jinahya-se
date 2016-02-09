/*
 * Copyright 2014 Jin Kwon.
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


package com.github.jinahya.nio.charset;


import com.github.jinahya.lang.ComparableFieldEnumTest;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon
 */
public class StandardCharsetTest
    extends ComparableFieldEnumTest<StandardCharset, Charset> {


    @Test
    public static void fromFieldValue_() {

        StandardCharset.fromFieldValue(StandardCharsets.ISO_8859_1);
        StandardCharset.fromFieldValue(StandardCharsets.US_ASCII);
        StandardCharset.fromFieldValue(StandardCharsets.UTF_16);
        StandardCharset.fromFieldValue(StandardCharsets.UTF_16BE);
        StandardCharset.fromFieldValue(StandardCharsets.UTF_16LE);
        StandardCharset.fromFieldValue(StandardCharsets.UTF_8);
    }


    @Test
    public static void fieldValues_() {

        final List<Charset> expected = Arrays.asList(
            StandardCharsets.ISO_8859_1,
            StandardCharsets.US_ASCII,
            StandardCharsets.UTF_16,
            StandardCharsets.UTF_16BE,
            StandardCharsets.UTF_16LE,
            StandardCharsets.UTF_8);

        final List<Charset> actual = new ArrayList<>();
        StandardCharset.fieldValues(actual);

        Assert.assertEquals(actual, expected);
    }


    public StandardCharsetTest() {

        super(StandardCharset.class, Charset.class);
    }

}

