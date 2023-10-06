package com.github.jinahya.lang;

/*-
 * #%L
 * verbose-hello-world-api
 * %%
 * Copyright (C) 2018 - 2023 Jinahya, Inc.
 * %%
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
 * #L%
 */

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Utilities for {@link java.lang} package.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public final class JinahyaLangUtils {

    static final Map<Class<?>, Class<?>> WRAPPER_CLASSES = Map.of(
            boolean.class, Boolean.class,
            byte.class, Byte.class,
            short.class, Short.class,
            int.class, Integer.class,
            long.class, Long.class,
            char.class, Character.class,
            float.class, Float.class,
            double.class, Double.class,
            void.class, Void.class
    );

    public static Set<Class<?>> getAllPrimitiveTypes() {
        return WRAPPER_CLASSES.keySet();
    }

    public static Class<?> getWrapperType(final Class<?> clazz) {
        Objects.requireNonNull(clazz, "clazz is null");
        if (!clazz.isPrimitive()) {
            throw new IllegalArgumentException("not a primitive type: " + clazz);
        }
        return WRAPPER_CLASSES.get(clazz);
    }

    static final Map<Class<?>, Class<?>> PRIMITIVE_CLASSES =
            WRAPPER_CLASSES.entrySet()
                    .stream()
                    .collect(Collectors.toUnmodifiableMap(Map.Entry::getValue, Map.Entry::getKey));

    public static Set<Class<?>> getAllWrapperTypes() {
        return PRIMITIVE_CLASSES.keySet();
    }

    public static boolean isWrapper(final Class<?> clazz) {
        Objects.requireNonNull(clazz, "clazz is null");
        return PRIMITIVE_CLASSES.containsKey(clazz);
    }

    // ---------------------------------------------------------------------------------------------
    private JinahyaLangUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
