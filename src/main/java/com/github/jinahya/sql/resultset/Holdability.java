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
 * Constants for {@link ResultSet}'s holdabilities.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public enum Holdability implements IntegerFieldEnum<Holdability> {


    /**
     * Constant for {@link ResultSet#HOLD_CURSORS_OVER_COMMIT}.
     */
    HOLD_CURSORS_OVER_COMMIT(ResultSet.HOLD_CURSORS_OVER_COMMIT), // 1

    /**
     * Constant for {@link ResultSet#CLOSE_CURSORS_AT_COMMIT}.
     */
    CLOSE_CURSORS_AT_COMMIT(ResultSet.CLOSE_CURSORS_AT_COMMIT); // 2


    /**
     * Returns the enum constant of this type with the specified result set's
     * current holdability.
     *
     * @param resultSet the result set
     *
     * @return the enum constant of this type with the specified result set's
     * current holdability
     *
     * @throws SQLException if a database access error occurs or this method is
     * called on a closed result set
     *
     * @see ResultSet#getHoldability()
     */
    public static Holdability fromResultSet(final ResultSet resultSet)
            throws SQLException {

        if (resultSet == null) {
            throw new NullPointerException("null resultSet");
        }

        return FieldEnums.fromFieldValue(Holdability.class,
                                         resultSet.getHoldability());
    }


    /**
     * Creates a new instance with specified field value.
     *
     * @param fieldValue the field value
     */
    private Holdability(final int fieldValue) {

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

