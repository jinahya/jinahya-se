package com.github.jinahya.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class BitMask_Serialization_Test {

    @Test
    void __(@TempDir final File tempDir) throws Exception {
        for (int e = BitMask.MIN_EXPONENT; e <= BitMask.MAX_EXPONENT; e++) {
            final var mask = BitMask.ofExponent(e);
            final var file = File.createTempFile("tmp", "tmp", tempDir);
            try (final var fos = new FileOutputStream(file);
                 final var oos = new ObjectOutputStream(fos)) {
                oos.writeObject(mask);
                oos.flush();
            }
            try (final var fis = new FileInputStream(file);
                 final var ois = new ObjectInputStream(fis)) {
                log.debug("e: {}", e);
                assertThat((BitMask) ois.readObject()).isSameAs(mask);
            }
        }
    }
}
