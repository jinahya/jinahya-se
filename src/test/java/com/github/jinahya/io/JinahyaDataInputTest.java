package com.github.jinahya.io;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class JinahyaDataInputTest {

    @DisplayName("readShortLe()S")
    @Nested
    class ReadShortLeTest {

        @RepeatedTest(128)
        void __() throws IOException {
            // --------------------------------------------------------------------------------------------------- GIVEN
            final var input = spy(JinahyaDataInput.class);
            final var bytes = new byte[Short.BYTES];
            ThreadLocalRandom.current().nextBytes(bytes);
            given(input.readByte()).willReturn(
                    bytes[0],
                    bytes[1]
            );
            // ---------------------------------------------------------------------------------------------------- WHEN
            final var value = input.readShortLe();
            // ---------------------------------------------------------------------------------------------------- THEN
            assertThat(value)
                    .isEqualTo((short) (
                            (bytes[1] << Byte.SIZE) |
                            (bytes[0] & 0xFF)
                    ));
            verify(input, times(2)).readByte();
        }
    }

    @DisplayName("readIntLe()I")
    @Nested
    class ReadIntLeTest {

        @RepeatedTest(128)
        void __() throws IOException {
            // --------------------------------------------------------------------------------------------------- GIVEN
            final var input = spy(JinahyaDataInput.class);
            final var bytes = new byte[Integer.BYTES];
            ThreadLocalRandom.current().nextBytes(bytes);
            given(input.readByte()).willReturn(
                    bytes[0],
                    bytes[1],
                    bytes[2],
                    bytes[3]
            );
            // ---------------------------------------------------------------------------------------------------- WHEN
            final var value = input.readIntLe();
            // ---------------------------------------------------------------------------------------------------- THEN
            assertThat(value)
                    .isEqualTo(
                            (bytes[3] << 24) |
                            ((bytes[2] & 0xFF) << 16) |
                            ((bytes[1] & 0xFF) << 8) |
                            (bytes[0] & 0xFF)
                    );
            verify(input, times(2))
                    .readShortLe();
        }
    }

    @DisplayName("readLongLe()J")
    @Nested
    class ReadLongLeTest {

        @RepeatedTest(128)
        void __() throws IOException {
            // --------------------------------------------------------------------------------------------------- GIVEN
            final var input = spy(JinahyaDataInput.class);
            final var bytes = new byte[Long.BYTES];
            ThreadLocalRandom.current().nextBytes(bytes);
            given(input.readByte()).willReturn(
                    bytes[0],
                    bytes[1],
                    bytes[2],
                    bytes[3],
                    bytes[4],
                    bytes[5],
                    bytes[6],
                    bytes[7]
            );
            // ---------------------------------------------------------------------------------------------------- WHEN
            final var value = input.readLongLe();
            // ---------------------------------------------------------------------------------------------------- THEN
            assertThat(value)
                    .isEqualTo(
                            ((bytes[7] & 0xFFL) << 56) |
                            ((bytes[6] & 0xFFL) << 48) |
                            ((bytes[5] & 0xFFL) << 40) |
                            ((bytes[4] & 0xFFL) << 32) |
                            ((bytes[3] & 0xFFL) << 24) |
                            ((bytes[2] & 0xFF) << 16) |
                            ((bytes[1] & 0xFF) << 8) |
                            (bytes[0] & 0xFF)
                    );
            verify(input, times(2)).readIntLe();
        }
    }
}
