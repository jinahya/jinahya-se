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


package com.github.jinahya.lang.reflect.modifier;


import java.lang.reflect.Modifier;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public enum InterfaceModifier implements ModifierFieldEnum<InterfaceModifier> {


    PUBLIC(Modifier.PUBLIC), // 1
    PRIVATE(Modifier.PRIVATE),//2
    PROTECTED(Modifier.PROTECTED), // 4
    STATIC(Modifier.STATIC), // 8
    ABSTRACT(Modifier.ABSTRACT), // 1024
    STRICT(Modifier.STRICT); // 2048


    private InterfaceModifier(final int fieldValue) {

        this.fieldValue = fieldValue;
    }


    @Override
    public Integer fieldValue() {

        return fieldValue;
    }

    @Override
    public boolean is(final int modifiers) {

        return ModifierFieldEnums.isAll(modifiers, this);
    }

    @Override
    public int add(int modifers) {

        return ModifierFieldEnums.add(modifers, this);
    }


    @Override
    public int remove(int modifers) {

        return ModifierFieldEnums.remove(modifers, this);
    }


    private final int fieldValue;


}

