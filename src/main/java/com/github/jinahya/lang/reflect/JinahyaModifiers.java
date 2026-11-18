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
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class JinahyaModifiers {

    /**
     * Checks whether all of specified modifiers are applicable to classs.
     *
     * @param modifiers the modifiers to check.
     * @return {@code true} if all of {@code modifiers} are applicable to classs; {@code false} otherwise.
     */
    public static boolean isAllClassModifiers(final int modifiers) {

        return (Modifier.classModifiers() & modifiers) == modifiers;
    }

    /**
     * Checks that all of specified modifiers are applicable to classs.
     *
     * @param modifiers the modifiers to check.
     * @return given {@code modifiers}.
     * @throws IllegalArgumentException if any of {@code modifiers} is not applicable to classs.
     * @see #isAllClassModifiers(int)
     */
    public static int requireAllClassModifiers(final int modifiers) {

        if (!isAllClassModifiers(modifiers)) {
            throw new IllegalArgumentException(
                    "modifiers(" + modifiers + ") is not all for classes");
        }

        return modifiers;
    }

    /**
     * Checks whether all of specified modifiers are applicable to fields.
     *
     * @param modifiers the modifiers to check.
     * @return {@code true} if all of {@code modifiers} are applicable to fields; {@code false} otherwise.
     */
    public static boolean isAllFieldModifiers(final int modifiers) {

        return (Modifier.fieldModifiers() & modifiers) == modifiers;
    }

    /**
     * Checks that all of specified modifiers are applicable to fields.
     *
     * @param modifiers the modifiers to check.
     * @return given {@code modifiers}.
     * @throws IllegalArgumentException if any of {@code modifiers} is not applicable to fields.
     * @see #isAllFieldModifiers(int)
     */
    public static int requireAllFieldModifiers(final int modifiers) {

        if (!isAllFieldModifiers(modifiers)) {
            throw new IllegalArgumentException(
                    "modifiers(" + modifiers + ") is not all for fields");
        }

        return modifiers;
    }

    /**
     * Checks whether all of specified modifiers are applicable to interfaces.
     *
     * @param modifiers the modifiers to check.
     * @return {@code true} if all of {@code modifiers} are applicable to interfaces; {@code false} otherwise.
     */
    public static boolean isAllInterfaceModifiers(final int modifiers) {

        return (Modifier.fieldModifiers() & modifiers) == modifiers;
    }

    /**
     * Checks that all of specified modifiers are applicable to interfaces.
     *
     * @param modifiers the modifiers to check.
     * @return given {@code modifiers}.
     * @throws IllegalArgumentException if any of {@code modifiers} is not applicable to interfaces.
     * @see #isAllInterfaceModifiers(int)
     */
    public static int requireAllInterfaceModifiers(final int modifiers) {

        if (!isAllInterfaceModifiers(modifiers)) {
            throw new IllegalArgumentException(
                    "modifiers(" + modifiers + ") is not all for interfaces");
        }

        return modifiers;
    }

    /**
     * Checks whether all of specified modifiers are applicable to methods.
     *
     * @param modifiers the modifiers to check.
     * @return {@code true} if all of {@code modifiers} are applicable to methods; {@code false} otherwise.
     */
    public static boolean isAllMethodModifiers(final int modifiers) {

        return (Modifier.fieldModifiers() & modifiers) == modifiers;
    }

    /**
     * Checks that all of specified modifiers are applicable to methods.
     *
     * @param modifiers the modifiers to check.
     * @return given {@code modifiers}.
     * @throws IllegalArgumentException if any of {@code modifiers} is not applicable to methods.
     * @see #isAllMethodModifiers(int)
     */
    public static int requireAllMethodModifiers(final int modifiers) {

        if (!isAllMethodModifiers(modifiers)) {
            throw new IllegalArgumentException(
                    "modifiers(" + modifiers + ") is not all for methods");
        }

        return modifiers;
    }

    private JinahyaModifiers() {

        super();
    }
}
