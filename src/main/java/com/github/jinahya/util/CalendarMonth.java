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
 * Constants for {@link Calendar#MONTH}.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public enum CalendarMonth implements CalendarIntFieldEnum<CalendarMonth> {

    JANUARY(Calendar.JANUARY), // 0

    FEBRUARY(Calendar.FEBRUARY), // 1

    MARCH(Calendar.MARCH), // 2

    APRIL(Calendar.APRIL), // 3

    MAY(Calendar.MAY), // 4

    JUNE(Calendar.JUNE), // 5

    JULY(Calendar.JULY), // 6

    AUGUST(Calendar.AUGUST), // 7

    SEPTEMBER(Calendar.SEPTEMBER), // 8

    OCTOBER(Calendar.OCTOBER), // 9

    NOVEMBER(Calendar.NOVEMBER), // 10

    DECEMBER(Calendar.DECEMBER); // 11

    //UNDECIMBER(Calendar.UNDECIMBER); // 12

    /**
     * The target field of {@link Calendar} which this enum type is for.
     *
     * @see Calendar#MONTH
     */
    public static final int CALENDAR_FIELD = Calendar.MONTH;

    public static CalendarMonth fromCalendar(final Calendar calendar) {

        return CalendarIntFieldEnum.fromCalendar(
                CalendarMonth.class, calendar, CALENDAR_FIELD);
    }

    /**
     * Creates a new instance.
     *
     * @param fieldValue field value.
     */
    private CalendarMonth(final int fieldValue) {

        this.fieldValue = fieldValue;
    }

    @Override
    public int getFieldValue() {

        return fieldValue;
    }

    @Override
    public void set(final Calendar calendar) {

        CalendarIntFieldEnum.set(calendar, CALENDAR_FIELD, this);
    }

    private final int fieldValue;
}
