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

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class WhiteInputStreamTest {

    @Test
    void read_Between0255_() throws IOException {
        for (int i = 0; i < 1024; i++) {
            assertThat(WhiteInputStream.getInstance().read())
                    .isBetween(0, 255);
        }
    }

    @Test
    void close_Idempotent_() throws IOException {
        WhiteInputStream.getInstance().close();
        assertThatCode(() -> {
            WhiteInputStream.getInstance().close();
        }).doesNotThrowAnyException();
    }
}
