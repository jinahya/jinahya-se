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
import java.security.ProtectionDomain;


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


    public static Object getUnsafeInstance() {

        return UNSAFE_INSTANCE;
    }


    // ------------------------------------------------------------- addressSize
    private static final Method ADDRESS_SIZE_METHOD;


    static {
        try {
            ADDRESS_SIZE_METHOD = UNSAFE_CLASS.getMethod("addressSize");
            ADDRESS_SIZE_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    /**
     *
     * @return
     */
    public static int addressSize() {

        try {
            return (Integer) ADDRESS_SIZE_METHOD.invoke(UNSAFE_INSTANCE);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ------------------------------------------------- allocateInstance(Clsss)
    private static final Method ALLOCATE_INSTANCE_METHOD;


    static {
        try {
            ALLOCATE_INSTANCE_METHOD = UNSAFE_CLASS.getMethod(
                "allocateInstance", Class.class);
            ALLOCATE_INSTANCE_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static Object allocateInstance(final Class<?> cls)
        throws InstantiationException {

        try {
            return (Integer) ALLOCATE_INSTANCE_METHOD.invoke(
                UNSAFE_INSTANCE, cls);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            final Throwable cause = ite.getCause();
            if (cause instanceof InstantiationException) {
                throw (InstantiationException) cause;
            }
            throw new RuntimeException(ite);
        }

    }


    // ---------------------------------------------------- allocateMemory(long)
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


    public static long allocateMemory(final long bytes) {

        try {
            return (Long) ALLOCATE_MEMORY_METHOD.invoke(UNSAFE_INSTANCE, bytes);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // -------------------------------------------------- arrayBaseOffset(Class)
    private static final Method ARRAY_BASE_OFFSET_METHOD;


    static {
        try {
            ARRAY_BASE_OFFSET_METHOD = UNSAFE_CLASS.getMethod(
                "arrayBaseOffset", Class.class);
            ARRAY_BASE_OFFSET_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static int arrayBaseOffset(final Class<?> arrayClass) {

        try {
            return (Integer) ARRAY_BASE_OFFSET_METHOD.invoke(
                UNSAFE_INSTANCE, arrayClass);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // -------------------------------------------------- arrayIndexScale(Class)
    private static final Method ARRAY_INDEX_SCALE_METHOD;


    static {
        try {
            ARRAY_INDEX_SCALE_METHOD = UNSAFE_CLASS.getMethod(
                "arrayIndexScale", Class.class);
            ARRAY_INDEX_SCALE_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static int arrayIndexScale(final Class<?> arrayClass) {

        try {
            return (Integer) ARRAY_BASE_OFFSET_METHOD.invoke(
                UNSAFE_INSTANCE, arrayClass);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ------------------------------- compareAndSwapInt(Object, long, int, int)
    private static final Method COMPARE_AND_SWAP_INT_METHOD;


    static {
        try {
            COMPARE_AND_SWAP_INT_METHOD = UNSAFE_CLASS.getMethod(
                "compareAndSwapInt", Object.class, Long.TYPE, Integer.TYPE,
                Integer.TYPE);
            COMPARE_AND_SWAP_INT_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    /**
     * Invokes {@code Unsafe#comapreAndSwapInt(Object, long, int, int)} with
     * given arguments.
     *
     * @param o
     * @param offset
     * @param expected
     * @param x
     *
     * @return
     */
    public static boolean compareAndSwapInt(final Object o, final long offset,
                                            final int expected, final int x) {

        try {
            return (Boolean) COMPARE_AND_SWAP_INT_METHOD.invoke(
                UNSAFE_INSTANCE, o, offset, expected, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ---------------------------- compareAndSwapLong(Object, long, long, long)
    private static final Method COMPARE_AND_SWAP_LONG_METHOD;


    static {
        try {
            COMPARE_AND_SWAP_LONG_METHOD = UNSAFE_CLASS.getMethod(
                "compareAndSwapLong", Object.class, Long.TYPE, Long.TYPE,
                Long.TYPE);
            COMPARE_AND_SWAP_LONG_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    /**
     * Invokes {@code Unsafe#comapreAndSwapLong(Object, long, long, long)} with
     * given arguments.
     *
     * @param o
     * @param offset
     * @param expected
     * @param x
     *
     * @return
     */
    public static boolean compareAndSwapLong(final Object o, final long offset,
                                             final long expected, final long x) {

        try {
            return (Boolean) COMPARE_AND_SWAP_LONG_METHOD.invoke(
                UNSAFE_INSTANCE, o, offset, expected, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ---------------------- compareAndSwapObject(Object, long, Object, Object)
    private static final Method COMPARE_AND_SWAP_OBJECT_METHOD;


    static {
        try {
            COMPARE_AND_SWAP_OBJECT_METHOD = UNSAFE_CLASS.getMethod(
                "compareAndSwapObject", Object.class, Long.TYPE, Object.class,
                Object.class);
            COMPARE_AND_SWAP_OBJECT_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    /**
     * Invokes {@code Unsafe#comapreAndSwapObject(Object, long, Object, Object)}
     * with given arguments.
     *
     * @param o
     * @param offset
     * @param expected
     * @param x
     *
     * @return
     */
    public static boolean compareAndSwapObject(final Object o,
                                               final long offset,
                                               final Object expected,
                                               final Object x) {

        try {
            return (Boolean) COMPARE_AND_SWAP_OBJECT_METHOD.invoke(
                UNSAFE_INSTANCE, o, offset, expected, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // -------------------------------------------- copyMemory(long, long, long)
    private static final Method COPY_MEMORY_lll_METHOD;


    static {
        try {
            COPY_MEMORY_lll_METHOD = UNSAFE_CLASS.getMethod(
                "copyMemory", Long.TYPE, Long.TYPE, Long.TYPE);
            COPY_MEMORY_lll_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    /**
     * Invokes {@code Unsafe#copyMemory(long, long, long)} with given arguments.
     *
     * @param srcAddress
     * @param destAddress
     * @param bytes
     */
    public static void copyMemory(final long srcAddress, final long destAddress,
                                  final long bytes) {

        try {
            COPY_MEMORY_lll_METHOD.invoke(
                UNSAFE_INSTANCE, srcAddress, destAddress, bytes);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ---------------------------- copyMemory(Object, long, Object, long, long)
    private static final Method COPY_MEMORY_OlOll_METHOD;


    static {
        try {
            COPY_MEMORY_OlOll_METHOD = UNSAFE_CLASS.getMethod(
                "copyMemory", Object.class, Long.TYPE, Object.class, Long.TYPE,
                Long.TYPE);
            COPY_MEMORY_OlOll_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    /**
     * Invokes {@code Unsafe#copyMemory(Object, long, Object, long, long)} with
     * given arguments.
     *
     * @param srcBase
     * @param srcAddress
     * @param destBase
     * @param destAddress
     * @param bytes
     */
    public static void copyMemory(final Object srcBase, final long srcAddress,
                                  final Object destBase, final long destAddress,
                                  final long bytes) {

        try {
            COPY_MEMORY_OlOll_METHOD.invoke(
                UNSAFE_INSTANCE, srcAddress, destAddress, bytes);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // --------------------------- defineAnonymousClass(Class, byte[], Object[])
    private static final Method DEFINE_ANONYMOUS_CLASS_METHOD;


    static {
        try {
            DEFINE_ANONYMOUS_CLASS_METHOD = UNSAFE_CLASS.getMethod(
                "defineAnonymousClass", Class.class, byte[].class,
                Object[].class);
            DEFINE_ANONYMOUS_CLASS_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void defineAnonymousClass(final Class<?> hostClass,
                                            final byte[] data,
                                            final Object[] cpPatches) {

        try {
            DEFINE_ANONYMOUS_CLASS_METHOD.invoke(
                UNSAFE_INSTANCE, hostClass, data, cpPatches);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ----------------------------------- defineClass(String, byte[], int, int)
    @Deprecated
static    Class defineClass(String name, byte[] b, int off, int len) {
        throw new UnsupportedOperationException("deprecated");
    }


    // ---- defineClass(String, byte[], int, int, ClassLoader, ProtectionDomain)
    private static final Method DEFINE_CLASS_METHOD;


    static {
        try {
            DEFINE_CLASS_METHOD = UNSAFE_CLASS.getMethod(
                "defineClass", String.class, byte[].class, Integer.TYPE,
                Integer.TYPE, ClassLoader.class, ProtectionDomain.class);
            DEFINE_CLASS_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static Class<?> defineClass(
        final String name, final byte[] b, final int off, final int len,
        final ClassLoader loader, final ProtectionDomain protectionDomain) {

        try {
            return (Class<?>) DEFINE_CLASS_METHOD.invoke(
                UNSAFE_INSTANCE, name, b, off, len, loader, protectionDomain);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ------------------------------------------- ensureClassInitialized(Class)
    private static final Method ENSURE_CLASS_INITIALIZED_METHOD;


    static {
        try {
            ENSURE_CLASS_INITIALIZED_METHOD = UNSAFE_CLASS.getMethod(
                "ensureClassInitialized", Class.class);
            ENSURE_CLASS_INITIALIZED_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void ensureClassInitialized(final Class<?> c) {

        try {
            ENSURE_CLASS_INITIALIZED_METHOD.invoke(UNSAFE_INSTANCE, c);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ------------------------------------------------------ fieldOffset(Field)
    @Deprecated
static    int fieldOffset(Field f) {
        throw new UnsupportedOperationException("deprecated");
    }


    // -------------------------------------------------------- freeMemory(long)
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


    public static void freeMemory(final long address) {

        try {
            FREE_MEMORY_METHOD.invoke(UNSAFE_INSTANCE, address);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // -------------------------------------------------------- getAddress(long)
    private static final Method GET_ADDRESS_METHOD;


    static {
        try {
            GET_ADDRESS_METHOD = UNSAFE_CLASS.getMethod(
                "getAddress", Long.TYPE);
            FREE_MEMORY_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static long getAddress(final long address) {

        try {
            return (Long) GET_ADDRESS_METHOD.invoke(UNSAFE_INSTANCE, address);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ---------------------------------------------- getBoolean(Object, offset)
    @Deprecated
    static boolean getBoolean(final Object o, final int offset) {

        throw new UnsupportedOperationException("deprecated");
    }


    // ------------------------------------------------ getBoolean(Object, long)
    private static final Method GET_BOOLEAN_METHOD;


    static {
        try {
            GET_BOOLEAN_METHOD = UNSAFE_CLASS.getMethod(
                "getBoolean", Object.class, Long.TYPE);
            GET_BOOLEAN_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static boolean getBoolean(final Object o, final long offset) {

        try {
            return (Boolean) GET_BOOLEAN_METHOD.invoke(
                UNSAFE_INSTANCE, o, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ---------------------------------------- getBooleanVolatile(Object, long)
    private static final Method GET_BOOLEAN_VOLATILE_METHOD;


    static {
        try {
            GET_BOOLEAN_VOLATILE_METHOD = UNSAFE_CLASS.getMethod(
                "getBooleanVolatile", Object.class, Long.TYPE);
            GET_BOOLEAN_VOLATILE_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static boolean getBooleanVolatile(final Object o,
                                             final long offset) {

        try {
            return (Boolean) GET_BOOLEAN_VOLATILE_METHOD.invoke(
                UNSAFE_INSTANCE, o, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ----------------------------------------------------------- getByte(long)
    private static final Method GET_BYTE_l_METHOD;


    static {
        try {
            GET_BYTE_l_METHOD = UNSAFE_CLASS.getMethod("getByte", Long.TYPE);
            GET_BYTE_l_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static byte getByte(final long offset) {

        try {
            return (Byte) GET_BYTE_l_METHOD.invoke(
                UNSAFE_INSTANCE, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // --------------------------------------------------- getByte(Object, long)
    private static final Method GET_BYTE_Ol_METHOD;


    static {
        try {
            GET_BYTE_Ol_METHOD = UNSAFE_CLASS.getMethod(
                "getByte", Object.class, Long.TYPE);
            GET_BYTE_Ol_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static byte getByte(final Object object, final long offset) {

        try {
            return (Byte) GET_BYTE_Ol_METHOD.invoke(
                UNSAFE_INSTANCE, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ---------------------------------------------------- getByte(Object, int)
    @Deprecated
    static byte getByte(final Object object, final int offset) {

        throw new UnsupportedOperationException("deprecated");
    }


    // ------------------------------------------- getByteVolatile(Object, long)
    private static final Method GET_BYTE_VOLATILE_METHOD;


    static {
        try {
            GET_BYTE_VOLATILE_METHOD = UNSAFE_CLASS.getMethod(
                "getByteVolatile", Object.class, Long.TYPE);
            GET_BYTE_VOLATILE_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static byte getByteVolatile(final Object object, final long offset) {

        try {
            return (Byte) GET_BYTE_VOLATILE_METHOD.invoke(
                UNSAFE_INSTANCE, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ----------------------------------------------------------- getChar(long)
    private static final Method GET_CHAR_l_METHOD;


    static {
        try {
            GET_CHAR_l_METHOD = UNSAFE_CLASS.getMethod("getChar", Long.TYPE);
            GET_CHAR_l_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static char getChar(final long offset) {

        try {
            return (Character) GET_CHAR_l_METHOD.invoke(
                UNSAFE_INSTANCE, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    @Deprecated
    static char getChar(final Object object, final int offset) {

        throw new UnsupportedOperationException("deprecated");
    }


    // --------------------------------------------------- getChar(Object, long)
    private static final Method GET_CHAR_Ol_METHOD;


    static {
        try {
            GET_CHAR_Ol_METHOD = UNSAFE_CLASS.getMethod(
                "getChar", Object.class, Long.TYPE);
            GET_CHAR_Ol_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static char getChar(final Object object, final long offset) {

        try {
            return (Character) GET_CHAR_Ol_METHOD.invoke(
                UNSAFE_INSTANCE, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ------------------------------------------- getCharVolatile(Object, long)
    private static final Method GET_CHAR_VOLATILE_METHOD;


    static {
        try {
            GET_CHAR_VOLATILE_METHOD = UNSAFE_CLASS.getMethod(
                "getCharVolatile", Object.class, Long.TYPE);
            GET_CHAR_VOLATILE_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static char getCharVolatile(final Object object, final long offset) {

        try {
            return (Character) GET_CHAR_VOLATILE_METHOD.invoke(
                UNSAFE_INSTANCE, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // --------------------------------------------------------- getDouble(long)
    private static final Method GET_DOUBLE_l_METHOD;


    static {
        try {
            GET_DOUBLE_l_METHOD = UNSAFE_CLASS.getMethod(
                "getDouble", Long.TYPE);
            GET_DOUBLE_l_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static double getDouble(final long offset) {

        try {
            return (Double) GET_DOUBLE_l_METHOD.invoke(UNSAFE_INSTANCE, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    @Deprecated
    static double getDouble(final Object object, final int offset) {

        throw new UnsupportedOperationException("deprecated");
    }


    // ------------------------------------------------- getDouble(Object, long)
    private static final Method GET_DOUBLE_Ol_METHOD;


    static {

        try {
            GET_DOUBLE_Ol_METHOD = UNSAFE_CLASS.getMethod(
                "getDouble", Object.class, Long.TYPE);
            GET_DOUBLE_Ol_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static double getDouble(final Object object, final long offset) {

        try {
            return (Double) GET_DOUBLE_Ol_METHOD.invoke(
                UNSAFE_INSTANCE, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ----------------------------------------- getDoubleVolatile(Object, long)
    private static final Method GET_DOUBLE_VOLATILE_METHOD;


    static {

        try {
            GET_DOUBLE_VOLATILE_METHOD = UNSAFE_CLASS.getMethod(
                "getDoubleVolatile", Object.class, Long.TYPE);
            GET_DOUBLE_VOLATILE_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static double getDoubleVolatile(final Object object,
                                           final long offset) {

        try {
            return (Double) GET_DOUBLE_VOLATILE_METHOD.invoke(
                UNSAFE_INSTANCE, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ---------------------------------------------------------- getFloat(long)
    private static final Method GET_FLOAT_l_METHOD;


    static {
        try {
            GET_FLOAT_l_METHOD = UNSAFE_CLASS.getMethod("getFloat", Long.TYPE);
            GET_FLOAT_l_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static float getFloat(final long offset) {

        try {
            return (Float) GET_FLOAT_l_METHOD.invoke(UNSAFE_INSTANCE, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    @Deprecated
    static float getFloat(final Object object, final int offset) {

        throw new UnsupportedOperationException("deprecated");
    }


    // -------------------------------------------------- getFloat(Object, long)
    private static final Method GET_FLOAT_Ol_METHOD;


    static {
        try {
            GET_FLOAT_Ol_METHOD = UNSAFE_CLASS.getMethod(
                "getFloat", Object.class, Long.TYPE);
            GET_FLOAT_Ol_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static float getFloat(final Object object, final long offset) {

        try {
            return (Float) GET_FLOAT_Ol_METHOD.invoke(
                UNSAFE_INSTANCE, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ----------------------------------------- getFloatVolatile(Object, long)
    private static final Method GET_FLOAT_VOLATILE_METHOD;


    static {

        try {
            GET_FLOAT_VOLATILE_METHOD = UNSAFE_CLASS.getMethod(
                "getFloatVolatile", Object.class, Long.TYPE);
            GET_FLOAT_VOLATILE_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static float getFloatVolatile(final Object object,
                                         final long offset) {

        try {
            return (Float) GET_FLOAT_VOLATILE_METHOD.invoke(
                UNSAFE_INSTANCE, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ------------------------------------------------------------ getInt(long)
    private static final Method GET_INT_l_METHOD;


    static {
        try {
            GET_INT_l_METHOD = UNSAFE_CLASS.getMethod("getInt", Long.TYPE);
            GET_INT_l_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static int getInt(final long offset) {

        try {
            return (Integer) GET_INT_l_METHOD.invoke(UNSAFE_INSTANCE, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    @Deprecated
    static int getInt(final Object object, final int offset) {

        throw new UnsupportedOperationException("deprecated");
    }


    // ---------------------------------------------------- getInt(Object, long)
    private static final Method GET_INT_Ol_METHOD;


    static {
        try {
            GET_INT_Ol_METHOD = UNSAFE_CLASS.getMethod(
                "getInt", Object.class, Long.TYPE);
            GET_INT_Ol_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static int getInt(final Object object, final long offset) {

        try {
            return (Integer) GET_INT_Ol_METHOD.invoke(
                UNSAFE_INSTANCE, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // -------------------------------------------- getIntVolatile(Object, long)
    private static final Method GET_INT_VOLATILE_METHOD;


    static {

        try {
            GET_INT_VOLATILE_METHOD = UNSAFE_CLASS.getMethod(
                "getIntVolatile", Object.class, Long.TYPE);
            GET_INT_VOLATILE_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static int getIntVolatile(final Object object,
                                     final long offset) {

        try {
            return (Integer) GET_INT_VOLATILE_METHOD.invoke(
                UNSAFE_INSTANCE, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ------------------------------------------- getLoadAverage(double[], int)
    private static final Method GET_LOAD_AVERAGE_METHOD;


    static {
        try {
            GET_LOAD_AVERAGE_METHOD = UNSAFE_CLASS.getMethod(
                "getLoadAverage", double[].class, Integer.TYPE);
            GET_LOAD_AVERAGE_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static int getLoadAverage(final double[] loadavg, final int nelems) {

        try {
            return (Integer) GET_LOAD_AVERAGE_METHOD.invoke(
                UNSAFE_INSTANCE, loadavg, nelems);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ----------------------------------------------------------- getLong(long)
    private static final Method GET_LONG_l_METHOD;


    static {
        try {
            GET_LONG_l_METHOD = UNSAFE_CLASS.getMethod("getLong", Long.TYPE);
            GET_LONG_l_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static long getLong(final long offset) {

        try {
            return (Long) GET_LONG_l_METHOD.invoke(UNSAFE_INSTANCE, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    @Deprecated
    static long getLong(final Object object, final int offset) {

        throw new UnsupportedOperationException("deprecated");
    }


    // --------------------------------------------------- getLong(Object, long)
    private static final Method GET_LONG_Ol_METHOD;


    static {
        try {
            GET_LONG_Ol_METHOD = UNSAFE_CLASS.getMethod(
                "getLong", Object.class, Long.TYPE);
            GET_LONG_Ol_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static long getLong(final Object object, final long offset) {

        try {
            return (Long) GET_LONG_Ol_METHOD.invoke(
                UNSAFE_INSTANCE, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ------------------------------------------- getLongVolatile(Object, long)
    private static final Method GET_LONG_VOLATILE_METHOD;


    static {
        try {
            GET_LONG_VOLATILE_METHOD = UNSAFE_CLASS.getMethod(
                "getLongVolatile", Object.class, Long.TYPE);
            GET_LONG_VOLATILE_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static long getLongVolatile(final Object object, final long offset) {

        try {
            return (Long) GET_LONG_VOLATILE_METHOD.invoke(
                UNSAFE_INSTANCE, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ------------------------------------------------- getObject(Object, long)
    private static final Method GET_OBJECT_Ol_METHOD;


    static {
        try {
            GET_OBJECT_Ol_METHOD = UNSAFE_CLASS.getMethod(
                "getObject", Object.class, Long.TYPE);
            GET_OBJECT_Ol_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static Object getObject(final Object object, final long offset) {

        try {
            return GET_OBJECT_Ol_METHOD.invoke(UNSAFE_INSTANCE, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // -------------------------------------------------- getObject(Object, int)
    @Deprecated
    static Object getObject(final Object object, final int offset) {

        throw new UnsupportedOperationException("deprecated");
    }


    // ----------------------------------------- getObjectVolatile(Object, long)
    private static final Method GET_OBJECT_VOLATILE_METHOD;


    static {
        try {
            GET_OBJECT_VOLATILE_METHOD = UNSAFE_CLASS.getMethod(
                "getObjectVolatile", Object.class, Long.TYPE);
            GET_OBJECT_VOLATILE_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static Object getObjectVolatile(final Object object,
                                           final long offset) {

        try {
            return GET_OBJECT_VOLATILE_METHOD.invoke(
                UNSAFE_INSTANCE, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // --------------------------------------------------------- getShort(short)
    private static final Method GET_SHORT_l_METHOD;


    static {
        try {
            GET_SHORT_l_METHOD = UNSAFE_CLASS.getMethod("getShort", Long.TYPE);
            GET_SHORT_l_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static short getShort(final long offset) {

        try {
            return (Short) GET_SHORT_l_METHOD.invoke(UNSAFE_INSTANCE, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    @Deprecated
    static short getShort(final Object object, final int offset) {

        throw new UnsupportedOperationException("deprecated");
    }


    // ------------------------------------------------- getShort(Object, short)
    private static final Method GET_SHORT_Ol_METHOD;


    static {
        try {
            GET_SHORT_Ol_METHOD = UNSAFE_CLASS.getMethod(
                "getShort", Object.class, Long.TYPE);
            GET_SHORT_Ol_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static short getShort(final Object object, final long offset) {

        try {
            return (Short) GET_SHORT_Ol_METHOD.invoke(
                UNSAFE_INSTANCE, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ------------------------------------------ getShortVolatile(Object, long)
    private static final Method GET_SHORT_VOLATILE_METHOD;


    static {
        try {
            GET_SHORT_VOLATILE_METHOD = UNSAFE_CLASS.getMethod(
                "getShortVolatile", Object.class, Long.TYPE);
            GET_SHORT_VOLATILE_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static short getShortVolatile(final Object object,
                                         final long offset) {

        try {
            return (Short) GET_SHORT_VOLATILE_METHOD.invoke(
                UNSAFE_INSTANCE, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ---------------------------------------------------- monitorEnter(Object)
    private static final Method MONITOR_ENTER_METHOD;


    static {
        try {
            MONITOR_ENTER_METHOD = UNSAFE_CLASS.getMethod(
                "monitorEnter", Object.class);
            MONITOR_ENTER_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static short monitorEnter(final Object o) {

        try {
            return (Short) MONITOR_ENTER_METHOD.invoke(UNSAFE_INSTANCE, o);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ----------------------------------------------------- monitorExit(Object)
    private static final Method MONITOR_EXIT_METHOD;


    static {
        try {
            MONITOR_EXIT_METHOD = UNSAFE_CLASS.getMethod(
                "monitorExit", Object.class);
            MONITOR_EXIT_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static short monitorExit(final Object o) {

        try {
            return (Short) MONITOR_EXIT_METHOD.invoke(UNSAFE_INSTANCE, o);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ------------------------------------------------ objectFieldOffset(Field)
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


    // -------------------------------------------------------------- pageSize()
    private static final Method PAGE_SIZE_METHOD;


    static {
        try {
            PAGE_SIZE_METHOD = UNSAFE_CLASS.getMethod("pageSize");
            PAGE_SIZE_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static int pageSize() {

        try {
            return (Integer) PAGE_SIZE_METHOD.invoke(UNSAFE_INSTANCE);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ----------------------------------------------------- park(boolean, long)
    private static final Method PARK_METHOD;


    static {
        try {
            PARK_METHOD = UNSAFE_CLASS.getMethod(
                "park", Boolean.TYPE, Long.TYPE);
            PARK_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void park(final boolean isAbsolute, final long time) {

        try {
            PARK_METHOD.invoke(UNSAFE_INSTANCE, isAbsolute, time);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // -------------------------------------------------- putAddress(long, long)
    private static final Method PUT_ADDRESS_METHOD;


    static {
        try {
            PUT_ADDRESS_METHOD = UNSAFE_CLASS.getMethod(
                "putAddress", Long.TYPE, Long.TYPE);
            PUT_ADDRESS_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void putAddress(final long address, final long time) {

        try {
            PUT_ADDRESS_METHOD.invoke(UNSAFE_INSTANCE, address, time);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    @Deprecated
    static void putBoolean(final Object object, final int offset,
                           final boolean x) {

        throw new UnsupportedOperationException("deprecated");
    }


    // --------------------------------------- putBoolean(Object, long, boolean)
    private static final Method PUT_BOOLEAN_Olb_METHOD;


    static {
        try {
            PUT_BOOLEAN_Olb_METHOD = UNSAFE_CLASS.getMethod(
                "putBoolean", Object.class, Long.TYPE, Boolean.TYPE);
            PUT_BOOLEAN_Olb_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void putBoolean(final Object object, final long offset,
                                  final boolean x) {

        try {
            PUT_BOOLEAN_Olb_METHOD.invoke(UNSAFE_INSTANCE, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ------------------------------- putBooleanVolatile(Object, long, boolean)
    private static final Method PUT_BOOLEAN_VOLATILE_METHOD;


    static {
        try {
            PUT_BOOLEAN_VOLATILE_METHOD = UNSAFE_CLASS.getMethod(
                "putBooleanVolatile", Object.class, Long.TYPE, Boolean.TYPE);
            PUT_BOOLEAN_VOLATILE_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void putBooleanVolatile(final Object object,
                                          final long offset, final boolean x) {

        try {
            PUT_BOOLEAN_VOLATILE_METHOD.invoke(
                UNSAFE_INSTANCE, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ----------------------------------------------------- putByte(long, byte)
    private static final Method PUT_BYTE_lb_METHOD;


    static {
        try {
            PUT_BYTE_lb_METHOD = UNSAFE_CLASS.getMethod(
                "putByte", Long.TYPE, Byte.TYPE);
            PUT_BYTE_lb_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void putByte(final long offset, final byte x) {

        try {
            PUT_BYTE_lb_METHOD.invoke(UNSAFE_INSTANCE, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    @Deprecated
    static void putByte(final Object object, final int offset,
                        final byte x) {

        throw new UnsupportedOperationException("deprecated");
    }


    // --------------------------------------------- putByte(Object, long, byte)
    private static final Method PUT_BYTE_Olb_METHOD;


    static {
        try {
            PUT_BYTE_Olb_METHOD = UNSAFE_CLASS.getMethod(
                "putByte", Object.class, Long.TYPE, Byte.TYPE);
            PUT_BYTE_Olb_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void putByte(final Object object, final long offset,
                               final byte x) {

        try {
            PUT_BYTE_Olb_METHOD.invoke(UNSAFE_INSTANCE, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ------------------------------------- putByteVolatile(Object, long, byte)
    private static final Method PUT_BYTE_VOLATILE_METHOD;


    static {
        try {
            PUT_BYTE_VOLATILE_METHOD = UNSAFE_CLASS.getMethod(
                "putByteVolatile", Object.class, Long.TYPE, Byte.TYPE);
            PUT_BYTE_VOLATILE_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void putByteVolatile(final Object object,
                                       final long offset, final byte x) {

        try {
            PUT_BYTE_VOLATILE_METHOD.invoke(UNSAFE_INSTANCE, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ----------------------------------------------------- putChar(long, char)
    private static final Method PUT_CHAR_lb_METHOD;


    static {
        try {
            PUT_CHAR_lb_METHOD = UNSAFE_CLASS.getMethod(
                "putChar", Long.TYPE, Character.TYPE);
            PUT_CHAR_lb_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void putChar(final long offset, final char x) {

        try {
            PUT_CHAR_lb_METHOD.invoke(UNSAFE_INSTANCE, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    @Deprecated
    static void putChar(final Object object, final int offset, final char x) {

        throw new UnsupportedOperationException("deprecated");
    }


    // --------------------------------------------- putChar(Object, long, char)
    private static final Method PUT_CHAR_Olb_METHOD;


    static {
        try {
            PUT_CHAR_Olb_METHOD = UNSAFE_CLASS.getMethod(
                "putChar", Object.class, Long.TYPE, Character.TYPE);
            PUT_CHAR_Olb_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void putChar(final Object object, final long offset,
                               final char x) {

        try {
            PUT_CHAR_Olb_METHOD.invoke(UNSAFE_INSTANCE, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ------------------------------------- putCharVolatile(Object, long, char)
    private static final Method PUT_CHAR_VOLATILE_METHOD;


    static {
        try {
            PUT_CHAR_VOLATILE_METHOD = UNSAFE_CLASS.getMethod(
                "putCharVolatile", Object.class, Long.TYPE, Character.TYPE);
            PUT_CHAR_VOLATILE_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void putCharVolatile(final Object object,
                                       final long offset, final char x) {

        try {
            PUT_CHAR_VOLATILE_METHOD.invoke(UNSAFE_INSTANCE, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ----------------------------------------------------- putDouble(long, double)
    private static final Method PUT_DOUBLE_lb_METHOD;


    static {
        try {
            PUT_DOUBLE_lb_METHOD = UNSAFE_CLASS.getMethod(
                "putDouble", Long.TYPE, Double.TYPE);
            PUT_DOUBLE_lb_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void putDouble(final long offset, final double x) {

        try {
            PUT_DOUBLE_lb_METHOD.invoke(UNSAFE_INSTANCE, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    @Deprecated
    static void putDouble(final Object object, final int offset,
                          final double x) {

        throw new UnsupportedOperationException("deprecated");
    }


    // ----------------------------------------- putDouble(Object, long, double)
    private static final Method PUT_DOUBLE_Olb_METHOD;


    static {
        try {
            PUT_DOUBLE_Olb_METHOD = UNSAFE_CLASS.getMethod(
                "putDouble", Object.class, Long.TYPE, Double.TYPE);
            PUT_DOUBLE_Olb_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void putDouble(final Object object, final long offset,
                                 final double x) {

        try {
            PUT_DOUBLE_Olb_METHOD.invoke(UNSAFE_INSTANCE, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ------------------------------------- putDoubleVolatile(Object, long, double)
    private static final Method PUT_DOUBLE_VOLATILE_METHOD;


    static {
        try {
            PUT_DOUBLE_VOLATILE_METHOD = UNSAFE_CLASS.getMethod(
                "putDoubleVolatile", Object.class, Long.TYPE, Double.TYPE);
            PUT_DOUBLE_VOLATILE_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void putDoubleVolatile(final Object object,
                                         final long offset, final double x) {

        try {
            PUT_DOUBLE_VOLATILE_METHOD.invoke(UNSAFE_INSTANCE, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // --------------------------------------------------- putFloat(long, float)
    private static final Method PUT_FLOAT_lb_METHOD;


    static {
        try {
            PUT_FLOAT_lb_METHOD = UNSAFE_CLASS.getMethod(
                "putFloat", Long.TYPE, Float.TYPE);
            PUT_FLOAT_lb_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void putFloat(final long offset, final float x) {

        try {
            PUT_FLOAT_lb_METHOD.invoke(UNSAFE_INSTANCE, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    @Deprecated
    static void putFloat(final Object object, final int offset, final float x) {

        throw new UnsupportedOperationException("deprecated");
    }


    // ------------------------------------------- putFloat(Object, long, float)
    private static final Method PUT_FLOAT_Olb_METHOD;


    static {
        try {
            PUT_FLOAT_Olb_METHOD = UNSAFE_CLASS.getMethod(
                "putFloat", Object.class, Long.TYPE, Float.TYPE);
            PUT_FLOAT_Olb_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void putFloat(final Object object, final long offset,
                                final float x) {

        try {
            PUT_FLOAT_Olb_METHOD.invoke(UNSAFE_INSTANCE, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ----------------------------------- putFloatVolatile(Object, long, float)
    private static final Method PUT_FLOAT_VOLATILE_METHOD;


    static {
        try {
            PUT_FLOAT_VOLATILE_METHOD = UNSAFE_CLASS.getMethod(
                "putFloatVolatile", Object.class, Long.TYPE, Float.TYPE);
            PUT_FLOAT_VOLATILE_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void putFloatVolatile(final Object object, final long offset,
                                        final float x) {

        try {
            PUT_FLOAT_VOLATILE_METHOD.invoke(
                UNSAFE_INSTANCE, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ------------------------------------------------------- putInt(long, int)
    private static final Method PUT_INT_lb_METHOD;


    static {
        try {
            PUT_INT_lb_METHOD = UNSAFE_CLASS.getMethod(
                "putInt", Long.TYPE, Integer.TYPE);
            PUT_INT_lb_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void putInt(final long offset, final int x) {

        try {
            PUT_INT_lb_METHOD.invoke(UNSAFE_INSTANCE, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    @Deprecated
    static void putInt(final Object object, final int offset, final int x) {

        throw new UnsupportedOperationException("deprecatd");
    }


    // ----------------------------------------------- putInt(Object, long, int)
    private static final Method PUT_INT_Olb_METHOD;


    static {
        try {
            PUT_INT_Olb_METHOD = UNSAFE_CLASS.getMethod(
                "putInt", Object.class, Long.TYPE, Integer.TYPE);
            PUT_INT_Olb_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void putInt(final Object object, final long offset,
                              final int x) {

        try {
            PUT_INT_Olb_METHOD.invoke(UNSAFE_INSTANCE, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // --------------------------------------- putIntVolatile(Object, long, int)
    private static final Method PUT_INT_VOLATILE_METHOD;


    static {
        try {
            PUT_INT_VOLATILE_METHOD = UNSAFE_CLASS.getMethod(
                "putIntVolatile", Object.class, Long.TYPE, Integer.TYPE);
            PUT_INT_VOLATILE_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void putIntVolatile(final Object object, final long offset,
                                      final int x) {

        try {
            PUT_INT_VOLATILE_METHOD.invoke(UNSAFE_INSTANCE, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ----------------------------------------------------- putLong(long, long)
    private static final Method PUT_LONG_lb_METHOD;


    static {
        try {
            PUT_LONG_lb_METHOD = UNSAFE_CLASS.getMethod(
                "putLong", Long.TYPE, Long.TYPE);
            PUT_LONG_lb_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void putLong(final long offset, final long x) {

        try {
            PUT_LONG_lb_METHOD.invoke(UNSAFE_INSTANCE, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    @Deprecated
    static void putLong(final Object object, final int offset, final long x) {

        throw new UnsupportedOperationException("deprecated");
    }


    // --------------------------------------------- putLong(Object, long, long)
    private static final Method PUT_LONG_Olb_METHOD;


    static {
        try {
            PUT_LONG_Olb_METHOD = UNSAFE_CLASS.getMethod(
                "putLong", Object.class, Long.TYPE, Long.TYPE);
            PUT_LONG_Olb_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void putLong(final Object object, final long offset,
                               final long x) {

        try {
            PUT_LONG_Olb_METHOD.invoke(UNSAFE_INSTANCE, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ------------------------------------- putLongVolatile(Object, long, long)
    private static final Method PUT_LONG_VOLATILE_METHOD;


    static {
        try {
            PUT_LONG_VOLATILE_METHOD = UNSAFE_CLASS.getMethod(
                "putLongVolatile", Object.class, Long.TYPE, Long.TYPE);
            PUT_LONG_VOLATILE_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void putLongVolatile(final Object object, final long offset,
                                       final long x) {

        try {
            PUT_LONG_VOLATILE_METHOD.invoke(UNSAFE_INSTANCE, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    @Deprecated
    static void putObject(final Object object, final int offset,
                          final Object x) {

        throw new UnsupportedOperationException("deprecated");
    }


    // --------------------------------------- putObject(Object, long, Object)
    private static final Method PUT_OBJECT_Olb_METHOD;


    static {
        try {
            PUT_OBJECT_Olb_METHOD = UNSAFE_CLASS.getMethod(
                "putObject", Object.class, Long.TYPE, Object.class);
            PUT_OBJECT_Olb_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void putObject(final Object object, final long offset,
                                 final Object x) {

        try {
            PUT_OBJECT_Olb_METHOD.invoke(UNSAFE_INSTANCE, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // --------------------------------- putObjectVolatile(Object, long, Object)
    private static final Method PUT_OBJECT_VOLATILE_METHOD;


    static {
        try {
            PUT_OBJECT_VOLATILE_METHOD = UNSAFE_CLASS.getMethod(
                "putObjectVolatile", Object.class, Long.TYPE, Object.class);
            PUT_OBJECT_VOLATILE_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void putObjectVolatile(final Object object, final long offset,
                                         final Object x) {

        try {
            PUT_OBJECT_VOLATILE_METHOD.invoke(
                UNSAFE_INSTANCE, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ---------------------------------------- putOrderedInt(Object, long, int)
    private static final Method PUT_ORDERED_INT_METHOD;


    static {
        try {
            PUT_ORDERED_INT_METHOD = UNSAFE_CLASS.getMethod(
                "putOrderedInt", Object.class, Long.TYPE, Integer.TYPE);
            PUT_ORDERED_INT_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void putOrderedInt(final Object object, final long offset,
                                     final int x) {

        try {
            PUT_ORDERED_INT_METHOD.invoke(
                UNSAFE_INSTANCE, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // --------------------------------------- putOrderedLong(Object, long, int)
    private static final Method PUT_ORDERED_LONG_METHOD;


    static {
        try {
            PUT_ORDERED_LONG_METHOD = UNSAFE_CLASS.getMethod(
                "putOrderedLong", Object.class, Long.TYPE, Long.TYPE);
            PUT_ORDERED_LONG_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void putOrderedLong(final Object object, final long offset,
                                      final long x) {

        try {
            PUT_ORDERED_LONG_METHOD.invoke(UNSAFE_INSTANCE, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ---------------------------------- putOrderedObject(Object, long, Object)
    private static final Method PUT_ORDERED_OBJECT_METHOD;


    static {
        try {
            PUT_ORDERED_OBJECT_METHOD = UNSAFE_CLASS.getMethod(
                "putOrderedObject", Object.class, Long.TYPE, Object.class);
            PUT_ORDERED_OBJECT_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void putOrderedObject(final Object object, final long offset,
                                        final Object x) {

        try {
            PUT_ORDERED_OBJECT_METHOD.invoke(
                UNSAFE_INSTANCE, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // --------------------------------------------------- putShort(long, short)
    private static final Method PUT_SHORT_lb_METHOD;


    static {
        try {
            PUT_SHORT_lb_METHOD = UNSAFE_CLASS.getMethod(
                "putShort", Long.TYPE, Short.TYPE);
            PUT_SHORT_lb_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void putShort(final long offset, final short x) {

        try {
            PUT_SHORT_lb_METHOD.invoke(UNSAFE_INSTANCE, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    @Deprecated
    static void putShort(final Object object, final int offset, final short x) {

        throw new UnsupportedOperationException("deprecated");
    }


    // ------------------------------------------- putShort(Object, long, short)
    private static final Method PUT_SHORT_Olb_METHOD;


    static {
        try {
            PUT_SHORT_Olb_METHOD = UNSAFE_CLASS.getMethod(
                "putShort", Object.class, Long.TYPE, Short.TYPE);
            PUT_SHORT_Olb_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void putShort(final Object object, final long offset,
                                final short x) {

        try {
            PUT_SHORT_Olb_METHOD.invoke(UNSAFE_INSTANCE, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ------------------------------------- putShortVolatile(Object, long, short)
    private static final Method PUT_SHORT_VOLATILE_METHOD;


    static {
        try {
            PUT_SHORT_VOLATILE_METHOD = UNSAFE_CLASS.getMethod(
                "putShortVolatile", Object.class, Long.TYPE, Short.TYPE);
            PUT_SHORT_VOLATILE_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void putShortVolatile(final Object object,
                                        final long offset, final short x) {

        try {
            PUT_SHORT_VOLATILE_METHOD.invoke(UNSAFE_INSTANCE, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // -------------------------------------------- reallocateMemory(long, long)
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


    // --------------------------------------------- setMemory(long, long, byte)
    private static final Method SET_MEMORY_llb_METHOD;


    static {
        try {
            SET_MEMORY_llb_METHOD = UNSAFE_CLASS.getMethod(
                "setMemory", Long.TYPE, Long.TYPE, Byte.TYPE);
            SET_MEMORY_llb_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void setMemory(final long address, final long bytes,
                                 final byte value) {

        try {
            SET_MEMORY_llb_METHOD.invoke(UNSAFE_INSTANCE, address, bytes, value);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ------------------------------------- setMemory(Object, long, long, byte)
    private static final Method SET_MEMORY_Ollb_METHOD;


    static {
        try {
            SET_MEMORY_Ollb_METHOD = UNSAFE_CLASS.getMethod(
                "setMemory", Object.class, Long.TYPE, Long.TYPE, Byte.TYPE);
            SET_MEMORY_Ollb_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void setMemory(final Object o, final long address,
                                 final long bytes, final byte value) {

        try {
            SET_MEMORY_Ollb_METHOD.invoke(
                UNSAFE_INSTANCE, o, address, bytes, value);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ---------------------------------------------- shouldBeInitialized(Class)
    private static final Method SHOULD_BE_INITIALIZED_METHOD;


    static {
        try {
            SHOULD_BE_INITIALIZED_METHOD = UNSAFE_CLASS.getMethod(
                "shouldBeInitialized", Class.class);
            SHOULD_BE_INITIALIZED_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static void shouldBeInitialized(final Class<?> c) {

        try {
            SHOULD_BE_INITIALIZED_METHOD.invoke(UNSAFE_INSTANCE, c);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // -------------------------------------------------- staticFieldBase(Field)
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


    public static Object staticFieldBase(final Field field) {

        try {
            return STATIC_FIELD_BASE_METHOD.invoke(UNSAFE_INSTANCE, field);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    @Deprecated
    static Object staticFieldBase(final Class c) {

        throw new UnsupportedOperationException("deprecated");
    }


    // ------------------------------------------------ staticFieldOffset(Field)
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


    // ----------------------------------------------- throwException(Throwable)
    private static final Method THROW_EXCEPTION_METHOD;


    static {
        try {
            THROW_EXCEPTION_METHOD = UNSAFE_CLASS.getMethod(
                "throwException", Throwable.class);
            THROW_EXCEPTION_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static long throwException(final Throwable ee) {

        try {
            return (Long) THROW_EXCEPTION_METHOD.invoke(UNSAFE_INSTANCE, ee);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ------------------------------------------------- tryMonitorEnter(Object)
    private static final Method TRY_MONITOR_ENTER_METHOD;


    static {
        try {
            TRY_MONITOR_ENTER_METHOD = UNSAFE_CLASS.getMethod(
                "tryMonitorEnter", Object.class);
            TRY_MONITOR_ENTER_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static long tryMonitorEnter(final Object o) {

        try {
            return (Long) TRY_MONITOR_ENTER_METHOD.invoke(UNSAFE_INSTANCE, o);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    // ---------------------------------------------------------- unpark(Object)
    private static final Method UNPARK_METHOD;


    static {
        try {
            UNPARK_METHOD = UNSAFE_CLASS.getMethod("unpark", Object.class);
            UNPARK_METHOD.setAccessible(true);
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.toString());
        }
    }


    public static long unpark(final Object thread) {

        try {
            return (Long) UNPARK_METHOD.invoke(UNSAFE_INSTANCE, thread);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


//    // --------------------------------------------------------- wait(long, int)
//    private static final Method WAIT_METHOD;
//
//
//    static {
//        try {
//            WAIT_METHOD = UNSAFE_CLASS.getMethod("wait", Long.TYPE, Integer.TYPE);
//            WAIT_METHOD.setAccessible(true);
//        } catch (final NoSuchMethodException nsme) {
//            throw new InstantiationError(nsme.toString());
//        }
//    }
//
//
//    public static Object wait(final long l, final int i) {
//
//        try {
//            return UNPARK_METHOD.invoke(UNSAFE_INSTANCE, l, i);
//        } catch (final IllegalAccessException iae) {
//            throw new RuntimeException(iae);
//        } catch (final InvocationTargetException ite) {
//            throw new RuntimeException(ite);
//        }
//    }
//

    /**
     * Creates a new instance.
     */
    private Dangerous() {

        super();
    }


}

