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
import java.util.concurrent.ThreadLocalRandom;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class DangerousTest {


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


        private byte objectByte;


        private short objectShort;


        private int objectInt;


        private long objectLong;


        private float objectFloat;


        private double objectDouble;


    }


    private static final Object OBJECT = new MyObject();


    @Test(enabled = true)
    public void getByte_memory() {

        final long address = Dangerous.allocateMemory(1);
        try {
            final byte value = Dangerous.getByte(address);
        } finally {
            Dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void putByte_memory() {

        final long address = Dangerous.allocateMemory(1);
        try {
            Dangerous.putByte(address, (byte) 0);
        } finally {
            Dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void byte_memory() {

        final long address = Dangerous.allocateMemory(2);
        try {
            final byte expected = nextByte();
            Dangerous.putByte(address, expected);
            final byte actual = Dangerous.getByte(address);
            Assert.assertEquals(actual, expected);
        } finally {
            Dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void getShort_memory() {

        final long address = Dangerous.allocateMemory(2);
        try {
            final short value = Dangerous.getShort(address);
        } finally {
            Dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void putShort_memory() {

        final long address = Dangerous.allocateMemory(2);
        try {
            Dangerous.putShort(address, (short) 0);
        } finally {
            Dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void short_memory() {

        final long address = Dangerous.allocateMemory(2);
        try {
            final short expected
                = (short) (ThreadLocalRandom.current().nextInt() & 0xFFFF);
            Dangerous.putShort(address, expected);
            final short actual = Dangerous.getShort(address);
            Assert.assertEquals(actual, expected);
        } finally {
            Dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void getInt_memory() {

        final long address = Dangerous.allocateMemory(4);
        try {
            final int value = Dangerous.getInt(address);
        } finally {
            Dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void putInt_memory() {

        final long address = Dangerous.allocateMemory(4);
        try {
            Dangerous.putInt(address, 0);
        } finally {
            Dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void int_memory() {

        final long address = Dangerous.allocateMemory(4);
        try {
            final int expected = ThreadLocalRandom.current().nextInt();
            Dangerous.putInt(address, expected);
            final int actual = Dangerous.getInt(address);
            Assert.assertEquals(actual, expected);
        } finally {
            Dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void getLong_memory() {

        final long address = Dangerous.allocateMemory(8);
        try {
            final long value = Dangerous.getLong(address);
        } finally {
            Dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void putLong_memory() {

        final long address = Dangerous.allocateMemory(8);
        try {
            Dangerous.putLong(address, 0L);
        } finally {
            Dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void long_memory() {

        final long address = Dangerous.allocateMemory(8);
        try {
            final long expected = ThreadLocalRandom.current().nextLong();
            Dangerous.putLong(address, expected);
            final long actual = Dangerous.getLong(address);
            Assert.assertEquals(actual, expected);
        } finally {
            Dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void getFloat_memory() {

        final long address = Dangerous.allocateMemory(4);
        try {
            final float value = Dangerous.getFloat(address);
        } finally {
            Dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void putFloat_memory() {

        final long address = Dangerous.allocateMemory(4);
        try {
            Dangerous.putFloat(address, .0f);
        } finally {
            Dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void float_memory() {

        final long address = Dangerous.allocateMemory(4);
        try {
            final float expected = nextFloat();
            Dangerous.putFloat(address, expected);
            final float actual = Dangerous.getFloat(address);
            Assert.assertEquals(actual, expected);
        } finally {
            Dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void getDouble_memory() {

        final long address = Dangerous.allocateMemory(8);
        try {
            final double value = Dangerous.getDouble(address);
        } finally {
            Dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void putDouble_memory() {

        final long address = Dangerous.allocateMemory(8);
        try {
            Dangerous.putDouble(address, .0d);
        } finally {
            Dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void double_memory() {

        final long address = Dangerous.allocateMemory(8);
        try {
            final double expected = nextDouble();
            Dangerous.putDouble(address, expected);
            final double actual = Dangerous.getDouble(address);
            Assert.assertEquals(actual, expected);
        } finally {
            Dangerous.freeMemory(address);
        }
    }


    @Test(enabled = true)
    public void getByte_static() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("staticByte");

        final Object object = Dangerous.staticFieldBase(field);

        final byte value = Dangerous.getByte(object, field);
    }


    @Test(enabled = true)
    public void putByte_static() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("staticByte");

        final Object object = Dangerous.staticFieldBase(field);

        Dangerous.putByte(object, field, (byte) 0);
    }


    @Test(enabled = true)
    public void byte_static() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("staticByte");

        final Object object = Dangerous.staticFieldBase(field);

        final byte expected = nextByte();
        Dangerous.putByte(object, field, expected);
        final byte actual = Dangerous.getByte(object, field);
        Assert.assertEquals(actual, expected);
    }


    @Test(enabled = true)
    public void getShort_static() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("staticShort");

        final Object object = Dangerous.staticFieldBase(field);

        final short value = Dangerous.getShort(object, field);
    }


    @Test(enabled = true)
    public void putShort_static() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("staticShort");

        final Object object = Dangerous.staticFieldBase(field);

        Dangerous.putShort(object, field, (short) 0);
    }


    @Test(enabled = true)
    public void short_static() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("staticShort");

        final Object object = Dangerous.staticFieldBase(field);

        final short expected = nextShort();
        Dangerous.putShort(object, field, expected);
        final short actual = Dangerous.getShort(object, field);
        Assert.assertEquals(actual, expected);
    }


    @Test(enabled = true)
    public void getInt_static() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("staticInt");

        final Object object = Dangerous.staticFieldBase(field);

        final int value = Dangerous.getInt(object, field);
    }


    @Test(enabled = true)
    public void putInt_static() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("staticInt");

        final Object object = Dangerous.staticFieldBase(field);

        Dangerous.putInt(object, field, 0);
    }


    @Test(enabled = true)
    public void int_static() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("staticInt");

        final Object object = Dangerous.staticFieldBase(field);

        final int expected = nextInt();
        Dangerous.putInt(object, field, expected);
        final int actual = Dangerous.getInt(object, field);
        Assert.assertEquals(actual, expected);
    }


    @Test(enabled = true)
    public void getLong_static() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("staticLong");

        final Object object = Dangerous.staticFieldBase(field);

        final long value = Dangerous.getLong(object, field);
    }


    @Test(enabled = true)
    public void putLong_static() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("staticLong");

        final Object object = Dangerous.staticFieldBase(field);

        Dangerous.putLong(object, field, 0);
    }


    @Test(enabled = true)
    public void long_static() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("staticLong");

        final Object object = Dangerous.staticFieldBase(field);

        final long expected = nextLong();
        Dangerous.putLong(object, field, expected);
        final long actual = Dangerous.getLong(object, field);
        Assert.assertEquals(actual, expected);
    }


    @Test(enabled = true)
    public void getFloat_static() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("staticFloat");

        final Object object = Dangerous.staticFieldBase(field);

        final float value = Dangerous.getFloat(object, field);
    }


    @Test(enabled = true)
    public void putFloat_static() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("staticFloat");

        final Object object = Dangerous.staticFieldBase(field);

        Dangerous.putFloat(object, field, 0);
    }


    @Test(enabled = true)
    public void float_static() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("staticFloat");

        final Object object = Dangerous.staticFieldBase(field);

        final float expected = nextFloat();
        Dangerous.putFloat(object, field, expected);
        final float actual = Dangerous.getFloat(object, field);
        Assert.assertEquals(actual, expected);
    }


    @Test(enabled = true)
    public void getDouble_static() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("staticDouble");

        final Object object = Dangerous.staticFieldBase(field);

        final double value = Dangerous.getDouble(object, field);
    }


    @Test(enabled = true)
    public void putDouble_static() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("staticDouble");

        final Object object = Dangerous.staticFieldBase(field);

        Dangerous.putDouble(object, field, 0);
    }


    @Test(enabled = true)
    public void double_static() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("staticDouble");

        final Object object = Dangerous.staticFieldBase(field);

        final double expected = nextDouble();
        Dangerous.putDouble(object, field, expected);
        final double actual = Dangerous.getDouble(object, field);
        Assert.assertEquals(actual, expected);
    }


    @Test(enabled = true)
    public void getByte_object() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("objectByte");

        final Object object = new MyObject();

        final byte value = Dangerous.getByte(object, field);
    }


    @Test(enabled = true)
    public void putByte_object() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("objectByte");

        final Object object = new MyObject();

        Dangerous.putByte(object, field, (byte) 0);
    }


    @Test(enabled = true)
    public void byte_object() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("objectByte");

        final Object object = new MyObject();

        final byte expected = nextByte();
        Dangerous.putByte(object, field, expected);
        final byte actual = Dangerous.getByte(object, field);
        Assert.assertEquals(actual, expected);
    }


    @Test(enabled = true)
    public void getShort_object() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("objectShort");

        final Object object = new MyObject();

        final short value = Dangerous.getShort(object, field);
    }


    @Test(enabled = true)
    public void putShort_object() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("objectShort");

        final Object object = new MyObject();

        Dangerous.putShort(object, field, (short) 0);
    }


    @Test(enabled = true)
    public void short_object() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("objectShort");

        final Object object = new MyObject();

        final short expected = nextShort();
        Dangerous.putShort(object, field, expected);
        final short actual = Dangerous.getShort(object, field);
        Assert.assertEquals(actual, expected);
    }


    @Test(enabled = true)
    public void getInt_object() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("objectInt");

        final Object object = new MyObject();

        final short value = Dangerous.getShort(object, field);
    }


    @Test(enabled = true)
    public void putInt_object() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("objectInt");

        final Object object = new MyObject();

        Dangerous.putInt(object, field, 0);
    }


    @Test(enabled = true)
    public void int_object() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("objectInt");

        final Object object = new MyObject();
        
        final int expected = nextInt();
        Dangerous.putInt(object, field, expected);
        final int actual = Dangerous.getInt(object, field);
        Assert.assertEquals(actual, expected);
    }


    @Test(enabled = true)
    public void getLong_object() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("objectLong");

        final Object object = new MyObject();

        final long value = Dangerous.getLong(object, field);
    }


    @Test(enabled = true)
    public void putLong_object() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("objectLong");

        final Object object = new MyObject();

        Dangerous.putLong(object, field, 0L);
    }


    @Test(enabled = true)
    public void long_object() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("objectLong");

        final Object object = new MyObject();

        final long expected = nextLong();
        Dangerous.putLong(object, field, expected);
        final long actual = Dangerous.getLong(object, field);
        Assert.assertEquals(actual, expected);
    }


    @Test(enabled = true)
    public void getFloat_object() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("objectFloat");

        final Object object = new MyObject();

        final float value = Dangerous.getFloat(object, field);
    }


    @Test(enabled = true)
    public void putFloat_object() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("objectFloat");

        final Object object = new MyObject();

        Dangerous.putFloat(object, field, 0.f);
    }


    @Test(enabled = true)
    public void float_object() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("objectFloat");

        final Object object = new MyObject();

        final float expected = nextFloat();
        Dangerous.putFloat(object, field, expected);
        final float actual = Dangerous.getFloat(object, field);
        Assert.assertEquals(actual, expected);
    }


    @Test(enabled = true)
    public void getDouble_object() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("objectDouble");

        final Object object = new MyObject();

        final double value = Dangerous.getDouble(object, field);
    }


    @Test(enabled = true)
    public void putDouble_object() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("objectDouble");

        final Object object = new MyObject();

        Dangerous.putDouble(object, field, 0.f);
    }


    @Test(enabled = true)
    public void double_object() throws NoSuchFieldException {

        final Field field = MyObject.class.getDeclaredField("objectDouble");

        final Object object = new MyObject();

        final double expected = nextDouble();
        Dangerous.putDouble(object, field, expected);
        final double actual = Dangerous.getDouble(object, field);
        Assert.assertEquals(actual, expected);
    }


}

