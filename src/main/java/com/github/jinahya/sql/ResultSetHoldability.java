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
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public enum ResultSetHoldability implements IntFieldEnum<ResultSetHoldability> {

    /**
     * Constant for {@link ResultSet#HOLD_CURSORS_OVER_COMMIT}({@value ResultSet#HOLD_CURSORS_OVER_COMMIT}).
     */
    HOLD_CURSORS_OVER_COMMIT(ResultSet.HOLD_CURSORS_OVER_COMMIT), // 1

    /**
     * Constant for {@link ResultSet#CLOSE_CURSORS_AT_COMMIT}({@value ResultSet#CLOSE_CURSORS_AT_COMMIT}).
     */
    CLOSE_CURSORS_AT_COMMIT(ResultSet.CLOSE_CURSORS_AT_COMMIT); // 2

    private ResultSetHoldability(final int fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public int fieldValueAsInt() {
        return fieldValue;
    }

    private final int fieldValue;
}
