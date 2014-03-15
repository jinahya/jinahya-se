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
import java.lang.reflect.Modifier;
import java.util.concurrent.ThreadLocalRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import sun.misc.Unsafe;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class DangerousTest {


    /**
     * logger.
     */
    private static final Logger logger
        = LoggerFactory.getLogger(DangerousTest.class);


    private static class Inaccessible {


        private static final boolean staticBoolean = false;


        private static volatile boolean staticBooleanVolatile;


        private static final byte staticByte = 0;


        private static volatile byte staticByteVolatile;


        private static final char staticChar = '0';


        private static volatile char staticCharVolatile;


        private static final double staticDouble = .0d;


        private static volatile double staticDoubleVolatile;


        private static final float staticFloat = .0f;


        private static volatile float staticFloatVolatile;


        private static final int staticInt = 0;


        private static volatile int staticIntVolatile;


        private static final long staticLong = 0L;


        private static volatile long staticLongVolatile;


        private static final Object staticObject = null;


        private static volatile Object staticObjectVolatile;


        private static final short staticShort = 0;


        private static volatile short staticShortVolatile;


        private final boolean instanceBoolean = false;


        private volatile boolean instanceBooleanVolatile;


        private final byte instanceByte = 0;


        private volatile byte instanceByteVolatile;


        private final char instanceChar = '0';


        private volatile char instanceCharVolatile;


        private final double instanceDouble = .0d;


        private volatile double instanceDoubleVolatile;


        private final float instanceFloat = .0f;


        private volatile float instanceFloatVolatile;


        private final int instanceInt = 0;


        private volatile int instanceIntVolatile;


        private final long instanceLong = 0L;


        private volatile long instanceLongVolatile;


        private final Object instanceObject = null;


        private volatile Object instanceObjectVolatile;


        private final short instanceShort = 0;


        private volatile short instanceShortVolatile;


        private Inaccessible() {

            super();
        }


    }


    private static ThreadLocalRandom random() {

        return ThreadLocalRandom.current();
    }


    private static byte nextByte() {

        return (byte) (random().nextInt() & 0xFF);
    }


    private static short nextShort() {

        return (short) (random().nextInt() & 0xFFFF);
    }


    private static int nextInt() {

        return random().nextInt();
    }


    private static long nextLong() {

        return random().nextLong();
    }


    private static float nextFloat() {

        return Float.intBitsToFloat(nextInt());
    }


    private static double nextDouble() {

        return Double.longBitsToDouble(nextLong());
    }


    @Test
    public void theUnsafeInstance_()
        throws NoSuchFieldException, IllegalAccessException {

        final Unsafe actual = Dangerous.theUnsafe();

        final Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        final Object expected = field.get(null);

        Assert.assertEquals(actual, expected);
    }


    @Test
    public void newUnsafeInstance_()
        throws NoSuchFieldException, IllegalAccessException {

        final Unsafe unsafe = Dangerous.newUnsafe();
    }


    @Test
    public void allocateInstance_() throws InstantiationException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Inaccessible instance
            = dangerous.allocateInstance(Inaccessible.class);

        Assert.assertNotNull(instance);
    }


    @Test
    public void compareAndSwapInt_instance_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base
            = dangerous.unsafe().allocateInstance(Inaccessible.class);
        final Field field = Inaccessible.class.getDeclaredField("instanceInt");
        assert !Modifier.isStatic(field.getModifiers());
        final int expected = 0;
        final int x = 0;

        Assert.assertTrue(
            dangerous.compareAndSwapInt(base, field, expected, x));
    }


    @Test
    public void compareAndSwapInt_static_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base = null;
        final Field field = Inaccessible.class.getDeclaredField("staticInt");
        assert Modifier.isStatic(field.getModifiers());
        final int expected = 0;
        final int x = 0;

        dangerous.compareAndSwapInt(base, field, expected, x);
    }


    @Test
    public void compareAndSwapLong_instance_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base
            = dangerous.unsafe().allocateInstance(Inaccessible.class);
        final Field field = Inaccessible.class.getDeclaredField("instanceLong");
        assert !Modifier.isStatic(field.getModifiers());
        final long expected = 0L;
        final long x = 0L;

        Assert.assertTrue(
            dangerous.compareAndSwapLong(base, field, expected, x));
    }


    @Test
    public void compareAndSwapLong_static_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base = null;
        final Field field = Inaccessible.class.getDeclaredField("staticLong");
        assert Modifier.isStatic(field.getModifiers());
        final long expected = 0L;
        final long x = 0L;

        dangerous.compareAndSwapLong(base, field, expected, x);
    }


    @Test
    public void compareAndSwapObject_instance_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base
            = dangerous.unsafe().allocateInstance(Inaccessible.class);
        final Field field
            = Inaccessible.class.getDeclaredField("instanceObject");
        assert !Modifier.isStatic(field.getModifiers());
        final Object expected = null;
        final Object x = new Object();

        Assert.assertTrue(
            dangerous.compareAndSwapObject(base, field, expected, x));
    }


    @Test
    public void compareAndSwapObject_static_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base = null;
        final Field field = Inaccessible.class.getDeclaredField("staticObject");
        assert Modifier.isStatic(field.getModifiers());
        final Object expected = null;
        final Object x = null;

        dangerous.compareAndSwapObject(base, field, expected, x);
    }


    @Test
    public void compareAndSwapObject_generic_instance_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base
            = dangerous.unsafe().allocateInstance(Inaccessible.class);
        final Field field
            = Inaccessible.class.getDeclaredField("instanceObject");
        assert !Modifier.isStatic(field.getModifiers());
        final Class<Object> type = Object.class;
        assert field.getType().equals(type);
        final Object expected = null;
        final Object x = new Object();

        Assert.assertTrue(
            dangerous.compareAndSwapObject(base, field, type, expected, x));
    }


    @Test
    public void compareAndSwapObject_generic_static_()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base = null;
        final Field field = Inaccessible.class.getDeclaredField("staticObject");
        assert Modifier.isStatic(field.getModifiers());
        final Class<Object> type = Object.class;
        assert field.getType().equals(type);
        final Object expected = null;
        final Object x = new Object();

        dangerous.compareAndSwapObject(base, field, type, expected, x);
    }


    @Test
    public void getBoolean_nonBooleanField_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base
            = dangerous.unsafe().allocateInstance(Inaccessible.class);
        final Field field
            = Inaccessible.class.getDeclaredField("instanceDouble");
        assert !Modifier.isStatic(field.getModifiers());
        dangerous.putObject(base, field, "");

        final boolean actual = dangerous.getBoolean(base, field);
        logger.debug("boolean value from an int field: {}", actual);
    }


    @Test
    public void getBoolean_instance_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base
            = dangerous.unsafe().allocateInstance(Inaccessible.class);
        final Field field
            = Inaccessible.class.getDeclaredField("instanceBoolean");
        assert !Modifier.isStatic(field.getModifiers());

        Assert.assertFalse(dangerous.getBoolean(base, field));
    }


    @Test
    public void getBoolean_instance_volatile_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base
            = dangerous.unsafe().allocateInstance(Inaccessible.class);
        final Field field
            = Inaccessible.class.getDeclaredField("instanceBooleanVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        Assert.assertFalse(dangerous.getBoolean(base, field));
    }


    @Test
    public void getBoolean_static_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base = null;
        final Field field = Inaccessible.class.getDeclaredField("staticBoolean");
        assert Modifier.isStatic(field.getModifiers());

        dangerous.getBoolean(base, field);
    }


    @Test
    public void getBoolean_static_volatile() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object object = null;
        final Field field
            = Inaccessible.class.getDeclaredField("staticBooleanVolatile");

        dangerous.getBoolean(object, field);
    }


    @Test
    public void getByte_instance_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base
            = dangerous.unsafe().allocateInstance(Inaccessible.class);
        final Field field
            = Inaccessible.class.getDeclaredField("instanceByte");
        assert !Modifier.isStatic(field.getModifiers());

        Assert.assertEquals(dangerous.getByte(base, field), (byte) 0);
    }


    @Test
    public void getByte_instance_volatile_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base
            = dangerous.unsafe().allocateInstance(Inaccessible.class);
        final Field field
            = Inaccessible.class.getDeclaredField("instanceByteVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        Assert.assertEquals(dangerous.getByte(base, field), 0b00);
    }


    @Test
    public void getByte_static_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base = null;
        final Field field = Inaccessible.class.getDeclaredField("staticByte");
        assert Modifier.isStatic(field.getModifiers());

        dangerous.getByte(base, field);
    }


    @Test
    public void getByte_static_volatile_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base = null;
        final Field field
            = Inaccessible.class.getDeclaredField("staticByteVolatile");

        final byte actual = dangerous.getByte(base, field);
    }


    @Test
    public void getChar_instance_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base
            = dangerous.unsafe().allocateInstance(Inaccessible.class);
        final Field field
            = Inaccessible.class.getDeclaredField("instanceChar");
        assert !Modifier.isStatic(field.getModifiers());

        final char actual = dangerous.getChar(base, field);
        final char expected = 0;
        Assert.assertEquals(actual, expected);
    }


    @Test
    public void getChar_instance_volatile_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base
            = dangerous.unsafe().allocateInstance(Inaccessible.class);
        final Field field
            = Inaccessible.class.getDeclaredField("instanceCharVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        final char actual = dangerous.getChar(base, field);
        final char expected = 0;
        Assert.assertEquals(actual, expected);
    }


    @Test
    public void getChar_static_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base = null;
        final Field field = Inaccessible.class.getDeclaredField("staticChar");
        assert Modifier.isStatic(field.getModifiers());

        final char actual = dangerous.getChar(base, field);
    }


    @Test
    public void getChar_static_volatile_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base = null;
        final Field field
            = Inaccessible.class.getDeclaredField("staticCharVolatile");
        assert Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        final char actual = dangerous.getChar(base, field);
    }


    @Test
    public void getDouble_instance_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base
            = dangerous.unsafe().allocateInstance(Inaccessible.class);
        final Field field
            = Inaccessible.class.getDeclaredField("instanceDouble");
        assert !Modifier.isStatic(field.getModifiers());

        final double actual = dangerous.getDouble(base, field);
        final double expected = .0d;
        Assert.assertEquals(actual, expected);
    }


    @Test
    public void getDouble_instance_volatile_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base
            = dangerous.unsafe().allocateInstance(Inaccessible.class);
        final Field field
            = Inaccessible.class.getDeclaredField("instanceDoubleVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        final double actual = dangerous.getDouble(base, field);
        final double expected = .0d;
        Assert.assertEquals(actual, expected);
    }


    @Test
    public void getDouble_static_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base = null;
        final Field field = Inaccessible.class.getDeclaredField("staticDouble");
        assert Modifier.isStatic(field.getModifiers());

        final double actual = dangerous.getDouble(base, field);
    }


    @Test
    public void getDouble_static_volatile_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base = null;
        final Field field
            = Inaccessible.class.getDeclaredField("staticDoubleVolatile");
        assert Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        final double actual = dangerous.getDouble(base, field);
    }


    @Test
    public void getFloat_instance_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base
            = dangerous.unsafe().allocateInstance(Inaccessible.class);
        final Field field
            = Inaccessible.class.getDeclaredField("instanceFloat");
        assert !Modifier.isStatic(field.getModifiers());

        final float actual = dangerous.getFloat(base, field);
        final float expected = .0f;
        Assert.assertEquals(actual, expected);
    }


    @Test
    public void getFloat_instance_volatile_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base
            = dangerous.unsafe().allocateInstance(Inaccessible.class);
        final Field field
            = Inaccessible.class.getDeclaredField("instanceFloatVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        final float actual = dangerous.getFloat(base, field);
        final float expected = .0f;
        Assert.assertEquals(actual, expected);
    }


    @Test
    public void getFloat_static_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base = null;
        final Field field = Inaccessible.class.getDeclaredField("staticFloat");
        assert Modifier.isStatic(field.getModifiers());

        final float actual = dangerous.getFloat(base, field);
    }


    @Test
    public void getFloat_static_volatile_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base = null;
        final Field field
            = Inaccessible.class.getDeclaredField("staticFloatVolatile");
        assert Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        final float actual = dangerous.getFloat(base, field);
    }


    @Test
    public void getInt_instance_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base
            = dangerous.unsafe().allocateInstance(Inaccessible.class);
        final Field field
            = Inaccessible.class.getDeclaredField("instanceInt");
        assert !Modifier.isStatic(field.getModifiers());

        final int actual = dangerous.getInt(base, field);
        final int expected = 0;
        Assert.assertEquals(actual, expected);
    }


    @Test
    public void getInt_instance_volatile_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base
            = dangerous.unsafe().allocateInstance(Inaccessible.class);
        final Field field
            = Inaccessible.class.getDeclaredField("instanceIntVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        final int actual = dangerous.getInt(base, field);
        final int expected = 0;
        Assert.assertEquals(actual, expected);
    }


    @Test
    public void getInt_static_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base = null;
        final Field field = Inaccessible.class.getDeclaredField("staticInt");
        assert Modifier.isStatic(field.getModifiers());

        final int actual = dangerous.getInt(base, field);
    }


    @Test
    public void getInt_static_volatile_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base = null;
        final Field field
            = Inaccessible.class.getDeclaredField("staticIntVolatile");
        assert Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        final int actual = dangerous.getInt(base, field);
    }


    @Test
    public void getLong_instance_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base
            = dangerous.unsafe().allocateInstance(Inaccessible.class);
        final Field field
            = Inaccessible.class.getDeclaredField("instanceLong");
        assert !Modifier.isStatic(field.getModifiers());

        final long actual = dangerous.getLong(base, field);
        final long expected = 0L;
        Assert.assertEquals(actual, expected);
    }


    @Test
    public void getLong_instance_volatile_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base
            = dangerous.unsafe().allocateInstance(Inaccessible.class);
        final Field field
            = Inaccessible.class.getDeclaredField("instanceLongVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        final long actual = dangerous.getLong(base, field);
        final long expected = 0L;
        Assert.assertEquals(actual, expected);
    }


    @Test
    public void getLong_static_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base = null;
        final Field field = Inaccessible.class.getDeclaredField("staticLong");
        assert Modifier.isStatic(field.getModifiers());

        final long actual = dangerous.getLong(base, field);
    }


    @Test
    public void getLong_static_volatile_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base = null;
        final Field field
            = Inaccessible.class.getDeclaredField("staticLongVolatile");
        assert Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        final long actual = dangerous.getLong(base, field);
    }


    @Test
    public void getObject_instance_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base
            = dangerous.unsafe().allocateInstance(Inaccessible.class);
        final Field field
            = Inaccessible.class.getDeclaredField("instanceObject");
        assert !Modifier.isStatic(field.getModifiers());

        final Object actual = dangerous.getObject(base, field);
        final Object expected = null;
        Assert.assertEquals(actual, expected);
    }


    @Test
    public void getObject_instance_volatile_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base
            = dangerous.unsafe().allocateInstance(Inaccessible.class);
        final Field field
            = Inaccessible.class.getDeclaredField("instanceObjectVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        final Object actual = dangerous.getObject(base, field);
        final Object expected = null;
        Assert.assertEquals(actual, expected);
    }


    @Test
    public void getObject_static_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base = null;
        final Field field = Inaccessible.class.getDeclaredField("staticObject");
        assert Modifier.isStatic(field.getModifiers());

        final Object actual = dangerous.getObject(base, field);
    }


    @Test
    public void getObject_static_volatile_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base = null;
        final Field field
            = Inaccessible.class.getDeclaredField("staticObjectVolatile");
        assert Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        final Object actual = dangerous.getObject(base, field);
    }


    @Test
    public void getShort_instance_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base
            = dangerous.unsafe().allocateInstance(Inaccessible.class);
        final Field field
            = Inaccessible.class.getDeclaredField("instanceShort");
        assert !Modifier.isStatic(field.getModifiers());

        final short actual = dangerous.getShort(base, field);
        final short expected = 0;
        Assert.assertEquals(actual, expected);
    }


    @Test
    public void getShort_instance_volatile_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base
            = dangerous.unsafe().allocateInstance(Inaccessible.class);
        final Field field
            = Inaccessible.class.getDeclaredField("instanceShortVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        final short actual = dangerous.getShort(base, field);
        final short expected = 0;
        Assert.assertEquals(actual, expected);
    }


    @Test
    public void getShort_static_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base = null;
        final Field field = Inaccessible.class.getDeclaredField("staticShort");
        assert Modifier.isStatic(field.getModifiers());

        final short actual = dangerous.getShort(base, field);
    }


    @Test
    public void getShort_static_volatile_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base = null;
        final Field field
            = Inaccessible.class.getDeclaredField("staticShortVolatile");
        assert Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        final short actual = dangerous.getShort(base, field);
    }


    @Test
    public void putBoolean_instance_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base
            = dangerous.unsafe().allocateInstance(Inaccessible.class);
        final Field field
            = Inaccessible.class.getDeclaredField("instanceBoolean");
        assert !Modifier.isStatic(field.getModifiers());

        dangerous.putBoolean(base, field, true);
    }


    @Test
    public void putBoolean_instance_volatile_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base
            = dangerous.unsafe().allocateInstance(Inaccessible.class);
        final Field field
            = Inaccessible.class.getDeclaredField("instanceBooleanVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        dangerous.putBoolean(base, field, true);
    }


    @Test
    public void putBoolean_static_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base = null;
        final Field field
            = Inaccessible.class.getDeclaredField("staticBoolean");
        assert Modifier.isStatic(field.getModifiers());

        dangerous.putBoolean(base, field, true);
    }


    @Test
    public void putBoolean_static_volatile_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base = null;
        final Field field
            = Inaccessible.class.getDeclaredField("staticBooleanVolatile");
        assert Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        dangerous.putBoolean(base, field, true);
    }


    @Test
    public void putByte_instance_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base
            = dangerous.unsafe().allocateInstance(Inaccessible.class);
        final Field field
            = Inaccessible.class.getDeclaredField("instanceByte");
        assert !Modifier.isStatic(field.getModifiers());

        dangerous.putByte(base, field, (byte) 1);
    }


    @Test
    public void putByte_instance_volatile_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base
            = dangerous.unsafe().allocateInstance(Inaccessible.class);
        final Field field
            = Inaccessible.class.getDeclaredField("instanceByteVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        dangerous.putByte(base, field, (byte) 1);
    }


    @Test
    public void putByte_static_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base = null;
        final Field field = Inaccessible.class.getDeclaredField("staticByte");
        assert Modifier.isStatic(field.getModifiers());

        dangerous.putByte(base, field, (byte) 1);
    }


    @Test
    public void putByte_static_volatile_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base = null;
        final Field field
            = Inaccessible.class.getDeclaredField("staticByteVolatile");
        assert Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        dangerous.putByte(base, field, (byte) 1);
    }


    @Test
    public void putChar_instance_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base
            = dangerous.unsafe().allocateInstance(Inaccessible.class);
        final Field field
            = Inaccessible.class.getDeclaredField("instanceChar");
        assert !Modifier.isStatic(field.getModifiers());

        dangerous.putChar(base, field, (char) 1);
    }


    @Test
    public void putChar_instance_volatile_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base
            = dangerous.unsafe().allocateInstance(Inaccessible.class);
        final Field field
            = Inaccessible.class.getDeclaredField("instanceCharVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        dangerous.putChar(base, field, (char) 1);
    }


    @Test
    public void putChar_static_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base = null;
        final Field field = Inaccessible.class.getDeclaredField("staticChar");
        assert Modifier.isStatic(field.getModifiers());

        dangerous.putChar(base, field, (char) 1);
    }


    @Test
    public void putChar_static_volatile_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base = null;
        final Field field
            = Inaccessible.class.getDeclaredField("staticCharVolatile");
        assert Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        dangerous.putChar(base, field, (char) 1);
    }


    @Test
    public void putDouble_instance_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base
            = dangerous.unsafe().allocateInstance(Inaccessible.class);
        final Field field
            = Inaccessible.class.getDeclaredField("instanceDouble");
        assert !Modifier.isStatic(field.getModifiers());

        dangerous.putDouble(base, field, .1d);
    }


    @Test
    public void putDouble_instance_volatile_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base
            = dangerous.unsafe().allocateInstance(Inaccessible.class);
        final Field field
            = Inaccessible.class.getDeclaredField("instanceDoubleVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        dangerous.putDouble(base, field, .1d);
    }


    @Test
    public void putDouble_static_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base = null;
        final Field field = Inaccessible.class.getDeclaredField("staticDouble");
        assert Modifier.isStatic(field.getModifiers());

        dangerous.putDouble(base, field, .1d);
    }


    @Test
    public void putDouble_static_volatile_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.getInstance();

        final Object base = null;
        final Field field
            = Inaccessible.class.getDeclaredField("staticDoubleVolatile");
        assert Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        dangerous.putDouble(base, field, .1d);
    }


}

