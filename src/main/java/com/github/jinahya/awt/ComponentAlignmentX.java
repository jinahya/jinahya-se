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

import com.github.jinahya.lang.FloatFieldEnum;
import java.awt.Component;

/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public enum ComponentAlignmentX implements FloatFieldEnum<ComponentAlignmentX> {

    /**
     * Constant for {@link Component#LEFT_ALIGNMENT}.
     */
    LEFT_ALIGNMENT(Component.LEFT_ALIGNMENT), // 0.0f
    /**
     * Constant for {@link Component#CENTER_ALIGNMENT}.
     */
    CENTER_ALIGHMENT(Component.CENTER_ALIGNMENT), // 0.5f
    /**
     * Constant for {@link Component#RIGHT_ALIGNMENT}.
     */
    RIGHT_ALIGHNMENT(Component.RIGHT_ALIGNMENT); // 1.0f

    private ComponentAlignmentX(final float fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public float fieldValueAsFloat() {
        return fieldValue;
    }

    private final float fieldValue;

}
