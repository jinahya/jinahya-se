package com.github.jinahya.util;

/*-
 * #%L
 * jinahya-se
 * %%
 * Copyright (C) 2025 - 2026 Jinahya
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

import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

public abstract class JinahyaClasses {

    /**
     * Traverses the class hierarchy from specified class through its superclasses while specified predicate tests
     * {@code true}.
     *
     * @param clazz     the class to start traversing.
     * @param predicate the predicate for testing classes.
     */
    public static void traverse(final Class<?> clazz, final Predicate<Class<?>> predicate) {
        requireNonNull(clazz, "clazz is null");
        requireNonNull(predicate, "predicate is null");
        for (Class<?> c = clazz; true; c = c.getSuperclass()) {
            if (c == null) {
                return;
            }
            if (!predicate.test(c)) {
                return;
            }
        }
    }

    protected JinahyaClasses() {
        super();
    }
}
