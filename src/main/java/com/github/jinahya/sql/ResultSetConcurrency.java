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
public enum ResultSetConcurrency implements IntFieldEnum<ResultSetConcurrency> {

    /**
     * Constant for
     * {@link ResultSet#CONCUR_READ_ONLY}({@value ResultSet#CONCUR_READ_ONLY}).
     */
    CONCUR_READ_ONLY(ResultSet.CONCUR_READ_ONLY), // 1007

    /**
     * Constant for
     * {@link ResultSet#CONCUR_UPDATABLE}({@value ResultSet#CONCUR_UPDATABLE}).
     */
    CONCUR_UPDATABLE(ResultSet.CONCUR_UPDATABLE) // 1008
    ;


    private ResultSetConcurrency(final int fieldValue) {

        this.fieldValue = fieldValue;
    }


    @Override
    public int fieldValueAsInt() {

        return fieldValue;
    }


    private final int fieldValue;

}

