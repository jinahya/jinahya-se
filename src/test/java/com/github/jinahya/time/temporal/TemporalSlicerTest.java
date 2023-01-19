package com.github.jinahya.time.temporal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.UnaryOperator;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class TemporalSlicerTest {

    @DisplayName("LocalDate")
    @Nested
    class LocalDateTest {

        @Test
        void _Day_1Day() {
            final LocalDate startInclusive = LocalDate.now();
            final LocalDate endExclusive = startInclusive.plusDays(1L);
            log.debug("startInclusive: {}", startInclusive);
            log.debug("endInclusive: {}", endExclusive);
            TemporalSlicer.slice(
                    startInclusive,
                    endExclusive,
                    UnaryOperator.identity(),
                    d -> d.plusDays(1L),
                    UnaryOperator.identity(),
                    s -> {
                        log.debug("slice: {}", s);
                        assertThat(s.getStartInclusive()).isAfterOrEqualTo(startInclusive);
                        assertThat(s.getEndExclusive()).isBeforeOrEqualTo(endExclusive);
                    }
            );
            log.debug("startInclusive: {}", startInclusive);
            log.debug("endInclusive: {}", endExclusive);
        }

        @Test
        void _Day_30Days() {
            final LocalDate startInclusive = LocalDate.now();
            final LocalDate endExclusive = startInclusive.plusDays(30L);
            log.debug("startInclusive: {}", startInclusive);
            log.debug("endInclusive: {}", endExclusive);
            TemporalSlicer.slice(
                    startInclusive,
                    endExclusive,
                    UnaryOperator.identity(),
                    d -> d.plusDays(1L),
                    UnaryOperator.identity(),
                    s -> {
                        log.debug("slice: {}", s);
                        assertThat(s.getStartInclusive()).isAfterOrEqualTo(startInclusive);
                        assertThat(s.getEndExclusive()).isBeforeOrEqualTo(endExclusive);
                    }
            );
            log.debug("startInclusive: {}", startInclusive);
            log.debug("endInclusive: {}", endExclusive);
        }
    }

    @Nested
    class LocalDateTimeTest {

        @Test
        void _Hour_30Days() {
            final LocalDateTime startInclusive = LocalDateTime.now().withNano(0);
            final LocalDateTime endExclusive = startInclusive.plusDays(30L).plusMinutes(1L);
            log.debug("startInclusive: {}", startInclusive);
            log.debug("endInclusive: {}", endExclusive);
            TemporalSlicer.slice(
                    startInclusive,
                    endExclusive,
                    UnaryOperator.identity(),
                    d -> d.plusHours(1L),
                    UnaryOperator.identity(),
                    s -> {
                        log.debug("slice: {}", s);
                        assertThat(s.getStartInclusive()).isAfterOrEqualTo(startInclusive);
                        assertThat(s.getEndExclusive()).isBeforeOrEqualTo(endExclusive);
                    }
            );
            log.debug("startInclusive: {}", startInclusive);
            log.debug("endInclusive: {}", endExclusive);
        }
    }
}
