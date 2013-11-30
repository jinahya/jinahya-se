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


/**
 *
 * @author <a href="mailto:onacit@gmail.com">Jin Kwon</a>
 */
public final class Classes {


    /**
     * Checks that the specified object reference is an instance of specified
     * class.
     *
     * @param <T> the class type parameter
     * @param is the object reference to check castability
     * @param of the class for which the object reference is checked
     *
     * @return casted {@code is} if castable and not {@code null}
     *
     * @throws NullPointerException if {@code of} is {@code null}
     *
     * @throws IllegalArgumentException if {@code is} is {@code null} or is not
     * an instance of {@code of}
     *
     * @see Class#isInstance(java.lang.Object)
     * @see Class#cast(java.lang.Object)
     */
    public static <T> T requireInstanceOf(final Object is, final Class<T> of) {

        if (of == null) {
            throw new NullPointerException("null of");
        }

        if (!of.isInstance(is)) {
            throw new IllegalArgumentException(
                is + " is not an instance of " + of);
        }

        return of.cast(is);
    }


    /**
     * Checks that the source class is assignable to the target class.
     *
     * @param <T> the class type parameter
     * @param from the source class to check for assignability to the target
     * class.
     * @param to the target class to check for assignability from the source
     * class.
     *
     * @return {@code from} as a subclass of {@code to} if assignable
     *
     * @see Class#isAssignableFrom(java.lang.Class)
     * @see Class#asSubclass(java.lang.Class)
     */
    public static <T> Class<? extends T> requireAssignableTo(
        final Class<?> from, final Class<T> to) {

        if (from == null) {
            throw new NullPointerException("null from");
        }

        if (to == null) {
            throw new NullPointerException("null to");
        }

        if (!to.isAssignableFrom(from)) {
            throw new IllegalArgumentException(
                from + " is not assignable to " + to);
        }

        return from.asSubclass(to);
    }


    /**
     * private constructor.
     */
    private Classes() {

        super();
    }


}

