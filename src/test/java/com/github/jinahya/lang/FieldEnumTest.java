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
package com.github.jinahya.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @param <E> enum type parameter
 * @param <F> field type parameter
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
@Slf4j
public abstract class FieldEnumTest<E extends Enum<E> & FieldEnum<E, F>, F> extends EnumTest<E> {

    public abstract static class OfInt<E extends Enum<E> & FieldEnum.OfInt<E>> extends EnumTest<E> {

        protected OfInt(final Class<E> enumClass) {
            super(enumClass);
        }

        @Test
        protected void fieldValueAsInt_NonNullUnique_() {
            final var fieldValues = new HashSet<Integer>();
            for (final var enumConstant : enumClass.getEnumConstants()) {
                final var fieldValue = enumConstant.fieldValueAsInt();
                assertThat(fieldValue)
                        .as("field value of %1$s", enumConstant)
                        .isNotIn(fieldValues);
            }
        }
    }

    public abstract static class OfLong<E extends Enum<E> & FieldEnum.OfLong<E>> extends EnumTest<E> {

        protected OfLong(final Class<E> enumClass) {
            super(enumClass);
        }

        @Test
        protected void fieldValueAsInt_NonNullUnique_() {
            final var fieldValues = new HashSet<Long>();
            for (final var enumConstant : enumClass.getEnumConstants()) {
                final var fieldValue = enumConstant.fieldValueAsLong();
                assertThat(fieldValue)
                        .as("field value of %1$s", enumConstant)
                        .isNotIn(fieldValues);
            }
        }
    }

    public abstract static class OfFloat<E extends Enum<E> & FieldEnum.OfFloat<E>> extends EnumTest<E> {

        protected OfFloat(final Class<E> enumClass) {
            super(enumClass);
        }

        @Test
        protected void fieldValueAsInt_NonNullUnique_() {
            final var fieldValues = new HashSet<Float>();
            for (final var enumConstant : enumClass.getEnumConstants()) {
                final var fieldValue = enumConstant.fieldValueAsFloat();
                assertThat(fieldValue)
                        .as("field value of %1$s", enumConstant)
                        .isNotIn(fieldValues);
            }
        }
    }

    protected FieldEnumTest(final Class<E> enumClass, final Class<F> fieldClass) {
        super(enumClass);
        this.fieldClass = Objects.requireNonNull(fieldClass, "fieldClass is null");
    }

    @Test
    protected void fieldValue_NonNullUnique_() {
        final var fieldValues = new HashSet<F>();
        for (final var enumConstant : enumClass.getEnumConstants()) {
            final var fieldValue = enumConstant.fieldValue();
            assertThat(fieldValue)
                    .as("field value of %1$s", enumConstant)
                    .isNotNull()
                    .isNotIn(fieldValues);
        }
    }

    /**
     * field type.
     */
    protected final Class<F> fieldClass;
}
