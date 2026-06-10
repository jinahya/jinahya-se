package com.github.jinahya.io;

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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.mockito.ArgumentCaptor;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Slf4j
class JinahyaDataOutputTest {

    @DisplayName("writeShortLe(I)V")
    @Nested
    class WriteShortLeTest {

        @RepeatedTest(128)
        void __() throws IOException {
            // --------------------------------------------------------------------------------------------------- GIVEN
            final var output = spy(JinahyaDataOutput.class);
            final var value = ThreadLocalRandom.current().nextInt(65536);
            // ---------------------------------------------------------------------------------------------------- WHEN
            output.writeShortLe(value);
            // ---------------------------------------------------------------------------------------------------- THEN
            final var captor = ArgumentCaptor.forClass(int.class);
            verify(output, times(2)).writeByte(captor.capture());
            final var values = captor.getAllValues();
            assertThat(values.get(0)).isEqualTo(value);
            assertThat(values.get(1)).isEqualTo(value >> Byte.SIZE);
        }
    }

    @DisplayName("writeIntLe(I)V")
    @Nested
    class WriteIntLeTest {

        @RepeatedTest(128)
        void __() throws IOException {
            // --------------------------------------------------------------------------------------------------- GIVEN
            final var output = spy(JinahyaDataOutput.class);
            final var value = ThreadLocalRandom.current().nextInt();
            // ---------------------------------------------------------------------------------------------------- WHEN
            output.writeIntLe(value);
            // ---------------------------------------------------------------------------------------------------- THEN
            final var captor = ArgumentCaptor.forClass(int.class);
            verify(output, times(2)).writeShortLe(captor.capture());
            final var values = captor.getAllValues();
            assertThat(values.get(0)).isEqualTo(value);
            assertThat(values.get(1)).isEqualTo(value >> Short.SIZE);
        }
    }

    @DisplayName("writeLongLe(I)V")
    @Nested
    class WriteLongLeTest {

        @RepeatedTest(128)
        void __() throws IOException {
            // --------------------------------------------------------------------------------------------------- GIVEN
            final var output = spy(JinahyaDataOutput.class);
            final var value = ThreadLocalRandom.current().nextLong();
            // ---------------------------------------------------------------------------------------------------- WHEN
            output.writeLongLe(value);
            // ---------------------------------------------------------------------------------------------------- THEN
            final var captor = ArgumentCaptor.forClass(int.class);
            verify(output, times(2)).writeIntLe(captor.capture());
            final var values = captor.getAllValues();
            assertThat(values.get(0)).isEqualTo((int) value);
            assertThat(values.get(1)).isEqualTo((int) (value >> Integer.SIZE));
        }
    }
}
