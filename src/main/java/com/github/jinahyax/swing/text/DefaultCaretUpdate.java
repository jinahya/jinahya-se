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
package com.github.jinahyax.swing.text;

import com.github.jinahya.lang.IntFieldEnum;

import javax.swing.text.DefaultCaret;

/**
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public enum DefaultCaretUpdate implements IntFieldEnum<DefaultCaretUpdate> {

    WHEN_ON_EDT(DefaultCaret.UPDATE_WHEN_ON_EDT),
    NEVER(DefaultCaret.NEVER_UPDATE),
    ALWAYS(DefaultCaret.ALWAYS_UPDATE);

    private DefaultCaretUpdate(final int fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public int getFieldValue() {
        return fieldValue;
    }

    private final int fieldValue;
}
