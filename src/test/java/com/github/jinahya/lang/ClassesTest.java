/*
 * Copyright 2013 <a href="mailto:onacit@gmail.com">Jin Kwon</a>.
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


import junit.framework.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:onacit@gmail.com">Jin Kwon</a>
 */
public class ClassesTest {


    @Test
    public static void requireIntanceOf_() {

        // String is an instance of Object.class
        {
            final Object is = "";
            final Class<?> of = Object.class;
            final Object casted = Classes.requireInstanceOf(is, of);

        }

        // String is an instance of String.class
        {
            final String is = "";
            final Class<String> of = String.class;
            final String casted = Classes.requireInstanceOf(is, of);
        }

        // Integer is an instance of Number.class
        {
            final Integer is = new Integer(0);
            final Class<Number> of = Number.class;
            final Number casted = Classes.requireInstanceOf(is, of);
        }

        // an Object is not an instance of String.class
        {
            try {
                Classes.requireInstanceOf(new Object(), String.class);
                Assert.fail("passed: requireInstanceOf(Object, String.class)");
            } catch (final IllegalArgumentException iae) {
                // expected;
            }
        }

        // null is not an instance of Object.class
        {
            try {
                Classes.requireInstanceOf(null, Object.class);
                Assert.fail("passed: requireInstanceOf(null, Object.class)");
            } catch (final IllegalArgumentException iae) {
                // expected;
            }
        }
    }


    @Test
    public static void requireAssignableTo_() {

        // String.class is assignable to Object.class
        {
            final Class<String> from = String.class;
            final Class<Object> to = Object.class;
            final Class<? extends Object> casted
                = Classes.requireAssignableTo(from, to);

        }

        // Integer.class is assignable to Number.class
        {
            final Class<Integer> from = Integer.class;
            final Class<Number> of = Number.class;
            final Class<? extends Number> casted
                = Classes.requireAssignableTo(from, of);
        }

        // Object.class is not assignble to String.class
        {
            final Class<Object> from = Object.class;
            final Class<String> to = String.class;
            try {
                Classes.requireAssignableTo(from, to);
                Assert.fail(
                    "passed: requireAssignableTo(Object.class, String.class)");
            } catch (final IllegalArgumentException aie) {
                // expected;
            }
        }

        {
            try {
                Classes.requireAssignableTo(null, Object.class);
                Assert.fail("passed: requireAssignableTo(null, Object.class)");
            } catch (final NullPointerException npe) {
                // expected;
            }
        }

        {
            try {
                Classes.requireAssignableTo(String.class, null);
                Assert.fail("passed: requireAssignableTo(String.class, null)");
            } catch (final NullPointerException npe) {
                // expected;
            }
        }
    }


}

