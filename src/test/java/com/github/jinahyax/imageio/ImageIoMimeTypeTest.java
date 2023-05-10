package com.github.jinahyax.imageio;

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
