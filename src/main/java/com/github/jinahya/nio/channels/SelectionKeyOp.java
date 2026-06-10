package com.github.jinahya.nio.channels;

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

import java.nio.channels.SelectionKey;

/**
 * Constants for fields of {@code OP_...} defined in {@link SelectionKey} class.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public enum SelectionKeyOp
        implements IntFieldEnum<SelectionKeyOp> {

    /**
     * A constant for {@link SelectionKey#OP_READ} field.
     */
    OP_READ(SelectionKey.OP_READ),

    /**
     * A constant for {@link SelectionKey#OP_WRITE} field.
     */
    OP_WRITE(SelectionKey.OP_WRITE),

    /**
     * A constant for {@link SelectionKey#OP_CONNECT} field.
     */
    OP_CONNECT(SelectionKey.OP_CONNECT),

    /**
     * A constant for {@link SelectionKey#OP_ACCEPT} field.
     */
    OP_ACCEPT(SelectionKey.OP_ACCEPT);

    public static SelectionKeyOp valueOfFieldValue(final int fieldValue) {
        return IntFieldEnum.valueOfFieldValue(SelectionKeyOp.class, fieldValue);
    }

    SelectionKeyOp(final int fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public int getFieldValue() {
        return fieldValue;
    }

    private final int fieldValue;
}
