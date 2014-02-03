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


        private static volatile boolean staticByteVolatile;


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


    @Test
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
    public void addressSize_() {

        final Dangerous dangerous = Dangerous.newInstance();

        final int addressSize = dangerous.addressSize();
        logger.info("addressSize: {}", addressSize);
    }


    @Test
    public void allocateInstance_() throws InstantiationException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object instance = dangerous.allocateInstance(MyObject.class);

        Assert.assertNotNull(instance);
    }


    @Test
    public void allocateInstanceGeneric_() throws InstantiationException {

        final Dangerous dangerous = Dangerous.newInstance();

        final MyObject instance
            = dangerous.allocateInstanceGeneric(MyObject.class);

        Assert.assertNotNull(instance);
    }


    @Test
    public void allocateMemory_() {

        final Dangerous dangerous = Dangerous.newInstance();

        dangerous.allocateMemory(0L);

        final long size = random().nextLong(1L, 1048576L + 1L);
        final long address = dangerous.allocateMemory(size);
        try {
        } finally {
            dangerous.freeMemory(address);
        }
    }


    @Test
    public void arrayBaseOffset_() {

        final Dangerous dangerous = Dangerous.newInstance();

        logger.trace("arrayBaseOffset(Object[].class): {}",
                     dangerous.arrayBaseOffset(Object[].class));

        logger.trace("arrayBaseOffset(String[].class): {}",
                     dangerous.arrayBaseOffset(String[].class));
    }


    @Test
    public void arrayIndexScale_() {

        final Dangerous dangerous = Dangerous.newInstance();

        logger.trace("arrayIndexScale(Object[].class): {}",
                     dangerous.arrayIndexScale(Object[].class));

        logger.trace("arrayIndexScale(String[].class): {}",
                     dangerous.arrayIndexScale(String[].class));
    }


    @Test
    public void staticCompareAndSwapInt_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("staticInt");

        dangerous.staticCompareAndSwapInt(field, 0, 0);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void staticCompareAndSwapInt_nullField()
        throws NoSuchFieldException {

        Dangerous.newInstance().staticCompareAndSwapInt(null, 0, 0);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void staticCompareAndSwapInt_instanceField()
        throws NoSuchFieldException {

        Dangerous.newInstance().staticCompareAndSwapInt(
            MyObject.class.getDeclaredField("instanceInt"), 0, 0);
    }


    @Test
    public void instanceCompareAndSwapInt_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("instanceInt");

        dangerous.instanceCompareAndSwapInt(new MyObject(), field, 0, 0);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void instanceCompareAndSwapInt_nullField() {

        Dangerous.newInstance().instanceCompareAndSwapInt(null, null, 0, 0);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void instanceCompareAndSwapInt_staticField()
        throws NoSuchFieldException {

        Dangerous.newInstance().instanceCompareAndSwapInt(
            null, MyObject.class.getDeclaredField("staticInt"), 0, 0);
    }


    @Test
    public void staticCompareAndSwapLong_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("staticLong");
        assert Modifier.isStatic(field.getModifiers());
        final long expected = 0L;
        final long x = 0L;

        dangerous.staticCompareAndSwapLong(field, expected, x);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void staticCompareAndSwapLong_nullField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = null;
        final long expected = 0L;
        final long x = 0L;

        dangerous.staticCompareAndSwapLong(field, expected, x);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void staticCompareAndSwapLong_instanceField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("instanceLong");
        assert !Modifier.isStatic(field.getModifiers());
        final long expected = 0L;
        final long x = 0L;

        dangerous.staticCompareAndSwapLong(field, expected, x);
    }


    @Test
    public void instanceCompareAndSwapLong_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = MyObject.class.getDeclaredField("instanceLong");
        final long expected = 0L;
        final long x = 0L;

        Assert.assertTrue(
            dangerous.instanceCompareAndSwapLong(base, field, expected, x));
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void instanceCompareAndSwapLong_nullField() {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = null;
        final long expected = 0L;
        final long x = 0L;

        dangerous.instanceCompareAndSwapLong(base, field, expected, x);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void instanceCompareAndSwapLong_staticField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = MyObject.class.getDeclaredField("staticLong");
        assert Modifier.isStatic(field.getModifiers());
        final long expected = 0L;
        final long x = 0L;

        dangerous.instanceCompareAndSwapLong(base, field, expected, x);
    }


    @Test
    public void staticCompareAndSwapObject_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("staticObject");
        final Object expected = null;
        final Object x = new Object();

        dangerous.staticCompareAndSwapObject(field, expected, x);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void staticCompareAndSwapObject_nullField() {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = null;
        final Object expected = new Object();
        final Object x = new Object();

        dangerous.staticCompareAndSwapObject(field, expected, x);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void staticCompareAndSwapObject_instanceField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("instanceObject");
        assert !Modifier.isStatic(field.getModifiers());
        assert field.getType().equals(Object.class);
        final Object expected = new Object();
        final Object x = new Object();

        dangerous.staticCompareAndSwapObject(field, expected, x);
    }


    @Test
    public void staticCompareAndSwapObjectGeneric_()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("staticObject");
        assert Modifier.isStatic(field.getModifiers());
        assert field.getType().equals(Object.class);
        final Class<Object> type = Object.class;
        final Object expected = null;
        final Object x = new Object();

        dangerous.staticCompareAndSwapObjectGeneric(field, type, expected, x);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void staticCompareAndSwapObjectGeneric_nullField() {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = null;
        final Class<Object> type = Object.class;
        final Object expected = new Object();
        final Object x = new Object();

        dangerous.staticCompareAndSwapObjectGeneric(field, type, expected, x);
    }


    @Test
    public void instanceCompareAndSwapObject_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = MyObject.class.getDeclaredField("instanceObject");
        final Object expected = null;
        final Object x = new Object();

        Assert.assertTrue(
            dangerous.instanceCompareAndSwapObject(base, field, expected, x));
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void instanceCompareAndSwapObject_nullField() {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = null;
        final Object expected = new Object();
        final Object x = new Object();

        dangerous.instanceCompareAndSwapObject(base, field, expected, x);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void instanceCompareAndSwapObject_staticField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = MyObject.class.getDeclaredField("staticObject");
        assert Modifier.isStatic(field.getModifiers());
        final Object expected = new Object();
        final Object x = new Object();

        dangerous.instanceCompareAndSwapObject(base, field, expected, x);
    }


    @Test
    public void instanceCompareAndSwapObjectGeneric_()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = MyObject.class.getDeclaredField("instanceObject");
        assert !Modifier.isStatic(field.getModifiers());
        assert field.getType().equals(Object.class);
        final Class<Object> type = Object.class;
        final Object expected = null;
        final Object x = new Object();

        Assert.assertTrue(
            dangerous.instanceCompareAndSwapObjectGeneric(
                base, field, type, expected, x));
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void instanceCompareAndSwapObjectGeneric_nullField() {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = null;
        final Class<Object> type = Object.class;
        final Object expected = new Object();
        final Object x = new Object();

        dangerous.instanceCompareAndSwapObjectGeneric(
            base, field, type, expected, x);
    }


    @Test
    public void copyMemory_address() {

        final Dangerous dangerous = Dangerous.newInstance();

        final long address = dangerous.allocateMemory(8);
        try {
            dangerous.copyMemory(address, address + 4, 4);
        } finally {
            dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true, invocationCount = 1)
    public void copyMemory_address_int() {

        final int intSizeInBytes = Integer.SIZE / 8;

        final Dangerous dangerous = Dangerous.newInstance();

        final long size = random().nextLong(intSizeInBytes * 2, 128L);
        logger.debug("size: {}", size);
        assert size >= intSizeInBytes * 2;

        final long address = dangerous.allocateMemory(size);
        try {
            logger.debug("address: {} {}", address,
                         String.format("%x", address));

            final long srcAddressLeast = address;
            final long srcAddressMost = address + size - intSizeInBytes * 2;
            final long srcAddress
                = srcAddressLeast == srcAddressMost
                  ? srcAddressLeast
                  : random().nextLong(srcAddressLeast, srcAddressMost);
            logger.debug("srcAddress: {} {}", srcAddress,
                         String.format("%x", srcAddress));
            assert srcAddress >= address;
            assert srcAddress < address + size - intSizeInBytes;

            final long destAddessLeast = srcAddress + intSizeInBytes;
            final long destAddressMost = address + size - intSizeInBytes;
            assert destAddessLeast <= destAddressMost;
            final long destAddress
                = destAddessLeast == destAddressMost
                  ? destAddessLeast
                  : random().nextLong(destAddessLeast, destAddressMost);
            logger.debug("destAddress: {}, {}", destAddress,
                         String.format("%x", destAddress));
            assert destAddress >= srcAddress + intSizeInBytes;
            assert destAddress <= address + size - intSizeInBytes;

            final int expected = random().nextInt();
            dangerous.putInt(srcAddress, expected);
            dangerous.copyMemory(srcAddress, destAddress, intSizeInBytes);
            Assert.assertEquals(dangerous.getInt(destAddress), expected);
        } finally {
            dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true, invocationCount = 1)
    public void copyMemory_address_long() {

        final int longSizeInBytes = Long.SIZE / 8;

        final Dangerous dangerous = Dangerous.newInstance();

        final long size = random().nextLong(longSizeInBytes * 2, 128L);
        logger.debug("size: {}", size);
        assert size >= longSizeInBytes * 2;

        final long address = dangerous.allocateMemory(size);
        try {
            logger.debug("address: {} {}", address,
                         String.format("%x", address));

            final long srcAddressLeast = address;
            final long srcAddressMost = address + size - longSizeInBytes * 2;
            final long srcAddress
                = srcAddressLeast == srcAddressMost
                  ? srcAddressLeast
                  : random().nextLong(srcAddressLeast, srcAddressMost);
            logger.debug("srcAddress: {} {}", srcAddress,
                         String.format("%x", srcAddress));
            assert srcAddress >= address;
            assert srcAddress < address + size - longSizeInBytes;

            final long destAddessLeast = srcAddress + longSizeInBytes;
            final long destAddressMost = address + size - longSizeInBytes;
            assert destAddessLeast <= destAddressMost;
            final long destAddress
                = destAddessLeast == destAddressMost
                  ? destAddessLeast
                  : random().nextLong(destAddessLeast, destAddressMost);
            logger.debug("destAddress: {}, {}", destAddress,
                         String.format("%x", destAddress));
            assert destAddress >= srcAddress + longSizeInBytes;
            assert destAddress <= address + size - longSizeInBytes;

            final long expected = random().nextLong();
            dangerous.putLong(srcAddress, expected);
            dangerous.copyMemory(srcAddress, destAddress, longSizeInBytes);
            Assert.assertEquals(dangerous.getLong(destAddress), expected);
        } finally {
            dangerous.freeMemory(address);
        }
    }


    @Test(enabled = false)
    public void copyMemory_object_() {

        // @todo implement this
    }


    @Test(enabled = false)
    public void defineAnonymousClass_() {

        // @todo implement this
    }


    @Test(enabled = false)
    public void defineClass_() {

        // @todo implement this
    }


    @Test(enabled = false)
    public void ensureClassInitialized_() {

        // @todo implement this
    }


    @Test(enabled = true)
    public void freeMemory_() {

        final Dangerous dangerous = Dangerous.newInstance();

        dangerous.freeMemory(0);
    }


    @Test
    public void getAddress_() {

        final Dangerous dangerous = Dangerous.newInstance();

        final long address = dangerous.allocateMemory(1);
        try {
            logger.trace("address: {}", address);
            final long pointer = dangerous.getAddress(address);
            logger.trace("pointer: {}", pointer);
        } finally {
            dangerous.freeMemory(address);
        }
    }


    @Test
    public void getStaticBoolean_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("staticBoolean");
        assert Modifier.isStatic(field.getModifiers());

        dangerous.getStaticBoolean(field);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getStaticBoolean_nullField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = null;

        dangerous.getStaticBoolean(field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getStaticBoolean_instanceField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("instanceBoolean");
        assert !Modifier.isStatic(field.getModifiers());

        dangerous.getStaticBoolean(field);
    }


    @Test
    public void getInstanceBoolean_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("instanceBoolean");
        assert !Modifier.isStatic(field.getModifiers());

        final Object base = new MyObject();

        Assert.assertFalse(
            dangerous.getInstanceBoolean(base, field));
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getInstanceBoolean_nullField() {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = null;

        final Object base = new MyObject();

        dangerous.getInstanceBoolean(base, field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getInstanceBoolean_staticField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("staticBoolean");
        assert Modifier.isStatic(field.getModifiers());

        final Object base = new MyObject();

        dangerous.getInstanceBoolean(base, field);
    }


    @Test
    public void getStaticBooleanVolatile_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field
            = MyObject.class.getDeclaredField("staticBooleanVolatile");

        dangerous.getStaticBooleanVolatile(field);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getStaticBooleanVolatile_nullField() {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = null;

        dangerous.getStaticBooleanVolatile(field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getStaticBooleanVolatile_instanceField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field
            = MyObject.class.getDeclaredField("instanceBooleanVolatile");
        assert !Modifier.isStatic(field.getModifiers());

        dangerous.getStaticBooleanVolatile(field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getStaticBooleanVolatile_nonVolatileField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("staticBoolean");
        assert !Modifier.isVolatile(field.getModifiers());

        dangerous.getStaticBooleanVolatile(field);
    }


    @Test
    public void getInstanceBooleanVolatile_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();

        final Field field
            = MyObject.class.getDeclaredField("instanceBooleanVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        Assert.assertFalse(
            dangerous.getInstanceBooleanVolatile(base, field));
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getInstanceBooleanVolatile_nullField() {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();

        final Field field = null;

        dangerous.getInstanceBooleanVolatile(base, field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getInstanceBooleanVolatile_staticField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();

        final Field field
            = MyObject.class.getDeclaredField("staticBooleanVolatile");
        assert Modifier.isStatic(field.getModifiers());

        dangerous.getInstanceBooleanVolatile(base, field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getInstanceBooleanVolatile_nonVolatileField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();

        final Field field = MyObject.class.getDeclaredField("instanceBoolean");
        assert !Modifier.isStatic(field.getModifiers());
        assert !Modifier.isVolatile(field.getModifiers());

        dangerous.getInstanceBooleanVolatile(base, field);
    }


    @Test(enabled = true)
    public void getByte_() {

        final Dangerous dangerous = Dangerous.newInstance();

        final long address = dangerous.allocateMemory(1);
        try {
            final byte value = dangerous.getByte(address);
        } finally {
            dangerous.freeMemory(address);
        }
    }


    @Test
    public void getStaticByte_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("staticByte");
        assert Modifier.isStatic(field.getModifiers());

        dangerous.getStaticByte(field);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getStaticByte_nullField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = null;

        dangerous.getStaticByte(field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getStaticByte_instanceField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("instanceByte");
        assert !Modifier.isStatic(field.getModifiers());

        dangerous.getStaticByte(field);
    }


    @Test
    public void getInstanceByte_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = MyObject.class.getDeclaredField("instanceByte");
        assert !Modifier.isStatic(field.getModifiers());

        dangerous.getInstanceByte(base, field);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getInstanceByte_nullField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = null;

        dangerous.getInstanceByte(base, field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getInstanceByte_staticField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = MyObject.class.getDeclaredField("staticByte");
        assert Modifier.isStatic(field.getModifiers());

        dangerous.getInstanceByte(base, field);
    }


    @Test
    public void getStaticByteVolatile_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field
            = MyObject.class.getDeclaredField("staticByteVolatile");
        assert Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        dangerous.getStaticByteVolatile(field);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getStaticByteVolatile_nullField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = null;

        dangerous.getStaticByteVolatile(field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getStaticByteVolatile_nonStaticField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field
            = MyObject.class.getDeclaredField("instanceByteVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        dangerous.getStaticByteVolatile(field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getStaticByteVolatile_nonVolatileField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("staticByte");
        assert Modifier.isStatic(field.getModifiers());
        assert !Modifier.isVolatile(field.getModifiers());

        dangerous.getStaticByteVolatile(field);
    }


    @Test
    public void getInstanceByteVolatile_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field
            = MyObject.class.getDeclaredField("instanceByteVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        Assert.assertEquals(dangerous.getInstanceByteVolatile(base, field), 0);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getInstanceByteVolatile_nullField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = null;

        dangerous.getInstanceByteVolatile(base, field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getInstanceByteVolatile_staticField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field
            = MyObject.class.getDeclaredField("staticByteVolatile");
        assert Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        dangerous.getInstanceByteVolatile(base, field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getInstanceByteVolatile_nonVolatileField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = MyObject.class.getDeclaredField("instanceByte");
        assert !Modifier.isStatic(field.getModifiers());
        assert !Modifier.isVolatile(field.getModifiers());

        dangerous.getInstanceByteVolatile(base, field);
    }


    @Test
    public void getChar_() {

        final Dangerous dangerous = Dangerous.newInstance();

        final long address = dangerous.allocateMemory(2);
        try {
            dangerous.getChar(address);
        } finally {
            dangerous.freeMemory(address);
        }
    }


    @Test
    public void getStaticChar_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("staticChar");
        assert Modifier.isStatic(field.getModifiers());

        dangerous.getStaticChar(field);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getStaticChar_nullField() {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = null;

        dangerous.getStaticChar(field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getStaticChar_instanceField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("instanceChar");
        assert !Modifier.isStatic(field.getModifiers());

        dangerous.getStaticChar(field);
    }


    @Test
    public void getInstanceChar_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = MyObject.class.getDeclaredField("instanceChar");
        assert !Modifier.isStatic(field.getModifiers());

        dangerous.getInstanceChar(base, field);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getInstanceChar_nullField() {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = null;

        dangerous.getInstanceChar(base, field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getInstanceChar_staticField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = MyObject.class.getDeclaredField("staticChar");
        assert Modifier.isStatic(field.getModifiers());

        dangerous.getInstanceChar(base, field);
    }


    @Test
    public void getStaticCharVolatile_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field
            = MyObject.class.getDeclaredField("staticCharVolatile");
        assert Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        dangerous.getStaticCharVolatile(field);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getStaticCharVolatile_nullField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = null;

        dangerous.getStaticCharVolatile(field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getStaticCharVolatile_nonStaticField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field
            = MyObject.class.getDeclaredField("instanceCharVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        dangerous.getStaticCharVolatile(field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getStaticCharVolatile_nonVolatileField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("staticChar");
        assert Modifier.isStatic(field.getModifiers());
        assert !Modifier.isVolatile(field.getModifiers());

        dangerous.getStaticCharVolatile(field);
    }


    @Test
    public void getInstanceCharVolatile_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field
            = MyObject.class.getDeclaredField("instanceCharVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        Assert.assertEquals(dangerous.getInstanceCharVolatile(base, field), 0);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getInstanceCharVolatile_nullField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = null;

        dangerous.getInstanceCharVolatile(base, field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getInstanceCharVolatile_staticField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field
            = MyObject.class.getDeclaredField("staticCharVolatile");
        assert Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        dangerous.getInstanceCharVolatile(base, field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getInstanceCharVolatile_nonVolatileField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = MyObject.class.getDeclaredField("instanceChar");
        assert !Modifier.isStatic(field.getModifiers());
        assert !Modifier.isVolatile(field.getModifiers());

        dangerous.getInstanceCharVolatile(base, field);
    }


    @Test
    public void getDouble_() {

        final Dangerous dangerous = Dangerous.newInstance();

        final long address = dangerous.allocateMemory(Long.SIZE / Byte.SIZE);
        try {
            dangerous.getDouble(address);
        } finally {
            dangerous.freeMemory(address);
        }
    }


    @Test
    public void getStaticDouble_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("staticDouble");
        assert Modifier.isStatic(field.getModifiers());

        dangerous.getStaticDouble(field);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getStaticDouble_nullField() {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = null;

        dangerous.getStaticDouble(field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getStaticDouble_instanceField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("instanceDouble");
        assert !Modifier.isStatic(field.getModifiers());

        dangerous.getStaticDouble(field);
    }


    @Test
    public void getInstanceDouble_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = MyObject.class.getDeclaredField("instanceDouble");
        assert !Modifier.isStatic(field.getModifiers());

        dangerous.getInstanceDouble(base, field);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getInstanceDouble_nullField() {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = null;

        dangerous.getInstanceDouble(base, field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getInstanceDouble_staticField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = MyObject.class.getDeclaredField("staticDouble");
        assert Modifier.isStatic(field.getModifiers());

        dangerous.getInstanceDouble(base, field);
    }


    @Test
    public void getStaticDoubleVolatile_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field
            = MyObject.class.getDeclaredField("staticDoubleVolatile");
        assert Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        dangerous.getStaticDoubleVolatile(field);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getStaticDoubleVolatile_nullField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = null;

        dangerous.getStaticDoubleVolatile(field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getStaticDoubleVolatile_nonStaticField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field
            = MyObject.class.getDeclaredField("instanceDoubleVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        dangerous.getStaticDoubleVolatile(field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getStaticDoubleVolatile_nonVolatileField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("staticDouble");
        assert Modifier.isStatic(field.getModifiers());
        assert !Modifier.isVolatile(field.getModifiers());

        dangerous.getStaticDoubleVolatile(field);
    }


    @Test
    public void getInstanceDoubleVolatile_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field
            = MyObject.class.getDeclaredField("instanceDoubleVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        Assert.assertEquals(
            dangerous.getInstanceDoubleVolatile(base, field), .0d);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getInstanceDoubleVolatile_nullField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = null;

        dangerous.getInstanceDoubleVolatile(base, field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getInstanceDoubleVolatile_staticField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field
            = MyObject.class.getDeclaredField("staticDoubleVolatile");
        assert Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        dangerous.getInstanceDoubleVolatile(base, field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getInstanceDoubleVolatile_nonVolatileField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = MyObject.class.getDeclaredField("instanceDouble");
        assert !Modifier.isStatic(field.getModifiers());
        assert !Modifier.isVolatile(field.getModifiers());

        dangerous.getInstanceDoubleVolatile(base, field);
    }


    @Test
    public void getFloat_() {

        final Dangerous dangerous = Dangerous.newInstance();

        final long address = dangerous.allocateMemory(Float.SIZE / Byte.SIZE);
        try {
            dangerous.getFloat(address);
        } finally {
            dangerous.freeMemory(address);
        }
    }


    @Test
    public void getStaticFloat_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("staticFloat");
        assert Modifier.isStatic(field.getModifiers());

        dangerous.getStaticFloat(field);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getStaticFloat_nullField() {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = null;

        dangerous.getStaticFloat(field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getStaticFloat_instanceField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("instanceFloat");
        assert !Modifier.isStatic(field.getModifiers());

        dangerous.getStaticFloat(field);
    }


    @Test
    public void getInstanceFloat_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = MyObject.class.getDeclaredField("instanceFloat");
        assert !Modifier.isStatic(field.getModifiers());

        dangerous.getInstanceFloat(base, field);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getInstanceFloat_nullField() {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = null;

        dangerous.getInstanceFloat(base, field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getInstanceFloat_staticField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = MyObject.class.getDeclaredField("staticFloat");
        assert Modifier.isStatic(field.getModifiers());

        dangerous.getInstanceFloat(base, field);
    }


    @Test
    public void getStaticFloatVolatile_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field
            = MyObject.class.getDeclaredField("staticFloatVolatile");
        assert Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        dangerous.getStaticFloatVolatile(field);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getStaticFloatVolatile_nullField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = null;

        dangerous.getStaticFloatVolatile(field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getStaticFloatVolatile_nonStaticField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field
            = MyObject.class.getDeclaredField("instanceFloatVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        dangerous.getStaticFloatVolatile(field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getStaticFloatVolatile_nonVolatileField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("staticFloat");
        assert Modifier.isStatic(field.getModifiers());
        assert !Modifier.isVolatile(field.getModifiers());

        dangerous.getStaticFloatVolatile(field);
    }


    @Test
    public void getInstanceFloatVolatile_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field
            = MyObject.class.getDeclaredField("instanceFloatVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        Assert.assertEquals(
            dangerous.getInstanceFloatVolatile(base, field), .0f);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getInstanceFloatVolatile_nullField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = null;

        dangerous.getInstanceFloatVolatile(base, field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getInstanceFloatVolatile_staticField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field
            = MyObject.class.getDeclaredField("staticFloatVolatile");
        assert Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        dangerous.getInstanceFloatVolatile(base, field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getInstanceFloatVolatile_nonVolatileField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = MyObject.class.getDeclaredField("instanceFloat");
        assert !Modifier.isStatic(field.getModifiers());
        assert !Modifier.isVolatile(field.getModifiers());

        dangerous.getInstanceFloatVolatile(base, field);
    }


    @Test
    public void getInt_() {

        final Dangerous dangerous = Dangerous.newInstance();

        final long address = dangerous.allocateMemory(Integer.SIZE / Byte.SIZE);
        try {
            dangerous.getInt(address);
        } finally {
            dangerous.freeMemory(address);
        }
    }


    @Test
    public void getStaticInt_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("staticInt");
        assert Modifier.isStatic(field.getModifiers());

        dangerous.getStaticInt(field);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getStaticInt_nullField() {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = null;

        dangerous.getStaticInt(field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getStaticInt_instanceField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("instanceInt");
        assert !Modifier.isStatic(field.getModifiers());

        dangerous.getStaticInt(field);
    }


    @Test
    public void getInstanceInt_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = MyObject.class.getDeclaredField("instanceInt");
        assert !Modifier.isStatic(field.getModifiers());

        dangerous.getInstanceInt(base, field);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getInstanceInt_nullField() {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = null;

        dangerous.getInstanceInt(base, field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getInstanceInt_staticField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = MyObject.class.getDeclaredField("staticInt");
        assert Modifier.isStatic(field.getModifiers());

        dangerous.getInstanceInt(base, field);
    }


    @Test
    public void getStaticIntVolatile_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field
            = MyObject.class.getDeclaredField("staticIntVolatile");
        assert Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        dangerous.getStaticIntVolatile(field);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getStaticIntVolatile_nullField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = null;

        dangerous.getStaticIntVolatile(field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getStaticIntVolatile_nonStaticField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field
            = MyObject.class.getDeclaredField("instanceIntVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        dangerous.getStaticIntVolatile(field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getStaticIntVolatile_nonVolatileField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("staticInt");
        assert Modifier.isStatic(field.getModifiers());
        assert !Modifier.isVolatile(field.getModifiers());

        dangerous.getStaticIntVolatile(field);
    }


    @Test
    public void getInstanceIntVolatile_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field
            = MyObject.class.getDeclaredField("instanceIntVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        Assert.assertEquals(
            dangerous.getInstanceIntVolatile(base, field), 0);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getInstanceIntVolatile_nullField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = null;

        dangerous.getInstanceIntVolatile(base, field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getInstanceIntVolatile_staticField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field
            = MyObject.class.getDeclaredField("staticIntVolatile");
        assert Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        dangerous.getInstanceIntVolatile(base, field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getInstanceIntVolatile_nonVolatileField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = MyObject.class.getDeclaredField("instanceInt");
        assert !Modifier.isStatic(field.getModifiers());
        assert !Modifier.isVolatile(field.getModifiers());

        dangerous.getInstanceIntVolatile(base, field);
    }


    @Test
    public void loadAverage_() {

        final Dangerous dangerous = Dangerous.newInstance();

        final double[] loadavg = new double[3];

        final int loadAverage
            = dangerous.getLoadAverage(loadavg, loadavg.length);
        logger.debug("loadAverage: {}", loadAverage);
    }


    @Test
    public void getLong_() {

        final Dangerous dangerous = Dangerous.newInstance();
        final long address = dangerous.allocateMemory(Long.SIZE / Byte.SIZE);
        try {
            dangerous.getLong(address);
        } finally {
            dangerous.freeMemory(address);
        }
    }


    @Test
    public void getStaticLong_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("staticLong");
        assert Modifier.isStatic(field.getModifiers());

        dangerous.getStaticLong(field);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getStaticLong_nullField() {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = null;

        dangerous.getStaticLong(field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getStaticLong_instanceField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("instanceLong");
        assert !Modifier.isStatic(field.getModifiers());

        dangerous.getStaticLong(field);
    }


    @Test
    public void getInstanceLong_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = MyObject.class.getDeclaredField("instanceLong");
        assert !Modifier.isStatic(field.getModifiers());

        dangerous.getInstanceLong(base, field);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getInstanceLong_nullField() {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = null;

        dangerous.getInstanceLong(base, field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getInstanceLong_staticField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = MyObject.class.getDeclaredField("staticLong");
        assert Modifier.isStatic(field.getModifiers());

        dangerous.getInstanceLong(base, field);
    }


    @Test
    public void getStaticLongVolatile_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field
            = MyObject.class.getDeclaredField("staticLongVolatile");
        assert Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        dangerous.getStaticLongVolatile(field);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getStaticLongVolatile_nullField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = null;

        dangerous.getStaticLongVolatile(field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getStaticLongVolatile_nonStaticField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field
            = MyObject.class.getDeclaredField("instanceLongVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        dangerous.getStaticLongVolatile(field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getStaticLongVolatile_nonVolatileField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("staticLong");
        assert Modifier.isStatic(field.getModifiers());
        assert !Modifier.isVolatile(field.getModifiers());

        dangerous.getStaticLongVolatile(field);
    }


    @Test
    public void getInstanceLongVolatile_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field
            = MyObject.class.getDeclaredField("instanceLongVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        Assert.assertEquals(
            dangerous.getInstanceLongVolatile(base, field), 0);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getInstanceLongVolatile_nullField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = null;

        dangerous.getInstanceLongVolatile(base, field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getInstanceLongVolatile_staticField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field
            = MyObject.class.getDeclaredField("staticLongVolatile");
        assert Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        dangerous.getInstanceLongVolatile(base, field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getInstanceLongVolatile_nonVolatileField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = MyObject.class.getDeclaredField("instanceLong");
        assert !Modifier.isStatic(field.getModifiers());
        assert !Modifier.isVolatile(field.getModifiers());

        dangerous.getInstanceLongVolatile(base, field);
    }


    @Test
    public void getStaticObject_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("staticObject");
        assert Modifier.isStatic(field.getModifiers());

        dangerous.getStaticObject(field);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getStaticObject_nullField() {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = null;

        dangerous.getStaticObject(field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getStaticObject_instanceField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("instanceObject");
        assert !Modifier.isStatic(field.getModifiers());

        dangerous.getStaticObject(field);
    }


    @Test
    public void getInstanceObject_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = MyObject.class.getDeclaredField("instanceObject");
        assert !Modifier.isStatic(field.getModifiers());

        Assert.assertNull(
            dangerous.getInstanceObject(base, field));
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getInstanceObject_nullField() {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = null;

        dangerous.getInstanceObject(base, field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getInstanceObject_staticField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = MyObject.class.getDeclaredField("staticObject");
        assert Modifier.isStatic(field.getModifiers());

        dangerous.getInstanceObject(base, field);
    }


    @Test
    public void getStaticObjectVolatile_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field
            = MyObject.class.getDeclaredField("staticObjectVolatile");
        assert Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        dangerous.getStaticObjectVolatile(field);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getStaticObjectVolatile_nullField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = null;

        dangerous.getStaticObjectVolatile(field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getStaticObjectVolatile_nonStaticField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field
            = MyObject.class.getDeclaredField("instanceObjectVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        dangerous.getStaticObjectVolatile(field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getStaticObjectVolatile_nonVolatileField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("staticObject");
        assert Modifier.isStatic(field.getModifiers());
        assert !Modifier.isVolatile(field.getModifiers());

        dangerous.getStaticObjectVolatile(field);
    }


    @Test
    public void getInstanceObjectVolatile_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field
            = MyObject.class.getDeclaredField("instanceObjectVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        Assert.assertNull(
            dangerous.getInstanceObjectVolatile(base, field));
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getInstanceObjectVolatile_nullField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = null;

        dangerous.getInstanceObjectVolatile(base, field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getInstanceObjectVolatile_staticField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field
            = MyObject.class.getDeclaredField("staticObjectVolatile");
        assert Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        dangerous.getInstanceObjectVolatile(base, field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getInstanceObjectVolatile_nonVolatileField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = MyObject.class.getDeclaredField("instanceObject");
        assert !Modifier.isStatic(field.getModifiers());
        assert !Modifier.isVolatile(field.getModifiers());

        dangerous.getInstanceObjectVolatile(base, field);
    }


    @Test
    public void getShort_() {

        final Dangerous dangerous = Dangerous.newInstance();

        final long address = dangerous.allocateMemory(Short.SIZE / Byte.SIZE);
        try {
            dangerous.getShort(address);
        } finally {
            dangerous.freeMemory(address);
        }
    }


    @Test
    public void getStaticShort_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("staticShort");
        assert Modifier.isStatic(field.getModifiers());

        dangerous.getStaticShort(field);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getStaticShort_nullField() {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = null;

        dangerous.getStaticShort(field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getStaticShort_instanceField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("instanceShort");
        assert !Modifier.isStatic(field.getModifiers());

        dangerous.getStaticShort(field);
    }


    @Test
    public void getInstanceShort_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = MyObject.class.getDeclaredField("instanceShort");
        assert !Modifier.isStatic(field.getModifiers());

        Assert.assertEquals(
            dangerous.getInstanceShort(base, field), (short) 0);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getInstanceShort_nullField() {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = null;

        dangerous.getInstanceShort(base, field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getInstanceShort_staticField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = MyObject.class.getDeclaredField("staticShort");
        assert Modifier.isStatic(field.getModifiers());

        dangerous.getInstanceShort(base, field);
    }


    @Test
    public void getStaticShortVolatile_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field
            = MyObject.class.getDeclaredField("staticShortVolatile");
        assert Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        dangerous.getStaticShortVolatile(field);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getStaticShortVolatile_nullField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = null;

        dangerous.getStaticShortVolatile(field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getStaticShortVolatile_nonStaticField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field
            = MyObject.class.getDeclaredField("instanceShortVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        dangerous.getStaticShortVolatile(field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getStaticShortVolatile_nonVolatileField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("staticShort");
        assert Modifier.isStatic(field.getModifiers());
        assert !Modifier.isVolatile(field.getModifiers());

        dangerous.getStaticShortVolatile(field);
    }


    @Test
    public void getInstanceShortVolatile_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field
            = MyObject.class.getDeclaredField("instanceShortVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        Assert.assertEquals(
            dangerous.getInstanceShortVolatile(base, field), (short) 0);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void getInstanceShortVolatile_nullField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = null;

        dangerous.getInstanceShortVolatile(base, field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getInstanceShortVolatile_staticField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field
            = MyObject.class.getDeclaredField("staticShortVolatile");
        assert Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        dangerous.getInstanceShortVolatile(base, field);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getInstanceShortVolatile_nonVolatileField()
        throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = MyObject.class.getDeclaredField("instanceShort");
        assert !Modifier.isStatic(field.getModifiers());
        assert !Modifier.isVolatile(field.getModifiers());

        dangerous.getInstanceShortVolatile(base, field);
    }


    @Test
    public void monitorEnter_() {

        final Dangerous dangerous = Dangerous.newInstance();

        // @todo test
    }


    @Test
    public void monitorExit_() {

        final Dangerous dangerous = Dangerous.newInstance();

        // @todo test
    }


    @Test
    public void pageSize_() {

        final Dangerous dangerous = Dangerous.newInstance();

        final int pageSize = dangerous.pageSize();
        logger.info("pageSize: {}", pageSize);
    }


    @Test
    public void park_() {

        final Dangerous dangerous = Dangerous.newInstance();

        // @todo test
    }


    @Test
    public void putAddress_() {

        final Dangerous dangerous = Dangerous.newInstance();

        final int addressSize = dangerous.addressSize();

        final long address = dangerous.allocateMemory(addressSize);
        try {
            dangerous.putAddress(address, random().nextLong());
        } finally {
            dangerous.freeMemory(address);
        }
    }


    @Test
    public void putStaticBoolean_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("staticBoolean");
        assert Modifier.isStatic(field.getModifiers());
        final boolean x = random().nextBoolean();

        dangerous.putStaticBoolean(field, x);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void putStaticBoolean_nullField() {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = null;
        final boolean x = random().nextBoolean();

        dangerous.putStaticBoolean(field, x);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void putStaticBoolean_instanceField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("instanceBoolean");
        assert !Modifier.isStatic(field.getModifiers());
        final boolean x = random().nextBoolean();

        dangerous.putStaticBoolean(field, x);
    }


    @Test
    public void putInstanceBoolean_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = MyObject.class.getDeclaredField("instanceBoolean");
        assert !Modifier.isStatic(field.getModifiers());
        final boolean x = random().nextBoolean();

        dangerous.putInstanceBoolean(base, field, x);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void putInstanceBoolean_nullField() {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = null;
        final boolean x = random().nextBoolean();

        dangerous.putInstanceBoolean(base, field, x);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void putInstanceBoolean_staticField() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = new MyObject();
        final Field field = MyObject.class.getDeclaredField("staticBoolean");
        assert Modifier.isStatic(field.getModifiers());
        final boolean x = random().nextBoolean();

        dangerous.putInstanceBoolean(base, field, x);
    }


    @Test(enabled = true)
    public void putByte_memory() {

        final Dangerous dangerous = Dangerous.newInstance();

        final long address = dangerous.allocateMemory(1);
        try {
            dangerous.putByte(address, (byte) 0);
        } finally {
            dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void byte_memory() {

        final Dangerous dangerous = Dangerous.newInstance();

        final long address = dangerous.allocateMemory(2);
        try {
            final byte expected = nextByte();
            dangerous.putByte(address, expected);
            final byte actual = dangerous.getByte(address);
            Assert.assertEquals(actual, expected);
        } finally {
            dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void getShort_memory() {

        final Dangerous dangerous = Dangerous.newInstance();

        final long address = dangerous.allocateMemory(2);
        try {
            final short value = dangerous.getShort(address);
        } finally {
            dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void putShort_memory() {

        final Dangerous dangerous = Dangerous.newInstance();

        final long address = dangerous.allocateMemory(2);
        try {
            dangerous.putShort(address, (short) 0);
        } finally {
            dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void short_memory() {

        final Dangerous dangerous = Dangerous.newInstance();

        final long address = dangerous.allocateMemory(2);
        try {
            final short expected
                = (short) (ThreadLocalRandom.current().nextInt() & 0xFFFF);
            dangerous.putShort(address, expected);
            final short actual = dangerous.getShort(address);
            Assert.assertEquals(actual, expected);
        } finally {
            dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void getInt_memory() {

        final Dangerous dangerous = Dangerous.newInstance();

        final long address = dangerous.allocateMemory(4);
        try {
            final int value = dangerous.getInt(address);
        } finally {
            dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void putInt_memory() {

        final Dangerous dangerous = Dangerous.newInstance();

        final long address = dangerous.allocateMemory(4);
        try {
            dangerous.putInt(address, 0);
        } finally {
            dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void int_memory() {

        final Dangerous dangerous = Dangerous.newInstance();

        final long address = dangerous.allocateMemory(4);
        try {
            final int expected = ThreadLocalRandom.current().nextInt();
            dangerous.putInt(address, expected);
            final int actual = dangerous.getInt(address);
            Assert.assertEquals(actual, expected);
        } finally {
            dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void getLong_memory() {

        final Dangerous dangerous = Dangerous.newInstance();

        final long address = dangerous.allocateMemory(8);
        try {
            final long value = dangerous.getLong(address);
        } finally {
            dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void putLong_memory() {

        final Dangerous dangerous = Dangerous.newInstance();

        final long address = dangerous.allocateMemory(8);
        try {
            dangerous.putLong(address, 0L);
        } finally {
            dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void long_memory() {

        final Dangerous dangerous = Dangerous.newInstance();

        final long address = dangerous.allocateMemory(8);
        try {
            final long expected = ThreadLocalRandom.current().nextLong();
            dangerous.putLong(address, expected);
            final long actual = dangerous.getLong(address);
            Assert.assertEquals(actual, expected);
        } finally {
            dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void getFloat_memory() {

        final Dangerous dangerous = Dangerous.newInstance();

        final long address = dangerous.allocateMemory(4);
        try {
            final float value = dangerous.getFloat(address);
        } finally {
            dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void putFloat_memory() {

        final Dangerous dangerous = Dangerous.newInstance();

        final long address = dangerous.allocateMemory(4);
        try {
            dangerous.putFloat(address, .0f);
        } finally {
            dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void float_memory() {

        final Dangerous dangerous = Dangerous.newInstance();

        final long address = dangerous.allocateMemory(4);
        try {
            final float expected = nextFloat();
            dangerous.putFloat(address, expected);
            final float actual = dangerous.getFloat(address);
            Assert.assertEquals(actual, expected);
        } finally {
            dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void getDouble_memory() {

        final Dangerous dangerous = Dangerous.newInstance();

        final long address = dangerous.allocateMemory(8);
        try {
            final double value = dangerous.getDouble(address);
        } finally {
            dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void putDouble_memory() {

        final Dangerous dangerous = Dangerous.newInstance();

        final long address = dangerous.allocateMemory(8);
        try {
            dangerous.putDouble(address, .0d);
        } finally {
            dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void double_memory() {

        final Dangerous dangerous = Dangerous.newInstance();

        final long address = dangerous.allocateMemory(8);
        try {
            final double expected = nextDouble();
            dangerous.putDouble(address, expected);
            final double actual = dangerous.getDouble(address);
            Assert.assertEquals(actual, expected);
        } finally {
            dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void getByte_static() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("staticByte");

        final Object object = dangerous.staticFieldBase(field);

        final long offset = dangerous.staticFieldOffset(field);

        final byte value = dangerous.getByte(object, offset);
    }


    @Test(enabled = true)
    public void putByte_static() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("staticByte");

        final Object object = dangerous.staticFieldBase(field);

        final long offset = dangerous.staticFieldOffset(field);

        dangerous.putByte(object, offset, (byte) 0);
    }


    @Test(enabled = true)
    public void byte_static() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("staticByte");

        final Object object = dangerous.staticFieldBase(field);

        final long offset = dangerous.staticFieldOffset(field);

        final byte expected = nextByte();
        dangerous.putByte(object, offset, expected);
        final byte actual = dangerous.getByte(object, offset);
        Assert.assertEquals(actual, expected);
    }


    @Test(enabled = true)
    public void getShort_static() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("staticShort");

        final Object object = dangerous.staticFieldBase(field);

        final long offset = dangerous.staticFieldOffset(field);

        final short value = dangerous.getShort(object, offset);
    }


    @Test(enabled = true)
    public void putShort_static() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("staticShort");

        final Object object = dangerous.staticFieldBase(field);

        final long offset = dangerous.staticFieldOffset(field);

        dangerous.putShort(object, offset, (short) 0);
    }


    @Test(enabled = true)
    public void short_static() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("staticShort");

        final Object object = dangerous.staticFieldBase(field);

        final long offset = dangerous.staticFieldOffset(field);

        final short expected = nextShort();
        dangerous.putShort(object, offset, expected);
        final short actual = dangerous.getShort(object, offset);
        Assert.assertEquals(actual, expected);
    }


    @Test(enabled = true)
    public void getInt_static() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Field field = MyObject.class.getDeclaredField("staticInt");

        final Object object = dangerous.staticFieldBase(field);

        final long offset = dangerous.staticFieldOffset(field);

        final int value = dangerous.getInt(object, offset);
    }

//
//    @Test(enabled = true)
//    public void putInt_static() throws NoSuchFieldException {
//
//        final Field field = MyObject.class.getDeclaredField("staticInt");
//
//        final Object object = Dangerous.staticFieldBase(field);
//
//        Dangerous.putInt(object, field, 0);
//    }
//
//
//    @Test(enabled = true)
//    public void int_static() throws NoSuchFieldException {
//
//        final Field field = MyObject.class.getDeclaredField("staticInt");
//
//        final Object object = Dangerous.staticFieldBase(field);
//
//        final int expected = nextInt();
//        Dangerous.putInt(object, field, expected);
//        final int actual = Dangerous.getInt(object, field);
//        Assert.assertEquals(actual, expected);
//    }
//
//
//    @Test(enabled = true)
//    public void getLong_static() throws NoSuchFieldException {
//
//        final Field field = MyObject.class.getDeclaredField("staticLong");
//
//        final Object object = Dangerous.staticFieldBase(field);
//
//        final long value = Dangerous.getLong(object, field);
//    }
//
//
//    @Test(enabled = true)
//    public void putLong_static() throws NoSuchFieldException {
//
//        final Field field = MyObject.class.getDeclaredField("staticLong");
//
//        final Object object = Dangerous.staticFieldBase(field);
//
//        Dangerous.putLong(object, field, 0);
//    }
//
//
//    @Test(enabled = true)
//    public void long_static() throws NoSuchFieldException {
//
//        final Field field = MyObject.class.getDeclaredField("staticLong");
//
//        final Object object = Dangerous.staticFieldBase(field);
//
//        final long expected = nextLong();
//        Dangerous.putLong(object, field, expected);
//        final long actual = Dangerous.getLong(object, field);
//        Assert.assertEquals(actual, expected);
//    }
//
//
//    @Test(enabled = true)
//    public void getFloat_static() throws NoSuchFieldException {
//
//        final Field field = MyObject.class.getDeclaredField("staticFloat");
//
//        final Object object = Dangerous.staticFieldBase(field);
//
//        final float value = Dangerous.getFloat(object, field);
//    }
//
//
//    @Test(enabled = true)
//    public void putFloat_static() throws NoSuchFieldException {
//
//        final Field field = MyObject.class.getDeclaredField("staticFloat");
//
//        final Object object = Dangerous.staticFieldBase(field);
//
//        Dangerous.putFloat(object, field, 0);
//    }
//
//
//    @Test(enabled = true)
//    public void float_static() throws NoSuchFieldException {
//
//        final Field field = MyObject.class.getDeclaredField("staticFloat");
//
//        final Object object = Dangerous.staticFieldBase(field);
//
//        final float expected = nextFloat();
//        Dangerous.putFloat(object, field, expected);
//        final float actual = Dangerous.getFloat(object, field);
//        Assert.assertEquals(actual, expected);
//    }
//
//
////    @Test(enabled = true)
////    public void getDouble_static() throws NoSuchFieldException {
////
////        final Field field = MyObject.class.getDeclaredField("staticDouble");
////
////        final Object object = Dangerous.staticFieldBase(field);
////
////        final double value = Dangerous.getDouble(object, field);
////    }
//    @Test(enabled = true)
//    public void putDouble_static() throws NoSuchFieldException {
//
//        final Field field = MyObject.class.getDeclaredField("staticDouble");
//
//        final Object object = Dangerous.staticFieldBase(field);
//
//        Dangerous.putDouble(object, field, 0);
//    }
//
//
////    @Test(enabled = true)
////    public void double_static() throws NoSuchFieldException {
////
////        final Field field = MyObject.class.getDeclaredField("staticDouble");
////
////        final Object object = Dangerous.staticFieldBase(field);
////
////        final double expected = nextDouble();
////        Dangerous.putDouble(object, field, expected);
////        final double actual = Dangerous.getDouble(object, field);
////        Assert.assertEquals(actual, expected);
////    }
//    @Test(enabled = true)
//    public void getByte_object() throws NoSuchFieldException {
//
//        final Field field = MyObject.class.getDeclaredField("objectByte");
//
//        final Object object = new MyObject();
//
//        final byte value = Dangerous.getByte(object, field);
//    }
//
//
//    @Test(enabled = true)
//    public void putByte_object() throws NoSuchFieldException {
//
//        final Field field = MyObject.class.getDeclaredField("objectByte");
//
//        final Object object = new MyObject();
//
//        Dangerous.putByte(object, field, (byte) 0);
//    }
//
//
//    @Test(enabled = true)
//    public void byte_object() throws NoSuchFieldException {
//
//        final Field field = MyObject.class.getDeclaredField("objectByte");
//
//        final Object object = new MyObject();
//
//        final byte expected = nextByte();
//        Dangerous.putByte(object, field, expected);
//        final byte actual = Dangerous.getByte(object, field);
//        Assert.assertEquals(actual, expected);
//    }
//
//
//    @Test(enabled = true)
//    public void getShort_object() throws NoSuchFieldException {
//
//        final Field field = MyObject.class.getDeclaredField("objectShort");
//
//        final Object object = new MyObject();
//
//        final short value = Dangerous.getShort(object, field);
//    }
//
//
//    @Test(enabled = true)
//    public void putShort_object() throws NoSuchFieldException {
//
//        final Field field = MyObject.class.getDeclaredField("objectShort");
//
//        final Object object = new MyObject();
//
//        Dangerous.putShort(object, field, (short) 0);
//    }
//
//
//    @Test(enabled = true)
//    public void short_object() throws NoSuchFieldException {
//
//        final Field field = MyObject.class.getDeclaredField("objectShort");
//
//        final Object object = new MyObject();
//
//        final short expected = nextShort();
//        Dangerous.putShort(object, field, expected);
//        final short actual = Dangerous.getShort(object, field);
//        Assert.assertEquals(actual, expected);
//    }
//
//
//    @Test(enabled = true)
//    public void getInt_object() throws NoSuchFieldException {
//
//        final Field field = MyObject.class.getDeclaredField("objectInt");
//
//        final Object object = new MyObject();
//
//        final short value = Dangerous.getShort(object, field);
//    }
//
//
//    @Test(enabled = true)
//    public void putInt_object() throws NoSuchFieldException {
//
//        final Field field = MyObject.class.getDeclaredField("objectInt");
//
//        final Object object = new MyObject();
//
//        Dangerous.putInt(object, field, 0);
//    }
//
//
//    @Test(enabled = true)
//    public void int_object() throws NoSuchFieldException {
//
//        final Field field = MyObject.class.getDeclaredField("objectInt");
//
//        final Object object = new MyObject();
//
//        final int expected = nextInt();
//        Dangerous.putInt(object, field, expected);
//        final int actual = Dangerous.getInt(object, field);
//        Assert.assertEquals(actual, expected);
//    }
//
//
//    @Test(enabled = true)
//    public void getLong_object() throws NoSuchFieldException {
//
//        final Field field = MyObject.class.getDeclaredField("objectLong");
//
//        final Object object = new MyObject();
//
//        final long value = Dangerous.getLong(object, field);
//    }
//
//
//    @Test(enabled = true)
//    public void putLong_object() throws NoSuchFieldException {
//
//        final Field field = MyObject.class.getDeclaredField("objectLong");
//
//        final Object object = new MyObject();
//
//        Dangerous.putLong(object, field, 0L);
//    }
//
//
//    @Test(enabled = true)
//    public void long_object() throws NoSuchFieldException {
//
//        final Field field = MyObject.class.getDeclaredField("objectLong");
//
//        final Object object = new MyObject();
//
//        final long expected = nextLong();
//        Dangerous.putLong(object, field, expected);
//        final long actual = Dangerous.getLong(object, field);
//        Assert.assertEquals(actual, expected);
//    }
//
//
//    @Test(enabled = true)
//    public void getFloat_object() throws NoSuchFieldException {
//
//        final Field field = MyObject.class.getDeclaredField("objectFloat");
//
//        final Object object = new MyObject();
//
//        final float value = Dangerous.getFloat(object, field);
//    }
//
//
//    @Test(enabled = true)
//    public void putFloat_object() throws NoSuchFieldException {
//
//        final Field field = MyObject.class.getDeclaredField("objectFloat");
//
//        final Object object = new MyObject();
//
////        Dangerous.putFloat(object, field, 0.f);
//    }
//
//
//    @Test(enabled = true)
//    public void float_object() throws NoSuchFieldException {
//
//        final Field field = MyObject.class.getDeclaredField("objectFloat");
//
//        final Object object = new MyObject();
//
//        final float expected = nextFloat();
////        Dangerous.putFloat(object, field, expected);
////        final float actual = Dangerous.getFloat(object, field);
////        Assert.assertEquals(actual, expected);
//    }
//
//
////    @Test(enabled = true)
////    public void getDouble_object() throws NoSuchFieldException {
////
////        final Field field = MyObject.class.getDeclaredField("objectDouble");
////
////        final Object object = new MyObject();
////
////        final double value = Dangerous.getDouble(object, field);
////    }
//    @Test(enabled = true)
//    public void putDouble_object() throws NoSuchFieldException {
//
//        final Field field = MyObject.class.getDeclaredField("objectDouble");
//
//        final Object object = new MyObject();
//
//        Dangerous.putDouble(object, field, 0.f);
//    }
//
//    @Test(enabled = true)
//    public void double_object() throws NoSuchFieldException {
//
//        final Field field = MyObject.class.getDeclaredField("objectDouble");
//
//        final Object object = new MyObject();
//
//        final double expected = nextDouble();
//        Dangerous.putDouble(object, field, expected);
//        final double actual = Dangerous.getDouble(object, field);
//        Assert.assertEquals(actual, expected);
//    }

}

