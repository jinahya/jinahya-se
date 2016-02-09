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


package com.github.jinahya.lang.reflect;


import com.github.jinahya.lang.IntFieldEnum;
import com.github.jinahya.lang.IntFieldEnumTest;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 * @param <E> enum type parameter
 */
abstract class ModifierFieldEnumTest<E extends Enum<E> & ModifierFieldEnum<E>>
    extends IntFieldEnumTest<E> {


    public ModifierFieldEnumTest(final Class<E> enumType, final int modifiers) {

        super(enumType);

        this.all = modifiers;
    }


    @Test
    public void all() {

        int actual = 0;
        for (final int fieldValues : IntFieldEnum.fieldValues(enumType)) {
            actual |= fieldValues;
        }

        assertEquals(actual, all);
    }


    @Test
    public void is() {

        for (final E enumConstant : enumType.getEnumConstants()) {
            assertTrue(enumConstant.is(all));
        }
    }


    @Test
    public void add() {

        for (final E enumConstant : enumType.getEnumConstants()) {
            final int added = enumConstant.add(0);
            assertEquals(added, enumConstant.fieldValueAsInt());
            assertEquals(enumConstant.remove(added), 0);
        }
    }


    @Test
    public void remove() {

        for (final E enumConstant : enumType.getEnumConstants()) {
            final int removed = enumConstant.remove(all);
            assertFalse(enumConstant.is(removed));
            assertEquals(enumConstant.add(removed), all);
        }
    }


    protected final int all;

}

