package com.github.jinahya.time.chrono;

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
