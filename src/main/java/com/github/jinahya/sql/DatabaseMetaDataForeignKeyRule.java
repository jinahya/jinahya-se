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
public enum DatabaseMetaDataForeignKeyRule
        implements IntFieldEnum<DatabaseMetaDataForeignKeyRule> {

    /**
     * A constant for {@link DatabaseMetaData#importedKeyCascade}.
     */
    importedKeyCascade(DatabaseMetaData.importedKeyCascade), // 0

    /**
     * A constant for {@link DatabaseMetaData#importedKeyRestrict}.
     */
    importedKeyRestrict(DatabaseMetaData.importedKeyRestrict), // 1

    /**
     * A constant for {@link DatabaseMetaData#importedKeySetNull}.
     */
    importedKeySetNull(DatabaseMetaData.importedKeySetNull), // 2

    /**
     * A constant for {@link DatabaseMetaData#importedKeyNoAction}.
     */
    importedKeyNoAction(DatabaseMetaData.importedKeyNoAction), // 3

    /**
     * A constant for {@link DatabaseMetaData#importedKeySetDefault}.
     */
    importedKeySetDefault(DatabaseMetaData.importedKeySetDefault); // 4

    private DatabaseMetaDataForeignKeyRule(final int fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public int fieldValueAsInt() {
        return fieldValue;
    }

    private final int fieldValue;
}
