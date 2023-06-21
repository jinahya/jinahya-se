package com.github.jinahya.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;

import static com.github.jinahya.time.TimeUtils.applyStartEndDateOf;
import static com.github.jinahya.time.TimeUtils.applyStartEndTimeOf;

@Slf4j
class TimeUtilsTest {

    @Nested
    class ApplyStartEndDateOfYearTest {

        @Test
        void __() {
            final Year year = Year.now();
            applyStartEndDateOf(year, (s, e) -> {
                log.debug("s: {}", s);
                log.debug("e: {}", e);
                return null;
            });
        }
    }

    @Nested
    class ApplyStartEndDateOfMonthTest {

        @Test
        void __() {
            final YearMonth month = YearMonth.now();
            applyStartEndDateOf(month, (s, e) -> {
                log.debug("s: {}", s);
                log.debug("e: {}", e);
                return null;
            });
        }
    }

    @Nested
    class ApplyStartEndTimeOfYearTest {

        @Test
        void __() {
            final Year year = Year.now();
            applyStartEndTimeOf(year, (s, e) -> {
                log.debug("s: {}", s);
                log.debug("e: {}", e);
                return null;
            });
        }
    }

    @Nested
    class ApplyStartEndTimeOfMonthTest {

        @Test
        void __() {
            final YearMonth month = YearMonth.now();
            applyStartEndTimeOf(month, (s, e) -> {
                log.debug("s: {}", s);
                log.debug("e: {}", e);
                return null;
            });
        }
    }

    @Nested
    class ApplyStartEndTimeOfDateTest {

        @Test
        void __() {
            final LocalDate date = LocalDate.now();
            applyStartEndTimeOf(date, (s, e) -> {
                log.debug("s: {}", s);
                log.debug("e: {}", e);
                return null;
            });
        }
    }
}
