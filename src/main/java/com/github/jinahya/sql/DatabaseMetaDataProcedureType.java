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

import com.github.jinahya.lang.IntFieldEnum;

import java.sql.DatabaseMetaData;

/**
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public enum DatabaseMetaDataProcedureType
        implements IntFieldEnum<DatabaseMetaDataProcedureType> {

    /**
     * A constant for {@link DatabaseMetaData#procedureResultUnknown}.
     */
    procedureResultUnknown(DatabaseMetaData.procedureResultUnknown), // 0

    /**
     * A constant for {@link DatabaseMetaData#procedureNoResult}.
     */
    procedureNoResult(DatabaseMetaData.procedureNoResult), // 1

    /**
     * A constant for {@link DatabaseMetaData#procedureReturnsResult}.
     */
    procedureReturnsResult(DatabaseMetaData.procedureReturnsResult); // 2

    /**
     * Creates a new instance.
     *
     * @param fieldValue field value.
     */
    private DatabaseMetaDataProcedureType(final int fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public int getFieldValue() {
        return fieldValue;
    }

    private final int fieldValue;
}
