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

import com.github.jinahya.lang.ValueEnum;

import java.awt.*;

/**
 * Constants defined in {@link Adjustable}.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public enum AdjustableOrientation
        implements ValueEnum.OfInt<AdjustableOrientation> {

    /**
     * A constant for {@link Adjustable#HORIZONTAL}.
     */
    HORIZONTAL(Adjustable.HORIZONTAL), // 0

    /**
     * A constant for {@link Adjustable#VERTICAL}.
     */
    VERTICAL(Adjustable.VERTICAL), // 1

    /**
     * A constant for {@link Adjustable#NO_ORIENTATION}.
     */
    NO_ORIENTATION(Adjustable.NO_ORIENTATION); // 2

    AdjustableOrientation(final int fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public int valueAsInt() {
        return fieldValue;
    }

    private final int fieldValue;
}
