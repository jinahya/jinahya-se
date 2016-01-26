/*
 * Copyright 2015 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class JinahyaDatabaseMetaData {


    public static enum BestRowIdentifierPseudoColumn
        implements IntFieldEnum<BestRowIdentifierPseudoColumn> {

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


        private BestRowIdentifierPseudoColumn(final int fieldValue) {

            this.fieldValue = fieldValue;
        }


        @Override
        public int fieldValueAsInt() {

            return fieldValue;
        }


        private final int fieldValue;

    }


    public static enum BestRowIdentifierScope
        implements IntFieldEnum<BestRowIdentifierScope> {

        /**
         * A constant for {@link DatabaseMetaData#bestRowTemporary}.
         */
        bestRowTemporary(DatabaseMetaData.bestRowTemporary), // 0

        /**
         * A constant for {@link DatabaseMetaData#bestRowTransaction}.
         */
        bestRowTransaction(DatabaseMetaData.bestRowTransaction), // 1

        /**
         * A constant for {@link DatabaseMetaData#bestRowSession}.
         */
        bestRowSession(DatabaseMetaData.bestRowSession); // 2// 2


        private BestRowIdentifierScope(final int fieldValue) {

            this.fieldValue = fieldValue;
        }


        @Override
        public int fieldValueAsInt() {

            return fieldValue;
        }


        private final int fieldValue;

    }


    public static enum AttributeNullable
        implements IntFieldEnum<AttributeNullable> {

        /**
         * A constant for
         * {@link DatabaseMetaData#attributeNoNulls}({@value DatabaseMetaData#attributeNoNulls}).
         */
        attributeNoNulls(DatabaseMetaData.attributeNoNulls), // 0

        /**
         * A constant for
         * {@link DatabaseMetaData#attributeNullable}({@value DatabaseMetaData#attributeNullable}).
         */
        attributeNullable(DatabaseMetaData.attributeNullable), // 1

        /**
         * A constant for
         * {@link DatabaseMetaData#attributeNullableUnknown}({@value DatabaseMetaData#attributeNullableUnknown}).
         */
        attributeNullableUnknown(DatabaseMetaData.attributeNullableUnknown); // 2


        private AttributeNullable(final int fieldValue) {

            this.fieldValue = fieldValue;
        }


        @Override
        public int fieldValueAsInt() {

            return fieldValue;
        }


        private final int fieldValue;

    }


    public static enum Deferrability implements IntFieldEnum<Deferrability> {

        /**
         * A constant for
         * {@link DatabaseMetaData#importedKeyInitiallyDeferred}({@value DatabaseMetaData#importedKeyInitiallyDeferred}).
         */
        importedKeyInitiallyDeferred(
            DatabaseMetaData.importedKeyInitiallyDeferred), // 5

        /**
         * A constant for
         * {@link DatabaseMetaData#importedKeyInitiallyImmediate}({@value DatabaseMetaData#importedKeyInitiallyImmediate}).
         */
        importedKeyInitiallyImmediate(
            DatabaseMetaData.importedKeyInitiallyImmediate), // 6

        /**
         * A constant for
         * {@link DatabaseMetaData#importedKeyNotDeferrable}({@value DatabaseMetaData#importedKeyNotDeferrable}).
         */
        importedKeyNotDeferrable(DatabaseMetaData.importedKeyNotDeferrable);


        private Deferrability(final int fieldValue) {

            this.fieldValue = fieldValue;
        }


        @Override
        public int fieldValueAsInt() {

            return fieldValue;
        }


        /**
         * field value.
         */
        private final int fieldValue;

    }


    public static enum DatabaseMetaDataForeignKeyRule
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

        //importedKeyInitiallyImmediate(DatabaseMetaData.importedKeyInitiallyImmediate) // 6

        private DatabaseMetaDataForeignKeyRule(final int fieldValue) {

            this.fieldValue = fieldValue;
        }


        @Override
        public int fieldValueAsInt() {

            return fieldValue;
        }


        private final int fieldValue;

    }


    public static enum SQLState implements IntFieldEnum<SQLState> {

        sqlStateSQL(DatabaseMetaData.sqlStateSQL),
        @Deprecated
        sqlStateSQL99(DatabaseMetaData.sqlStateSQL99),
        sqlStateXOpen(DatabaseMetaData.sqlStateXOpen);


        SQLState(final int fieldValue) {

            this.fieldValue = fieldValue;
        }


        @Override
        public int fieldValueAsInt() {

            return fieldValue;
        }


        private final int fieldValue;

    }


    @XmlEnum(Integer.class)
    public static enum TypeNullable implements IntFieldEnum<TypeNullable> {

        /**
         * A constant for
         * {@link DatabaseMetaData#typeNoNulls}({@value DatabaseMetaData#typeNoNulls}).
         */
        @XmlEnumValue("0")
        typeNoNulls(DatabaseMetaData.typeNoNulls), // 0

        /**
         * A constant for
         * {@link DatabaseMetaData#typeNullable}({@value DatabaseMetaData#typeNullable}).
         */
        @XmlEnumValue("1")
        typeNullable(DatabaseMetaData.typeNullable), // 1

        /**
         * A constant for
         * {@link DatabaseMetaData#typeNullableUnknown}({@value DatabaseMetaData#typeNullableUnknown}).
         */
        @XmlEnumValue("2")
        typeNullableUnknown(DatabaseMetaData.typeNullableUnknown); // 2


        private TypeNullable(final int fieldValue) {

            this.fieldValue = fieldValue;
        }


        @Override
        public int fieldValueAsInt() {

            return fieldValue;
        }


        private final int fieldValue;

    }


    public static enum ProcedureType implements IntFieldEnum<ProcedureType> {

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


        private ProcedureType(final int fieldValue) {

            this.fieldValue = fieldValue;
        }


        @Override
        public int fieldValueAsInt() {

            return fieldValue;
        }


        private final int fieldValue;

    }


    public static enum FunctionType implements IntFieldEnum<FunctionType> {

        /**
         * A constant for {@link DatabaseMetaData#functionResultUnknown}.
         */
        functionResultUnknown(DatabaseMetaData.functionResultUnknown), // 0

        /**
         * A constant for {@link DatabaseMetaData#functionReturnsTable}.
         */
        functionReturnsTable(DatabaseMetaData.functionReturnsTable), // 2

        /**
         * A constant for {@link DatabaseMetaData#functionReturn}.
         */
        functionReturn(DatabaseMetaData.functionReturn); // 4// 4


        private FunctionType(final int fieldValue) {

            this.fieldValue = fieldValue;
        }


        @Override
        public int fieldValueAsInt() {

            return fieldValue;
        }


        private final int fieldValue;

    }


    private JinahyaDatabaseMetaData() {

        super();
    }

}

