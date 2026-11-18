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

/*-
 * #%L
 * jinahya-se
 * %%
 * Copyright (C) 2025 - 2026 Jinahya
 * %%
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
 * #L%
 */

import com.github.jinahya.lang.IntFieldEnum;

/**
 * Constants for modifiers defined in {@link java.lang.reflect.Modifier} class.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public enum Modifier implements IntFieldEnum<Modifier> {

    /**
     * Constant for {@link java.lang.reflect.Modifier#PUBLIC}({@value java.lang.reflect.Modifier#PUBLIC}).
     */
    PUBLIC(java.lang.reflect.Modifier.PUBLIC),

    /**
     * Constant for {@link java.lang.reflect.Modifier#PRIVATE}({@value java.lang.reflect.Modifier#PRIVATE}).
     */
    PRIVATE(java.lang.reflect.Modifier.PRIVATE),

    /**
     * Constant for {@link java.lang.reflect.Modifier#PROTECTED}({@value java.lang.reflect.Modifier#PROTECTED}).
     */
    PROTECTED(java.lang.reflect.Modifier.PROTECTED),

    /**
     * Constant for {@link java.lang.reflect.Modifier#STATIC}({@value java.lang.reflect.Modifier#STATIC}).
     */
    STATIC(java.lang.reflect.Modifier.STATIC),

    /**
     * Constant for {@link java.lang.reflect.Modifier#FINAL}({@value java.lang.reflect.Modifier#FINAL}).
     */
    FINAL(java.lang.reflect.Modifier.FINAL),

    /**
     * Constant for {@link java.lang.reflect.Modifier#SYNCHRONIZED}({@value java.lang.reflect.Modifier#SYNCHRONIZED}).
     */
    SYNCHRONIZED(java.lang.reflect.Modifier.SYNCHRONIZED),

    /**
     * Constant for {@link java.lang.reflect.Modifier#VOLATILE}({@value java.lang.reflect.Modifier#VOLATILE}).
     */
    VOLATILE(java.lang.reflect.Modifier.VOLATILE),

    /**
     * Constant for {@link java.lang.reflect.Modifier#TRANSIENT}({@value java.lang.reflect.Modifier#TRANSIENT}).
     */
    TRANSIENT(java.lang.reflect.Modifier.TRANSIENT),

    /**
     * Constant for {@link java.lang.reflect.Modifier#NATIVE}({@value java.lang.reflect.Modifier#NATIVE}).
     */
    NATIVE(java.lang.reflect.Modifier.NATIVE),

    /**
     * Constant for {@link java.lang.reflect.Modifier#INTERFACE}({@value java.lang.reflect.Modifier#INTERFACE}).
     */
    INTERFACE(java.lang.reflect.Modifier.INTERFACE),

    /**
     * Constant for {@link java.lang.reflect.Modifier#ABSTRACT}({@value java.lang.reflect.Modifier#ABSTRACT}).
     */
    ABSTRACT(java.lang.reflect.Modifier.ABSTRACT),

    /**
     * Constant for {@link java.lang.reflect.Modifier#STRICT}({@value java.lang.reflect.Modifier#STRICT}).
     */
    STRICT(java.lang.reflect.Modifier.STRICT);

    Modifier(final int fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public int getFieldValue() {
        return fieldValue;
    }

    private final int fieldValue;
}
