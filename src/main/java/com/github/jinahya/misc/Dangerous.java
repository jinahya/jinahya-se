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


import com.github.jinahya.lang.reflect.Fields;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import sun.misc.Unsafe;


/**
 * A dangerous class.
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class Dangerous {


    /**
     * The {@code theUnsafe} instance.
     */
    private static final Unsafe THE_UNSAFE;


    static {
        try {
            final Field field = Unsafe.class.getDeclaredField("theUnsafe");
            assert !field.isAccessible();
            field.setAccessible(true);
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
            assert !NEW_UNSAFE.isAccessible();
            NEW_UNSAFE.setAccessible(true);
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
     * @throws RuntimeException if failed to construct the instance.
     */
    public static Unsafe newUnsafe() {

        try {
            return NEW_UNSAFE.newInstance();
        } catch (final InstantiationException ie) {
            throw new RuntimeException(ie);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    private static class InstanceHolder {


        private static final Dangerous INSTANCE = new Dangerous(THE_UNSAFE);


        private InstanceHolder() {

            super();
        }


    }


    /**
     * Returns the pre-constructed instance with {@link #theUnsafe()}.
     *
     * @return the pre-constructed instance.
     */
    public static Dangerous getInstance() {

        return InstanceHolder.INSTANCE;
    }


    /**
     * Creates a new instance with {@link #newUnsafe()}.
     *
     * @return a new instance.
     */
    public static Dangerous newInstance() {

        return new Dangerous(newUnsafe());
    }


    /**
     * Creates a new instance with specified unsafe instance.
     *
     * @param unsafe the unsafe instance.
     *
     * @throws NullPointerException if {@code unsafe} is {@code null}.
     */
    public Dangerous(final Unsafe unsafe) {

        super();

        if (unsafe == null) {
            throw new NullPointerException("null unsafe");
        }

        this.unsafe = unsafe;
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
    public <T> T allocateInstance(final Class<T> cls)
        throws InstantiationException {

        if (cls == null) {
            throw new NullPointerException("null cls");
        }

        return cls.cast(unsafe.allocateInstance(cls));
    }


    /**
     *
     * @param base
     * @param field
     * @param expected
     * @param x
     *
     * @return
     */
    public boolean compareAndSwapInt(Object base, final Field field,
                                     final int expected, final int x) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();

        final long offset;
        final boolean static_ = Modifier.isStatic(modifiers);
        if (static_) {
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


    /**
     *
     * @param base the object reference or {@code null} for static field.
     * @param field
     * @param expected
     * @param x
     *
     * @return
     */
    public boolean compareAndSwapLong(Object base, final Field field,
                                      final long expected, final long x) {
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

        return unsafe.compareAndSwapLong(base, offset, expected, x);
    }


    /**
     *
     * @param base
     * @param field
     * @param expected
     * @param x
     *
     * @return
     */
    public boolean compareAndSwapObject(Object base, final Field field,
                                        final Object expected, final Object x) {

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

        return unsafe.compareAndSwapObject(base, offset, expected, x);
    }


    public <T> boolean compareAndSwapObject(final Object base, Field field,
                                            final Class<T> type,
                                            final T expected, final T x) {

        field = Fields.requireTypeAssignableFrom(field, type); // NPE, IAE

        return compareAndSwapObject(base, field, expected, x);
    }


    /**
     *
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
    public boolean getBoolean(Object base, final Field field) {

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


    /**
     *
     * @param base
     * @param field
     *
     * @return
     *
     * @throws NullPointerException if {@code base} is {@code null} while
     * {@code field} is an instance field.
     * @throws NullPointerException if {@code field} is {@code null}.
     */
    public byte getByte(Object base, final Field field) {

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


    /**
     *
     * @param base
     * @param field
     *
     * @return
     *
     * @throws NullPointerException if {@code base} is {@code null} while
     * {@code field} is an instance field.
     * @throws NullPointerException if {@code field} is {@code null}.
     */
    public char getChar(Object base, final Field field) {

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


    public double getDouble(Object base, final Field field) {

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


    public float getFloat(Object base, final Field field) {

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


    public int getInt(Object base, final Field field) {

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


    /**
     *
     * @param base
     * @param field
     *
     * @return
     */
    public long getLong(Object base, final Field field) {

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


    public Object getObject(Object base, final Field field) {

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


    /**
     *
     * @param base
     * @param field
     *
     * @return
     */
    public short getShort(Object base, final Field field) {

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


    /**
     *
     * @param base
     * @param field
     * @param x
     */
    public void putBoolean(Object base, final Field field, final boolean x) {

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


    /**
     *
     * @param base
     * @param field
     * @param x
     */
    public void putByte(Object base, final Field field, final byte x) {

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


    /**
     *
     * @param base
     * @param field
     * @param x
     */
    public void putChar(Object base, final Field field, final char x) {

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


    /**
     *
     * @param base
     * @param field
     * @param x
     */
    public void putDouble(Object base, final Field field, final double x) {

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


    /**
     *
     * @param base
     * @param field
     * @param x
     */
    public void putFloat(Object base, final Field field, final float x) {

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


    public void putInt(Object base, final Field field, final int x) {

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


    public void putLong(Object base, final Field field, final long x) {

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


    /**
     *
     * @param base
     * @param field
     * @param x
     */
    public void putObject(Object base, final Field field, final Object x) {

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


    public void putOrderedInt(Object base, final Field field, final int x) {

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


    /**
     *
     * @param base
     * @param field
     * @param x
     */
    public void putOrderedLong(Object base, final Field field, final long x) {

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


    /**
     *
     * @param base
     * @param field
     * @param x
     */
    public void putOrderedObject(Object base, final Field field,
                                 final Object x) {

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


    /**
     *
     * @param base
     * @param field
     * @param x
     */
    public void putShort(Object base, final Field field, final short x) {

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


    /**
     * Returns the unsafe instance.
     *
     * @return the unsafe instance.
     */
    public Unsafe unsafe() {

        return unsafe;
    }


    /**
     * The unsafe instance.
     */
    protected final Unsafe unsafe;


}

