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


package com.github.jinahya.sql;


import com.github.jinahya.lang.FieldEnum;
import com.github.jinahya.lang.FieldEnums;
import java.sql.DatabaseMetaData;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public enum DatabaseMetaDataTableIndexType
    implements FieldEnum<DatabaseMetaDataTableIndexType, Short> {


    /**
     * A constant for {@link DatabaseMetaData#tableIndexStatistic}.
     */
    tableIndexStatistic(DatabaseMetaData.tableIndexStatistic), // 0
    /**
     * A constant for {@link DatabaseMetaData#tableIndexClustered}.
     */
    tableIndexClustered(DatabaseMetaData.tableIndexClustered), // 1
    /**
     * A constant for {@link DatabaseMetaData#tableIndexHashed}.
     */
    tableIndexHashed(DatabaseMetaData.tableIndexHashed), // 2
    /**
     * A constant for {@link DatabaseMetaData#tableIndexOther}.
     */
    tableIndexOther(DatabaseMetaData.tableIndexOther); // 3


    public static DatabaseMetaDataTableIndexType fromFieldValue(
        final short fieldValue) {

        return FieldEnums.fromFieldValue(
            DatabaseMetaDataTableIndexType.class, Short.valueOf(fieldValue));
    }


    public static Short[] fieldValues() {

        return FieldEnums.fieldValues(DatabaseMetaDataTableIndexType.class,
                                           Short.class);
    }


    private DatabaseMetaDataTableIndexType(final short fieldValue) {

        this.fieldValue = fieldValue;
    }


    @Override
    public Short getFieldValue() {

        return fieldValue;
    }


    /**
     * field value.
     */
    private final short fieldValue;


}
