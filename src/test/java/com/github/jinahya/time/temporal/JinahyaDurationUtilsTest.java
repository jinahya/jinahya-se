package com.github.jinahya.time.temporal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoField;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class JinahyaDurationUtilsTest {

    @Test
    void toDays__() {
        // ------------------------------------------------------------------------------------------------------- given
        final var days = ThreadLocalRandom.current().nextLong(10000);
        final var hours = ThreadLocalRandom.current().nextLong(ChronoField.HOUR_OF_DAY.range().getMaximum() + 1);
        final var minutes = ThreadLocalRandom.current().nextLong(ChronoField.MINUTE_OF_HOUR.range().getMaximum() + 1);
        final var seconds = ThreadLocalRandom.current().nextLong(ChronoField.SECOND_OF_MINUTE.range().getMaximum() + 1);
        final var nanos = ThreadLocalRandom.current().nextLong(ChronoField.NANO_OF_SECOND.range().getMaximum() + 1);
        final var duration = Duration.ofDays(days)
                .plus(Duration.ofHours(hours))
                .plus(Duration.ofMinutes(minutes))
                .plus(Duration.ofSeconds(seconds))
                .plus(Duration.ofNanos(nanos));
        log.debug("duration.: {}, units: {}", duration, duration.getUnits());
        // -------------------------------------------------------------------------------------------------------------
        JinahyaDurationUtils.applyDays(duration, qd -> rd -> {
            log.debug("days: {}", qd);
            JinahyaDurationUtils.applyHours(rd, qh -> rh -> {
                log.debug("hours: {}", qh);
                assertThat(qh)
                        .isEqualTo(hours)
                        .isLessThanOrEqualTo(ChronoField.HOUR_OF_DAY.range().getMaximum());
                JinahyaDurationUtils.applyMinutes(rh, qm -> rm -> {
                    log.debug("minutes: {}", qm);
                    assertThat(qm)
                            .isEqualTo(minutes)
                            .isLessThanOrEqualTo(ChronoField.MINUTE_OF_DAY.range().getMaximum());
                    JinahyaDurationUtils.applySeconds(rm, qs -> rs -> {
                        log.debug("seconds: {}", qs);
                        log.debug("nanos: {}", rs);
                        assertThat(qs)
                                .isEqualTo(seconds)
                                .isLessThanOrEqualTo(ChronoField.SECOND_OF_MINUTE.range().getMaximum());
                        assertThat(rs.toNanos())
                                .isEqualTo(nanos)
                                .isLessThanOrEqualTo(ChronoField.NANO_OF_SECOND.range().getMaximum());
                        return null;
                    });
                    return null;
                });
                return null;
            });
            return null;
        });
    }

    @Test
    void __() {
        // ------------------------------------------------------------------------------------------------------- given
        final var days = ThreadLocalRandom.current().nextLong(10);
        final var hours = ThreadLocalRandom.current().nextLong(ChronoField.HOUR_OF_DAY.range().getMaximum() + 1);
        final var minutes = ThreadLocalRandom.current().nextLong(ChronoField.MINUTE_OF_HOUR.range().getMaximum() + 1);
        final var seconds = ThreadLocalRandom.current().nextLong(ChronoField.SECOND_OF_MINUTE.range().getMaximum() + 1);
        final var nanos = ThreadLocalRandom.current().nextLong(ChronoField.NANO_OF_SECOND.range().getMaximum() + 1);
        final var duration = Duration.ofDays(days)
                .plus(Duration.ofHours(hours))
                .plus(Duration.ofMinutes(minutes))
                .plus(Duration.ofSeconds(seconds))
                .plus(Duration.ofNanos(nanos));
        log.debug("nanos: {}", duration.toNanos());
        // --------------------------------------------------------------------------------------------------- when/then
        JinahyaDurationUtils.applyUnitValues(duration, d -> h -> m -> s -> n -> {
            log.debug("days: {}", d);
            log.debug("hours: {}", h);
            log.debug("minutes: {}", m);
            log.debug("seconds: {}", s);
            log.debug("nanos: {}", n);
            assertThat(d).isEqualTo(days);
            assertThat(h).isEqualTo(hours);
            assertThat(m).isEqualTo(minutes);
            assertThat(s).isEqualTo(seconds);
            assertThat(n).isEqualTo(nanos);
            return null;
        });
    }
}