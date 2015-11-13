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
public class JinahyaResultSet {


    public static enum Concurrency implements IntFieldEnum<Concurrency> {

        /**
         * Constant for
         * {@link ResultSet#CONCUR_READ_ONLY}({@value ResultSet#CONCUR_READ_ONLY}).
         */
        CONCUR_READ_ONLY(ResultSet.CONCUR_READ_ONLY), // 1007
        /**
         * Constant for
         * {@link ResultSet#CONCUR_UPDATABLE}({@value ResultSet#CONCUR_UPDATABLE}).
         */
        CONCUR_UPDATABLE(ResultSet.CONCUR_UPDATABLE); // 1008// 1008


        private Concurrency(final int fieldValue) {

            this.fieldValue = fieldValue;
        }


        @Override
        public int fieldValueAsInt() {

            return fieldValue;
        }


        private final int fieldValue;

    }


    public static enum FetchDirection implements IntFieldEnum<FetchDirection> {

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
        FETCH_UNKNOWN(ResultSet.FETCH_UNKNOWN); // 1002// 1002


        private FetchDirection(final int fieldValue) {

            this.fieldValue = fieldValue;
        }


        @Override
        public int fieldValueAsInt() {

            return fieldValue;
        }


        private final int fieldValue;

    }


    public static enum Holdability implements IntFieldEnum<Holdability> {

        /**
         * Constant for
         * {@link ResultSet#HOLD_CURSORS_OVER_COMMIT}({@value ResultSet#HOLD_CURSORS_OVER_COMMIT}).
         */
        HOLD_CURSORS_OVER_COMMIT(ResultSet.HOLD_CURSORS_OVER_COMMIT),
        /**
         * Constant for
         * {@link ResultSet#CLOSE_CURSORS_AT_COMMIT}({@value ResultSet#CLOSE_CURSORS_AT_COMMIT}).
         */
        CLOSE_CURSORS_AT_COMMIT(ResultSet.CLOSE_CURSORS_AT_COMMIT); // 2// 2


        private Holdability(final int fieldValue) {

            this.fieldValue = fieldValue;
        }


        @Override
        public int fieldValueAsInt() {

            return fieldValue;
        }


        private final int fieldValue;

    }


    public static enum Type implements IntFieldEnum<Type> {

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
        TYPE_SCROLL_SENSITIVE(ResultSet.TYPE_SCROLL_SENSITIVE); // 1005// 1005


        private Type(final int fieldValue) {

            this.fieldValue = fieldValue;
        }


        @Override
        public int fieldValueAsInt() {

            return fieldValue;
        }


        private final int fieldValue;

    }


    private JinahyaResultSet() {

        super();
    }


}

