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


package com.github.jinahya.awt;


import com.github.jinahya.lang.IntFieldEnum;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class JinahyaAdjustable {


    public static enum Orientation implements IntFieldEnum<Orientation> {

        HORIZONTAL(java.awt.Adjustable.HORIZONTAL),
        VERTICAL(java.awt.Adjustable.VERTICAL),
        NO_OPERATIONAL(java.awt.Adjustable.NO_ORIENTATION);


        private Orientation(final int fieldValue) {

            this.fieldValue = fieldValue;
        }


        @Override
        public int fieldValueAsInt() {

            return fieldValue;
        }


        private final int fieldValue;

    }


    private JinahyaAdjustable() {

        super();
    }


}

