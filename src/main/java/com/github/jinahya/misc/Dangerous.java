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

        final Class<?> c;
        try {
            c = Class.forName("sun.misc.Unsafe");
        } catch (final ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        }

        final Field f;
        try {
            f = c.getDeclaredField("theUnsafe");
        } catch (final NoSuchFieldException nsfe) {
            throw new InstantiationError(nsfe.getMessage());
        }

        if (!f.isAccessible()) {
            f.setAccessible(true);
        }

        try {
            UNSAFE = (Unsafe) f.get(null);
        } catch (final IllegalAccessException iae) {
            throw new InstantiationError(iae.getMessage());
        }
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
    public static byte getByte(final Object object, final Field field) {

        return UNSAFE.getByte(object, UNSAFE.objectFieldOffset(field));
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
    public static void putByte(final Object object, final Field field,
                               final byte value) {

        UNSAFE.putByte(object, UNSAFE.objectFieldOffset(field), value);
    }


    public static short getShort(final Object object, final Field field) {

        return UNSAFE.getShort(object, UNSAFE.objectFieldOffset(field));
    }


    public void putShort(final Object object, final Field field,
                         final short value) {

        UNSAFE.putShort(object, UNSAFE.objectFieldOffset(field), value);
    }


    public int getInt(final Object object, final Field field) {

        return UNSAFE.getInt(object, UNSAFE.objectFieldOffset(field));
    }


    public void putInt(final Object object, final Field field,
                       final int value) {

        UNSAFE.putInt(object, UNSAFE.objectFieldOffset(field), value);
    }


    public Object getObject(final Object object, final Field field) {

        return UNSAFE.getObject(object, UNSAFE.objectFieldOffset(field));
    }


    public void putObject(final Object object, final Field field,
                          final Object value) {

        UNSAFE.putObject(object, UNSAFE.objectFieldOffset(field), value);
    }


    /**
     * Creates a new instance.
     */
    private Dangerous() {

        super();
    }


}

