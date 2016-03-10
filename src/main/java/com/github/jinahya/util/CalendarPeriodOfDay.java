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

import java.util.Calendar;

/**
 * Constants for {@link Calendar#AM_PM}.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public enum CalendarPeriodOfDay
        implements CalendarIntFieldEnum<CalendarPeriodOfDay> {

    /**
     * Constant for {@link Calendar#AM}.
     */
    AM(Calendar.AM), // 0

    /**
     * Constant for {@link Calendar#PM}.
     */
    PM(Calendar.PM); // 1

    /**
     * The target field of {@link Calendar} which this enum type is for.
     *
     * @see Calendar#AM_PM
     */
    public static final int CALENDAR_FIELD = Calendar.AM_PM;

    /**
     * Returns the enum constant of this type with the specified calendar's
     * field value.
     *
     * @param calendar calendar
     *
     * @throws IllegalArgumentException if this enum type has no constant with
     * the specified calendar's field value.
     *
     * @return the enum constant with the specified calendar's field value.
     */
    public static CalendarPeriodOfDay fromCalendar(final Calendar calendar) {

        return CalendarIntFieldEnum.fromCalendar(
                CalendarPeriodOfDay.class, calendar, CALENDAR_FIELD);
    }

    /**
     * Creates a new instance.
     *
     * @param fieldValue field value.
     */
    private CalendarPeriodOfDay(final int fieldValue) {

        this.fieldValue = fieldValue;
    }

    @Override
    public int fieldValueAsInt() {

        return fieldValue;
    }

    @Override
    public void set(final Calendar calendar) {

        CalendarIntFieldEnum.set(calendar, CALENDAR_FIELD, this);
    }

    /**
     * field value.
     */
    private final int fieldValue;

}
