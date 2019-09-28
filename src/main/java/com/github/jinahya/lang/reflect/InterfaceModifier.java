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
public enum InterfaceModifier implements ModifierFieldEnum<InterfaceModifier> {

    /**
     * Constant for {@link Modifier#PUBLIC}({@value Modifier#PUBLIC}).
     */
    PUBLIC(Modifier.PUBLIC), // 1

    /**
     * Constant for {@link Modifier#PRIVATE}({@value Modifier#PRIVATE}).
     */
    PRIVATE(Modifier.PRIVATE),//2

    /**
     * Constant for {@link Modifier#PROTECTED}({@value Modifier#PROTECTED}).
     */
    PROTECTED(Modifier.PROTECTED), // 4

    /**
     * Constant for {@link Modifier#STATIC}({@value Modifier#STATIC}).
     */
    STATIC(Modifier.STATIC), // 8

    /**
     * Constant for {@link Modifier#ABSTRACT}({@value Modifier#ABSTRACT}).
     */
    ABSTRACT(Modifier.ABSTRACT), // 1024

    /**
     * Constant for {@link Modifier#STRICT}({@value Modifier#STRICT}).
     */
    STRICT(Modifier.STRICT); // 2048

    private InterfaceModifier(final int fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public int fieldValueAsInt() {
        return fieldValue;
    }

    private final int fieldValue;
}
