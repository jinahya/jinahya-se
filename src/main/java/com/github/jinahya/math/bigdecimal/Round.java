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


package com.github.jinahya.math.bigdecimal;


import com.github.jinahya.lang.IntegerFieldEnum;
import java.math.BigDecimal;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public enum Round implements IntegerFieldEnum<Round> {


    UP(BigDecimal.ROUND_UP), // 0
    DOWN(BigDecimal.ROUND_DOWN), // 1
    CEILING(BigDecimal.ROUND_CEILING), // 2
    FLOOR(BigDecimal.ROUND_FLOOR), //3
    HALF_UP(BigDecimal.ROUND_HALF_UP), // 4
    HALF_DOWN(BigDecimal.ROUND_HALF_DOWN), // 5
    HALF_EVEN(BigDecimal.ROUND_HALF_EVEN), // 6
    UNNECESSARY(BigDecimal.ROUND_UNNECESSARY);// 7


    private Round(final int fieldValue) {

        this.fieldValue = fieldValue;
    }


    @Override
    public Integer fieldValue() {

        return fieldValue;
    }


    private final int fieldValue;


}
