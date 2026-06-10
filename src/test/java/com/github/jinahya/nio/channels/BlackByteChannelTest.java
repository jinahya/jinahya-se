package com.github.jinahya.nio.channels;

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
import java.nio.ByteBuffer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class BlackByteChannelTest {

    @Test
    void write_Zero_NoRemaining() throws IOException {
        final var src = ByteBuffer.allocate(0);
        assertThat(BlackByteChannel.getInstance().write(src)).isZero();
    }

    @Test
    void write__() throws IOException {
        for (var src = ByteBuffer.allocate(1024); src.hasRemaining(); ) {
            final var written = BlackByteChannel.getInstance().write(src);
            assertThat(written).isNotNegative();
        }
    }

    @Test
    void isOpen_True_() {
        assertThat(BlackByteChannel.getInstance().isOpen()).isTrue();
    }

    @Test
    void close_DoesNotThrow_() {
        assertThatCode(() -> BlackByteChannel.getInstance().close()).doesNotThrowAnyException();
    }
}
