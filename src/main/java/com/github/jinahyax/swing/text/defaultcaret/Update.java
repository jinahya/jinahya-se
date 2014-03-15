/*
 * Copyright 2014 Jin Kwon <onacit at gmail.com>.
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


package com.github.jinahyax.swing.text.defaultcaret;


import com.github.jinahya.lang.IntegerFieldEnum;
import javax.swing.text.DefaultCaret;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public enum Update implements IntegerFieldEnum<Update> {


    WHEN_ON_EDT(DefaultCaret.UPDATE_WHEN_ON_EDT),
    NEVER(DefaultCaret.NEVER_UPDATE),
    ALWAYS(DefaultCaret.ALWAYS_UPDATE);


    private Update(final int fieldValue) {

        this.fieldValue = fieldValue;
    }


    @Override
    public Integer fieldValue() {

        return fieldValue;
    }


    private final int fieldValue;


}
