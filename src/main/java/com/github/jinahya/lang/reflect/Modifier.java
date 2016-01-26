/*
 * Copyright 2015 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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


import com.github.jinahya.lang.IntFieldEnum;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public enum Modifier implements IntFieldEnum<Modifier> {

    PUBLIC(java.lang.reflect.Modifier.PUBLIC),
    PRIVATE(java.lang.reflect.Modifier.PRIVATE),
    PROTECTED(java.lang.reflect.Modifier.PROTECTED),
    STATIC(java.lang.reflect.Modifier.STATIC),
    FINAL(java.lang.reflect.Modifier.FINAL),
    SYNCHRONIZED(java.lang.reflect.Modifier.SYNCHRONIZED),
    VOLATILE(java.lang.reflect.Modifier.VOLATILE),
    TRANSIENT(java.lang.reflect.Modifier.TRANSIENT),
    NATIVE(java.lang.reflect.Modifier.NATIVE),
    INTERFACE(java.lang.reflect.Modifier.INTERFACE),
    ABSTRACT(java.lang.reflect.Modifier.ABSTRACT),
    STRICT(java.lang.reflect.Modifier.STRICT);


    Modifier(final int fieldValue) {

        this.fieldValue = fieldValue;
    }


    @Override
    public int fieldValueAsInt() {

        return fieldValue;
    }


    private final int fieldValue;

}

