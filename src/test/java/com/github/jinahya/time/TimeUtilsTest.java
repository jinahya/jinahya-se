package com.github.jinahya.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;

import static com.github.jinahya.time.TimeUtils.applyStartEndDateOf;
import static com.github.jinahya.time.TimeUtils.applyStartEndTimeOf;
import static java.time.LocalTime.MIDNIGHT;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class TimeUtilsTest {

    @Nested
    class ApplyStartEndDateOfYearTest {

        @Test
        void __() {
            final Year year = Year.now();
            applyStartEndDateOf(year, (s, e) -> {
                log.debug("y.s: {}", s);
                log.debug("y.e: {}", e);
                assertThat(s).isEqualTo(year.atDay(1));
                assertThat(e).isEqualTo(s.plusYears(1L).minusDays(1L));
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
                log.debug("m.s: {}", s);
                log.debug("m.e: {}", e);
                assertThat(s).isEqualTo(month.atDay(1));
                assertThat(e).isEqualTo(s.plusMonths(1L).minusDays(1L));
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
                log.debug("y.s: {}", s);
                log.debug("y.e: {}", e);
                assertThat(s).isEqualTo(year.atDay(1).atStartOfDay());
                assertThat(e).isEqualTo(s.plusYears(1L));
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
                log.debug("m.s: {}", s);
                log.debug("m.e: {}", e);
                assertThat(s.toLocalDate()).isEqualTo(month.atDay(1));
                assertThat(s.toLocalTime()).isEqualTo(MIDNIGHT);
                assertThat(e).isEqualTo(s.plusMonths(1L));
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
                log.debug("d.s: {}", s);
                log.debug("d.e: {}", e);
                assertThat(s.toLocalDate()).isEqualTo(date);
                assertThat(s.toLocalTime()).isEqualTo(MIDNIGHT);
                assertThat(e).isEqualTo(s.plusDays(1L));
                return null;
            });
        }
    }
}
