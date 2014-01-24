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


import java.lang.reflect.Constructor;
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


    private static final Constructor<?> UNSAFE_CONSTRUCTOR;


    static {

        try {
            UNSAFE_CLASS = Class.forName("sun.misc.Unsafe");
        } catch (final ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        }

        try {
            UNSAFE_CONSTRUCTOR = UNSAFE_CLASS.getDeclaredConstructor();
            if (!UNSAFE_CONSTRUCTOR.isAccessible()) {
                UNSAFE_CONSTRUCTOR.setAccessible(true);
            }
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.getMessage());
        }
    }


    public static Object newUnsafeInstance() {

        try {
            return UNSAFE_CONSTRUCTOR.newInstance();
        } catch (final InstantiationException ie) {
            throw new RuntimeException(ie);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
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


    /**
     * Creates a new instance.
     *
     * @return a new instance.
     */
    public static Dangerous newInstance() {

        return new Dangerous(newUnsafeInstance());
    }


    /**
     * Creates a new instance.
     */
    private Dangerous(final Object unsafe) {

        super();

        if (unsafe == null) {
            throw new NullPointerException("null unsafe");
        }

        this.unsafe = unsafe;
    }


    private Object invoke(final Method method, final Object object,
                          final Object... parameters) {

        try {
            return method.invoke(object, parameters);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            final Throwable cause = ite.getCause();
            if (RuntimeException.class.isInstance(cause)) {
                throw (RuntimeException) cause;
            }
            throw new RuntimeException(ite);
        }
    }


    /**
     * Invokes {@code Unsafe#addressSize()} and returns result.
     *
     * @return the result of {@code Unsafe#addressSize()}.
     */
    public int addressSize() {

        return (Integer) invoke(ADDRESS_SIZE_METHOD, unsafe);
    }


    public Object allocateInstance(final Class<?> cls)
        throws InstantiationException {

        try {
            return ALLOCATE_INSTANCE_METHOD.invoke(unsafe, cls);
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


    public <T> T allocateInstanceGeneric(final Class<T> cls)
        throws InstantiationException {

        if (cls == null) {
            throw new NullPointerException("null cls");
        }

        return cls.cast(allocateInstance(cls));
    }


    public long allocateMemory(final long bytes) {

        return (Long) invoke(ALLOCATE_MEMORY_METHOD, unsafe, bytes);
    }


    public int arrayBaseOffset(final Class<?> arrayClass) {

        return (Integer) invoke(ARRAY_BASE_OFFSET_METHOD, unsafe, arrayClass);
    }


    public int arrayIndexScale(final Class<?> arrayClass) {

        return (Integer) invoke(ARRAY_INDEX_SCALE_METHOD, unsafe, arrayClass);
    }


    public boolean compareAndSwapInt(final Object o, final long offset,
                                     final int expected, final int x) {

        return (Boolean) invoke(COMPARE_AND_SWAP_INT_METHOD, unsafe, o, offset,
                                expected, x);
    }


    /**
     *
     * @param field
     * @param expected
     * @param x
     *
     * @return
     *
     * @throws NullPointerException if {@code field} is {@code null}
     * @throws IllegalArgumentException if {@code field} is not a static field.
     */
    public boolean staticCompareAndSwapInt(final Field field,
                                           final int expected, final int x) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        if (!Modifier.isStatic(modifiers)) {
            throw new IllegalArgumentException("non-static field");
        }

        return compareAndSwapInt(staticFieldBase(field),
                                 staticFieldOffset(field), expected, x);
    }


    /**
     *
     * @param base
     * @param field
     * @param expected
     * @param x
     *
     * @return
     *
     * @throws NullPointerException if {@code field} is {@code null}.
     * @throws IllegalArgumentException if {@code field} is a static field.
     */
    public boolean instanceCompareAndSwapInt(final Object base,
                                             final Field field,
                                             final int expected, final int x) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        if (Modifier.isStatic(modifiers)) {
            throw new IllegalArgumentException("static field");
        }

        return compareAndSwapInt(base, objectFieldOffset(field), expected, x);
    }


    public boolean compareAndSwapLong(final Object o, final long offset,
                                      final long expected, final long x) {

        return (Boolean) invoke(COMPARE_AND_SWAP_LONG_METHOD, unsafe, o, offset,
                                expected, x);
    }


    /**
     *
     * @param field
     * @param expected
     * @param x
     *
     * @return
     *
     * @throws NullPointerException if {@code field} is {@code null}
     * @throws IllegalArgumentException if {@code field} is not a static field.
     */
    public boolean staticCompareAndSwapLong(final Field field,
                                            final long expected, final long x) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        if (!Modifier.isStatic(modifiers)) {
            throw new IllegalArgumentException("non-static field");
        }

        return compareAndSwapLong(staticFieldBase(field),
                                  staticFieldOffset(field), expected, x);
    }


    /**
     *
     * @param base
     * @param field
     * @param expected
     * @param x
     *
     * @return
     *
     * @throws NullPointerException if {@code field} is {@code null}
     * @throws IllegalArgumentException if {@code field} is a static field.
     */
    public boolean instanceCompareAndSwapLong(final Object base,
                                              final Field field,
                                              final long expected,
                                              final long x) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        if (Modifier.isStatic(modifiers)) {
            throw new IllegalArgumentException("static field");
        }

        return compareAndSwapLong(base, objectFieldOffset(field), expected, x);
    }


    public boolean compareAndSwapObject(final Object o, final long offset,
                                        final Object expected, final Object x) {

        return (Boolean) invoke(COMPARE_AND_SWAP_OBJECT_METHOD, unsafe, o,
                                offset, expected, x);
    }


    /**
     *
     * @param field
     * @param expected
     * @param x
     *
     * @return
     *
     * @throws NullPointerException if {@code field} is {@code null}.
     *
     * @see #compareAndSwapObject(java.lang.Object, long, java.lang.Object,
     * java.lang.Object)
     */
    public boolean staticCompareAndSwapObject(final Field field,
                                              final Object expected,
                                              final Object x) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        if (!Modifier.isStatic(modifiers)) {
            throw new IllegalArgumentException("non-static field");
        }

        return compareAndSwapObject(staticFieldBase(field),
                                    staticFieldOffset(field), expected, x);
    }


    /**
     *
     * @param <T>
     * @param field
     * @param type
     * @param expected
     * @param x
     *
     * @return
     *
     * @throws NullPointerException if {@code field} is {@code null}.
     *
     * @see Field#getType()
     * @see Class#isAssignableFrom(java.lang.Class)
     * @see #staticCompareAndSwapObject(java.lang.reflect.Field,
     * java.lang.Object, java.lang.Object)
     */
    public <T> boolean staticCompareAndSwapObjectGeneric(
        final Field field, final Class<T> type, final T expected, final T x) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        if (!field.getType().isAssignableFrom(type)) {
            throw new IllegalArgumentException(
                "field.type(" + field.getType()
                + ") is not assignable from type(" + type + ")");
        }

        return staticCompareAndSwapObject(field, expected, x);
    }


    /**
     *
     * @param base
     * @param field
     * @param expected
     * @param x
     *
     * @return
     *
     * @throws NullPointerException if {@code field} is {@code null}.
     * @throws IllegalArgumentException if {@code field} is a static field.
     *
     * @see #compareAndSwapObject(java.lang.Object, long, java.lang.Object,
     * java.lang.Object)
     */
    public boolean instanceCompareAndSwapObject(final Object base,
                                                final Field field,
                                                final Object expected,
                                                final Object x) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        if (Modifier.isStatic(modifiers)) {
            throw new IllegalArgumentException("static field");
        }

        return compareAndSwapObject(
            base, objectFieldOffset(field), expected, x);
    }


    /**
     *
     * @param <T>
     * @param base
     * @param field
     * @param type
     * @param expected
     * @param x
     *
     * @return
     *
     * @throws NullPointerException if {@code field} is {@code null}.
     * @throws IllegalArgumentException if {@code field.type} is not assignable
     * to {@code type}.
     *
     * @see Field#getType()
     * @see Class#isAssignableFrom(java.lang.Class)
     * @see #instanceCompareAndSwapObject(java.lang.Object,
     * java.lang.reflect.Field, java.lang.Object, java.lang.Object)
     */
    public <T> boolean instanceCompareAndSwapObjectGeneric(
        final Object base, final Field field, final Class<T> type,
        final T expected, final T x) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        if (!field.getType().isAssignableFrom(type)) {
            throw new IllegalArgumentException(
                "field.type(" + field.getType()
                + ") is not assignable from type(" + type + ")");
        }

        return instanceCompareAndSwapObject(base, field, expected, x);
    }


    public void copyMemory(final long srcAddress, final long destAddress,
                           final long bytes) {

        invoke(COPY_MEMORY_lll_METHOD, unsafe, srcAddress, destAddress, bytes);
    }


    public void copyMemory(final Object srcBase, final long srcAddress,
                           final Object destBase, final long destAddress,
                           final long bytes) {

        invoke(COPY_MEMORY_OlOll_METHOD, unsafe, srcAddress, srcAddress,
               destBase, destAddress, bytes);
    }


    public void defineAnonymousClass(final Class<?> hostClass,
                                     final byte[] data,
                                     final Object[] cpPatches) {

        invoke(DEFINE_ANONYMOUS_CLASS_METHOD, unsafe, hostClass, data,
               cpPatches);
    }


    @Deprecated
    Class defineClass(String name, byte[] b, int off, int len) {
        throw new UnsupportedOperationException("deprecated");
    }


    public Class<?> defineClass(final String name, final byte[] b,
                                final int off, final int len,
                                final ClassLoader loader,
                                final ProtectionDomain protectionDomain) {

        return (Class<?>) invoke(DEFINE_CLASS_METHOD, unsafe, name, b, off, len,
                                 loader, protectionDomain);
    }


    public void ensureClassInitialized(final Class<?> c) {

        invoke(ENSURE_CLASS_INITIALIZED_METHOD, unsafe, c);
    }


    // ------------------------------------------------------ fieldOffset(Field)
    @Deprecated
    int fieldOffset(Field f) {
        throw new UnsupportedOperationException("deprecated");
    }


    /**
     * Disposes of a block of native memory, as obtained from
     * {@link #allocateMemory} or {@link #reallocateMemory}. The address passed
     * to this method may be null, in which case no action is taken.
     *
     * @param address
     *
     * @see #allocateMemory
     */
    public void freeMemory(final long address) {

        invoke(FREE_MEMORY_METHOD, unsafe, address);
    }


    public long getAddress(final long address) {

        return (Long) invoke(GET_ADDRESS_METHOD, unsafe, address);
    }


    @Deprecated
    boolean getBoolean(final Object o, final int offset) {

        throw new UnsupportedOperationException("deprecated");
    }


    public boolean getBoolean(final Object o, final long offset) {

        return (Boolean) invoke(GET_BOOLEAN_METHOD, unsafe, o, offset);
    }


    /**
     *
     * @param field
     *
     * @return
     *
     * @throws NullPointerException if {@code field} is {@code null}.
     * @throws IllegalArgumentException if {@code field} is not a static field.
     *
     * @see #staticFieldBase(java.lang.reflect.Field)
     * @see #staticFieldOffset(java.lang.reflect.Field)
     * @see #getBoolean(java.lang.Object, long)
     */
    public boolean getStaticBoolean(final Field field) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        if (!Modifier.isStatic(modifiers)) {
            throw new IllegalArgumentException("instance field");
        }

        return getBoolean(staticFieldBase(field), staticFieldOffset(field));
    }


    /**
     *
     * @param base
     * @param field
     *
     * @return
     *
     * @throws NullPointerException if {@code field} is {@code null}.
     * @throws IllegalArgumentException if {@code field} is a static field.
     *
     * @see #getBoolean(java.lang.Object, long)
     */
    public boolean getInstanceBoolean(final Object base, final Field field) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        if (Modifier.isStatic(modifiers)) {
            throw new IllegalArgumentException("static field");
        }

        return getBoolean(base, objectFieldOffset(field));
    }


    public boolean getBooleanVolatile(final Object o, final long offset) {

        return (Boolean) invoke(GET_BOOLEAN_VOLATILE_METHOD, unsafe, o, offset);
    }


    /**
     * Reads the boolean value from given static field.
     *
     * @param field the volatile static boolean field to read.
     *
     * @return the boolean value.
     *
     * @throws NullPointerException if {@code field} is {@code null}.
     * @throws IllegalArgumentException if {@code field} is not a static field.
     *
     * @see #staticFieldBase(java.lang.reflect.Field)
     * @see #staticFieldOffset(java.lang.reflect.Field)
     * @see #getBooleanVolatile(java.lang.Object, long)
     */
    public boolean getStaticBooleanVolatile(final Field field) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        if (!Modifier.isStatic(modifiers)) {
            throw new IllegalArgumentException("instance field");
        }

        if (!Modifier.isVolatile(modifiers)) {
            throw new IllegalArgumentException("non-volatile field");
        }

        return getBooleanVolatile(staticFieldBase(field),
                                  staticFieldOffset(field));
    }


    /**
     * Reads the volatile boolean value from given instance field.
     *
     * @param base the base object reference.
     * @param field the volatile boolean field to read.
     *
     * @return the boolean value.
     *
     * @throws NullPointerException if {@code field} is {@code null}.
     * @throws IllegalArgumentException if {@code field} is a static field.
     *
     * @see #objectFieldOffset(java.lang.reflect.Field)
     * @see #getBooleanVolatile(java.lang.Object, long)
     */
    public boolean getInstanceBooleanVolatile(final Object base,
                                              final Field field) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        if (Modifier.isStatic(modifiers)) {
            throw new IllegalArgumentException("static field");
        }

        if (!Modifier.isVolatile(modifiers)) {
            throw new IllegalArgumentException("non-volatile field");
        }

        return getBooleanVolatile(base, objectFieldOffset(field));
    }


    public byte getByte(final long offset) {

        return (Byte) invoke(GET_BYTE_l_METHOD, unsafe, offset);
    }


    public byte getByte(final Object object, final long offset) {

        return (Byte) invoke(GET_BYTE_Ol_METHOD, unsafe, object, offset);
    }


    /**
     *
     * @param field
     *
     * @return
     *
     * @throws NullPointerException if {@code field} is {@code null}.
     * @throws IllegalArgumentException if {@code field} is not a static field.
     *
     * @see #staticFieldBase(java.lang.reflect.Field)
     * @see #staticFieldOffset(java.lang.reflect.Field)
     * @see #getByte(java.lang.Object, long)
     */
    public byte getStaticByte(final Field field) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        if (!Modifier.isStatic(modifiers)) {
            throw new IllegalArgumentException("instance field");
        }

        return getByte(staticFieldBase(field), staticFieldOffset(field));
    }


    /**
     *
     * @param base
     * @param field
     *
     * @return
     *
     * @throws NullPointerException if {@code field} is {@code null}.
     * @throws IllegalArgumentException if {@code field} is a static field.
     *
     * @see #objectFieldOffset(java.lang.reflect.Field)
     * @see #getByte(java.lang.Object, long)
     */
    public byte getInstanceByte(final Object base, final Field field) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        if (Modifier.isStatic(modifiers)) {
            throw new IllegalArgumentException("static field");
        }

        return getByte(base, objectFieldOffset(field));
    }


    @Deprecated
    byte getByte(final Object object, final int offset) {

        throw new UnsupportedOperationException("deprecated");
    }


    public byte getByteVolatile(final Object object, final long offset) {

        return (Byte) invoke(GET_BYTE_VOLATILE_METHOD, unsafe, object, offset);
    }


    public byte getStaticByteVolatile(final Field field) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        if (!Modifier.isStatic(modifiers)) {
            throw new IllegalArgumentException("instance field");
        }

        if (!Modifier.isVolatile(modifiers)) {
            throw new IllegalArgumentException("non-volatile field");
        }

        return getByteVolatile(staticFieldBase(field),
                               staticFieldOffset(field));
    }


    public byte getInstanceByteVolatile(final Field field, final Object base) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        if (Modifier.isStatic(modifiers)) {
            throw new IllegalArgumentException("static field");
        }

        if (!Modifier.isVolatile(modifiers)) {
            throw new IllegalArgumentException("non-volatile field");
        }

        return getByteVolatile(base, objectFieldOffset(field));
    }


    public char getChar(final long offset) {

        try {
            return (Character) GET_CHAR_l_METHOD.invoke(unsafe, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    @Deprecated
    char getChar(final Object object, final int offset) {

        throw new UnsupportedOperationException("deprecated");
    }


    public char getChar(final Object object, final long offset) {

        try {
            return (Character) GET_CHAR_Ol_METHOD.invoke(
                unsafe, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public char getCharVolatile(final Object object, final long offset) {

        try {
            return (Character) GET_CHAR_VOLATILE_METHOD.invoke(
                unsafe, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public double getDouble(final long offset) {

        try {
            return (Double) GET_DOUBLE_l_METHOD.invoke(unsafe, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    @Deprecated
    double getDouble(final Object object, final int offset) {

        throw new UnsupportedOperationException("deprecated");
    }


    public double getDouble(final Object object, final long offset) {

        try {
            return (Double) GET_DOUBLE_Ol_METHOD.invoke(
                unsafe, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public double getDoubleVolatile(final Object object, final long offset) {

        try {
            return (Double) GET_DOUBLE_VOLATILE_METHOD.invoke(
                unsafe, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public float getFloat(final long offset) {

        try {
            return (Float) GET_FLOAT_l_METHOD.invoke(unsafe, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    @Deprecated
    float getFloat(final Object object, final int offset) {

        throw new UnsupportedOperationException("deprecated");
    }


    public float getFloat(final Object object, final long offset) {

        try {
            return (Float) GET_FLOAT_Ol_METHOD.invoke(unsafe, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public float getFloatVolatile(final Object object, final long offset) {

        try {
            return (Float) GET_FLOAT_VOLATILE_METHOD.invoke(
                unsafe, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public int getInt(final long offset) {

        try {
            return (Integer) GET_INT_l_METHOD.invoke(unsafe, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    @Deprecated
    int getInt(final Object object, final int offset) {

        throw new UnsupportedOperationException("deprecated");
    }


    public int getInt(final Object object, final long offset) {

        try {
            return (Integer) GET_INT_Ol_METHOD.invoke(unsafe, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public int getIntVolatile(final Object object, final long offset) {

        try {
            return (Integer) GET_INT_VOLATILE_METHOD.invoke(
                unsafe, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public int getLoadAverage(final double[] loadavg, final int nelems) {

        try {
            return (Integer) GET_LOAD_AVERAGE_METHOD.invoke(
                unsafe, loadavg, nelems);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public long getLong(final long offset) {

        try {
            return (Long) GET_LONG_l_METHOD.invoke(unsafe, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    @Deprecated
    long getLong(final Object object, final int offset) {

        throw new UnsupportedOperationException("deprecated");
    }


    public long getLong(final Object object, final long offset) {

        try {
            return (Long) GET_LONG_Ol_METHOD.invoke(unsafe, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public long getLongVolatile(final Object object, final long offset) {

        try {
            return (Long) GET_LONG_VOLATILE_METHOD.invoke(
                unsafe, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    /**
     * Fetches a reference value from a given Java variable.
     *
     * @param object
     * @param offset
     *
     * @return
     *
     * @see #getInt(Object, long)
     */
    public Object getObject(final Object object, final long offset) {

        try {
            return GET_OBJECT_Ol_METHOD.invoke(unsafe, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    @Deprecated
    Object getObject(final Object object, final int offset) {

        throw new UnsupportedOperationException("deprecated");
    }


    public Object getObjectVolatile(final Object object, final long offset) {

        try {
            return GET_OBJECT_VOLATILE_METHOD.invoke(unsafe, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public short getShort(final long offset) {

        try {
            return (Short) GET_SHORT_l_METHOD.invoke(unsafe, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    @Deprecated
    short getShort(final Object object, final int offset) {

        throw new UnsupportedOperationException("deprecated");
    }


    public short getShort(final Object object, final long offset) {

        try {
            return (Short) GET_SHORT_Ol_METHOD.invoke(unsafe, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public short getShortVolatile(final Object object, final long offset) {

        try {
            return (Short) GET_SHORT_VOLATILE_METHOD.invoke(
                unsafe, object, offset);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public short monitorEnter(final Object o) {

        try {
            return (Short) MONITOR_ENTER_METHOD.invoke(unsafe, o);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public short monitorExit(final Object o) {

        try {
            return (Short) MONITOR_EXIT_METHOD.invoke(unsafe, o);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public long objectFieldOffset(final Field field) {

        try {
            return (Long) OBJECT_FIELD_OFFSET_METHOD.invoke(unsafe, field);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public int pageSize() {

        try {
            return (Integer) PAGE_SIZE_METHOD.invoke(unsafe);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public void park(final boolean isAbsolute, final long time) {

        try {
            PARK_METHOD.invoke(unsafe, isAbsolute, time);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public void putAddress(final long address, final long time) {

        try {
            PUT_ADDRESS_METHOD.invoke(unsafe, address, time);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    @Deprecated
    void putBoolean(final Object object, final int offset, final boolean x) {

        throw new UnsupportedOperationException("deprecated");
    }


    public void putBoolean(final Object object, final long offset,
                           final boolean x) {

        try {
            PUT_BOOLEAN_Olb_METHOD.invoke(unsafe, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public void putBooleanVolatile(final Object object, final long offset,
                                   final boolean x) {

        try {
            PUT_BOOLEAN_VOLATILE_METHOD.invoke(unsafe, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public void putByte(final long offset, final byte x) {

        try {
            PUT_BYTE_lb_METHOD.invoke(unsafe, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    @Deprecated
    void putByte(final Object object, final int offset, final byte x) {

        throw new UnsupportedOperationException("deprecated");
    }


    public void putByte(final Object object, final long offset, final byte x) {

        try {
            PUT_BYTE_Olb_METHOD.invoke(unsafe, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public void putByteVolatile(final Object object, final long offset,
                                final byte x) {

        try {
            PUT_BYTE_VOLATILE_METHOD.invoke(unsafe, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public void putChar(final long offset, final char x) {

        try {
            PUT_CHAR_lb_METHOD.invoke(unsafe, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    @Deprecated
    void putChar(final Object object, final int offset, final char x) {

        throw new UnsupportedOperationException("deprecated");
    }


    public void putChar(final Object object, final long offset, final char x) {

        try {
            PUT_CHAR_Olb_METHOD.invoke(unsafe, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public void putCharVolatile(final Object object, final long offset,
                                final char x) {

        try {
            PUT_CHAR_VOLATILE_METHOD.invoke(unsafe, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public void putDouble(final long offset, final double x) {

        try {
            PUT_DOUBLE_lb_METHOD.invoke(unsafe, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    @Deprecated
    void putDouble(final Object object, final int offset, final double x) {

        throw new UnsupportedOperationException("deprecated");
    }


    public void putDouble(final Object object, final long offset,
                          final double x) {

        try {
            PUT_DOUBLE_Olb_METHOD.invoke(unsafe, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public void putDoubleVolatile(final Object object, final long offset,
                                  final double x) {

        try {
            PUT_DOUBLE_VOLATILE_METHOD.invoke(unsafe, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public void putFloat(final long offset, final float x) {

        try {
            PUT_FLOAT_lb_METHOD.invoke(unsafe, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    @Deprecated
    void putFloat(final Object object, final int offset, final float x) {

        throw new UnsupportedOperationException("deprecated");
    }


    public void putFloat(final Object object, final long offset,
                         final float x) {

        try {
            PUT_FLOAT_Olb_METHOD.invoke(unsafe, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public void putFloatVolatile(final Object object, final long offset,
                                 final float x) {

        try {
            PUT_FLOAT_VOLATILE_METHOD.invoke(unsafe, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public void putInt(final long offset, final int x) {

        try {
            PUT_INT_lb_METHOD.invoke(unsafe, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    @Deprecated
    void putInt(final Object object, final int offset, final int x) {

        throw new UnsupportedOperationException("deprecatd");
    }


    public void putInt(final Object object, final long offset, final int x) {

        try {
            PUT_INT_Olb_METHOD.invoke(unsafe, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public void putIntVolatile(final Object object, final long offset,
                               final int x) {

        try {
            PUT_INT_VOLATILE_METHOD.invoke(unsafe, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public void putLong(final long offset, final long x) {

        try {
            PUT_LONG_lb_METHOD.invoke(unsafe, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    @Deprecated
    void putLong(final Object object, final int offset, final long x) {

        throw new UnsupportedOperationException("deprecated");
    }


    public void putLong(final Object object, final long offset, final long x) {

        try {
            PUT_LONG_Olb_METHOD.invoke(unsafe, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public void putLongVolatile(final Object object, final long offset,
                                final long x) {

        try {
            PUT_LONG_VOLATILE_METHOD.invoke(unsafe, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    @Deprecated
    void putObject(final Object object, final int offset, final Object x) {

        throw new UnsupportedOperationException("deprecated");
    }


    /**
     * Stores a reference value into a given Java variable.
     *
     * Unless the reference {@code x} being stored is either null or matches the
     * field type, the results are undefined. If the reference {@code o} is
     * non-null, car marks or other store barriers for that object (if the VM
     * requires them) are updated.
     *
     * @param object
     * @param offset
     * @param x
     *
     * @see #putInt(Object, int, int)
     */
    public void putObject(final Object object, final long offset,
                          final Object x) {

        try {
            PUT_OBJECT_Olb_METHOD.invoke(unsafe, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public void putObjectVolatile(final Object object, final long offset,
                                  final Object x) {

        try {
            PUT_OBJECT_VOLATILE_METHOD.invoke(unsafe, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public void putOrderedInt(final Object object, final long offset,
                              final int x) {

        try {
            PUT_ORDERED_INT_METHOD.invoke(unsafe, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public void putOrderedLong(final Object object, final long offset,
                               final long x) {

        try {
            PUT_ORDERED_LONG_METHOD.invoke(unsafe, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public void putOrderedObject(final Object object, final long offset,
                                 final Object x) {

        try {
            PUT_ORDERED_OBJECT_METHOD.invoke(unsafe, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public void putShort(final long offset, final short x) {

        try {
            PUT_SHORT_lb_METHOD.invoke(unsafe, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    @Deprecated
    void putShort(final Object object, final int offset, final short x) {

        throw new UnsupportedOperationException("deprecated");
    }


    public void putShort(final Object object, final long offset,
                         final short x) {

        try {
            PUT_SHORT_Olb_METHOD.invoke(unsafe, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public void putShortVolatile(final Object object, final long offset,
                                 final short x) {

        try {
            PUT_SHORT_VOLATILE_METHOD.invoke(unsafe, object, offset, x);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public long reallocateMemory(final long address, final long bytes) {

        try {
            return (Long) REALLOCATE_MEMORY_METHOD.invoke(
                unsafe, address, bytes);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public void setMemory(final long address, final long bytes,
                          final byte value) {

        try {
            SET_MEMORY_llb_METHOD.invoke(unsafe, address, bytes, value);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public void setMemory(final Object o, final long address, final long bytes,
                          final byte value) {

        try {
            SET_MEMORY_Ollb_METHOD.invoke(unsafe, o, address, bytes, value);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public void shouldBeInitialized(final Class<?> c) {

        try {
            SHOULD_BE_INITIALIZED_METHOD.invoke(unsafe, c);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public Object staticFieldBase(final Field field) {

        try {
            return STATIC_FIELD_BASE_METHOD.invoke(unsafe, field);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    @Deprecated
    Object staticFieldBase(final Class c) {

        throw new UnsupportedOperationException("deprecated");
    }


    public long staticFieldOffset(final Field field) {

        try {
            return (Long) STATIC_FIELD_OFFSET_METHOD.invoke(unsafe, field);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public long throwException(final Throwable ee) {

        try {
            return (Long) THROW_EXCEPTION_METHOD.invoke(unsafe, ee);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public long tryMonitorEnter(final Object o) {

        try {
            return (Long) TRY_MONITOR_ENTER_METHOD.invoke(unsafe, o);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public long unpark(final Object thread) {

        try {
            return (Long) UNPARK_METHOD.invoke(unsafe, thread);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    private final Object unsafe;


}

