package com.github.jinahyax.imageio;

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
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@Slf4j
class ImageIoMimeTypeTest extends ImageIoFeatureTest<ImageIoMimeType> {

    ImageIoMimeTypeTest() {
        super(ImageIoMimeType.class);
    }

    @Test
    void __() {
        final var list = ImageIoMimeType.availableImageIoMimeTypes();
        assertThat(list).isNotEmpty().doesNotHaveDuplicates().allSatisfy(e -> {
            assertThat(e.getValue()).isNotBlank();
            assertThatCode(e::isReadable).doesNotThrowAnyException();
            assertThatCode(e::isWritable).doesNotThrowAnyException();
        });
        list.forEach(e -> {
            log.debug("image io file suffix: {}", e);
        });
    }
}
