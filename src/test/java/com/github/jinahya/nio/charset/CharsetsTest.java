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


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon
 */
public class CharsetsTest {


    @Test
    public void SUPPORTED_CHARSET_NAMES_() throws IllegalAccessException {

        for (final String charsetName : JinahyaCharsets.SUPPORTED_CHARSET_NAMES) {
            final Charset charset = Charset.forName(charsetName);
            boolean matches = false;
            for (final Field field : StandardCharsets.class.getFields()) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                if (!field.getType().equals(Charset.class)) {
                    return;
                }
                if (charset.equals(field.get(null))) {
                    matches = true;
                    break;
                }
            }
            Assert.assertTrue(
                matches, charset + " is not found in "
                         + StandardCharsets.class);
        }
    }


    @Test
    public void SUPPORTED_CHARSET_() throws IllegalAccessException {

        for (final Charset charset : JinahyaCharsets.SUPPORTED_CHARSETS) {
            boolean matches = false;
            for (final Field field : StandardCharsets.class.getFields()) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                if (!field.getType().equals(Charset.class)) {
                    return;
                }
                if (charset.equals(field.get(null))) {
                    matches = true;
                    break;
                }
            }
            Assert.assertTrue(matches, charset + " is not found in "
                                       + StandardCharsets.class);
        }
    }

}

