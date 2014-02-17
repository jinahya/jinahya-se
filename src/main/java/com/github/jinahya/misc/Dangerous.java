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
import java.lang.reflect.Modifier;
import sun.misc.Unsafe;


/**
 * A dangerous class.
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public final class Dangerous {


    private static final Unsafe THE_INSTANCE;


    static {
        try {
            final Field field = Unsafe.class.getDeclaredField("theUnsafe");
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            try {
                THE_INSTANCE = (Unsafe) field.get(null);
            } catch (final IllegalAccessException iae) {
                throw new InstantiationError(iae.getMessage());
            }
        } catch (final NoSuchFieldException nsfe) {
            throw new InstantiationError(nsfe.getMessage());
        }
    }


    public static Unsafe theUnsafeInstance() {

        return THE_INSTANCE;
    }


    private static final Constructor<Unsafe> UNSAFE_CONSTRUCTOR;


    static {
        try {
            UNSAFE_CONSTRUCTOR = Unsafe.class.getDeclaredConstructor();
            if (!UNSAFE_CONSTRUCTOR.isAccessible()) {
                UNSAFE_CONSTRUCTOR.setAccessible(true);
            }
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.getMessage());
        }
    }


    public static Unsafe newUnsafeInstance() {

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


    /**
     * Creates a new instance.
     *
     * @return a new instance.
     */
    public static Dangerous newInstance() {

        return new Dangerous(newUnsafeInstance());
    }


    private static void check(final Field field,
                              final Class<?> declaringTypeAssignableTo,
                              final Class<?> valueTypeAssignableTo,
                              final Boolean modifierStatic,
                              final Boolean modifierVolatile) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        if (declaringTypeAssignableTo != null
            && !declaringTypeAssignableTo.isAssignableFrom(
            field.getDeclaringClass())) {
            throw new IllegalArgumentException(
                "field's declaring type(" + field.getDeclaringClass()
                + ") is  not assignable to " + valueTypeAssignableTo);
        }

        if (valueTypeAssignableTo != null
            && !valueTypeAssignableTo.isAssignableFrom(field.getType())) {
            throw new IllegalArgumentException(
                "field's type(" + field.getType() + ") is  not assignable to "
                + valueTypeAssignableTo);
        }

        final int modifiers = field.getModifiers();

        if (modifierStatic != null && modifierStatic.booleanValue()
            && !Modifier.isStatic(modifiers)) {
            throw new IllegalArgumentException("static field");
        }

        if (modifierVolatile != null && modifierVolatile.booleanValue()
            && !Modifier.isVolatile(modifiers)) {
            throw new IllegalArgumentException("vilatile field");
        }
    }


    private static void check(final Field field,
                              final Class<?> valueTypeAssignableTo,
                              final Boolean modifierStatic,
                              final Boolean modifierVolatile) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        if (valueTypeAssignableTo != null
            && !valueTypeAssignableTo.isAssignableFrom(field.getType())) {
            throw new IllegalArgumentException(
                "field's type(" + field.getType() + ") is  not assignable to "
                + valueTypeAssignableTo);
        }

        final int modifiers = field.getModifiers();

        if (modifierStatic != null && modifierStatic.booleanValue()
            && !Modifier.isStatic(modifiers)) {
            throw new IllegalArgumentException("static field");
        }

        if (modifierVolatile != null && modifierVolatile.booleanValue()
            && !Modifier.isVolatile(modifiers)) {
            throw new IllegalArgumentException("vilatile field");
        }
    }


    /**
     * Creates a new instance.
     */
    private Dangerous(final Unsafe unsafe) {

        super();

        if (unsafe == null) {
            throw new NullPointerException("null unsafe");
        }

        this.unsafe = unsafe;
    }


    /**
     *
     * @param <T>
     * @param cls
     *
     * @return
     *
     * @throws InstantiationException
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
     * @param object
     * @param field
     * @param expected
     * @param x
     *
     * @return
     */
    public boolean compareAndSwapInt(Object object, final Field field,
                                     final int expected, final int x) {
        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();
        final boolean static_ = Modifier.isStatic(modifiers);

        if (static_) {
            object = unsafe.staticFieldBase(field);
            final long offset = unsafe.staticFieldOffset(field);
            return unsafe.compareAndSwapInt(object, offset, expected, x);
        } else {
            final long offset = unsafe.objectFieldOffset(field);
            return unsafe.compareAndSwapInt(object, offset, expected, x);
        }
    }


    /**
     *
     * @param object
     * @param field
     * @param expected
     * @param x
     *
     * @return
     */
    public boolean compareAndSwapLong(Object object, final Field field,
                                      final long expected, final long x) {
        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();
        final boolean static_ = Modifier.isStatic(modifiers);

        if (static_) {
            object = unsafe.staticFieldBase(field);
            final long offset = unsafe.staticFieldOffset(field);
            return unsafe.compareAndSwapLong(object, offset, expected, x);
        } else {
            final long offset = unsafe.objectFieldOffset(field);
            return unsafe.compareAndSwapLong(object, offset, expected, x);
        }
    }


    /**
     *
     * @param object
     * @param field
     * @param expected
     * @param x
     *
     * @return
     */
    public boolean compareAndSwapObject(Object object, final Field field,
                                        final Object expected, final Object x) {
        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();
        final boolean static_ = Modifier.isStatic(modifiers);

        if (static_) {
            object = unsafe.staticFieldBase(field);
            final long offset = unsafe.staticFieldOffset(field);
            return unsafe.compareAndSwapObject(object, offset, expected, x);
        } else {
            final long offset = unsafe.objectFieldOffset(field);
            return unsafe.compareAndSwapObject(object, offset, expected, x);
        }
    }


    public <T> boolean compareAndSwapObject(Object object, final Field field,
                                            final Class<T> type,
                                            final T expected, final T x) {

        check(field, null, type, null, null);

        return compareAndSwapObject(object, field, expected, x);
    }


    /**
     *
     * @param object
     * @param field
     *
     * @return
     */
    public boolean getBoolean(Object object, final Field field) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();
        final boolean static_ = Modifier.isStatic(modifiers);
        final boolean volatile_ = Modifier.isVolatile(modifiers);

        if (static_) {
            object = unsafe.staticFieldBase(field);
            final long offset = unsafe.staticFieldOffset(field);
            if (volatile_) {
                return unsafe.getBooleanVolatile(object, offset);
            } else {
                return unsafe.getBoolean(object, offset);
            }
        } else {
            final long offset = unsafe.objectFieldOffset(field);
            if (volatile_) {
                return unsafe.getBooleanVolatile(object, offset);
            } else {
                return unsafe.getBoolean(object, offset);
            }
        }
    }


    /**
     *
     * @param object
     * @param field
     *
     * @return
     */
    public byte getByte(Object object, final Field field) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();
        final boolean static_ = Modifier.isStatic(modifiers);
        final boolean volatile_ = Modifier.isVolatile(modifiers);

        if (static_) {
            object = unsafe.staticFieldBase(field);
            final long offset = unsafe.staticFieldOffset(field);
            if (volatile_) {
                return unsafe.getByteVolatile(object, offset);
            } else {
                return unsafe.getByte(object, offset);
            }
        } else {
            final long offset = unsafe.objectFieldOffset(field);
            if (volatile_) {
                return unsafe.getByteVolatile(object, offset);
            } else {
                return unsafe.getByte(object, offset);
            }
        }
    }


    public char getChar(Object object, final Field field) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();
        final boolean static_ = Modifier.isStatic(modifiers);
        final boolean volatile_ = Modifier.isVolatile(modifiers);

        if (static_) {
            object = unsafe.staticFieldBase(field);
            final long offset = unsafe.staticFieldOffset(field);
            if (volatile_) {
                return unsafe.getCharVolatile(object, offset);
            } else {
                return unsafe.getChar(object, offset);
            }
        } else {
            final long offset = unsafe.objectFieldOffset(field);
            if (volatile_) {
                return unsafe.getCharVolatile(object, offset);
            } else {
                return unsafe.getChar(object, offset);
            }
        }
    }


    public double getDouble(Object object, final Field field) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();
        final boolean static_ = Modifier.isStatic(modifiers);
        final boolean volatile_ = Modifier.isVolatile(modifiers);

        if (static_) {
            object = unsafe.staticFieldBase(field);
            final long offset = unsafe.staticFieldOffset(field);
            if (volatile_) {
                return unsafe.getDoubleVolatile(object, offset);
            } else {
                return unsafe.getDouble(object, offset);
            }
        } else {
            final long offset = unsafe.objectFieldOffset(field);
            if (volatile_) {
                return unsafe.getDoubleVolatile(object, offset);
            } else {
                return unsafe.getDouble(object, offset);
            }
        }
    }


    public float getFloat(Object object, final Field field) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();
        final boolean static_ = Modifier.isStatic(modifiers);
        final boolean volatile_ = Modifier.isVolatile(modifiers);

        if (static_) {
            object = unsafe.staticFieldBase(field);
            final long offset = unsafe.staticFieldOffset(field);
            if (volatile_) {
                return unsafe.getFloatVolatile(object, offset);
            } else {
                return unsafe.getFloat(object, offset);
            }
        } else {
            final long offset = unsafe.objectFieldOffset(field);
            if (volatile_) {
                return unsafe.getFloatVolatile(object, offset);
            } else {
                return unsafe.getFloat(object, offset);
            }
        }

    }


    public int getInt(Object object, final Field field) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();
        final boolean static_ = Modifier.isStatic(modifiers);
        final boolean volatile_ = Modifier.isVolatile(modifiers);

        if (static_) {
            object = unsafe.staticFieldBase(field);
            final long offset = unsafe.staticFieldOffset(field);
            if (volatile_) {
                return unsafe.getIntVolatile(object, offset);
            } else {
                return unsafe.getInt(object, offset);
            }
        } else {
            final long offset = unsafe.objectFieldOffset(field);
            if (volatile_) {
                return unsafe.getIntVolatile(object, offset);
            } else {
                return unsafe.getInt(object, offset);
            }
        }
    }


    public long getLong(Object object, final Field field) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();
        final boolean static_ = Modifier.isStatic(modifiers);
        final boolean volatile_ = Modifier.isVolatile(modifiers);

        if (static_) {
            object = unsafe.staticFieldBase(field);
            final long offset = unsafe.staticFieldOffset(field);
            if (volatile_) {
                return unsafe.getLongVolatile(object, offset);
            } else {
                return unsafe.getLong(object, offset);
            }
        } else {
            final long offset = unsafe.objectFieldOffset(field);
            if (volatile_) {
                return unsafe.getLongVolatile(object, offset);
            } else {
                return unsafe.getLong(object, offset);
            }
        }
    }


    public Object getObject(Object object, final Field field) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();
        final boolean static_ = Modifier.isStatic(modifiers);
        final boolean volatile_ = Modifier.isVolatile(modifiers);

        if (static_) {
            object = unsafe.staticFieldBase(field);
            final long offset = unsafe.staticFieldOffset(field);
            if (volatile_) {
                return unsafe.getObjectVolatile(object, offset);
            } else {
                return unsafe.getObject(object, offset);
            }
        } else {
            final long offset = unsafe.objectFieldOffset(field);
            if (volatile_) {
                return unsafe.getObjectVolatile(object, offset);
            } else {
                return unsafe.getObject(object, offset);
            }
        }
    }


    public short getShort(Object object, final Field field) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();
        final boolean static_ = Modifier.isStatic(modifiers);
        final boolean volatile_ = Modifier.isVolatile(modifiers);

        if (static_) {
            object = unsafe.staticFieldBase(field);
            final long offset = unsafe.staticFieldOffset(field);
            if (volatile_) {
                return unsafe.getShortVolatile(object, offset);
            } else {
                return unsafe.getShort(object, offset);
            }
        } else {
            final long offset = unsafe.objectFieldOffset(field);
            if (volatile_) {
                return unsafe.getShortVolatile(object, offset);
            } else {
                return unsafe.getShort(object, offset);
            }
        }
    }


    /**
     *
     * @param object
     * @param field
     * @param x
     */
    public void putBoolean(Object object, final Field field, final boolean x) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();
        final boolean static_ = Modifier.isStatic(modifiers);
        final boolean volatile_ = Modifier.isVolatile(modifiers);

        if (static_) {
            object = unsafe.staticFieldBase(field);
            final long offset = unsafe.staticFieldOffset(field);
            if (volatile_) {
                unsafe.putBoolean(object, offset, x);
            } else {
                unsafe.putBooleanVolatile(object, offset, x);
            }
        } else {
            final long offset = unsafe.objectFieldOffset(field);
            if (volatile_) {
                unsafe.putBooleanVolatile(object, offset, x);
            } else {
                unsafe.putBoolean(object, offset, x);
            }
        }
    }


    /**
     *
     * @param object
     * @param field
     * @param x
     */
    public void putByte(Object object, final Field field, final byte x) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();
        final boolean static_ = Modifier.isStatic(modifiers);
        final boolean volatile_ = Modifier.isVolatile(modifiers);

        if (static_) {
            object = unsafe.staticFieldBase(field);
            final long offset = unsafe.staticFieldOffset(field);
            if (volatile_) {
                unsafe.putByteVolatile(object, offset, x);
            } else {
                unsafe.putByte(object, offset, x);
            }
        } else {
            final long offset = unsafe.objectFieldOffset(field);
            if (volatile_) {
                unsafe.putByteVolatile(object, offset, x);
            } else {
                unsafe.putByte(object, offset, x);
            }
        }
    }


    /**
     *
     * @param object
     * @param field
     * @param x
     */
    public void putChar(Object object, final Field field, final char x) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();
        final boolean static_ = Modifier.isStatic(modifiers);
        final boolean volatile_ = Modifier.isVolatile(modifiers);

        if (static_) {
            object = unsafe.staticFieldBase(field);
            final long offset = unsafe.staticFieldOffset(field);
            if (volatile_) {
                unsafe.putCharVolatile(object, offset, x);
            } else {
                unsafe.putChar(object, offset, x);
            }
        } else {
            final long offset = unsafe.objectFieldOffset(field);
            if (volatile_) {
                unsafe.putCharVolatile(object, offset, x);
            } else {
                unsafe.putChar(object, offset, x);
            }
        }
    }


    /**
     *
     * @param object
     * @param field
     * @param x
     */
    public void putDouble(Object object, final Field field, final double x) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();
        final boolean static_ = Modifier.isStatic(modifiers);
        final boolean volatile_ = Modifier.isVolatile(modifiers);

        if (static_) {
            object = unsafe.staticFieldBase(field);
            final long offset = unsafe.staticFieldOffset(field);
            if (volatile_) {
                unsafe.putDoubleVolatile(object, offset, x);
            } else {
                unsafe.putDouble(object, offset, x);
            }
        } else {
            final long offset = unsafe.objectFieldOffset(field);
            if (volatile_) {
                unsafe.putDoubleVolatile(object, offset, x);
            } else {
                unsafe.putDouble(object, offset, x);
            }
        }
    }


    /**
     *
     * @param object
     * @param field
     * @param x
     */
    public void putFloat(Object object, final Field field, final float x) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();
        final boolean static_ = Modifier.isStatic(modifiers);
        final boolean volatile_ = Modifier.isVolatile(modifiers);

        if (static_) {
            object = unsafe.staticFieldBase(field);
            final long offset = unsafe.staticFieldOffset(field);
            if (volatile_) {
                unsafe.putFloatVolatile(object, offset, x);
            } else {
                unsafe.putFloat(object, offset, x);
            }
        } else {
            final long offset = unsafe.objectFieldOffset(field);
            if (volatile_) {
                unsafe.putFloatVolatile(object, offset, x);
            } else {
                unsafe.putFloat(object, offset, x);
            }
        }
    }


    public void putInt(Object object, final Field field, final int x) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();
        final boolean static_ = Modifier.isStatic(modifiers);
        final boolean volatile_ = Modifier.isVolatile(modifiers);

        if (static_) {
            object = unsafe.staticFieldBase(field);
            final long offset = unsafe.staticFieldOffset(field);
            if (volatile_) {
                unsafe.putIntVolatile(object, offset, x);
            } else {
                unsafe.putInt(object, offset, x);
            }
        } else {
            final long offset = unsafe.objectFieldOffset(field);
            if (volatile_) {
                unsafe.putIntVolatile(object, offset, x);
            } else {
                unsafe.putInt(object, offset, x);
            }
        }
    }


    public void putLong(Object object, final Field field, final long x) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();
        final boolean static_ = Modifier.isStatic(modifiers);
        final boolean volatile_ = Modifier.isVolatile(modifiers);

        if (static_) {
            object = unsafe.staticFieldBase(field);
            final long offset = unsafe.staticFieldOffset(field);
            if (volatile_) {
                unsafe.putLongVolatile(object, offset, x);
            } else {
                unsafe.putLong(object, offset, x);
            }
        } else {
            final long offset = unsafe.objectFieldOffset(field);
            if (volatile_) {
                unsafe.putLongVolatile(object, offset, x);
            } else {
                unsafe.putLong(object, offset, x);
            }
        }
    }


    /**
     *
     * @param object
     * @param field
     * @param x
     */
    public void putObject(Object object, final Field field, final Object x) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();
        final boolean static_ = Modifier.isStatic(modifiers);
        final boolean volatile_ = Modifier.isVolatile(modifiers);

        if (static_) {
            object = unsafe.staticFieldBase(field);
            final long offset = unsafe.staticFieldOffset(field);
            if (volatile_) {
                unsafe.putObjectVolatile(object, offset, x);
            } else {
                unsafe.putObject(object, offset, x);
            }
        } else {
            final long offset = unsafe.objectFieldOffset(field);
            if (volatile_) {
                unsafe.putObjectVolatile(object, offset, x);
            } else {
                unsafe.putObject(object, offset, x);
            }
        }
    }


    public void putOrderedInt(Object object, final Field field, final int x) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();
        final boolean static_ = Modifier.isStatic(modifiers);

        if (static_) {
            object = unsafe.staticFieldBase(field);
            final long offset = unsafe.staticFieldOffset(field);
            unsafe.putOrderedInt(object, offset, x);
        } else {
            final long offset = unsafe.objectFieldOffset(field);
            unsafe.putOrderedInt(object, offset, x);
        }
    }


    /**
     *
     * @param object
     * @param field
     * @param x
     */
    public void putOrderedLong(Object object, final Field field, final long x) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();
        final boolean static_ = Modifier.isStatic(modifiers);

        if (static_) {
            object = unsafe.staticFieldBase(field);
            final long offset = unsafe.staticFieldOffset(field);
            unsafe.putOrderedLong(object, offset, x);
        } else {
            final long offset = unsafe.objectFieldOffset(field);
            unsafe.putOrderedLong(object, offset, x);
        }
    }


    /**
     *
     * @param object
     * @param field
     * @param x
     */
    public void putOrderedObject(Object object, final Field field,
                                 final Object x) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();
        final boolean static_ = Modifier.isStatic(modifiers);

        if (static_) {
            object = unsafe.staticFieldBase(field);
            final long offset = unsafe.staticFieldOffset(field);
            unsafe.putOrderedObject(object, offset, x);
        } else {
            final long offset = unsafe.objectFieldOffset(field);
            unsafe.putOrderedObject(object, offset, x);
        }
    }


    /**
     *
     * @param object
     * @param field
     * @param x
     */
    public void putShort(Object object, final Field field, final short x) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();
        final boolean static_ = Modifier.isStatic(modifiers);
        final boolean volatile_ = Modifier.isVolatile(modifiers);

        if (static_) {
            object = unsafe.staticFieldBase(field);
            final long offset = unsafe.staticFieldOffset(field);
            if (volatile_) {
                unsafe.putShortVolatile(object, offset, x);
            } else {
                unsafe.putShort(object, offset, x);
            }
        } else {
            final long offset = unsafe.objectFieldOffset(field);
            if (volatile_) {
                unsafe.putShortVolatile(object, offset, x);
            } else {
                unsafe.putShort(object, offset, x);
            }
        }
    }


    /**
     *
     * @param object
     * @param field
     * @param bytes
     * @param value
     */
    public void setMemory(final Object object, final Field field,
                          final long bytes, final byte value) {
        // @todo: implement
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

