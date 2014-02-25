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
import javax.security.sasl.Sasl;
import sun.misc.Unsafe;


/**
 * A dangerous class.
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public final class Dangerous {


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
     * Returns the {@code theUnsafe} instance of {@link sun.misc.Unsafe} class.
     *
     * @return the {@code theUnsafe} instance.
     */
    public static Unsafe theUnsafeInstance() {

        return THE_UNSAFE;
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


    /**
     * Creates and returns a new instance of {@link sun.misc.Unsafe} class.
     *
     * @return a new instance of {@link sun.misc.Unsafe}.
     */
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
                              final Class<?> typeAssignableTo,
                              final Class<?> typeAssignableFrom,
                              final Integer requiredModifiers,
                              final Class<?> declaringClassAssignableTo,
                              final Class<?> declaringClassAssignableFrom) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        if (requiredModifiers != null) {
            if ((field.getModifiers() & requiredModifiers)
                != requiredModifiers) {
                throw new IllegalArgumentException("unmatched modifiers");
            }
        }

        if (typeAssignableTo != null) {
            final Class<?> type = field.getType();
            if (!typeAssignableTo.isAssignableFrom(type)) {
                throw new IllegalArgumentException(
                    "type(" + type + ") is  not assignable to "
                    + typeAssignableTo);
            }
        }

        if (typeAssignableFrom != null) {
            final Class<?> type = field.getType();
            if (!type.isAssignableFrom(typeAssignableFrom)) {
                throw new IllegalArgumentException(
                    "type(" + type + ") is  not assignable from "
                    + typeAssignableFrom);
            }
        }

        if (declaringClassAssignableTo != null) {
            final Class<?> declaringClass = field.getDeclaringClass();
            if (!declaringClassAssignableTo.isAssignableFrom(declaringClass)) {
                throw new IllegalArgumentException(
                    "declaring class(" + declaringClass
                    + ") is  not assignable to " + declaringClassAssignableTo);
            }
        }

        if (declaringClassAssignableFrom != null) {
            final Class<?> declaringClass = field.getDeclaringClass();
            if (!declaringClassAssignableFrom.isAssignableFrom(
                declaringClass)) {
                throw new IllegalArgumentException(
                    "declaring class(" + declaringClass
                    + ") is  not assignable from "
                    + declaringClassAssignableFrom);
            }
        }
    }


    /**
     *
     * @param field
     * @param declaringClassAssignableTo
     * @param fieldTypeAssignableTo
     * @param modifierStatic
     * @param modifierVolatile
     *
     * @deprecated
     */
    @Deprecated
    private static void check(final Field field,
                              final Class<?> declaringClassAssignableTo,
                              final Class<?> fieldTypeAssignableTo,
                              final Boolean modifierStatic,
                              final Boolean modifierVolatile) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        if (declaringClassAssignableTo != null) {
            final Class<?> declaringClass = field.getDeclaringClass();
            if (!declaringClassAssignableTo.isAssignableFrom(declaringClass)) {
                throw new IllegalArgumentException(
                    "field's declaring class(" + declaringClass
                    + ") is  not assignable to " + declaringClassAssignableTo);
            }
        }

        if (fieldTypeAssignableTo != null) {
            final Class<?> fieldType = field.getType();
            if (!fieldTypeAssignableTo.isAssignableFrom(fieldType)) {
                throw new IllegalArgumentException(
                    "field's type(" + fieldType + ") is  not assignable to "
                    + fieldTypeAssignableTo);
            }
        }

        final int modifiers = field.getModifiers();

        if (modifierStatic != null && modifierStatic.booleanValue()
            && !Modifier.isStatic(modifiers)) {
            throw new IllegalArgumentException("non-static field");
        }

        if (modifierVolatile != null && modifierVolatile.booleanValue()
            && !Modifier.isVolatile(modifiers)) {
            throw new IllegalArgumentException("non-volatile field");
        }
    }


    /**
     *
     * @param field
     * @param fieldTypeAssignableTo
     * @param modifierStatic
     * @param modifierVolatile
     *
     * @deprecated
     */
    @Deprecated
    private static void check(final Field field,
                              final Class<?> fieldTypeAssignableTo,
                              final Boolean modifierStatic,
                              final Boolean modifierVolatile) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        check(field, null, fieldTypeAssignableTo, modifierStatic,
              modifierVolatile);
    }


    /**
     * Creates a new instance.
     *
     * @param unsafe
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
        final boolean static_ = Modifier.isStatic(modifiers);

        final long offset;
        if (static_) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException("null base for instance field");
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
        final boolean static_ = Modifier.isStatic(modifiers);

        final long offset;
        if (static_) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException("null base for instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        return unsafe.compareAndSwapLong(base, offset, expected, x);
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

        final long offset;
        if (static_) {
            if (object == null) {
                object = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (object == null) {
                throw new NullPointerException("null base for instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        return unsafe.compareAndSwapObject(object, offset, expected, x);
    }


    public <T> boolean compareAndSwapObject(Object base, final Field field,
                                            final Class<T> type,
                                            final T expected, final T x) {

        check(field, null, type, null, null);

        return compareAndSwapObject(base, field, expected, x);
    }


    /**
     *
     * @param base the object reference for instance fields or {@code null} for
     * static fields.
     * @param field the field
     *
     * @return the boolean value.
     */
    public boolean getBoolean(Object base, final Field field) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();
        final boolean static_ = Modifier.isStatic(modifiers);
        final boolean volatile_ = Modifier.isVolatile(modifiers);

        final long offset;
        if (static_) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException("null base for instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        if (volatile_) {
            return unsafe.getBooleanVolatile(base, offset);
        } else {
            return unsafe.getBoolean(base, offset);
        }
    }


    public byte getByte(Object base, final Field field) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();
        final boolean static_ = Modifier.isStatic(modifiers);
        final boolean volatile_ = Modifier.isVolatile(modifiers);

        final long offset;
        if (static_) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException("null base for instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        if (volatile_) {
            return unsafe.getByteVolatile(base, offset);
        } else {
            return unsafe.getByte(base, offset);
        }
    }


    public char getChar(Object base, final Field field) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();
        final boolean static_ = Modifier.isStatic(modifiers);
        final boolean volatile_ = Modifier.isVolatile(modifiers);

        final long offset;
        if (static_) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException("null base for instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        if (volatile_) {
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
        final boolean static_ = Modifier.isStatic(modifiers);
        final boolean volatile_ = Modifier.isVolatile(modifiers);

        final long offset;
        if (static_) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException("null base for instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        if (volatile_) {
            return unsafe.getDoubleVolatile(base, offset);
        } else {
            return unsafe.getDouble(base, offset);
        }
    }


    public float getFloat(Object object, final Field field) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();
        final boolean static_ = Modifier.isStatic(modifiers);
        final boolean volatile_ = Modifier.isVolatile(modifiers);

        final long offset;
        if (static_) {
            if (object == null) {
                object = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (object == null) {
                throw new NullPointerException("null base for instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        if (volatile_) {
            return unsafe.getFloatVolatile(object, offset);
        } else {
            return unsafe.getFloat(object, offset);
        }
    }


    public int getInt(Object base, final Field field) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();
        final boolean static_ = Modifier.isStatic(modifiers);
        final boolean volatile_ = Modifier.isVolatile(modifiers);

        final long offset;
        if (static_) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException("null base for instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        if (volatile_) {
            return unsafe.getIntVolatile(base, offset);
        } else {
            return unsafe.getInt(base, offset);
        }
    }


    public long getLong(Object base, final Field field) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();
        final boolean static_ = Modifier.isStatic(modifiers);
        final boolean volatile_ = Modifier.isVolatile(modifiers);

        final long offset;
        if (static_) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
            if (volatile_) {
                return unsafe.getLongVolatile(base, offset);
            } else {
                return unsafe.getLong(base, offset);
            }
        } else {
            if (base == null) {
                throw new NullPointerException("null base for instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        if (volatile_) {
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
        final boolean static_ = Modifier.isStatic(modifiers);
        final boolean volatile_ = Modifier.isVolatile(modifiers);

        final long offset;
        if (static_) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
            if (volatile_) {
                return unsafe.getObjectVolatile(base, offset);
            } else {
                return unsafe.getObject(base, offset);
            }
        } else {
            if (base == null) {
                throw new NullPointerException("null base for instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        if (volatile_) {
            return unsafe.getObjectVolatile(base, offset);
        } else {
            return unsafe.getObject(base, offset);
        }
    }


    public short getShort(Object base, final Field field) {

        if (field == null) {
            throw new NullPointerException("null field");
        }

        final int modifiers = field.getModifiers();
        final boolean static_ = Modifier.isStatic(modifiers);
        final boolean volatile_ = Modifier.isVolatile(modifiers);

        final long offset;
        if (static_) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException("null base for instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        if (volatile_) {
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
        final boolean static_ = Modifier.isStatic(modifiers);
        final boolean volatile_ = Modifier.isVolatile(modifiers);

        final long offset;
        if (static_) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException("null base for instance field");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        if (volatile_) {
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
        final boolean static_ = Modifier.isStatic(modifiers);
        final boolean volatile_ = Modifier.isVolatile(modifiers);

        final long offset;
        if (static_) {
            if (base == null) {
                base = unsafe.staticFieldBase(field);
            }
            offset = unsafe.staticFieldOffset(field);
        } else {
            if (base == null) {
                throw new NullPointerException("null base");
            }
            offset = unsafe.objectFieldOffset(field);
        }

        if (volatile_) {
            unsafe.putByteVolatile(base, offset, x);
        } else {
            unsafe.putByte(base, offset, x);
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

