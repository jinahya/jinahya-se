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
import static java.lang.reflect.Modifier.isStatic;
import static java.lang.reflect.Modifier.isVolatile;
import java.security.ProtectionDomain;
import sun.misc.Unsafe;

/**
 * A dangerous class.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 * @deprecated don't use this if you don't know what you're gonna do.
 */
@Deprecated
public final class Dangerous {

    public static Object fieldBase(final Unsafe unsafe, final Field field,
                                   final Object base) {
        if (isStatic(field.getModifiers())) {
            return unsafe.staticFieldBase(field);
        }
        if (base == null) {
            throw new NullPointerException("null base for an instance field");
        }
        return base;
    }

    public static long fieldOffset(final Unsafe unsafe, final Field field) {
        return isStatic(field.getModifiers())
               ? unsafe.staticFieldOffset(field)
               : unsafe.objectFieldOffset(field);
    }

    /**
     * Finds the {@code theUnsafe} instance of {@link sun.misc.Unsafe} class.
     *
     * @return the {@code theUnsafe} instance.
     * @throws ReflectiveOperationException if failed to get the value.
     */
    public static Unsafe theUnsafe() throws ReflectiveOperationException {
        final Field field = Unsafe.class.getDeclaredField("theUnsafe");
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        try {
            return (Unsafe) field.get(null);
        } catch (final IllegalAccessException iae) {
            throw new InstantiationError(iae.getMessage());
        }
    }

    /**
     * Creates a new instance of {@link Unsafe} class.
     *
     * @return a new instance of {@link Unsafe} class.
     *
     * @throws ReflectiveOperationException if failed to create a new instance.
     */
    public static Unsafe newUnsafe() throws ReflectiveOperationException {
        final Constructor<Unsafe> constructor
                = Unsafe.class.getDeclaredConstructor();
        if (!constructor.isAccessible()) {
            constructor.setAccessible(true);
        }
        return constructor.newInstance();
    }

    /**
     * Returns the value of {@link Unsafe#addressSize()} invoked on given
     * instance.
     *
     * @param unsafe an unsafe
     * @return the value of {@link Unsafe#addressSize()}.
     * @see Unsafe#ADDRESS_SIZE
     */
    @Deprecated
    public static int addressSize(final Unsafe unsafe) {
        return unsafe.addressSize();
    }

    /**
     * Returns the value of {@link Unsafe#addressSize()} invoked on
     * {@code theUnsafe}.
     *
     * @return the value of {@link Unsafe#addressSize()} invoked on
     * {@link #theUnsafe()}.
     * @throws ReflectiveOperationException if failed to get {@code theUnsafe}
     * @see #theUnsafe()
     * @see #addressSize(sun.misc.Unsafe)
     * @see Unsafe#ADDRESS_SIZE
     */
    @Deprecated
    public static int addressSize() throws ReflectiveOperationException {
        return addressSize(theUnsafe());
    }

    /**
     * Returns the value of {@link Unsafe#allocateInstance(java.lang.Class)}
     * invoked on given {@code unsafe} with specified {@code cls}.
     *
     * @param <T> instance type parameter.
     * @param cls instance type.
     * @return a new allocated instance.
     * @throws InstantiationException if failed to instantiate
     */
    public static <T> T allocateInstance(final Unsafe unsafe,
                                         final Class<T> cls)
            throws InstantiationException {
        return cls.cast(unsafe.allocateInstance(cls));
    }

    public static <T> T allocateInstance(final Class<T> cls)
            throws ReflectiveOperationException, InstantiationException {
        return allocateInstance(theUnsafe(), cls);
    }

    // ---------------------------------------------------------- allocateMemory
    @Deprecated
    public static long allocateMemory(final Unsafe unsafe, final long bytes) {
        return unsafe.allocateMemory(bytes);
    }

    @Deprecated
    public static long allocateMemory(final long bytes)
            throws ReflectiveOperationException {
        return allocateMemory(theUnsafe(), bytes);
    }

    // --------------------------------------------------------- arrayBaseOffset
    @Deprecated
    public static int arrayBaseOffset(final Unsafe unsafe,
                                      final Class<?> arrayClass) {
        return unsafe.arrayBaseOffset(arrayClass);
    }

    @Deprecated
    public static int arrayBaseOffset(final Class<?> arrayClass)
            throws ReflectiveOperationException {
        return arrayBaseOffset(theUnsafe(), arrayClass);
    }

    // --------------------------------------------------------- arrayIndexScale
    @Deprecated
    public static int arrayIndexScale(final Unsafe unsafe,
                                      final Class<?> arrayClass) {
        return unsafe.arrayIndexScale(arrayClass);
    }

