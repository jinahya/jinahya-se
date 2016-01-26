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


package com.github.jinahya.lang;


import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class ClassesTest {


    @Test(expectedExceptions = {NullPointerException.class})
    public static void requireIntanceOf_nullIs() {

        Classes.requireInstanceOf(null, Object.class);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public static void requireIntanceOf_nullOf() {

        Classes.requireInstanceOf(new Object(), null);
    }


    @Test
    public static void requireIntanceOf_() {

        // a String is an instance of Object.class
        Classes.requireInstanceOf("", Object.class);

        // a String is an instance of String.class
        Classes.requireInstanceOf("", String.class);

        // an Integer is an instance of Object.class
        Classes.requireInstanceOf(new Integer(0), Object.class);

        // an Integer is an instance of Number.class
        Classes.requireInstanceOf(new Integer(0), Number.class);

        // an Object is not an instance of String.class
        try {
            Classes.requireInstanceOf(new Object(), String.class);
            Assert.fail("passed: requireInstanceOf(Object, String.class)");
        } catch (final IllegalArgumentException iae) {
            // expected;
        }
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public static void requireAssignableTo_nullIs() {

        Classes.requireAssignableTo(null, Object.class);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public static void requireAssignableTo_nullTo() {

        Classes.requireAssignableTo(Object.class, null);
    }


    @Test
    public static void requireAssignableTo_() {

        // Object.class is assignable to Object.class
        Classes.requireAssignableTo(Object.class, Object.class);

        // String.class is assignable to Object.class
        Classes.requireAssignableTo(String.class, Object.class);

        // Integer.class is assignable to Number.class
        Classes.requireAssignableTo(Integer.class, Number.class);

        // Object.class is not assignble to String.class
        try {
            Classes.requireAssignableTo(Object.class, String.class);
            Assert.fail(
                "passed: requireAssignableTo(Object.class, String.class)");
        } catch (final IllegalArgumentException aie) {
            // expected;
        }
    }


    @Test
    public static void requireAssignableFrom_() {

        // Object.class is assignable to Object.class
        Assert.assertEquals(
            Classes.requireAssignableFrom(Object.class, Object.class),
            Object.class);

        // String.class is assignable to Object.class
        Assert.assertEquals(
            Classes.requireAssignableFrom(Object.class, String.class),
            Object.class);

        // Integer.class is assignable to Number.class
        Assert.assertEquals(
            Classes.requireAssignableFrom(Number.class, Integer.class),
            Number.class);

        // Object.class is not assignble to String.class
        try {
            Classes.requireAssignableFrom(String.class, Object.class);
            Assert.fail(
                "passed: requireAssignableFrom(String.class, Object.class)");
        } catch (final IllegalArgumentException aie) {
            // expected;
        }
    }


}

