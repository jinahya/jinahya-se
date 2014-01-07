/*
 * Copyright 2013 Jin Kwon <onacit at gmail.com>.
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


package com.github.jinahya.misc;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


/**
 * A dangerous class.
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public final class Dangerous {


    private static final Class<?> UNSAFE_CLASS;


    private static final Object UNSAFE_INSTANCE;


    static {

        try {
            UNSAFE_CLASS = Class.forName("sun.misc.Unsafe");
        } catch (final ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        }

        final Field f;
        try {
            f = UNSAFE_CLASS.getDeclaredField("theUnsafe");
        } catch (final NoSuchFieldException nsfe) {
            throw new InstantiationError(nsfe.getMessage());
        }

        if (!f.isAccessible()) {
            f.setAccessible(true);
        }

        try {
            UNSAFE_INSTANCE = f.get(null);
        } catch (final IllegalAccessException iae) {
            throw new InstantiationError(iae.getMessage());
        }
    }


    private static final Method ALLOCATE_MEMORY_METHOD;


    static {
        try {
            ALLOCATE_MEMORY_METHOD = UNSAFE_CLASS.getMethod(
                "allocateMemory", Long.TYPE);
            ALLOCATE_MEMORY_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    private static final Method REALLOCATE_MEMORY_METHOD;


    static {
        try {
            REALLOCATE_MEMORY_METHOD = UNSAFE_CLASS.getMethod(
                "reallocateMemory", Long.TYPE, Long.TYPE);
            REALLOCATE_MEMORY_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    private static final Method FREE_MEMORY_METHOD;


    static {
        try {
            FREE_MEMORY_METHOD = UNSAFE_CLASS.getMethod(
                "freeMemory", Long.TYPE);
            FREE_MEMORY_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    private static final Method STATIC_FIELD_OFFSET_METHOD;


    static {
        try {
            STATIC_FIELD_OFFSET_METHOD = UNSAFE_CLASS.getMethod(
                "staticFieldOffset", Field.class);
            STATIC_FIELD_OFFSET_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    private static final Method STATIC_FIELD_BASE_METHOD;


    static {
        try {
            STATIC_FIELD_BASE_METHOD = UNSAFE_CLASS.getMethod(
                "staticFieldBase", Field.class);
            STATIC_FIELD_BASE_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    private static final Method OBJECT_FIELD_OFFSET_METHOD;


    static {
        try {
            OBJECT_FIELD_OFFSET_METHOD = UNSAFE_CLASS.getMethod(
                "objectFieldOffset", Field.class);
            OBJECT_FIELD_OFFSET_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    private static final Method GET_MEMORY_BYTE_METHOD;


    static {
        try {
            GET_MEMORY_BYTE_METHOD = UNSAFE_CLASS.getMethod(
                "getByte", Long.TYPE);
            GET_MEMORY_BYTE_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    private static final Method PUT_MEMORY_BYTE_METHOD;


    static {
        try {
            PUT_MEMORY_BYTE_METHOD = UNSAFE_CLASS.getMethod(
                "putByte", Long.TYPE, Byte.TYPE);
            PUT_MEMORY_BYTE_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    private static final Method GET_MEMORY_SHORT_METHOD;


    static {
        try {
            GET_MEMORY_SHORT_METHOD = UNSAFE_CLASS.getMethod(
                "getShort", Long.TYPE);
            GET_MEMORY_SHORT_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    private static final Method PUT_MEMORY_SHORT_METHOD;


    static {
        try {
            PUT_MEMORY_SHORT_METHOD = UNSAFE_CLASS.getMethod(
                "putShort", Long.TYPE, Short.TYPE);
            PUT_MEMORY_SHORT_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    private static final Method GET_MEMORY_INT_METHOD;


    static {
        try {
            GET_MEMORY_INT_METHOD = UNSAFE_CLASS.getMethod("getInt", Long.TYPE);
            GET_MEMORY_INT_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    private static final Method PUT_MEMORY_INT_METHOD;


    static {
        try {
            PUT_MEMORY_INT_METHOD = UNSAFE_CLASS.getMethod(
                "putInt", Long.TYPE, Integer.TYPE);
            PUT_MEMORY_INT_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    private static final Method GET_MEMORY_LONG_METHOD;


    static {
        try {
            GET_MEMORY_LONG_METHOD = UNSAFE_CLASS.getMethod(
                "getLong", Long.TYPE);
            GET_MEMORY_LONG_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    private static final Method PUT_MEMORY_LONG_METHOD;


    static {
        try {
            PUT_MEMORY_LONG_METHOD = UNSAFE_CLASS.getMethod(
                "putLong", Long.TYPE, Long.TYPE);
            PUT_MEMORY_LONG_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    private static final Method GET_MEMORY_FLOAT_METHOD;


    static {
        try {
            GET_MEMORY_FLOAT_METHOD = UNSAFE_CLASS.getMethod(
                "getFloat", Long.TYPE);
            GET_MEMORY_FLOAT_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    private static final Method PUT_MEMORY_FLOAT_METHOD;


    static {
        try {
            PUT_MEMORY_FLOAT_METHOD = UNSAFE_CLASS.getMethod(
                "putFloat", Long.TYPE, Float.TYPE);
            PUT_MEMORY_FLOAT_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    private static final Method GET_MEMORY_DOUBLE_METHOD;


    static {
        try {
            GET_MEMORY_DOUBLE_METHOD = UNSAFE_CLASS.getMethod(
                "getDouble", Long.TYPE);
            GET_MEMORY_DOUBLE_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    private static final Method PUT_MEMORY_DOUBLE_METHOD;


    static {
        try {
            PUT_MEMORY_DOUBLE_METHOD = UNSAFE_CLASS.getMethod(
                "putDouble", Long.TYPE, Double.TYPE);
            PUT_MEMORY_DOUBLE_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    private static final Method GET_FIELD_BYTE_METHOD;


    static {
        try {
            GET_FIELD_BYTE_METHOD = UNSAFE_CLASS.getMethod(
                "getByte", Object.class, Long.TYPE);
            GET_FIELD_BYTE_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    private static final Method PUT_FIELD_BYTE_METHOD;


    static {
        try {
            PUT_FIELD_BYTE_METHOD = UNSAFE_CLASS.getMethod(
                "putByte", Object.class, Long.TYPE, Byte.TYPE);
            PUT_FIELD_BYTE_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    private static final Method GET_FIELD_SHORT_METHOD;


    static {
        try {
            GET_FIELD_SHORT_METHOD = UNSAFE_CLASS.getMethod(
                "getShort", Object.class, Long.TYPE);
            GET_FIELD_SHORT_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    private static final Method PUT_FIELD_SHORT_METHOD;


    static {
        try {
            PUT_FIELD_SHORT_METHOD = UNSAFE_CLASS.getMethod(
                "putShort", Object.class, Long.TYPE, Short.TYPE);
            PUT_FIELD_SHORT_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    private static final Method GET_FIELD_INT_METHOD;


    static {
        try {
            GET_FIELD_INT_METHOD = UNSAFE_CLASS.getMethod(
                "getInt", Object.class, Long.TYPE);
            GET_FIELD_INT_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    private static final Method PUT_FIELD_INT_METHOD;


    static {

        try {
            PUT_FIELD_INT_METHOD = UNSAFE_CLASS.getMethod(
                "putInt", Object.class, Long.TYPE, Integer.TYPE);
            PUT_FIELD_INT_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    private static final Method GET_FIELD_LONG_METHOD;


    static {

        try {
            GET_FIELD_LONG_METHOD = UNSAFE_CLASS.getMethod(
                "getLong", Object.class, Long.TYPE);
            GET_FIELD_LONG_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    private static final Method PUT_FIELD_LONG_METHOD;


    static {

        try {
            PUT_FIELD_LONG_METHOD = UNSAFE_CLASS.getMethod(
                "putLong", Object.class, Long.TYPE, Long.TYPE);
            PUT_FIELD_LONG_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    private static final Method GET_FIELD_FLOAT_METHOD;


    static {

        try {
            GET_FIELD_FLOAT_METHOD = UNSAFE_CLASS.getMethod(
                "getFloat", Object.class, Long.TYPE);
            GET_FIELD_FLOAT_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    private static final Method PUT_FIELD_FLOAT_METHOD;


    static {

        try {
            PUT_FIELD_FLOAT_METHOD = UNSAFE_CLASS.getMethod(
                "putFloat", Object.class, Long.TYPE, Float.TYPE);
            PUT_FIELD_FLOAT_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    private static final Method GET_FIELD_DOUBLE_METHOD;


    static {

        try {
            GET_FIELD_DOUBLE_METHOD = UNSAFE_CLASS.getMethod(
                "getDouble", Object.class, Long.TYPE);
            GET_FIELD_DOUBLE_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    private static final Method PUT_FIELD_DOUBLE_METHOD;


    static {

        try {
            PUT_FIELD_DOUBLE_METHOD = UNSAFE_CLASS.getMethod(
                "putDouble", Object.class, Long.TYPE, Double.TYPE);
            PUT_FIELD_DOUBLE_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static long allocateMemory(final long bytes) {

        try {
            return (Long) ALLOCATE_MEMORY_METHOD.invoke(UNSAFE_INSTANCE, bytes);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public static long reallocateMemory(final long address, final long bytes) {

        try {
            return (Long) REALLOCATE_MEMORY_METHOD.invoke(
                UNSAFE_INSTANCE, address, bytes);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public static void freeMemory(final long address) {

        try {
            FREE_MEMORY_METHOD.invoke(UNSAFE_INSTANCE, address);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public static long staticFieldOffset(final Field field) {

        try {
            return (Long) STATIC_FIELD_OFFSET_METHOD.invoke(
                UNSAFE_INSTANCE, field);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public static Object staticFieldBase(final Field field) {

        try {
            return STATIC_FIELD_BASE_METHOD.invoke(UNSAFE_INSTANCE, field);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public static long objectFieldOffset(final Field field) {

        try {
            return (Long) OBJECT_FIELD_OFFSET_METHOD.invoke(
                UNSAFE_INSTANCE, field);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    /**
     * Returns the offset of given field.
     *
     * @param field the field whose offset is calculated.
     *
     * @return the offset of {@code field}.
     *
     * @see #staticFieldOffset(java.lang.reflect.Field)
     * @see #objectFieldOffset(java.lang.reflect.Field)
     */
    public static long fieldOffset(final Field field) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        return Modifier.isStatic(field.getModifiers())
               ? staticFieldOffset(field) : objectFieldOffset(field);
    }


    public static byte getByte(final long offset) {

        try {
            return (Byte) GET_MEMORY_BYTE_METHOD.invoke(
                UNSAFE_INSTANCE, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public static void putByte(final long offset, final byte value) {

        try {
            PUT_MEMORY_BYTE_METHOD.invoke(UNSAFE_INSTANCE, offset, value);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public static short getShort(final long offset) {

        try {
            return (Short) GET_MEMORY_SHORT_METHOD.invoke(
                UNSAFE_INSTANCE, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public static void putShort(final long offset, final short value) {

        try {
            PUT_MEMORY_SHORT_METHOD.invoke(UNSAFE_INSTANCE, offset, value);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public static int getInt(final long offset) {

        try {
            return (Integer) GET_MEMORY_INT_METHOD.invoke(
                UNSAFE_INSTANCE, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public static void putInt(final long offset, final int value) {

        try {
            PUT_MEMORY_INT_METHOD.invoke(UNSAFE_INSTANCE, offset, value);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public static long getLong(final long offset) {

        try {
            return (Long) GET_MEMORY_LONG_METHOD.invoke(
                UNSAFE_INSTANCE, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public static void putLong(final long offset, final long value) {

        try {
            PUT_MEMORY_LONG_METHOD.invoke(UNSAFE_INSTANCE, offset, value);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public static float getFloat(final long offset) {

        try {
            return (Float) GET_MEMORY_FLOAT_METHOD.invoke(
                UNSAFE_INSTANCE, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public static void putFloat(final long offset, final float value) {

        try {
            PUT_MEMORY_FLOAT_METHOD.invoke(UNSAFE_INSTANCE, offset, value);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public static double getDouble(final long offset) {

        try {
            return (Double) GET_MEMORY_DOUBLE_METHOD.invoke(
                UNSAFE_INSTANCE, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public static void putDouble(final long offset, final double value) {

        try {
            PUT_MEMORY_DOUBLE_METHOD.invoke(UNSAFE_INSTANCE, offset, value);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public static byte getByte(final Object object, final long offset) {

        try {
            return (Byte) GET_FIELD_BYTE_METHOD.invoke(
                UNSAFE_INSTANCE, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    /**
     *
     * @param object
     * @param field
     *
     * @return
     *
     * @see #fieldOffset(java.lang.reflect.Field)
     */
    public static byte getByte(final Object object, final Field field) {

        return getByte(object, fieldOffset(field));
    }


    public static void putByte(final Object object, final long offset,
                               final byte value) {

        try {
            PUT_FIELD_BYTE_METHOD.invoke(
                UNSAFE_INSTANCE, object, offset, value);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public static void putByte(final Object object, final Field field,
                               final byte value) {

        putByte(object, fieldOffset(field), value);
    }


    public static short getShort(final Object object, final long offset) {

        try {
            return (Short) GET_FIELD_SHORT_METHOD.invoke(
                UNSAFE_INSTANCE, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public static short getShort(final Object object, final Field field) {

        return getShort(object, fieldOffset(field));
    }


    public static void putShort(final Object object, final long offset,
                                final short value) {

        try {
            PUT_FIELD_SHORT_METHOD.invoke(
                UNSAFE_INSTANCE, object, offset, value);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public static void putShort(final Object object, final Field field,
                                final short value) {

        putShort(object, fieldOffset(field), value);
    }


    public static int getInt(final Object object, final long offset) {

        try {
            return (Integer) GET_FIELD_INT_METHOD.invoke(
                UNSAFE_INSTANCE, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public static int getInt(final Object object, final Field field) {

        return getInt(object, fieldOffset(field));
    }


    public static void putInt(final Object object, final long offset,
                              final int value) {

        try {
            PUT_FIELD_INT_METHOD.invoke(UNSAFE_INSTANCE, object, offset, value);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public static void putInt(final Object object, final Field field,
                              final int value) {

        putInt(object, fieldOffset(field), value);
    }


    public static long getLong(final Object object, final long offset) {

        try {
            return (Long) GET_FIELD_LONG_METHOD.invoke(
                UNSAFE_INSTANCE, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public static long getLong(final Object object, final Field field) {

        return getLong(object, fieldOffset(field));
    }


    public static void putLong(final Object object, final long offset,
                               final long value) {

        try {
            PUT_FIELD_LONG_METHOD.invoke(
                UNSAFE_INSTANCE, object, offset, value);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public static void putLong(final Object object, final Field field,
                               final long value) {

        putLong(object, fieldOffset(field), value);
    }


    public static float getFloat(final Object object, final long offset) {

        try {
            return (Float) GET_FIELD_FLOAT_METHOD.invoke(
                UNSAFE_INSTANCE, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public static float getFloat(final Object object, final Field field) {

        return getFloat(object, fieldOffset(field));
    }


    public static void putFloat(final Object object, final long offset,
                                final float value) {

        try {
            PUT_FIELD_FLOAT_METHOD.invoke(
                UNSAFE_INSTANCE, object, offset, value);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public static void putFloat(final Object object, final Field field,
                                final float value) {

        putFloat(object, fieldOffset(field), value);
    }


    public static double getDouble(final Object object, final long offset) {

        try {
            return (Double) GET_FIELD_DOUBLE_METHOD.invoke(
                UNSAFE_INSTANCE, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public static double getDouble(final Object object, final Field field) {

        return getDouble(object, fieldOffset(field));
    }


    public static void putDouble(final Object object, final long offset,
                                 final double value) {

        try {
            PUT_FIELD_DOUBLE_METHOD.invoke(
                UNSAFE_INSTANCE, object, offset, value);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public static void putDouble(final Object object, final Field field,
                                 final double value) {

        putDouble(object, fieldOffset(field), value);
    }


    /**
     * Creates a new instance.
     */
    private Dangerous() {

        super();
    }


}

