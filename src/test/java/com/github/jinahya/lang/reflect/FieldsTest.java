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
package com.github.jinahya.lang.reflect;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class FieldsTest {

    private static final Object F = null;

    @Test
    public static void unprivate_()
            throws NoSuchFieldException, IllegalAccessException {

        final Field field = FieldsTest.class.getDeclaredField("F");
        int modifiers = field.getModifiers();
        Assert.assertTrue(Modifier.isPrivate(modifiers));
        Assert.assertTrue(Modifier.isFinal(modifiers));

        JinahyaFields.unprivate(field);
        modifiers = field.getModifiers();
        Assert.assertTrue(!Modifier.isPrivate(modifiers));
        Assert.assertTrue(Modifier.isFinal(modifiers));
    }

    @Test
    public static void unfinal_()
            throws NoSuchFieldException, IllegalAccessException {

        final Field field = FieldsTest.class.getDeclaredField("F");
        int modifiers = field.getModifiers();
        Assert.assertTrue(Modifier.isPrivate(modifiers));
        Assert.assertTrue(Modifier.isFinal(modifiers));

        JinahyaFields.unfinal(field);
        modifiers = field.getModifiers();
        Assert.assertTrue(Modifier.isPrivate(modifiers));
        Assert.assertTrue(!Modifier.isFinal(modifiers));
    }
}
