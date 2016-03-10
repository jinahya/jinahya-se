/*
 * Copyright 2013 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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
import java.lang.reflect.Modifier;
import sun.misc.Unsafe;

/**
 * A dangerous class.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 * @deprecated just don't use this.
 */
@Deprecated
public final class Dangerous {

    /**
     * The {@code theUnsafe} instance.
     */
    private static final Unsafe THE_UNSAFE;

    static {
        try {
            final Field field = Unsafe.class.getDeclaredField("theUnsafe");
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            try {
                THE_UNSAFE = (Unsafe) field.get(null);
            } catch (final IllegalAccessException iae) {
                throw new InstantiationError(iae.getMessage());
            }
        } catch (final NoSuchFieldException nsfe) {
            throw new InstantiationError(nsfe.getMessage());
        }
    }

    /**
     * The default constructor of {@link sun.misc.Unsafe} class.
     */
    private static final Constructor<Unsafe> NEW_UNSAFE;

    static {
        try {
            NEW_UNSAFE = Unsafe.class.getDeclaredConstructor();
            if (!NEW_UNSAFE.isAccessible()) {
                NEW_UNSAFE.setAccessible(true);
            }
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.getMessage());
        }
    }

    /**
     * Returns the {@code theUnsafe} instance of {@link sun.misc.Unsafe} class.
     *
     * @return the {@code theUnsafe} instance.
     */
    public static Unsafe theUnsafe() {

        return THE_UNSAFE;
    }

    /**
     * Creates a new instance of {@link sun.misc.Unsafe} class.
     *
     * @return a new instance of {@link sun.misc.Unsafe} class.
     *
     * @throws ReflectiveOperationException if failed to create a new instance.
     */
    public static Unsafe newUnsafe() throws ReflectiveOperationException {

        return NEW_UNSAFE.newInstance();
    }

    /**
     *
     * @param <T> instance type parameter.
     * @param cls instance type.
     *
     * @return a new allocated instance.
     *
     * @throws InstantiationException if thrown from
     * {@link Unsafe#allocateInstance(java.lang.Class)}
     *
     * @see Unsafe#allocateInstance(java.lang.Class)
     */
    public static <T> T allocateInstance(final Unsafe unsafe,
                                         final Class<T> cls)
            throws InstantiationException {

        if (cls == null) {
            throw new NullPointerException("null cls");
        }

        return cls.cast(unsafe.allocateInstance(cls));
    }

    public static boolean compareAndSwapInt(final Unsafe unsafe, Object base,
                                            final Field field,
                                            final int expected, final int x) {

        if (unsafe == null) {
            throw new NullPointerException("null unsafe");
        }

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        final long offset;
        if (Modifier.isStatic(field.getModifiers())) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException(
                        "null base for an instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        return unsafe.compareAndSwapInt(base, offset, expected, x);
    }

    public static boolean compareAndSwapLong(final Unsafe unsafe, Object base,
                                             final Field field,
                                             final long expected,
                                             final long x) {

        if (unsafe == null) {
            throw new NullPointerException("null unsafe");
        }

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final long offset;
        if (Modifier.isStatic(field.getModifiers())) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException(
                        "null base for an instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        return unsafe.compareAndSwapLong(base, offset, expected, x);
    }

    public static boolean compareAndSwapObject(final Unsafe unsafe, Object base,
                                               final Field field,
                                               final Object expected,
                                               final Object x) {

        if (unsafe == null) {
            throw new NullPointerException("null unsafe");
        }

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final long offset;
        if (Modifier.isStatic(field.getModifiers())) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException(
                        "null base for an instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        return unsafe.compareAndSwapObject(base, offset, expected, x);
    }

    /**
     *
     * @param unsafe an unsafe
     * @param base the object reference for instance fields or {@code null} for
     * static fields.
     * @param field the field whose value is returned.
     *
     * @return the field value as boolean.
     *
     * @throws NullPointerException if {@code base} is {@code null} while
     * {@code field} is an instance field.
     * @throws NullPointerException if {@code field} is {@code null}.
     *
     * @see Unsafe#getBoolean(java.lang.Object, long)
     * @see Unsafe#getBooleanVolatile(java.lang.Object, long)
     */
    public static boolean getBoolean(final Unsafe unsafe, Object base,
                                     final Field field) {

        if (unsafe == null) {
            throw new NullPointerException("null unsafe");
        }

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        final long offset;
        if (Modifier.isStatic(modifiers)) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException(
                        "null base for an instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        if (Modifier.isVolatile(modifiers)) {
            return unsafe.getBooleanVolatile(base, offset);
        } else {
            return unsafe.getBoolean(base, offset);
        }
    }

    public static byte getByte(final Unsafe unsafe, Object base, final Field field) {

        if (unsafe == null) {
            throw new NullPointerException("null unsafe");
        }

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        final long offset;
        if (Modifier.isStatic(modifiers)) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException(
                        "null base for an instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        if (Modifier.isVolatile(modifiers)) {
            return unsafe.getByteVolatile(base, offset);
        } else {
            return unsafe.getByte(base, offset);
        }
    }

    public static char getChar(final Unsafe unsafe, Object base,
                               final Field field) {

        if (unsafe == null) {
            throw new NullPointerException("null unsafe");
        }

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        final long offset;
        if (Modifier.isStatic(modifiers)) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException(
                        "null base for an instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        if (Modifier.isVolatile(modifiers)) {
            return unsafe.getCharVolatile(base, offset);
        } else {
            return unsafe.getChar(base, offset);
        }
    }

    public static double getDouble(final Unsafe unsafe, Object base,
                                   final Field field) {

        if (unsafe == null) {
            throw new NullPointerException("null unsafe");
        }

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        final long offset;
        if (Modifier.isStatic(modifiers)) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException(
                        "null base for an instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        if (Modifier.isVolatile(modifiers)) {
            return unsafe.getDoubleVolatile(base, offset);
        } else {
            return unsafe.getDouble(base, offset);
        }
    }

    public static float getFloat(final Unsafe unsafe, Object base,
                                 final Field field) {

        if (unsafe == null) {
            throw new NullPointerException("null unsafe");
        }

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        final long offset;
        if (Modifier.isStatic(modifiers)) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException(
                        "null base for an instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        if (Modifier.isVolatile(modifiers)) {
            return unsafe.getFloatVolatile(base, offset);
        } else {
            return unsafe.getFloat(base, offset);
        }
    }

    public static int getInt(final Unsafe unsafe, Object base,
                             final Field field) {

        if (unsafe == null) {
            throw new NullPointerException("null unsafe");
        }

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        final long offset;
        if (Modifier.isStatic(modifiers)) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException(
                        "null base for an instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        if (Modifier.isVolatile(modifiers)) {
            return unsafe.getIntVolatile(base, offset);
        } else {
            return unsafe.getInt(base, offset);
        }
    }

    public static long getLong(final Unsafe unsafe, Object base,
                               final Field field) {

        if (unsafe == null) {
            throw new NullPointerException("null unsafe");
        }

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        final long offset;
        if (Modifier.isStatic(modifiers)) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException(
                        "null base for an instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        if (Modifier.isVolatile(modifiers)) {
            return unsafe.getLongVolatile(base, offset);
        } else {
            return unsafe.getLong(base, offset);
        }
    }

    public static Object getObject(final Unsafe unsafe, Object base,
                                   final Field field) {

        if (unsafe == null) {
            throw new NullPointerException("null unsafe");
        }

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        final long offset;
        if (Modifier.isStatic(modifiers)) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException(
                        "null base for an instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        if (Modifier.isVolatile(modifiers)) {
            return unsafe.getObjectVolatile(base, offset);
        } else {
            return unsafe.getObject(base, offset);
        }
    }

    public static short getShort(final Unsafe unsafe, Object base,
                                 final Field field) {

        if (unsafe == null) {
            throw new NullPointerException("null unsafe");
        }

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        final long offset;
        if (Modifier.isStatic(modifiers)) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException(
                        "null base for an instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        if (Modifier.isVolatile(modifiers)) {
            return unsafe.getShortVolatile(base, offset);
        } else {
            return unsafe.getShort(base, offset);
        }
    }

    public static void putBoolean(final Unsafe unsafe, Object base,
                                  final Field field, final boolean x) {

        if (unsafe == null) {
            throw new NullPointerException("null unsafe");
        }

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        final long offset;
        if (Modifier.isStatic(modifiers)) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException(
                        "null base for an instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        if (Modifier.isVolatile(modifiers)) {
            unsafe.putBooleanVolatile(base, offset, x);
        } else {
            unsafe.putBoolean(base, offset, x);
        }
    }

    public static void putByte(final Unsafe unsafe, Object base,
                               final Field field, final byte x) {

        if (unsafe == null) {
            throw new NullPointerException("null unsafe");
        }

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        final long offset;
        if (Modifier.isStatic(modifiers)) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException(
                        "null base for an instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        if (Modifier.isVolatile(modifiers)) {
            unsafe.putByteVolatile(base, offset, x);
        } else {
            unsafe.putByte(base, offset, x);
        }
    }

    public static void putChar(final Unsafe unsafe, Object base,
                               final Field field, final char x) {

        if (unsafe == null) {
            throw new NullPointerException("null unsafe");
        }

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        final long offset;
        if (Modifier.isStatic(modifiers)) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException(
                        "null base for an instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        if (Modifier.isVolatile(modifiers)) {
            unsafe.putCharVolatile(base, offset, x);
        } else {
            unsafe.putChar(base, offset, x);
        }
    }

    public static void putDouble(final Unsafe unsafe, Object base,
                                 final Field field, final double x) {

        if (unsafe == null) {
            throw new NullPointerException("null unsafe");
        }

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        final long offset;
        if (Modifier.isStatic(modifiers)) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException(
                        "null base for an instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        if (Modifier.isVolatile(modifiers)) {
            unsafe.putDoubleVolatile(base, offset, x);
        } else {
            unsafe.putDouble(base, offset, x);
        }
    }

    public void putFloat(final Unsafe unsafe, Object base, final Field field,
                         final float x) {

        if (unsafe == null) {
            throw new NullPointerException("null unsfae");
        }

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        final long offset;
        if (Modifier.isStatic(modifiers)) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException(
                        "null base for an instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        if (Modifier.isVolatile(modifiers)) {
            unsafe.putFloatVolatile(base, offset, x);
        } else {
            unsafe.putFloat(base, offset, x);
        }
    }

    public static void putInt(final Unsafe unsafe, Object base, final Field field, final int x) {

        if (unsafe == null) {
            throw new NullPointerException("null unsafe");
        }

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        final long offset;
        if (Modifier.isStatic(modifiers)) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException(
                        "null base for an instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        if (Modifier.isVolatile(modifiers)) {
            unsafe.putIntVolatile(base, offset, x);
        } else {
            unsafe.putInt(base, offset, x);
        }
    }

    public static void putLong(final Unsafe unsafe, Object base,
                               final Field field, final long x) {

        if (unsafe == null) {
            throw new NullPointerException("null unsafe");
        }

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        final long offset;
        if (Modifier.isStatic(modifiers)) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException(
                        "null base for an instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        if (Modifier.isVolatile(modifiers)) {
            unsafe.putLongVolatile(base, offset, x);
        } else {
            unsafe.putLong(base, offset, x);
        }
    }

    public static void putObject(final Unsafe unsafe, Object base,
                                 final Field field, final Object x) {

        if (unsafe == null) {
            throw new NullPointerException("null unsafe");
        }

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        final long offset;
        if (Modifier.isStatic(modifiers)) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException(
                        "null base for an instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        if (Modifier.isVolatile(modifiers)) {
            unsafe.putObjectVolatile(base, offset, x);
        } else {
            unsafe.putObject(base, offset, x);
        }
    }

    public static void putOrderedInt(final Unsafe unsafe, Object base,
                                     final Field field, final int x) {

        if (unsafe == null) {
            throw new NullPointerException("null unsafe");
        }

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        final long offset;
        if (Modifier.isStatic(modifiers)) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException(
                        "null base for an instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        unsafe.putOrderedInt(base, offset, x);
    }

    public static void putOrderedLong(final Unsafe unsafe, Object base,
                                      final Field field, final long x) {

        if (unsafe == null) {
            throw new NullPointerException("null unsafe");
        }

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        final long offset;
        if (Modifier.isStatic(modifiers)) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException(
                        "null base for an instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        unsafe.putOrderedLong(base, offset, x);
    }

    public static void putOrderedObject(final Unsafe unsafe, Object base,
                                        final Field field, final Object x) {

        if (unsafe == null) {
            throw new NullPointerException("null unsafe");
        }

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        final long offset;
        if (Modifier.isStatic(modifiers)) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException(
                        "null base for an instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        unsafe.putOrderedObject(base, offset, x);
    }

    public static void putShort(final Unsafe unsafe, Object base,
                                final Field field, final short x) {

        if (unsafe == null) {
            throw new NullPointerException("null unsafe");
        }

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        final long offset;
        if (Modifier.isStatic(modifiers)) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException(
                        "null base for an instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        if (Modifier.isVolatile(modifiers)) {
            unsafe.putShortVolatile(base, offset, x);
        } else {
            unsafe.putShort(base, offset, x);
        }
    }

    private Dangerous() {

        super();
    }

}
