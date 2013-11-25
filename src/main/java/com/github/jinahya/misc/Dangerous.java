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


import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public final class Dangerous {


    /**
     * The {@code Unsafe} instance.
     */
    private static final Object UNSAFE;


    static {

        Object unsafeInstance = null;

        final Class<?> unsafeClass;
        try {
            unsafeClass = Class.forName("sun.misc.Unsafe");
        } catch (final ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        }

        try {
            final Field field = unsafeClass.getDeclaredField("theUnsafe");
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            try {
                unsafeInstance = field.get(null);
            } catch (IllegalAccessException iae) {
                throw new InstantiationError(iae.getMessage());
            }
        } catch (NoSuchFieldException nsfe) {
            throw new InstantiationError(nsfe.getMessage());
        }

//        for (final Field field : Unsafe.class.getDeclaredFields()) {
//            final int modifiers = field.getModifiers();
//            if (!Modifier.isStatic(modifiers)) {
//                continue;
//            }
//            if (!Unsafe.class.equals(field.getType())) {
//                continue;
//            }
//            if (!field.isAccessible()) {
//                field.setAccessible(true);
//            }
//            try {
//                unsafe = (Unsafe) field.get(null);
//                break;
//            } catch (IllegalAccessException iae) {
//                continue;
//            }
//        }
        if (unsafeInstance == null) {
            throw new InstantiationError("unable to locate the unsafe");
        }

        UNSAFE = unsafeInstance;
    }


    public static int getInt(final Object object, final int offset)
        throws Throwable {

        final MethodType type
            = MethodType.methodType(Object.class, Integer.TYPE);

        final MethodHandles.Lookup lookup = MethodHandles.lookup();

        final MethodHandle handle
            = lookup.findVirtual(UNSAFE.getClass(), "getInt", type);

        return ((Integer) handle.invokeExact(object, offset)).intValue();
    }


    private Dangerous() {

        super();
    }


}

