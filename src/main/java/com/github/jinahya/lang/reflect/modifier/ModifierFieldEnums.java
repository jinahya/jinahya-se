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
package com.github.jinahya.lang.reflect.modifier;

import static com.github.jinahya.lang.FieldEnums.fieldValues;

/**
 * A utility class for {@link ModifierFieldEnum}s.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class ModifierFieldEnums {

    public static <E extends Enum<E> & ModifierFieldEnum<E>> int modifiers(
            final Class<E> enumType) {

        int modifiers = 0;

        for (final int modifier : fieldValues(enumType, Integer.class)) {
            modifiers |= modifier;
        }

        return modifiers;
    }

    public static <E extends Enum<E> & ModifierFieldEnum<E>> boolean isAll(
            final int modifiers, final E... constants) {

        if (constants == null) {
            throw new NullPointerException("null constants");
        }

        for (final E constant : constants) {
            final int modifier = constant.fieldValue();
            if ((modifiers & modifier) != modifier) {
                return false;
            }
        }

        return true;
    }

    public static <E extends Enum<E> & ModifierFieldEnum<E>> boolean isAny(
            final int modifiers, final E... constants) {

        if (constants == null) {
            throw new NullPointerException("null constants");
        }

        for (final E constant : constants) {
            final int modifier = constant.fieldValue();
            if ((modifiers & modifier) == modifier) {
                return true;
            }
        }

        return false;
    }

    public static <E extends Enum<E> & ModifierFieldEnum<E>> int add(
            int modifiers, final E... constants) {

        if (constants == null) {
            throw new NullPointerException("null constants");
        }

        for (final E constant : constants) {
            modifiers |= constant.fieldValue().intValue();
        }

        return modifiers;
    }

    public static <E extends Enum<E> & ModifierFieldEnum<E>> int remove(
            int modifiers, final E... constants) {

        if (constants == null) {
            throw new NullPointerException("null constants");
        }

        for (final E constant : constants) {
            modifiers &= ~constant.fieldValue().intValue();
        }

        return modifiers;
    }

    private ModifierFieldEnums() {

        super();
    }

}
