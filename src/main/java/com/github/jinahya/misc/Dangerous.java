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


import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;
import sun.misc.Unsafe;


/**
 * A dangerous class.
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public final class Dangerous {


    /**
     * The {@code Unsafe} instance.
     */
    public static final Unsafe UNSAFE;


    static {

        final Class<?> clazz;
        try {
            clazz = Class.forName("sun.misc.Unsafe");
        } catch (final ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        }

        final Field field;
        try {
            field = clazz.getDeclaredField("theUnsafe");
        } catch (NoSuchFieldException nsfe) {
            throw new InstantiationError(nsfe.getMessage());
        }

        if (!field.isAccessible()) {
            field.setAccessible(true);
        }

        try {
            UNSAFE = (Unsafe) field.get(null);
        } catch (IllegalAccessException iae) {
            throw new InstantiationError(iae.getMessage());
        }
    }


//    public static int getInt(final Object object, final int offset)
//        throws Throwable {
//
//        final MethodType type
//            = MethodType.methodType(Object.class, Integer.TYPE);
//
//        final MethodHandles.Lookup lookup = MethodHandles.lookup();
//
//        final MethodHandle handle
//            = lookup.findVirtual(UNSAFE.getClass(), "getInt", type);
//
//        return ((Integer) handle.invokeExact(object, offset)).intValue();
//    }


    /**
     * Creates a new instance.
     */
    public Dangerous() {

        super();

        cf2fo = new ConcurrentHashMap<Field, Long>();
    }


    /**
     * Reads a byte field value from specified object.
     *
     * @param object the object whose field is read
     * @param field the field whose value is returned.
     *
     * @return the field value
     *
     * @see Unsafe#getByte(java.lang.Object, long)
     */
    public byte getByte(final Object object, final Field field) {

        Long offset = cf2fo.get(field);
        if (offset == null) {
            offset = UNSAFE.objectFieldOffset(field);
            cf2fo.putIfAbsent(field, offset);
            return getByte(object, field);
        }

        assert offset != null;

        return UNSAFE.getByte(object, offset);
    }


    /**
     * Writes a byte field value to specified object.
     *
     * @param object the object whose field is written
     * @param field the field whose value is written.
     * @param value the value to write
     *
     * @see Unsafe#putByte(java.lang.Object, long, byte)
     */
    public void putByte(final Object object, final Field field,
                        final byte value) {

        Long offset = cf2fo.get(field);
        if (offset == null) {
            offset = UNSAFE.objectFieldOffset(field);
            cf2fo.putIfAbsent(field, offset);
            putByte(object, field, value);
            return;
        }

        assert offset != null;

        UNSAFE.putByte(object, offset, value);
    }


    public short getShort(final Object object, final Field field) {

        Long offset = cf2fo.get(field);
        if (offset == null) {
            offset = UNSAFE.objectFieldOffset(field);
            cf2fo.putIfAbsent(field, offset);
            return getShort(object, field);
        }

        assert offset != null;

        return UNSAFE.getShort(object, offset);
    }


    public void putShort(final Object object, final Field field,
                         final short value) {

        Long offset = cf2fo.get(field);
        if (offset == null) {
            offset = UNSAFE.objectFieldOffset(field);
            cf2fo.putIfAbsent(field, offset);
            putShort(object, field, value);
            return;
        }

        assert offset != null;

        UNSAFE.putShort(object, offset, value);
    }


    public int getInt(final Object object, final Field field) {

        Long offset = cf2fo.get(field);
        if (offset == null) {
            offset = UNSAFE.objectFieldOffset(field);
            cf2fo.putIfAbsent(field, offset);
            return getInt(object, field);
        }

        assert offset != null;

        return UNSAFE.getInt(object, offset);
    }


    public void putInt(final Object object, final Field field,
                       final int value) {

        Long offset = cf2fo.get(field);
        if (offset == null) {
            offset = UNSAFE.objectFieldOffset(field);
            cf2fo.putIfAbsent(field, offset);
            putInt(object, field, value);
            return;
        }

        assert offset != null;

        UNSAFE.putInt(object, offset, value);
    }


    public Object getObject(final Object instance, final Field field) {

        Long offset = cf2fo.get(field);
        if (offset == null) {
            offset = UNSAFE.objectFieldOffset(field);
            cf2fo.putIfAbsent(field, offset);
            return getObject(instance, field);
        }

        assert offset != null;

        return UNSAFE.getObject(instance, offset);
    }


    public void putObject(final Object instance, final Field field,
                          final Object value) {

        Long offset = cf2fo.get(field);
        if (offset == null) {
            offset = UNSAFE.objectFieldOffset(field);
            cf2fo.putIfAbsent(field, offset);
            putObject(instance, field, value);
            return;
        }

        assert offset != null;
        UNSAFE.putObject(instance, offset, value);
    }


    /**
     * class field -> field offset.
     */
    private final ConcurrentHashMap<Field, Long> cf2fo;


}

