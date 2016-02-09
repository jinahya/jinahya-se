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
import java.sql.ResultSet;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public enum ResultSetType implements IntFieldEnum<ResultSetType> {

    /**
     * Constant for
     * {@link ResultSet#TYPE_FORWARD_ONLY}({@value ResultSet#TYPE_FORWARD_ONLY}).
     */
    TYPE_FORWARD_ONLY(ResultSet.TYPE_FORWARD_ONLY), // 1003
    /**
     * Constant for
     * {@link ResultSet#TYPE_SCROLL_INSENSITIVE}({@value ResultSet#TYPE_SCROLL_INSENSITIVE}).
     */
    TYPE_SCROLL_INSENSITIVE(ResultSet.TYPE_SCROLL_INSENSITIVE), // 1004
    /**
     * Constant for
     * {@link ResultSet#TYPE_SCROLL_SENSITIVE}({@value ResultSet#TYPE_SCROLL_SENSITIVE}).
     */
    TYPE_SCROLL_SENSITIVE(ResultSet.TYPE_SCROLL_SENSITIVE); // 1005


    private ResultSetType(final int fieldValue) {
        this.fieldValue = fieldValue;
    }


    @Override
    public int fieldValueAsInt() {
        return fieldValue;
    }


    private final int fieldValue;

}

