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


import com.github.jinahya.lang.Classes;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public final class Fields {


    /**
     *
     * @param field
     * @param delta
     *
     * @return
     *
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @deprecated
     */
    @Deprecated
    public static Field removeModifiers(final Field field, final int delta)
        throws NoSuchFieldException, IllegalAccessException {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        Modifiers.requireFieldModifiers(delta);

        int modifiers = field.getModifiers();
        modifiers &= ~delta;

        final Field reflected = Field.class.getDeclaredField("modifiers");
        reflected.setAccessible(true);
        reflected.setInt(field, modifiers);

        return field;
    }


    public static Field unprivate(final Field field)
        throws NoSuchFieldException, IllegalAccessException {

        return removeModifiers(field, Modifier.PRIVATE);
    }


    public static Field unfinal(final Field field)
        throws NoSuchFieldException, IllegalAccessException {

        return removeModifiers(field, Modifier.FINAL);
    }


    /**
     *
     * @param field
     * @param type
     *
     * @return
     */
    public static Field requireTypeIsAssignableTo(final Field field,
                                                  final Class<?> type) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        if (type == null) {
            throw new NullPointerException("null type");
        }

        Classes.requireAssignableTo(field.getType(), type);

        return field;
    }


    /**
     * Check if the type of given field is assignable from specified child type.
     *
     * @param field the field whose type requires to be assignable from
     * {@code type}.
     * @param type the type requires to be assignable to {@code field}'s type.
     *
     * @return given field.
     *
     * @throws NullPointerException if {@code field} is {@code null}
     * @throws NullPointerException if {@code type} is {@code null}
     * @throws IllegalArgumentException if {@code field}'s type is not
     * assignable from {@code type}.
     */
    public static Field requireTypeIsAssignableFrom(final Field field,
                                                    final Class<?> type) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        if (type == null) {
            throw new NullPointerException("null type");
        }

        Classes.requireAssignableFrom(field.getType(), type);

        return field;
    }


    private Fields() {

        super();
    }


}

