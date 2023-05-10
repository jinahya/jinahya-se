package com.github.jinahyax.imageio;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@Slf4j
class ImageIoFormatNameTest extends ImageIoFeatureTest<ImageIoFormatName> {

    ImageIoFormatNameTest() {
        super(ImageIoFormatName.class);
    }

    @Test
    void __() {
        final var list = ImageIoFormatName.availableImageIoFormatNames();
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
