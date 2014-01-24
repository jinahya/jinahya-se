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


        private static short staticShort;


        private static int staticInt;


        private static long staticLong;


        private static float staticFloat;


        private static double staticDouble;


        private static Object staticObject;


        private boolean instanceBoolean;


        private volatile boolean instanceBooleanVolatile;


        private byte instanceByte;


        private volatile byte instanceByteVolatile;


        private short objectShort;


        private int instanceInt;


        private long instanceLong;


        private float objectFloat;


        private double objectDouble;


        private Object instanceObject;


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


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getStaticByteVolatile_instanceField()
        throws NoSuchFieldException {

        Dangerous.newInstance().getStaticByteVolatile(
            MyObject.class.getDeclaredField("instanceByteVolatile"));
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getStaticByteVolatile_nonVolatileField()
        throws NoSuchFieldException {

        Dangerous.newInstance().getStaticByteVolatile(
            MyObject.class.getDeclaredField("staticByte"));
    }


    public void getInstanceByteVolatile_() throws NoSuchFieldException {

        Dangerous.newInstance().getInstanceByteVolatile(
            new MyObject(), MyObject.class.getDeclaredField("instanceByteVolatile"));
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getInstanceByteVolatile_staticField()
        throws NoSuchFieldException {

        Dangerous.newInstance().getInstanceByteVolatile(
            null, MyObject.class.getDeclaredField("staticByte"));
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void getInstanceByteVolatile_nonVolatileField()
        throws NoSuchFieldException {

        Dangerous.newInstance().getInstanceByteVolatile(
            null, MyObject.class.getDeclaredField("instanceByte"));
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

