package com.github.jinahya.security;

import com.github.jinahya.util._ExcludeFromCoverage_PrivateConstructor_Obviously;

import java.io.IOException;
import java.nio.file.Path;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.function.Function;

public final class JinahyaPrivateKeyUtils {

    private static final Function<byte[], EncodedKeySpec> PKCS8 = PKCS8EncodedKeySpec::new;

    public static void writeEncoded(
            final PrivateKey key,
            final Function<? super byte[], ? extends EncodedKeySpec> function,
            final Path path)
            throws IOException {
        JinahyaKeyUtils.writeEncoded(key, function, path);
    }

    public static void writeEncoded(final PrivateKey key, final Path path)
            throws IOException {
        writeEncoded(key, PKCS8, path);
    }

    public static PrivateKey readEncoded(
            final Path path, final Function<? super byte[], ? extends EncodedKeySpec> function,
            final KeyFactory factory)
            throws IOException {
        return JinahyaKeyUtils.readEncoded(
                path,
                function,
                ks -> {
                    try {
                        return factory.generatePrivate(ks);
                    } catch (final InvalidKeySpecException ikse) {
                        throw new RuntimeException(
                                "failed to generate private key with " + ks,
                                ikse
                        );
                    }
                }
        );
    }

    public static PrivateKey readEncoded(final Path path, final KeyFactory factory)
            throws IOException {
        return readEncoded(path, PKCS8, factory);
    }

    @_ExcludeFromCoverage_PrivateConstructor_Obviously
    private JinahyaPrivateKeyUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}