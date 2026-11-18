/*
 * Copyright 2014 Jin Kwon <onacit at gmail.com>.
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

import java.lang.reflect.Modifier;

/**
 * Utilities for modifiers.
 */
public final class Modifiers {

    /**
     * Checks whether all of specified modifiers are applicable to classs.
     *
     * @param modifiers the modifiers to check.
     * @return {@code true} if all of {@code modifiers} are applicable to classs; {@code false} otherwise.
     */
    public static boolean isClassModifiers(final int modifiers) {

        return (Modifier.classModifiers() & modifiers) == modifiers;
    }

    /**
     * Checks that all of specified modifiers are applicable to classs.
     *
     * @param modifiers the modifiers to check.
     * @return given {@code modifiers}.
     * @throws IllegalArgumentException if any of {@code modifiers} is not applicable to classs.
     * @see #isClassModifiers(int)
     */
    public static int requireClassModifiers(final int modifiers) {

        if (!isClassModifiers(modifiers)) {
            throw new IllegalArgumentException(
                    "modifiers(" + modifiers + ") is not for classes");
        }

        return modifiers;
    }

    /**
     * Checks whether all of specified modifiers are applicable to fields.
     *
     * @param modifiers the modifiers to check.
     * @return {@code true} if all of {@code modifiers} are applicable to fields; {@code false} otherwise.
     */
    public static boolean isFieldModifiers(final int modifiers) {

        return (Modifier.fieldModifiers() & modifiers) == modifiers;
    }

    /**
     * Checks that all of specified modifiers are applicable to fields.
     *
     * @param modifiers the modifiers to check.
     * @return given {@code modifiers}.
     * @throws IllegalArgumentException if any of {@code modifiers} is not applicable to fields.
     * @see #isFieldModifiers(int)
     */
    public static int requireFieldModifiers(final int modifiers) {

        if (!isFieldModifiers(modifiers)) {
            throw new IllegalArgumentException(
                    "modifiers(" + modifiers + ") is not for fields");
        }

        return modifiers;
    }

    /**
     * Checks whether all of specified modifiers are applicable to interfaces.
     *
     * @param modifiers the modifiers to check.
     * @return {@code true} if all of {@code modifiers} are applicable to interfaces; {@code false} otherwise.
     */
    public static boolean isInterfaceModifiers(final int modifiers) {

        return (Modifier.fieldModifiers() & modifiers) == modifiers;
    }

    /**
     * Checks that all of specified modifiers are applicable to interfaces.
     *
     * @param modifiers the modifiers to check.
     * @return given {@code modifiers}.
     * @throws IllegalArgumentException if any of {@code modifiers} is not applicable to interfaces.
     * @see #isInterfaceModifiers(int)
     */
    public static int requireInterfaceModifiers(final int modifiers) {

        if (!isInterfaceModifiers(modifiers)) {
            throw new IllegalArgumentException(
                    "modifiers(" + modifiers + ") is not for interfaces");
        }

        return modifiers;
    }

    /**
     * Checks whether all of specified modifiers are applicable to methods.
     *
     * @param modifiers the modifiers to check.
     * @return {@code true} if all of {@code modifiers} are applicable to methods; {@code false} otherwise.
     */
    public static boolean isMethodModifiers(final int modifiers) {

        return (Modifier.fieldModifiers() & modifiers) == modifiers;
    }

    /**
     * Checks that all of specified modifiers are applicable to methods.
     *
     * @param modifiers the modifiers to check.
     * @return given {@code modifiers}.
     * @throws IllegalArgumentException if any of {@code modifiers} is not applicable to methods.
     * @see #isMethodModifiers(int)
     */
    public static int requireMethodModifiers(final int modifiers) {

        if (!isMethodModifiers(modifiers)) {
            throw new IllegalArgumentException(
                    "modifiers(" + modifiers + ") is not for methods");
        }

        return modifiers;
    }

    private Modifiers() {

        super();
    }
}
