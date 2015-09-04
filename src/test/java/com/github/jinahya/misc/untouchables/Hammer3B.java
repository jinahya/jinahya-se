/*
 * Copyright 2014 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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


package com.github.jinahya.misc.untouchables;


import com.github.jinahya.misc.Dangerous;
import java.lang.reflect.Field;
import sun.misc.Unsafe;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class Hammer3B {


    public static void main(final String[] args) throws NoSuchFieldException, ReflectiveOperationException {

        final Field field = Untouchable3.class.getDeclaredField("SECRET");

        final Unsafe unsafe = Dangerous.theUnsafe();
        final Object base = unsafe.staticFieldBase(field);
        final long offset = unsafe.staticFieldOffset(field);
        final Object value = unsafe.getObject(base, offset);
        System.out.println(value);
        System.out.println(Dangerous.newInstance().getObject(null, field));
    }


}

