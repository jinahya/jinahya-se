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

/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
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
     * @throws NullPointerException if either {@code is} or {@code of} is
     * {@code null}
     * @throws IllegalArgumentException if {@code is} is not an instance of
     * {@code of}.
     *
     * @see Class#isInstance(java.lang.Object)
     * @see Class#cast(java.lang.Object)
     */
    public static <T> T requireInstanceOf(final Object is, final Class<T> of) {

        if (is == null) {
            throw new NullPointerException("null is");
        }

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
     * @param is the source class to check for assignability to the target
     * class.
     * @param to the target class to check for assignability from the source
     * class.
     *
     * @return {@code is} if it's assignable to {@code to}
     *
     * @throws NullPointerException if {@code is} is {@code null}.
     * @throws NullPointerException if {@code to} is {@code null}.
     * @throws IllegalArgumentException if {@code is} is not assignable to
     * {@code to}.
     *
     * @see Class#isAssignableFrom(java.lang.Class)
     * @see Class#asSubclass(java.lang.Class)
     */
    public static Class<?> requireAssignableTo(final Class<?> is,
            final Class<?> to) {

        if (is == null) {
            throw new NullPointerException("null is");
        }

        if (to == null) {
            throw new NullPointerException("null to");
        }

        if (!to.isAssignableFrom(is)) {
            throw new IllegalArgumentException(
                    is + " is not assignable to " + to);
        }

        return is;
    }

    /**
     *
     * @param is
     * @param from
     *
     * @return {@code is} if it's assignable from {@code from}.
     *
     * @throws NullPointerException if {@code is} is {@code null}.
     * @throws NullPointerException if {@code from} is {@code null}.
     * @throws IllegalArgumentException if {@code is} is not assignable from
     * {@code from}.
     */
    public static Class<?> requireAssignableFrom(final Class<?> is,
            final Class<?> from) {

        if (is == null) {
            throw new NullPointerException("null is");
        }

        if (from == null) {
            throw new NullPointerException("null from");
        }

        if (!is.isAssignableFrom(from)) {
            throw new IllegalArgumentException(
                    is + " is not assignable from " + from);
        }

        return is;
    }

    /**
     * private constructor.
     */
    private Classes() {

        super();
    }

}
