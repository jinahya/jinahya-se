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


package com.github.jinahya.sql;


import com.github.jinahya.lang.FieldEnum;
import com.github.jinahya.lang.FieldEnums;
import java.sql.DatabaseMetaData;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public enum DatabaseMetaDataAttributeNullable
    implements FieldEnum<DatabaseMetaDataAttributeNullable, Integer> {


    /**
     * A constant for {@link DatabaseMetaData#attributeNoNulls}.
     */
    attributeNoNulls(DatabaseMetaData.attributeNoNulls), // 0

    /**
     * A constant for {@link DatabaseMetaData#attributeNullable}.
     */
    attributeNullable(DatabaseMetaData.attributeNullable), // 1

    /**
     * A constant for {@link DatabaseMetaData#attributeNullableUnknown}.
     */
    attributeNullableUnknown(DatabaseMetaData.attributeNullableUnknown); // 2


    public static DatabaseMetaDataAttributeNullable fromFieldValue(
        final int fieldValue) {

        return FieldEnums.fromFieldValue(
            DatabaseMetaDataAttributeNullable.class,
            Integer.valueOf(fieldValue));
    }


    public static Integer[] fieldValues() {

        return FieldEnums.fieldValues(
            DatabaseMetaDataAttributeNullable.class, Integer.class);
    }


    private DatabaseMetaDataAttributeNullable(final int fieldValue) {

        this.fieldValue = fieldValue;
    }


    @Override
    public Integer fieldValue() {

        return fieldValue;
    }


    /**
     * field value.
     */
    private final int fieldValue;


}

