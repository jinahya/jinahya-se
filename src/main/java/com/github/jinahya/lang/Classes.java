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
     * Checks that the specified object reference an instance of specified
     * class.
     *
     * @param <T> the class type parameter
     * @param obj the object reference to check castability
     * @param cls the class
     *
     * @return casted obj if castable and not {@code null}
     *
     * @throws NullPointerException if {@code cls} is {@code null}
     *
     * @throws IllegalArgumentException if {@code obj} is {@code null} or is not
     * an instance of {@code cls}
     *
     * @see Class#isInstance(java.lang.Object)
     */
    public static <T> T requireInstanceOf(final Object obj,
                                          final Class<T> cls) {

        if (cls == null) {
            throw new NullPointerException("null cls");
        }

        if (!cls.isInstance(obj)) {
            throw new IllegalArgumentException(
                obj + " is not an instance of " + cls);
        }

        return cls.cast(obj);
    }


    private Classes() {

        super();

    }


}

