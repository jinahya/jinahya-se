package com.github.jinahya.io;

import com.github.jinahya.security.JinahyaMessageDigestConstants;
import com.github.jinahya.util.JinahyaRandomUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

class DigestOutputStreamTest {

    private static List<String> getMessageDigestAlgorithmList() {
        return JinahyaMessageDigestConstants.ALGORITHMS_REQUIRED_TO_BE_SUPPORTED;
    }

    @MethodSource({"getMessageDigestAlgorithmList"})
    @ParameterizedTest
    void __(final String algorithm) throws IOException, NoSuchAlgorithmException {
        try (var baos = new ByteArrayOutputStream();
             var output = new DigestOutputStream(baos, null)) {
            final var digest = spy(MessageDigest.getInstance(algorithm));
            output.setDigest(digest);
            final var b = JinahyaRandomUtils.newRandomBytesOfRandomLength(null, 1024);
            final var expected = MessageDigest.getInstance(algorithm).digest(b);
            // ---------------------------------------------------------------------------------------------------- WHEN
            output.write(b);
            output.flush();
            final var actual = output.getDigest().digest();
            // ---------------------------------------------------------------------------------------------------- THEN
            assertThat(actual).isEqualTo(expected);
        }
    }

    @Test
    void __(@TempDir final File tempDir) throws IOException {
        final var file = File.createTempFile("tmp", "tmp", tempDir);
        try (var stream = new FileOutputStream(file)) {
            final var b = JinahyaRandomUtils.newRandomBytesOfRandomLength(null, 1024);
            stream.write(b);
            stream.flush();
        }
    }
}
