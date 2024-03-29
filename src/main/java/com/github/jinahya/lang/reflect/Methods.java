/*
 * Copyright 2015 Jin Kwon &lt;onacit at gmail.com&gt;.
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

import java.lang.reflect.Method;

/**
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
public class Methods {

    public static Method findDeclaredMethod(final Class<?> klass,
                                            final String name,
                                            final Class<?>... parameterTypes)
            throws NoSuchMethodException {

        try {
            return klass.getDeclaredMethod(name, parameterTypes);
        } catch (final NoSuchMethodException nsme) {
            final Class<?> superclass = klass.getSuperclass();
            if (superclass == null) {
                throw nsme;
            }
            return findDeclaredMethod(superclass, name, parameterTypes);
        }
    }

    private Methods() {
        super();
    }
}
