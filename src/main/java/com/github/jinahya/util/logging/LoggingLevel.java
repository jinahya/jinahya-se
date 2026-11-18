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
package com.github.jinahya.util.logging;

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

import com.github.jinahya.lang.ValueEnum;
import com.github.jinahya.lang.ValueEnums;

import java.util.logging.Level;

/**
 * Constants for {@link Level}s, each mapped to the level's int value.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public enum LoggingLevel implements ValueEnum<LoggingLevel, Integer> {

    /**
     * A constant for {@link Level#OFF}.
     */
    OFF(Level.OFF),

    /**
     * A constant for {@link Level#SEVERE}.
     */
    SEVERE(Level.SEVERE),

    /**
     * A constant for {@link Level#WARNING}.
     */
    WARNING(Level.WARNING),

    /**
     * A constant for {@link Level#INFO}.
     */
    INFO(Level.INFO),

    /**
     * A constant for {@link Level#CONFIG}.
     */
    CONFIG(Level.CONFIG),

    /**
     * A constant for {@link Level#FINE}.
     */
    FINE(Level.FINE),

    /**
     * A constant for {@link Level#FINER}.
     */
    FINER(Level.FINER),

    /**
     * A constant for {@link Level#FINEST}.
     */
    FINEST(Level.FINEST),

    /**
     * A constant for {@link Level#ALL}.
     */
    ALL(Level.ALL);

    /**
     * Returns the enum constant of this type with the specified field value.
     *
     * @param fieldValue field value
     * @return the enum constant with the specified field value.
     * @throws IllegalArgumentException if this enum type has no constant with the specified field value.
     */
    public static LoggingLevel fromFieldValue(final int fieldValue) {

        return ValueEnums.fromFieldValue(LoggingLevel.class, fieldValue);
    }

    /**
     * Returns the enum constant of this type with the specified level's int value.
     *
     * @param level level
     * @return the enum constant with the specified level's field value.
     * @throws IllegalArgumentException if this enum type has no constant with the specified level's int value.
     */
    public static LoggingLevel fromLevel(final Level level) {

        if (level == null) {
            throw new NullPointerException("level");
        }

        return fromFieldValue(level.intValue());
    }

    /**
     * Returns an array containing the field values of this enum type, in order they are declared.
     *
     * @return an array containing the fields values of this enum type, in the order they are declared
     */
    public Integer[] fieldValues() {

        return ValueEnums.fieldValues(LoggingLevel.class, int.class);
    }

    private LoggingLevel(final int fieldValue) {

        this.fieldValue = fieldValue;
    }

    private LoggingLevel(final Level level) {

        this(level.intValue());
    }

    @Override
    public Integer value() {

        return fieldValue;
    }

    /**
     * field value.
     */
    private final int fieldValue;
}
