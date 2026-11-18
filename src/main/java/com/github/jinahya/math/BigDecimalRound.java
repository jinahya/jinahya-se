/*
 * Copyright 2014 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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
package com.github.jinahya.math;

/*-
 * #%L
 * jinahya-se
 * %%
 * Copyright (C) 2025 - 2026 Jinahya
 * %%
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
 * #L%
 */

import com.github.jinahya.lang.IntFieldEnum;

import java.math.BigDecimal;

/**
 * Constants for legacy rounding mode fields of {@link BigDecimal}.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public enum BigDecimalRound implements IntFieldEnum<BigDecimalRound> {

    /**
     * A constant for {@link BigDecimal#ROUND_UP}.
     */
    UP(BigDecimal.ROUND_UP), // 0

    /**
     * A constant for {@link BigDecimal#ROUND_DOWN}.
     */
    DOWN(BigDecimal.ROUND_DOWN), // 1

    /**
     * A constant for {@link BigDecimal#ROUND_CEILING}.
     */
    CEILING(BigDecimal.ROUND_CEILING), // 2

    /**
     * A constant for {@link BigDecimal#ROUND_FLOOR}.
     */
    FLOOR(BigDecimal.ROUND_FLOOR), //3

    /**
     * A constant for {@link BigDecimal#ROUND_HALF_UP}.
     */
    HALF_UP(BigDecimal.ROUND_HALF_UP), // 4

    /**
     * A constant for {@link BigDecimal#ROUND_HALF_DOWN}.
     */
    HALF_DOWN(BigDecimal.ROUND_HALF_DOWN), // 5

    /**
     * A constant for {@link BigDecimal#ROUND_HALF_EVEN}.
     */
    HALF_EVEN(BigDecimal.ROUND_HALF_EVEN), // 6

    /**
     * A constant for {@link BigDecimal#ROUND_UNNECESSARY}.
     */
    UNNECESSARY(BigDecimal.ROUND_UNNECESSARY); // 7

    private BigDecimalRound(final int fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public int getFieldValue() {
        return fieldValue;
    }

    private final int fieldValue;
}
