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

import com.github.jinahya.lang.IntFieldEnum;

import java.util.Calendar;

/**
 * @param <E> enum type parameter
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public interface CalendarIntFieldEnum<E extends Enum<E>>
        extends IntFieldEnum<E> {

    static <E extends Enum<E> & CalendarIntFieldEnum<E>> E fromCalendar(
            final Class<E> enumType, final Calendar calendar, final int field) {

        if (enumType == null) {
            throw new NullPointerException("null enumType");
        }

        if (calendar == null) {
            throw new NullPointerException("null calendar");
        }

        final int fieldValue = calendar.get(field);

        return IntFieldEnum.fromIntFieldValue(enumType, fieldValue);
    }

    static void set(final Calendar calendar, final int field,
                    final CalendarIntFieldEnum<?> constant) {

        calendar.set(field, constant.fieldValueAsInt());
    }

    void set(final Calendar calendar);
}
