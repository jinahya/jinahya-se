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


package com.github.jinahya.sql.resultset;


import com.github.jinahya.lang.FieldEnums;
import com.github.jinahya.lang.IntegerFieldEnum;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.xml.bind.annotation.XmlEnum;


/**
 * Constants for {@link ResultSet}'s types.
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
@XmlEnum
public enum Type implements IntegerFieldEnum<Type> {


    /**
     * Constant for {@link ResultSet#TYPE_FORWARD_ONLY}.
     */
    TYPE_FORWARD_ONLY(ResultSet.TYPE_FORWARD_ONLY), // 1003

    /**
     * Constant for {@link ResultSet#TYPE_SCROLL_INSENSITIVE}.
     */
    TYPE_SCROLL_INSENSITIVE(ResultSet.TYPE_SCROLL_INSENSITIVE), // 1004

    /**
     * Constant for {@link ResultSet#TYPE_SCROLL_SENSITIVE}.
     */
    TYPE_SCROLL_SENSITIVE(ResultSet.TYPE_SCROLL_SENSITIVE); // 1005


    /**
     * Returns the enum constant of this type with the specified field value.
     *
     * @param fieldValue the field value
     *
     * @throws IllegalArgumentException if this enum type has no constant with
     * the specified field value.
     *
     * @return the enum constant with the specified field value.
     */
    public static Type fromFieldValue(final int fieldValue) {

        return FieldEnums.fromFieldValue(Type.class, fieldValue);
    }


    /**
     * Returns the enum constant of this type with the specified result set's
     * type.
     *
     * @param resultSet the result set
     *
     * @throws SQLException if a database access error occurs or this method is
     * called on a closed result set
     * @throws NullPointerException if {@code resultSet} is {@code null}.
     * @throws IllegalArgumentException if this enum type has no constant with
     * the specified field value.
     *
     * @return the enum constant with the specified field value.
     *
     * @see ResultSet#getType()
     */
    public static Type fromResultSet(final ResultSet resultSet)
            throws SQLException {

        if (resultSet == null) {
            throw new NullPointerException("null resultSet");
        }

        return fromFieldValue(resultSet.getType());
    }


    /**
     * Creates a new instance with specified field value.
     *
     * @param fieldValue the field value.
     */
    private Type(final int fieldValue) {

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

