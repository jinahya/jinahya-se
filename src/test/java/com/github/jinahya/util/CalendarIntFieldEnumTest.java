/*
 * Copyright 2013 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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
package com.github.jinahya.util;

import com.github.jinahya.lang.IntFieldEnumTest;
import org.testng.annotations.Test;

import java.util.Calendar;

/**
 * @param <E>
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public abstract class CalendarIntFieldEnumTest<E extends Enum<E> & CalendarIntFieldEnum<E>>
        extends IntFieldEnumTest<E> {

    public CalendarIntFieldEnumTest(final Class<E> enumType,
                                    final int calendarField) {

        super(enumType);

        this.calendarField = calendarField;
    }

    @Test
    public void set() {

        final Calendar calendar = Calendar.getInstance();

        for (final E enumConstant : enumType.getEnumConstants()) {
            enumConstant.set(calendar);
        }
    }

    protected final int calendarField;
}
