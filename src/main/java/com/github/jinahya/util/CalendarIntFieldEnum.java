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

import java.util.Calendar;

/**
 * An {@link IntFieldEnum} whose field values correspond to values of a {@link Calendar} field.
 *
 * @param <E> enum type parameter
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public interface CalendarIntFieldEnum<E extends Enum<E>>
        extends IntFieldEnum<E> {

    /**
     * Returns the constant of specified enum type matching the value of specified field of specified calendar.
     *
     * @param <E>      enum type parameter
     * @param enumType the enum type.
     * @param calendar the calendar whose field value is read.
     * @param field    the calendar field.
     * @return the constant of {@code enumType} whose field value equals the value of {@code field} in {@code calendar}.
     * @throws NullPointerException     if {@code enumType} or {@code calendar} is {@code null}.
     * @throws IllegalArgumentException if no constant matches the field value.
     */
    static <E extends Enum<E> & CalendarIntFieldEnum<E>> E fromCalendar(
            final Class<E> enumType, final Calendar calendar, final int field) {

        if (enumType == null) {
            throw new NullPointerException("null enumType");
        }

        if (calendar == null) {
            throw new NullPointerException("null calendar");
        }

        final int fieldValue = calendar.get(field);

        return IntFieldEnum.valueOfFieldValue(enumType, fieldValue);
    }

    /**
     * Sets the field value of specified constant to specified field of specified calendar.
     *
     * @param calendar the calendar to modify.
     * @param field    the calendar field to set.
     * @param constant the constant whose field value is set.
     */
    static void set(final Calendar calendar, final int field,
                    final CalendarIntFieldEnum<?> constant) {

        calendar.set(field, constant.getFieldValue());
    }

    /**
     * Sets this constant's field value to the corresponding field of specified calendar.
     *
     * @param calendar the calendar to modify.
     */
    void set(final Calendar calendar);
}
