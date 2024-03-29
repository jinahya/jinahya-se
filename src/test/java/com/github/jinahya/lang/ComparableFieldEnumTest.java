/*
 * Copyright 2013 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @param <E> enum type parameter
 * @param <F> field type parameter
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public abstract class ComparableFieldEnumTest<E extends Enum<E> & ComparableFieldEnum<E, F>, F extends Comparable<? super F>>
        extends FieldEnumTest<E, F> {

    /**
     * logger.
     */
    private static final Logger logger
            = LoggerFactory.getLogger(ComparableFieldEnumTest.class);

    /**
     * @param enumType  the enum type
     * @param fieldType the field type.
     */
    public ComparableFieldEnumTest(final Class<E> enumType,
                                   final Class<F> fieldType) {

        super(enumType, fieldType);
    }

    @Test
    public void assertOrderedFieldValues() {

        final F[] fieldValues = FieldEnums.fieldValues(enumClass, fieldClass);
        for (int i = 1; i < fieldValues.length; i++) {
            assertTrue(fieldValues[i - 1].compareTo(fieldValues[i]) < 0);
        }
    }
}
