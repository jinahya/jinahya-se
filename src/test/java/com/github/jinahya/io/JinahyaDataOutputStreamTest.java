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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static java.util.concurrent.ThreadLocalRandom.current;
import static org.assertj.core.api.Assertions.assertThatCode;

class JinahyaDataOutputStreamTest {

    @DisplayName("writeShortLe(S)V")
    @Test
    void writeShortLe_DoesNotThrow_() throws IOException {
        try (var output = new JinahyaDataOutputStream(BlackOutputStream.getInstance())) {
            assertThatCode(() -> {
                output.writeShortLe(current().nextInt());
            }).doesNotThrowAnyException();
        }
    }

    @DisplayName("writeIntLe(I)V")
    @Test
    void writeIntLe_DoesNotThrow_() throws IOException {
        try (var output = new JinahyaDataOutputStream(BlackOutputStream.getInstance())) {
            assertThatCode(() -> {
                output.writeIntLe(current().nextInt());
            }).doesNotThrowAnyException();
        }
    }

    @DisplayName("writeLongLe(L)V")
    @Test
    void writeLongLe_DoesNotThrow_() throws IOException {
        try (var output = new JinahyaDataOutputStream(BlackOutputStream.getInstance())) {
            assertThatCode(() -> {
                output.writeLongLe(current().nextLong());
            }).doesNotThrowAnyException();
        }
    }
}
