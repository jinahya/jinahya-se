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

import static org.assertj.core.api.Assertions.assertThatCode;

class JinahyaDataInputStreamTest {

    @DisplayName("readIntLe()S")
    @Test
    void readShortLe_DoesNotThrow_() throws IOException {
        try (var input = new JinahyaDataInputStream(WhiteInputStream.getInstance())) {
            assertThatCode(() -> {
                final short value = input.readShortLe();
            }).doesNotThrowAnyException();
        }
    }

    @DisplayName("readIntLe()I")
    @Test
    void readIntLe_DoesNotThrow_() throws IOException {
        try (var input = new JinahyaDataInputStream(WhiteInputStream.getInstance())) {
            assertThatCode(() -> {
                final int value = input.readIntLe();
            }).doesNotThrowAnyException();
        }
    }

    @DisplayName("readLongLe()J")
    @Test
    void readLongLe_DoesNotThrow_() throws IOException {
        try (var input = new JinahyaDataInputStream(WhiteInputStream.getInstance())) {
            assertThatCode(() -> {
                final long value = input.readLongLe();
            }).doesNotThrowAnyException();
        }
    }
}
