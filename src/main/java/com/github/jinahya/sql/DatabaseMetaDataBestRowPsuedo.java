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
public enum DatabaseMetaDataBestRowPsuedo
    implements FieldEnum<DatabaseMetaDataBestRowPsuedo, Integer> {


    /**
     * A constant for {@link DatabaseMetaData#bestRowUnknown}.
     */
    bestRowUnknown(DatabaseMetaData.bestRowUnknown), // 0

    /**
     * A constant for {@link DatabaseMetaData#bestRowNotPseudo}.
     */
    bestRowNotPseudo(DatabaseMetaData.bestRowNotPseudo), // 1

    /**
     * A constant for {@link DatabaseMetaData#bestRowPseudo}.
     */
    bestRowPseudo(DatabaseMetaData.bestRowPseudo); // 2


    public static DatabaseMetaDataBestRowPsuedo fromFieldValue(
        final int fieldValue) {

        return FieldEnums.fromFieldValue(
            DatabaseMetaDataBestRowPsuedo.class,
            Integer.valueOf(fieldValue));
    }


    public static Integer[] fieldValues() {

        return FieldEnums.fieldValues(
            DatabaseMetaDataBestRowPsuedo.class, Integer.class);
    }


    private DatabaseMetaDataBestRowPsuedo(final int fieldValue) {

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

