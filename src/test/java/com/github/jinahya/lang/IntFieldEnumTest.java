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

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * @param <E> enum type parameter
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public abstract class IntFieldEnumTest<E extends Enum<E> & IntFieldEnum<E>> {

    public IntFieldEnumTest(final Class<E> enumType) {

        super();

        this.enumType = Objects.requireNonNull(enumType, "null enumType");
    }

    @Test
    public void assertUniqueFieldValues() {

        final int[] fieldValues = IntFieldEnum.fieldValues(enumType);
        for (int i = 0; i < fieldValues.length; i++) {
            for (int j = 0; j < i; j++) {
                if (fieldValues[j] == fieldValues[i]) {
                    fail("value[" + j + "] == value[" + i + "] in " + enumType);
                }
            }
        }
    }

    protected final Class<E> enumType;
}
