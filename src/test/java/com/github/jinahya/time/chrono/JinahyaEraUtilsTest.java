package com.github.jinahya.time.chrono;

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

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.chrono.Chronology;
import java.time.temporal.ChronoField;

@Slf4j
class JinahyaEraUtilsTest {

    @Nested
    class FilterSupportedTest {

        @Test
        void __() {
            for (var chronology : Chronology.getAvailableChronologies()) {
                log.debug("chronology: {}", chronology);
                for (var era : chronology.eras()) {
                    log.debug("\tera: {}", era);
                    for (var field : ChronoField.values()) {
                        final var supported = era.isSupported(field);
                        log.debug("\t\tfield: {}, supported: {}", field, supported);
                    }
                }
            }
        }
    }
}
