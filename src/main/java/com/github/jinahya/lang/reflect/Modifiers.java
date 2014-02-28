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


    public static boolean isFieldModifiers(final int modifiers) {

        return (Modifier.fieldModifiers() & modifiers) == modifiers;
    }


    public static void requireFieldModifiers(final int modifiers) {

        if (!isFieldModifiers(modifiers)) {
            throw new IllegalArgumentException(
                "modifiers(" + modifiers + ") is not for fields");
        }
    }


    private Modifiers() {

        super();
    }


}

