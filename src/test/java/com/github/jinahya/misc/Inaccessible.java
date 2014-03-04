/*
 * Copyright 2014 Jin Kwon <onacit at gmail.com>.
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


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
class Inaccessible {


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

