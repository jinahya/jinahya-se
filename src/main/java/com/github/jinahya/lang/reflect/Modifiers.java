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


package com.github.jinahya.lang.reflect;


import java.lang.reflect.Modifier;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public final class Modifiers {


    public static boolean isClassModifiers(final int modifiers) {

        return (Modifier.classModifiers() & modifiers) == modifiers;
    }


    public static int requireClassModifiers(final int modifiers) {

        if (!isClassModifiers(modifiers)) {
            throw new IllegalArgumentException(
                "modifiers(" + modifiers + ") is not for classes");
        }

        return modifiers;
    }


    public static boolean isFieldModifiers(final int modifiers) {

        return (Modifier.fieldModifiers() & modifiers) == modifiers;
    }


    public static int requireFieldModifiers(final int modifiers) {

        if (!isFieldModifiers(modifiers)) {
            throw new IllegalArgumentException(
                "modifiers(" + modifiers + ") is not for fields");
        }

        return modifiers;
    }


    public static boolean isInterfaceModifiers(final int modifiers) {

        return (Modifier.fieldModifiers() & modifiers) == modifiers;
    }


    public static int requireInterfaceModifiers(final int modifiers) {

        if (!isInterfaceModifiers(modifiers)) {
            throw new IllegalArgumentException(
                "modifiers(" + modifiers + ") is not for interfaces");
        }

        return modifiers;
    }


    public static boolean isMethodModifiers(final int modifiers) {

        return (Modifier.fieldModifiers() & modifiers) == modifiers;
    }


    public static int requireMethodModifiers(final int modifiers) {

        if (!isMethodModifiers(modifiers)) {
            throw new IllegalArgumentException(
                "modifiers(" + modifiers + ") is not for methods");
        }

        return modifiers;
    }


    private Modifiers() {

        super();
    }

}

