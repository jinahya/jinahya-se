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

import com.github.jinahya.lang.Classes;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class JinahyaFields {

    @Deprecated
    public static Field removeModifiers(final Field field, final int delta)
            throws NoSuchFieldException, IllegalAccessException {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        JinahyaModifiers.requireAllFieldModifiers(delta);

        int modifiers = field.getModifiers();
        modifiers &= ~delta;

        final Field reflected = Field.class.getDeclaredField("modifiers");
        reflected.setAccessible(true);
        reflected.setInt(field, modifiers);

        return field;
    }

    @Deprecated
    public static Field unprivate(final Field field)
            throws NoSuchFieldException, IllegalAccessException {

        return removeModifiers(field, Modifier.PRIVATE);
    }

    @Deprecated
    public static Field unfinal(final Field field)
            throws NoSuchFieldException, IllegalAccessException {

        return removeModifiers(field, Modifier.FINAL);
    }

    /**
     * Checks that the specified field's type is assignable to specified type. This method is designed primarily for
     * doing parameter validation in methods and constructors, as demonstrated below:
     * <blockquote><pre>
     * {@code
     * public void foo(Field field) {
     *     Fields.requireTypeAssignableTo(field, CharSequence.class));
     * }
     * }
     * </pre></blockquote>
     *
     * @param field the field whose type requires to be assignable to {@code to}.
     * @param to    the type requires to be assignable from {@code field}'s type
     * @return {@code field} if its type is assignable to {@code to}.
     * @throws NullPointerException     if {@code field} is {@code null}.
     * @throws NullPointerException     if {@code type} is {@code null}.
     * @throws IllegalArgumentException if {@code field}'s type is not assignable to {@code to}.
     * @see Field#getType()
     * @see Classes#requireAssignableTo(java.lang.Class, java.lang.Class)
     */
    public static Field requireTypeAssignableTo(final Field field,
                                                final Class<?> to) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        if (to == null) {
            throw new NullPointerException("null to");
        }

        Classes.requireAssignableTo(field.getType(), to);

        return field;
    }

    /**
     * Checks that the specified field's type is assignable from specified type. This method is designed primarily for
     * doing parameter validation in methods and constructors, as demonstrated below:
     * <blockquote><pre>
     * {@code
     * public void foo(Field field) {
     *     Fields.requireTypeAssignableFrom(field, String.class);
     * }
     * }
     * </pre></blockquote>
     *
     * @param field the field whose type requires to be assignable from {@code type}.
     * @param from  the type requires to be assignable to {@code field}'s type.
     * @return {@code field} if its type is assignable from {@code from}.
     * @throws NullPointerException     if {@code field} is {@code null}
     * @throws NullPointerException     if {@code type} is {@code null}
     * @throws IllegalArgumentException if {@code field}'s type is not assignable from {@code from}.
     * @see Field#getType()
     * @see Classes#requireAssignableFrom(java.lang.Class, java.lang.Class)
     */
    public static Field requireTypeAssignableFrom(final Field field,
                                                  final Class<?> from) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        if (from == null) {
            throw new NullPointerException("null from");
        }

        Classes.requireAssignableFrom(field.getType(), from);

        return field;
    }

    /**
     * Creates a new instance.
     */
    private JinahyaFields() {

        super();
    }
}
