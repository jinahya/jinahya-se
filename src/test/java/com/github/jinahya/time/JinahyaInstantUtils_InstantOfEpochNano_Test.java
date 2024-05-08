package com.github.jinahya.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Slf4j
class JinahyaInstantUtils_InstantOfEpochNano_Test {

    private static LongStream epochNanoStream() {
        return LongStream.concat(
                LongStream.of(
                        -1L,
                        0L,
                        +1L,
                        Long.MIN_VALUE,
                        Long.MAX_VALUE
                ),
                IntStream.range(0, 8).mapToLong(i -> {
                    return ThreadLocalRandom.current().nextLong(-1000000000, 1000000000);
                })
        );
    }

    @MethodSource({"epochNanoStream"})
    @ParameterizedTest
    void __(final long epochNano) {
    }
}
