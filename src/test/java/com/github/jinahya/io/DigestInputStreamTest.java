package com.github.jinahya.io;

import com.github.jinahya.security.JinahyaMessageDigests;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class DigestInputStreamTest {

    private static List<String> getAlgorithms() {
        return JinahyaMessageDigests.ALGORITHMS_REQUIRED_TO_BE_SUPPORTED;
    }

    @MethodSource({"getAlgorithms"})
    @ParameterizedTest
    void __(final String algorithm) throws NoSuchAlgorithmException, IOException {
        final byte[] bytes = new byte[ThreadLocalRandom.current().nextInt(1024)];
        ThreadLocalRandom.current().nextBytes(bytes);
        final MessageDigest digest = MessageDigest.getInstance(algorithm);
        final byte[] result = digest.digest(bytes);
        digest.reset();
        try (DigestInputStream dis = new DigestInputStream(new ByteArrayInputStream(bytes))) {
            dis.setDigest(digest);
            while (dis.read() != -1) {
                // empty
            }
        }
        assertThat(digest.digest()).isEqualTo(result);
    }
}
