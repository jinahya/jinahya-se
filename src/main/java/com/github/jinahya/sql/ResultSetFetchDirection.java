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
public enum ResultSetFetchDirection
        implements IntFieldEnum<ResultSetFetchDirection> {

    /**
     * Constant for
     * {@link ResultSet#FETCH_FORWARD}({@value ResultSet#FETCH_FORWARD}).
     *
     * @see ResultSet#FETCH_REVERSE
     */
    FETCH_FORWARD(ResultSet.FETCH_FORWARD), // 1000

    /**
     * Constant for
     * {@link ResultSet#FETCH_REVERSE}({@value ResultSet#FETCH_REVERSE}).
     *
     * @see ResultSet#FETCH_REVERSE
     */
    FETCH_REVERSE(ResultSet.FETCH_REVERSE), // 1001

    /**
     * Constant for
     * {@link ResultSet#FETCH_UNKNOWN}({@value ResultSet#FETCH_UNKNOWN}).
     *
     * @see ResultSet#FETCH_UNKNOWN
     */
    FETCH_UNKNOWN(ResultSet.FETCH_UNKNOWN) // 1002
    ;

    private ResultSetFetchDirection(final int fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public int fieldValueAsInt() {
        return fieldValue;
    }

    private final int fieldValue;
}
