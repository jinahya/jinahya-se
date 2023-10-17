package com.github.jinahya.security;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

@Slf4j
class JinahyaMessageDigestConstantsTest {

    private static List<String> algorithmsRequiredToBeSupported() {
        return JinahyaMessageDigestConstants.ALGORITHMS_REQUIRED_TO_BE_SUPPORTED;
    }

    private static List<String> algorithms() {
        return JinahyaMessageDigestConstants.ALGORITHMS;
    }

    @MethodSource({"algorithmsRequiredToBeSupported"})
    @ParameterizedTest
    void _ShouldBeSupported_(final String algorithm) {
        assertThatCode(() -> MessageDigest.getInstance(algorithm)).doesNotThrowAnyException();
    }

    @MethodSource({"algorithms"})
    @ParameterizedTest
    void _MayBeSupported_(final String algorithm) {
        try {
            MessageDigest.getInstance(algorithm);
        } catch (final NoSuchAlgorithmException nsae) {
            log.info("seems not supported: {}", algorithm, nsae);
        }
    }
}