    @Deprecated
    public static int arrayIndexScale(final Class<?> arrayClass)
            throws ReflectiveOperationException {
        return arrayIndexScale(theUnsafe(), arrayClass);
    }

    // ------------------------------------------------------- compareAndSwapInt
    @Deprecated
    public static boolean compareAndSwapInt(final Unsafe unsafe, final Object o,
                                            final long offset,
                                            final int expected, final int x) {
        return unsafe.compareAndSwapInt(o, offset, expected, x);
    }

    @Deprecated
    public static boolean compareAndSwapInt(final Object o, final long offset,
                                            final int expected, final int x)
            throws ReflectiveOperationException {
        return compareAndSwapInt(theUnsafe(), o, offset, expected, x);
    }

    public static boolean compareAndSwapInt(final Unsafe unsafe, Object base,
                                            final Field field,
                                            final int expected, final int x) {
        final long offset;
        if (isStatic(field.getModifiers())) {
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

    // ------------------------------------------------------ compareAndSwapLong
    public static boolean compareAndSwapLong(final Unsafe unsafe, Object base,
                                             final Field field,
                                             final long expected,
                                             final long x) {
        final long offset;
        if (isStatic(field.getModifiers())) {
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

    // ---------------------------------------------------- compareAndSwapObject
    public static boolean compareAndSwapObject(final Unsafe unsafe, Object base,
                                               final Field field,
                                               final Object expected,
                                               final Object x) {
        final long offset;
        if (isStatic(field.getModifiers())) {
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

    // -------------------------------------------------------------- copyMemory
    @Deprecated
    public static void copyMemory(final Unsafe unsafe, final long srcAddress,
                                  final long destAddress, final long bytes) {
        unsafe.copyMemory(srcAddress, destAddress, bytes);
    }

    @Deprecated
    public static void copyMemory(final long srcAddress, final long destAddress,
                                  final long bytes)
            throws ReflectiveOperationException {
        copyMemory(theUnsafe(), srcAddress, destAddress, bytes);
    }

    @Deprecated
    public static void copyMemory(final Unsafe unsafe, final Object srcBase,
                                  final long srcOffset, final Object destBase,
                                  final long destOffset, final long bytes) {
        unsafe.copyMemory(srcBase, srcOffset, destBase, destOffset, bytes);
    }

    @Deprecated
    public static void copyMemory(final Object srcBase, final long srcOffset,
                                  final Object destBase, final long destOffset,
                                  final long bytes)
            throws ReflectiveOperationException {
        copyMemory(theUnsafe(), srcBase, srcOffset, destBase, destOffset,
                   bytes);
    }

    // ---------------------------------------------------- defineAnonymousClass
    @Deprecated
    public static Class<?> defineAnonymousClass(final Unsafe unsafe,
                                                final Class hostClass,
                                                final byte[] data,
                                                final Object[] cpPatches) {
        return unsafe.defineAnonymousClass(hostClass, data, cpPatches);
    }

    @Deprecated
    public static Class<?> defineAnonymousClass(final Class hostClass,
                                                final byte[] data,
                                                final Object[] cpPatches)
            throws ReflectiveOperationException {
        return defineAnonymousClass(theUnsafe(), hostClass, data, cpPatches);
    }

    // ------------------------------------------------------------- defineClass
    @Deprecated
    public static Class defineClass(final Unsafe unsafe, final String name,
                                    final byte[] b, final int off,
                                    final int len, final ClassLoader cl,
                                    final ProtectionDomain pd) {
        return unsafe.defineClass(name, b, off, len, cl, pd);
    }

    @Deprecated
    public static Class defineClass(final String name, final byte[] b,
                                    final int off, final int len,
                                    final ClassLoader cl,
                                    final ProtectionDomain pd)
            throws ReflectiveOperationException {
        return defineClass(theUnsafe(), name, b, off, len, cl, pd);
    }

    // -------------------------------------------------- ensureClassInitialized
    @Deprecated
    public static void ensureClassInitialized(final Unsafe unsafe,
                                              final Class<?> type) {
        unsafe.ensureClassInitialized(type);
    }

    @Deprecated
    public static void ensureClassInitialized(final Class<?> type)
            throws ReflectiveOperationException {
        ensureClassInitialized(theUnsafe(), type);
    }

    // -------------------------------------------------------------- freeMemory
    @Deprecated
    public static void freeMemory(final Unsafe unsafe, final long address) {
        unsafe.freeMemory(address);
    }

    @Deprecated
    public static void freeMemory(final long address)
            throws ReflectiveOperationException {
        freeMemory(theUnsafe(), address);
    }

    // -------------------------------------------------------------- getAddress
    @Deprecated
    public static long getAddress(final Unsafe unsafe, final long address) {
        return unsafe.getAddress(address);
    }

    @Deprecated
    public static long getAddress(final long address)
            throws ReflectiveOperationException {
        return getAddress(theUnsafe(), address);
    }

    // -------------------------------------------------------------- getBoolean
    @Deprecated
    public static boolean getBoolean(final Unsafe unsafe, final Object o,
                                     final long offset) {
        return unsafe.getBoolean(o, offset);
    }

    @Deprecated
    public static boolean getBoolean(final Object o, final long offset)
            throws ReflectiveOperationException {
        return getBoolean(theUnsafe(), o, offset);
    }

    public static boolean getBoolean(final Unsafe unsafe, Object base,
                                     final Field field) {
        final int modifiers = field.getModifiers();
        final long offset;
        if (isStatic(modifiers)) {
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
        if (isVolatile(modifiers)) {
            return unsafe.getBooleanVolatile(base, offset);
        } else {
            return unsafe.getBoolean(base, offset);
        }
    }

    // ----------------------------------------------------------------- getByte
    public static byte getByte(final Unsafe unsafe, Object base,
                               final Field field) {
        final int modifiers = field.getModifiers();
        final long offset;
        if (isStatic(modifiers)) {
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
        if (isVolatile(modifiers)) {
            return unsafe.getByteVolatile(base, offset);
        } else {
            return unsafe.getByte(base, offset);
        }
    }

    // ----------------------------------------------------------------- getChar
    public static char getChar(final Unsafe unsafe, Object base,
                               final Field field) {
        final int modifiers = field.getModifiers();
        final long offset;
        if (isStatic(modifiers)) {
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
        if (isVolatile(modifiers)) {
            return unsafe.getCharVolatile(base, offset);
        } else {
            return unsafe.getChar(base, offset);
        }
    }

    // --------------------------------------------------------------- getDouble
    public static double getDouble(final Unsafe unsafe, Object base,
                                   final Field field) {
        final int modifiers = field.getModifiers();
        final long offset;
        if (isStatic(modifiers)) {
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
        if (isVolatile(modifiers)) {
            return unsafe.getDoubleVolatile(base, offset);
        } else {
            return unsafe.getDouble(base, offset);
        }
    }

    // ---------------------------------------------------------------- getFloat
    public static float getFloat(final Unsafe unsafe, Object base,
                                 final Field field) {
        final int modifiers = field.getModifiers();
        final long offset;
        if (isStatic(modifiers)) {
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
        if (isVolatile(modifiers)) {
            return unsafe.getFloatVolatile(base, offset);
        } else {
            return unsafe.getFloat(base, offset);
        }
    }

    // ------------------------------------------------------------------ getInt
    public static int getInt(final Unsafe unsafe, Object base,
                             final Field field) {
        final int modifiers = field.getModifiers();
        final long offset;
        if (isStatic(modifiers)) {
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
        if (isVolatile(modifiers)) {
            return unsafe.getIntVolatile(base, offset);
        } else {
            return unsafe.getInt(base, offset);
        }
    }

    // ----------------------------------------------------------------- getLong
    public static long getLong(final Unsafe unsafe, Object base,
                               final Field field) {
        final int modifiers = field.getModifiers();
        final long offset;
        if (isStatic(modifiers)) {
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
        if (isVolatile(modifiers)) {
            return unsafe.getLongVolatile(base, offset);
        } else {
            return unsafe.getLong(base, offset);
        }
    }

    // --------------------------------------------------------------- getObject
    public static Object getObject(final Unsafe unsafe, Object base,
                                   final Field field) {
        final int modifiers = field.getModifiers();
        final long offset;
        if (isStatic(modifiers)) {
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
        if (isVolatile(modifiers)) {
            return unsafe.getObjectVolatile(base, offset);
        } else {
            return unsafe.getObject(base, offset);
        }
    }

    // ---------------------------------------------------------------- getShort
    public static short getShort(final Unsafe unsafe, Object base,
                                 final Field field) {
        final int modifiers = field.getModifiers();
        final long offset;
        if (isStatic(modifiers)) {
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
        if (isVolatile(modifiers)) {
            return unsafe.getShortVolatile(base, offset);
        } else {
            return unsafe.getShort(base, offset);
        }
    }

    // -------------------------------------------------------------- putBoolean
    public static void putBoolean(final Unsafe unsafe, Object base,
                                  final Field field, final boolean x) {
        final int modifiers = field.getModifiers();
        final long offset;
        if (isStatic(modifiers)) {
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
        if (isVolatile(modifiers)) {
            unsafe.putBooleanVolatile(base, offset, x);
        } else {
            unsafe.putBoolean(base, offset, x);
        }
    }

    // ----------------------------------------------------------------- putByte
    public static void putByte(final Unsafe unsafe, Object base,
                               final Field field, final byte x) {
        final int modifiers = field.getModifiers();
        final long offset;
        if (isStatic(modifiers)) {
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
        if (isVolatile(modifiers)) {
            unsafe.putByteVolatile(base, offset, x);
        } else {
            unsafe.putByte(base, offset, x);
        }
    }

    // ----------------------------------------------------------------- putChar
    public static void putChar(final Unsafe unsafe, Object base,
                               final Field field, final char x) {
        final int modifiers = field.getModifiers();
        final long offset;
        if (isStatic(modifiers)) {
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
        if (isVolatile(modifiers)) {
            unsafe.putCharVolatile(base, offset, x);
        } else {
            unsafe.putChar(base, offset, x);
        }
    }

    // --------------------------------------------------------------- putDouble
    public static void putDouble(final Unsafe unsafe, Object base,
                                 final Field field, final double x) {
        final int modifiers = field.getModifiers();
        final long offset;
        if (isStatic(modifiers)) {
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
        if (isVolatile(modifiers)) {
            unsafe.putDoubleVolatile(base, offset, x);
        } else {
            unsafe.putDouble(base, offset, x);
        }
    }

    // ---------------------------------------------------------------- putFloat
    public void putFloat(final Unsafe unsafe, Object base, final Field field,
                         final float x) {
        final int modifiers = field.getModifiers();
        final long offset;
        if (isStatic(modifiers)) {
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
        if (isVolatile(modifiers)) {
            unsafe.putFloatVolatile(base, offset, x);
        } else {
            unsafe.putFloat(base, offset, x);
        }
    }

    // ------------------------------------------------------------------ putInt
    public static void putInt(final Unsafe unsafe, Object base,
                              final Field field, final int x) {
        final int modifiers = field.getModifiers();
        final long offset;
        if (isStatic(modifiers)) {
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
        if (isVolatile(modifiers)) {
            unsafe.putIntVolatile(base, offset, x);
        } else {
            unsafe.putInt(base, offset, x);
        }
    }

    // ----------------------------------------------------------------- putLong
    public static void putLong(final Unsafe unsafe, Object base,
                               final Field field, final long x) {
        final int modifiers = field.getModifiers();
        final long offset;
        if (isStatic(modifiers)) {
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
        if (isVolatile(modifiers)) {
            unsafe.putLongVolatile(base, offset, x);
        } else {
            unsafe.putLong(base, offset, x);
        }
    }

    // --------------------------------------------------------------- putObject
    public static void putObject(final Unsafe unsafe, Object base,
                                 final Field field, final Object x) {
        final int modifiers = field.getModifiers();
        final long offset;
        if (isStatic(modifiers)) {
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
        if (isVolatile(modifiers)) {
            unsafe.putObjectVolatile(base, offset, x);
        } else {
            unsafe.putObject(base, offset, x);
        }
    }

    // ----------------------------------------------------------- putOrderedInt
    public static void putOrderedInt(final Unsafe unsafe, Object base,
                                     final Field field, final int x) {
        final int modifiers = field.getModifiers();
        final long offset;
        if (isStatic(modifiers)) {
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

    // ---------------------------------------------------------- putOrderedLong
    public static void putOrderedLong(final Unsafe unsafe, Object base,
                                      final Field field, final long x) {
        final int modifiers = field.getModifiers();
        final long offset;
        if (isStatic(modifiers)) {
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

    // -------------------------------------------------------- putOrderedObject
    public static void putOrderedObject(final Unsafe unsafe, Object base,
                                        final Field field, final Object x) {
        final int modifiers = field.getModifiers();
        final long offset;
        if (isStatic(modifiers)) {
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

    // ---------------------------------------------------------------- putShort
    public static void putShort(final Unsafe unsafe, Object base,
                                final Field field, final short x) {
        final int modifiers = field.getModifiers();
        final long offset;
        if (isStatic(modifiers)) {
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
        if (isVolatile(modifiers)) {
            unsafe.putShortVolatile(base, offset, x);
        } else {
            unsafe.putShort(base, offset, x);
        }
    }

    private Dangerous() {
        super();
    }
}
