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

import java.util.Calendar;

/**
 * Constants for {@link Calendar#MONTH}.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public enum CalendarMonth implements CalendarIntFieldEnum<CalendarMonth> {

    /**
     * Constant for {@link Calendar#JANUARY}.
     */
    JANUARY(Calendar.JANUARY), // 0

    /**
     * Constant for {@link Calendar#FEBRUARY}.
     */
    FEBRUARY(Calendar.FEBRUARY), // 1

    /**
     * Constant for {@link Calendar#MARCH}.
     */
    MARCH(Calendar.MARCH), // 2

    /**
     * Constant for {@link Calendar#APRIL}.
     */
    APRIL(Calendar.APRIL), // 3

    /**
     * Constant for {@link Calendar#MAY}.
     */
    MAY(Calendar.MAY), // 4

    /**
     * Constant for {@link Calendar#JUNE}.
     */
    JUNE(Calendar.JUNE), // 5

    /**
     * Constant for {@link Calendar#JULY}.
     */
    JULY(Calendar.JULY), // 6

    /**
     * Constant for {@link Calendar#AUGUST}.
     */
    AUGUST(Calendar.AUGUST), // 7

    /**
     * Constant for {@link Calendar#SEPTEMBER}.
     */
    SEPTEMBER(Calendar.SEPTEMBER), // 8

    /**
     * Constant for {@link Calendar#OCTOBER}.
     */
    OCTOBER(Calendar.OCTOBER), // 9

    /**
     * Constant for {@link Calendar#NOVEMBER}.
     */
    NOVEMBER(Calendar.NOVEMBER), // 10

    /**
     * Constant for {@link Calendar#DECEMBER}.
     */
    DECEMBER(Calendar.DECEMBER); // 11

    //UNDECIMBER(Calendar.UNDECIMBER); // 12

    /**
     * The target field of {@link Calendar} which this enum type is for.
     *
     * @see Calendar#MONTH
     */
    public static final int CALENDAR_FIELD = Calendar.MONTH;

    /**
     * Returns the constant matching the {@link Calendar#MONTH} field value of specified calendar.
     *
     * @param calendar the calendar whose month is read.
     * @return the constant matching the month of {@code calendar}.
     * @throws NullPointerException if {@code calendar} is {@code null}.
     */
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
