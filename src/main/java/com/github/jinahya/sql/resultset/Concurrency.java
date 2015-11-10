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


package com.github.jinahya.sql.resultset;


import com.github.jinahya.lang.FieldEnums;
import com.github.jinahya.lang.IntegerFieldEnum;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Constants for {@link ResultSet}'s concurrencies.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public enum Concurrency implements IntegerFieldEnum<Concurrency> {


    /**
     * Constant for {@link ResultSet#CONCUR_READ_ONLY}.
     */
    CONCUR_READ_ONLY(ResultSet.CONCUR_READ_ONLY), // 1007

    /**
     * Constant for {@link ResultSet#CONCUR_UPDATABLE}.
     */
    CONCUR_UPDATABLE(ResultSet.CONCUR_UPDATABLE); // 1008


    /**
     * Returns the enum constant of this type with the specified result set's
     * concurrency.
     *
     * @param resultSet the result set
     *
     * @return the enum constant with specified result set's concurrency
     *
     * @throws SQLException if a database access error occurs or this method is
     * called on a closed result set
     *
     * @see ResultSet#getConcurrency()
     */
    public static Concurrency fromResultSet(final ResultSet resultSet)
            throws SQLException {

        if (resultSet == null) {
            throw new NullPointerException("null resultSet");
        }

        return FieldEnums.fromFieldValue(Concurrency.class,
                                         resultSet.getFetchDirection());
    }


    /**
     * Creates a new instance.
     *
     * @param fieldValue field value.
     */
    private Concurrency(final int fieldValue) {

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

