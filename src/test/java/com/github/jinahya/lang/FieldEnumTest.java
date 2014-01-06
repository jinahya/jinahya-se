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


import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 * @param <E>
 * @param <F>
 */
public abstract class FieldEnumTest<E extends Enum<E> & FieldEnum<E, F>, F> {


    private static final Logger LOGGER
        = LoggerFactory.getLogger(FieldEnumTest.class);


    public FieldEnumTest(final Class<E> enumType) {

        super();

        if (enumType == null) {
            throw new NullPointerException("null enumType");
        }

        this.enumType = enumType;
    }


    @Test
    public void assertUniqueFieldValues() {

        final Set<F> fieldValues = new HashSet<F>();
        for (final E enumConstant : enumType.getEnumConstants()) {
            final F fieldValue = enumConstant.getFieldValue();
            if (!fieldValues.add(fieldValue)) {
                Assert.fail("duplicate field value: " + fieldValue);
            }
        }
    }


    /**
     * enum type.
     */
    protected final Class<E> enumType;


}

