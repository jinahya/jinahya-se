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


    @Test
    public void getByte_() throws NoSuchFieldException {

        final Dangerous dangerous = new Dangerous();

        final Object object = new DangerousTest();
        final Field field = DangerousTest.class.getDeclaredField("$byte");

        final byte actual = dangerous.getByte(object, field);

        Assert.assertEquals(actual, (byte) 0);
    }


    @Test
    public void putByte_() throws NoSuchFieldException {

        final Dangerous dangerous = new Dangerous();

        final Object object = new DangerousTest();
        final Field field = DangerousTest.class.getDeclaredField("$byte");

        final byte expected = (byte) ThreadLocalRandom.current().nextInt();
        dangerous.putByte(object, field, expected);

        final byte actual = dangerous.getByte(object, field);

        Assert.assertEquals(actual, expected);
    }


    @Test
    public void getShort_() throws NoSuchFieldException {

        final Dangerous dangerous = new Dangerous();

        final Object object = new DangerousTest();
        final Field field = DangerousTest.class.getDeclaredField("$short");

        final short actual = dangerous.getShort(object, field);

        Assert.assertEquals(actual, (short) 0);
    }


    @Test
    public void putShort_() throws NoSuchFieldException {

        final Dangerous dangerous = new Dangerous();

        final Object object = new DangerousTest();
        final Field field = DangerousTest.class.getDeclaredField("$short");

        final short expected = (short) ThreadLocalRandom.current().nextInt();
        dangerous.putShort(object, field, expected);

        final short actual = dangerous.getShort(object, field);

        Assert.assertEquals(actual, expected);
    }


    @Test
    public void getInt_() throws NoSuchFieldException {

        final Dangerous dangerous = new Dangerous();

        final Object object = new DangerousTest();
        final Field field = DangerousTest.class.getDeclaredField("$int");

        final int actual = dangerous.getInt(object, field);

        Assert.assertEquals(actual, 0);
    }


    @Test
    public void putInt_() throws NoSuchFieldException {

        final Dangerous dangerous = new Dangerous();

        final Object object = new DangerousTest();
        final Field field = DangerousTest.class.getDeclaredField("$int");

        final int expected = ThreadLocalRandom.current().nextInt();
        dangerous.putInt(object, field, expected);

        final int actual = dangerous.getInt(object, field);

        Assert.assertEquals(actual, expected);
    }


    @Test
    public void getObject_() throws NoSuchFieldException {

        final Dangerous dangerous = new Dangerous();

        final Object object = new DangerousTest();
        final Field field = DangerousTest.class.getDeclaredField("$Object");

        final Object actual = dangerous.getObject(object, field);

        Assert.assertNull(actual);
    }


    @Test
    public void putObject_() throws NoSuchFieldException {

        final Dangerous dangerous = new Dangerous();

        final Object object = new DangerousTest();
        final Field field = DangerousTest.class.getDeclaredField("$Object");

        final Object expected = new Object();
        dangerous.putObject(object, field, expected);

        final Object actual = dangerous.getObject(object, field);

        Assert.assertEquals(actual, expected);
    }


    private byte $byte;


    private Byte $Byte;


    private short $short;


    private Short $Short;


    private int $int;


    private Integer $Integer;


    private long $long;


    private Long $Long;


    private float $float;


    private Float $Float;


    private double $double;


    private Double $Double;


    private String $String;


    private Object $Object;


}

