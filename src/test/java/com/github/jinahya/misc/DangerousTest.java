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
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.concurrent.ThreadLocalRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;


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


    private static class MyObject {


        private static boolean staticBoolean;


        private static volatile boolean staticBooleanVolatile;


        private static byte staticByte;


        private static volatile byte staticByteVolatile;


        private static char staticChar;


        private static volatile char staticCharVolatile;


        private static double staticDouble;


        private static volatile double staticDoubleVolatile;


        private static float staticFloat;


        private static volatile float staticFloatVolatile;


        private static int staticInt;


        private static volatile int staticIntVolatile;


        private static long staticLong;


        private static volatile long staticLongVolatile;


        private static Object staticObject;


        private static volatile Object staticObjectVolatile;


        private static short staticShort;


        private static volatile short staticShortVolatile;


        private boolean instanceBoolean;


        private volatile boolean instanceBooleanVolatile;


        private byte instanceByte;


        private volatile byte instanceByteVolatile;


        private char instanceChar;


        private volatile char instanceCharVolatile;


        private double instanceDouble;


        private volatile double instanceDoubleVolatile;


        private float instanceFloat;


        private volatile float instanceFloatVolatile;


        private int instanceInt;


        private volatile int instanceIntVolatile;


        private long instanceLong;


        private volatile long instanceLongVolatile;


        private Object instanceObject;


        private volatile Object instanceObjectVolatile;


        private short instanceShort;


        private volatile short instanceShortVolatile;


    }


    @Test(enabled = false)
    public static void checkAllMethodsWrapped()
        throws ClassNotFoundException, NoSuchMethodException {

        final Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
        for (final Method method : unsafeClass.getMethods()) {
            if (!method.getDeclaringClass().equals(unsafeClass)) {
                continue;
            }
            if (!Modifier.isPublic(method.getModifiers())) {
                continue;
            }
            final Method wrapped;
            try {
                wrapped = Dangerous.class.getDeclaredMethod(
                    method.getName(), method.getParameterTypes());
            } catch (final NoSuchMethodException nsme) {
                logger.error("method not wrapped: {}", method);
                throw nsme;
            }
        }
    }


    @Test
    public void allocateInstance_() throws InstantiationException {

        final Dangerous dangerous = Dangerous.newInstance();

        final MyObject instance
            = dangerous.allocateInstance(MyObject.class);

        Assert.assertNotNull(instance);
    }


    @Test
    public void compareAndSwapInt_instanceField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object object = new MyObject();
        final Field field = MyObject.class.getDeclaredField("instanceInt");
        final int expected = 0;
        final int x = 0;

        dangerous.compareAndSwapInt(object, field, expected, x);
    }


    @Test
    public void compareAndSwapInt_staticField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object object = null;
        final Field field = MyObject.class.getDeclaredField("staticInt");
        final int expected = 0;
        final int x = 0;

        dangerous.compareAndSwapInt(object, field, expected, x);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void compareAndSwapInt_nullField() {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object object = new MyObject();
        final Field field = null;
        final int expected = 0;
        final int x = 0;

        dangerous.compareAndSwapInt(object, null, expected, x);
    }


    @Test
    public void compareAndSwapLong_instanceField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = MyObject.class.getDeclaredField("instanceLong");
        final long expected = 0L;
        final long x = 0L;

        Assert.assertTrue(
            dangerous.compareAndSwapLong(base, field, expected, x));
    }


    @Test
    public void compareAndSwapLong_staticField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object object = null;
        final Field field = MyObject.class.getDeclaredField("staticLong");
        assert Modifier.isStatic(field.getModifiers());
        final long expected = 0L;
        final long x = 0L;

        dangerous.compareAndSwapLong(object, field, expected, x);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void compareAndSwapLong_nullField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object object = null;
        final Field field = null;
        final long expected = 0L;
        final long x = 0L;

        dangerous.compareAndSwapLong(object, field, expected, x);
    }


    @Test
    public void compareAndSwapObject_instanceField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object object = new MyObject();
        final Field field = MyObject.class.getDeclaredField("instanceObject");
        final Object expected = null;
        final Object x = new Object();

        Assert.assertTrue(
            dangerous.compareAndSwapObject(object, field, expected, x));
    }


    @Test
    public void compareAndSwapObject_staticField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object object = null;
        final Field field = MyObject.class.getDeclaredField("staticObject");
        final Object expected = null;
        final Object x = new Object();

        dangerous.compareAndSwapObject(object, field, expected, x);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void compareAndSwapObject_nullField() {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object object = null;
        final Field field = null;
        final Object expected = new Object();
        final Object x = new Object();

        dangerous.compareAndSwapObject(object, field, expected, x);
    }


    @Test
    public void compareAndSwapObject_generic_instanceField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object object = new MyObject();
        final Field field = MyObject.class.getDeclaredField("instanceObject");
        assert !Modifier.isStatic(field.getModifiers());
        final Class<Object> type = Object.class;
        assert field.getType().equals(type);
        final Object expected = null;
        final Object x = new Object();

        Assert.assertTrue(
            dangerous.compareAndSwapObject(object, field, type, expected, x));
    }


    @Test
    public void compareAndSwapObject_generic_staticField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object object = null;
        final Field field = MyObject.class.getDeclaredField("staticObject");
        assert Modifier.isStatic(field.getModifiers());
        final Class<Object> type = Object.class;
        assert field.getType().equals(type);
        final Object expected = null;
        final Object x = new Object();

        dangerous.compareAndSwapObject(object, field, type, expected, x);
    }


    @Test
    public void getBoolean_instanceField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object object = new MyObject();
        final Field field = MyObject.class.getDeclaredField("instanceBoolean");
        assert !Modifier.isStatic(field.getModifiers());

        Assert.assertFalse(dangerous.getBoolean(object, field));
    }


    @Test
    public void getBoolean_instanceField_volatile()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object object = new MyObject();

        final Field field
            = MyObject.class.getDeclaredField("instanceBooleanVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        Assert.assertFalse(dangerous.getBoolean(object, field));
    }


    @Test
    public void getBoolean_staticField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object object = null;
        final Field field = MyObject.class.getDeclaredField("staticBoolean");
        assert Modifier.isStatic(field.getModifiers());

        dangerous.getBoolean(object, field);
    }


    @Test
    public void getBoolean_staticField_volatile() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object object = null;
        final Field field
            = MyObject.class.getDeclaredField("staticBooleanVolatile");

        dangerous.getBoolean(object, field);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getBoolean_nullField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object object = null;
        final Field field = null;

        dangerous.getBoolean(object, field);
    }


    @Test
    public void getByte_instanceField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object object = new MyObject();
        final Field field = MyObject.class.getDeclaredField("instanceBoolean");
        assert !Modifier.isStatic(field.getModifiers());

        Assert.assertEquals(dangerous.getByte(object, field), (byte) 0);
    }


    @Test
    public void getByte_instanceField_volatile()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object object = new MyObject();

        final Field field
            = MyObject.class.getDeclaredField("instanceBooleanVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        Assert.assertEquals(
            dangerous.getByte(object, field), 0b00);
    }


    @Test
    public void getByte_staticField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object object = null;
        final Field field = MyObject.class.getDeclaredField("staticBoolean");
        assert Modifier.isStatic(field.getModifiers());

        dangerous.getByte(object, field);
    }


    @Test
    public void getByte_staticField_volatile() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object object = null;
        final Field field
            = MyObject.class.getDeclaredField("staticBooleanVolatile");

        dangerous.getByte(object, field);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getByte_nullField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object object = null;
        final Field field = null;

        dangerous.getByte(object, field);
    }


}

