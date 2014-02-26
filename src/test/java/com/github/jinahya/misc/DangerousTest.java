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


    @Test
    public void allocateInstance_() throws InstantiationException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Untouchable instance
            = dangerous.allocateInstance(Untouchable.class);

        Assert.assertNotNull(instance);
    }


    @Test
    public void compareAndSwapInt_instance_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base
            = dangerous.unsafe.allocateInstance(Untouchable.class);
        final Field field = Untouchable.class.getDeclaredField("instanceInt");
        assert !Modifier.isStatic(field.getModifiers());
        final int expected = 0;
        final int x = 0;

        Assert.assertTrue(
            dangerous.compareAndSwapInt(base, field, expected, x));
    }


    @Test
    public void compareAndSwapInt_static_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = null;
        final Field field = Untouchable.class.getDeclaredField("staticInt");
        assert Modifier.isStatic(field.getModifiers());
        final int expected = 0;
        final int x = 0;

        dangerous.compareAndSwapInt(base, field, expected, x);
    }


    @Test
    public void compareAndSwapLong_instance_() throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base
            = dangerous.unsafe().allocateInstance(Untouchable.class);
        final Field field = Untouchable.class.getDeclaredField("instanceLong");
        assert !Modifier.isStatic(field.getModifiers());
        final long expected = 0L;
        final long x = 0L;

        Assert.assertTrue(
            dangerous.compareAndSwapLong(base, field, expected, x));
    }


    @Test
    public void compareAndSwapLong_static_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = null;
        final Field field = Untouchable.class.getDeclaredField("staticLong");
        assert Modifier.isStatic(field.getModifiers());
        final long expected = 0L;
        final long x = 0L;

        dangerous.compareAndSwapLong(base, field, expected, x);
    }


    @Test
    public void compareAndSwapObject_instance_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base
            = dangerous.unsafe.allocateInstance(Untouchable.class);
        final Field field
            = Untouchable.class.getDeclaredField("instanceObject");
        assert !Modifier.isStatic(field.getModifiers());
        final Object expected = null;
        final Object x = new Object();

        Assert.assertTrue(
            dangerous.compareAndSwapObject(base, field, expected, x));
    }


    @Test
    public void compareAndSwapObject_static_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = null;
        final Field field = Untouchable.class.getDeclaredField("staticObject");
        assert Modifier.isStatic(field.getModifiers());
        final Object expected = null;
        final Object x = null;

        dangerous.compareAndSwapObject(base, field, expected, x);
    }


    @Test
    public void compareAndSwapObject_generic_instance_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base
            = dangerous.unsafe.allocateInstance(Untouchable.class);
        final Field field
            = Untouchable.class.getDeclaredField("instanceObject");
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

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = null;
        final Field field = Untouchable.class.getDeclaredField("staticObject");
        assert Modifier.isStatic(field.getModifiers());
        final Class<Object> type = Object.class;
        assert field.getType().equals(type);
        final Object expected = null;
        final Object x = new Object();

        dangerous.compareAndSwapObject(base, field, type, expected, x);
    }


    @Test
    public void getBoolean_instance_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base
            = dangerous.unsafe.allocateInstance(Untouchable.class);
        final Field field
            = Untouchable.class.getDeclaredField("instanceBoolean");
        assert !Modifier.isStatic(field.getModifiers());

        Assert.assertFalse(dangerous.getBoolean(base, field));
    }


    @Test
    public void getBoolean_instance_volatile_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base
            = dangerous.unsafe.allocateInstance(Untouchable.class);
        final Field field
            = Untouchable.class.getDeclaredField("instanceBooleanVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        Assert.assertFalse(dangerous.getBoolean(base, field));
    }


    @Test
    public void getBoolean_static_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = null;
        final Field field = Untouchable.class.getDeclaredField("staticBoolean");
        assert Modifier.isStatic(field.getModifiers());

        dangerous.getBoolean(base, field);
    }


    @Test
    public void getBoolean_static_volatile() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object object = null;
        final Field field
            = Untouchable.class.getDeclaredField("staticBooleanVolatile");

        dangerous.getBoolean(object, field);
    }


    @Test
    public void getByte_instance_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base
            = dangerous.unsafe.allocateInstance(Untouchable.class);
        final Field field
            = Untouchable.class.getDeclaredField("instanceBoolean");
        assert !Modifier.isStatic(field.getModifiers());

        Assert.assertEquals(dangerous.getByte(base, field), (byte) 0);
    }


    @Test
    public void getByte_instance_volatile_()
        throws InstantiationException, NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base
            = dangerous.unsafe.allocateInstance(Untouchable.class);
        final Field field
            = Untouchable.class.getDeclaredField("instanceBooleanVolatile");
        assert !Modifier.isStatic(field.getModifiers());
        assert Modifier.isVolatile(field.getModifiers());

        Assert.assertEquals(dangerous.getByte(base, field), 0b00);
    }


    @Test
    public void getByte_static_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = null;
        final Field field = Untouchable.class.getDeclaredField("staticBoolean");
        assert Modifier.isStatic(field.getModifiers());

        dangerous.getByte(base, field);
    }


    @Test
    public void getByte_static_volatile_() throws NoSuchFieldException {

        final Dangerous dangerous = Dangerous.newInstance();

        final Object base = null;
        final Field field
            = Untouchable.class.getDeclaredField("staticBooleanVolatile");

        dangerous.getByte(base, field);
    }


}

