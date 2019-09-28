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
package com.github.jinahya.lang.reflect;

import java.lang.reflect.Modifier;

/**
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public enum FieldModifier implements ModifierFieldEnum<FieldModifier> {

    /**
     *
     */
    PUBLIC(Modifier.PUBLIC), // 1

    /**
     *
     */
    PRIVATE(Modifier.PRIVATE), // 2

    /**
     *
     */
    PROTECTED(Modifier.PROTECTED), // 4

    /**
     *
     */
    STATIC(Modifier.STATIC), // 8

    /**
     *
     */
    FINAL(Modifier.FINAL), // 16
    /**
     *
     */
    VOLATILE(Modifier.VOLATILE), // 64
    /**
     *
     */
    TRANSIENT(Modifier.TRANSIENT); // 128

    private FieldModifier(final int fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public int fieldValueAsInt() {
        return fieldValue;
    }

    private final int fieldValue;
}
