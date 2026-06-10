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

import io.vavr.CheckedConsumer;
import io.vavr.CheckedFunction1;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class JinahyaDataIoTest {

    private void test(
            final CheckedFunction1<? super JinahyaDataOutput,
                    ? extends CheckedConsumer<? super JinahyaDataInput>> function)
            throws Throwable {
        Objects.requireNonNull(function, "function is null");
        try (var out = new ByteArrayOutputStream();
             var output = new JinahyaDataOutputStream(out)) {
            final var consumer = function.apply(output);
            output.flush();
            out.flush();
            try (var in = new ByteArrayInputStream(out.toByteArray());
                 var input = new JinahyaDataInputStream(in)) {
                consumer.accept(input);
            }
        }
    }

    @Test
    void shortLe__() throws Throwable {
        test(o -> {
            final var expected = (short) ThreadLocalRandom.current().nextInt();
            o.writeShortLe(expected);
            return i -> {
                final var actual = i.readShortLe();
                assertThat(actual).isEqualTo(expected);
            };
        });
    }

    @Test
    void intLe__() throws Throwable {
        test(o -> {
            final var expected = ThreadLocalRandom.current().nextInt();
            o.writeIntLe(expected);
            return i -> {
                final var actual = i.readIntLe();
                assertThat(actual).isEqualTo(expected);
            };
        });
    }

    @Test
    void longLe__() throws Throwable {
        test(o -> {
            final var expected = ThreadLocalRandom.current().nextLong();
            o.writeLongLe(expected);
            return i -> {
                final var actual = i.readLongLe();
                assertThat(actual).isEqualTo(expected);
            };
        });
    }
}
