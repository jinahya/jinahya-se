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


package com.github.jinahya.lang.reflect.modifier;


import com.github.jinahya.lang.IntegerFieldEnumTest;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 * @param <E> enum type parameter
 */
public abstract class ModifierFieldEnumTest<E extends Enum<E> & ModifierFieldEnum<E>>
    extends IntegerFieldEnumTest<E> {


    public ModifierFieldEnumTest(final Class<E> enumType, final int modifiers) {

        super(enumType);

        this.modifiers = modifiers;
    }


    @Test
    public void modifiers_() {

        Assert.assertEquals(ModifierFieldEnums.modifiers(enumType), modifiers);
    }


    protected final int modifiers;


}

