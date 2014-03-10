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


package com.github.jinahya.lang;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 * @param <E> enum type parameter
 * @param <F> field type parameter
 */
public abstract class ComparableFieldEnumTest<E extends Enum<E> & FieldEnum<E, F>, F extends Comparable<? super F>>
    extends FieldEnumTest<E, F> {


    /**
     * logger.
     */
    private static final Logger logger
        = LoggerFactory.getLogger(ComparableFieldEnumTest.class);


    public ComparableFieldEnumTest(final Class<E> enumType,
                                   final Class<F> fieldType) {

        super(enumType, fieldType);
    }


    @Test
    public void assertOrderedFieldValues() {

        final F[] fieldValues = FieldEnums.fieldValues(enumType, fieldType);
        for (int i = 1; i < fieldValues.length; i++) {
            Assert.assertTrue(fieldValues[i - 1].compareTo(fieldValues[i]) < 0);
        }
    }


}

