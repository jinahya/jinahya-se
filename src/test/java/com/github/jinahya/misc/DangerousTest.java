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
import java.text.Format;
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


        private static byte staticByte;


        private static short staticShort;


        private static int staticInt;


        private static long staticLong;


        private static float staticFloat;


        private static double staticDouble;


        private static Object staticObject;


        private byte objectByte;


        private short objectShort;


        private int objectInt;


        private long objectLong;


        private float objectFloat;


        private double objectDouble;


        private Object objectObject;


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
        logger.debug("instance: {}", instance);
        Assert.assertNotNull(instance);
    }


    @Test
    public void allocateInstanceGeneric_() throws InstantiationException {

        final Dangerous dangerous = Dangerous.newInstance();

        final MyObject instance
            = dangerous.allocateInstanceGeneric(MyObject.class);
        logger.debug("instance: {}", instance);
        Assert.assertNotNull(instance);
    }


    @Test
    public void allocateMemory_() {

        final Dangerous dangerous = Dangerous.newInstance();

        dangerous.allocateMemory(0L);

        final long size = random().nextLong(1L, 1048576L + 1L);
        final long address = dangerous.allocateMemory(size);
        dangerous.freeMemory(address);
    }


    @Test
    public void arrayBaseOffset_() {

        final Dangerous dangerous = Dangerous.newInstance();

        logger.debug("arrayBaseOffset(Object[].class): {}",
                     dangerous.arrayBaseOffset(Object[].class));

        logger.debug("arrayBaseOffset(String[].class): {}",
                     dangerous.arrayBaseOffset(String[].class));
    }


    @Test
    public void arrayIndexScale_() {

        final Dangerous dangerous = Dangerous.newInstance();

        logger.debug("arrayIndexScale(Object[].class): {}",
                     dangerous.arrayIndexScale(Object[].class));

        logger.debug("arrayIndexScale(String[].class): {}",
                     dangerous.arrayIndexScale(String[].class));
    }


    @Test
    public void compareAndSwapInt_()
        throws NoSuchFieldException, IllegalAccessException {

        final Dangerous dangerous = Dangerous.newInstance();

        if (false) {
            final Field field = MyObject.class.getDeclaredField("staticInt");
            field.setAccessible(true);

            final long offset = dangerous.staticFieldOffset(field);

            final Object o = null;

            Assert.assertEquals(field.getInt(o), 0);
            Assert.assertTrue(dangerous.compareAndSwapInt(o, offset, 0, 1));
            Assert.assertEquals(field.getInt(o), 1);
            Assert.assertFalse(dangerous.compareAndSwapInt(o, offset, 0, 1));
        }

        {
            final Field field = MyObject.class.getDeclaredField("objectInt");
            field.setAccessible(true);

            final long offset = dangerous.objectFieldOffset(field);

            final Object o = new MyObject();

            Assert.assertEquals(field.getInt(o), 0);
            Assert.assertTrue(dangerous.compareAndSwapInt(o, offset, 0, 1));
            Assert.assertEquals(field.getInt(o), 1);
            Assert.assertFalse(dangerous.compareAndSwapInt(o, offset, 0, 1));
        }
    }


    @Test
    public void compareAndSwapLong_()
        throws NoSuchFieldException, IllegalAccessException {

        final Dangerous dangerous = Dangerous.newInstance();

        if (false) {
            final Field field = MyObject.class.getDeclaredField("staticLong");
            field.setAccessible(true);

            final long offset = dangerous.staticFieldOffset(field);

            final Object o = null;

            Assert.assertEquals(field.getLong(o), 0L);
            Assert.assertTrue(dangerous.compareAndSwapLong(o, offset, 0L, 1L));
            Assert.assertEquals(field.getLong(o), 1L);
            Assert.assertFalse(dangerous.compareAndSwapLong(o, offset, 0L, 1L));
        }

        {
            final Field field = MyObject.class.getDeclaredField("objectLong");
            field.setAccessible(true);

            final long offset = dangerous.objectFieldOffset(field);

            final Object o = new MyObject();

            Assert.assertEquals(field.getLong(o), 0L);
            Assert.assertTrue(dangerous.compareAndSwapLong(o, offset, 0L, 1L));
            Assert.assertEquals(field.getLong(o), 1L);
            Assert.assertFalse(dangerous.compareAndSwapLong(o, offset, 0L, 1L));
        }
    }


    @Test
    public void compareAndSwapObject_()
        throws NoSuchFieldException, IllegalAccessException {

        final Dangerous dangerous = Dangerous.newInstance();

        if (false) {
            final Field field = MyObject.class.getDeclaredField("staticObject");
            field.setAccessible(true);

            final long offset = dangerous.staticFieldOffset(field);

            final Object o = null;

            final Object x = new Object();

            Assert.assertNull(field.get(o));
            Assert.assertTrue(dangerous.compareAndSwapObject(
                o, offset, null, x));
            Assert.assertEquals(field.get(o), x);
            Assert.assertFalse(dangerous.compareAndSwapObject(
                o, offset, null, x));
        }

        {
            final Field field = MyObject.class.getDeclaredField("objectObject");
            field.setAccessible(true);

            final long offset = dangerous.objectFieldOffset(field);

            final Object o = new MyObject();

            final Object x = new Object();

            Assert.assertNull(field.get(o));
            Assert.assertTrue(dangerous.compareAndSwapObject(
                o, offset, null, x));
            Assert.assertEquals(field.get(o), x);
            Assert.assertFalse(dangerous.compareAndSwapObject(
                o, offset, null, x));
        }
    }


    @Test
    public void copyMemory_() {

        final Dangerous dangerous = Dangerous.newInstance();

        final long address = dangerous.allocateMemory(8);
        try {
            dangerous.copyMemory(address, address + 4, 4);
        } finally {
            dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true, invocationCount = 1024)
    public void copyMemory_int() {

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


    @Test(enabled = true, invocationCount = 1024)
    public void copyMemory_long() {

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


    @Test(enabled = true)
    public void getByte_memory() {

        final Dangerous dangerous = Dangerous.newInstance();

        final long address = dangerous.allocateMemory(1);
        try {
            final byte value = dangerous.getByte(address);
        } finally {
            dangerous.freeMemory(address);
        }
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

