/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
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


package com.github.jinahya.sql;


import com.github.jinahya.lang.FieldEnumTest;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ResultSetHoldabilityTest
    extends FieldEnumTest<ResultSetHoldability, Integer> {


    public ResultSetHoldabilityTest() {

        super(ResultSetHoldability.class, Integer.class);
    }


    @Test
    public void testToResultSet()
        throws NoSuchFieldException, IllegalAccessException {

        for (ResultSetHoldability value : ResultSetHoldability.values()) {

            // locate by name, compare field
            final String name = value.name();
            {
                final Field field = ResultSet.class.getField(name);
                Assert.assertEquals(field.get(null), value.fieldValue());
            }

            // locate by field, compare name
            final int fieldValue = value.fieldValue();
            for (Field field : ResultSet.class.getFields()) {
                if (field.getInt(null) == fieldValue) {
                    Assert.assertEquals(field.getName(), value.name());
                    break;
                }
            }
        }
    }


    @Test
    public void testFromResultSet()
        throws NoSuchFieldException, IllegalAccessException {

        for (Field field : ResultSet.class.getFields()) {

            // locate by name, compare field
            final String name = field.getName();
            {
                try {
                    final ResultSetHoldability type =
                        ResultSetHoldability.valueOf(name);
                    Assert.assertEquals(type.fieldValue(),
                                        field.get(null));
                } catch (IllegalArgumentException iae) {
                }
            }

            // locate by field, compare name
            final int fieldValue = field.getInt(null);
            {
                try {
                    final ResultSetHoldability type =
                        ResultSetHoldability.fromFieldValue(fieldValue);
                    Assert.assertEquals(type.name(), name);
                } catch (IllegalArgumentException iae) {
                }
            }
        }
    }


}
