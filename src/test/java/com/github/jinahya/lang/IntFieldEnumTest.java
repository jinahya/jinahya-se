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
package com.github.jinahya.lang;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * @param <E> enum type parameter
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public abstract class IntFieldEnumTest<E extends Enum<E> & IntFieldEnum<E>> {

    public IntFieldEnumTest(final Class<E> enumClass) {

        super();

        this.enumClass = Objects.requireNonNull(enumClass, "null enumClass");
    }

    @Test
    void valueOfFieldValue__() throws InvocationTargetException, IllegalAccessException {
        final Method valueOfFieldValue;
        try {
            valueOfFieldValue = enumClass.getMethod("valueOfFieldValue");
        } catch (final ReflectiveOperationException roe) {
            return;
        }
        for (final var enumConstant : enumClass.getEnumConstants()) {
            final var value = valueOfFieldValue.invoke(null, new Object[]{enumConstant.getFieldValue()});
            assertThat(value)
                    .isEqualTo(enumConstant);
        }
    }

    @Test
    public void assertUniqueFieldValues() {
        final int[] fieldValues = IntFieldEnum.fieldValues(enumClass);
        for (int i = 0; i < fieldValues.length; i++) {
            for (int j = 0; j < i; j++) {
                if (fieldValues[j] == fieldValues[i]) {
                    fail("value[" + j + "] == value[" + i + "] in " + enumClass);
                }
            }
        }
    }

    protected final Class<E> enumClass;
}
