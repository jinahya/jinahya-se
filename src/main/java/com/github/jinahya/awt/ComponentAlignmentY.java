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


package com.github.jinahya.awt;


import com.github.jinahya.lang.FloatFieldEnum;
import java.awt.Component;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public enum ComponentAlignmentY implements FloatFieldEnum<ComponentAlignmentY> {


    /**
     * Constant for {@link Component#TOP_ALIGNMENT}.
     */
    TOP_ALIGNMENT(Component.TOP_ALIGNMENT), // 0.0f

    /**
     * Constant for {@link Component#CENTER_ALIGNMENT}.
     */
    CENTER_ALIGHMENT(Component.CENTER_ALIGNMENT), // 0.5f

    /**
     * Constant for {@link Component#BOTTOM_ALIGNMENT}.
     */
    BOTTOM_ALIGHNMENT(Component.BOTTOM_ALIGNMENT); // 1.0f


    private ComponentAlignmentY(final float fieldValue) {

        this.fieldValue = fieldValue;
    }


    @Override
    public Float fieldValue() {

        return fieldValue;
    }


    /**
     * field value.
     */
    private final float fieldValue;


}

