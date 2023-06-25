package com.github.jinahya.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static com.github.jinahya.util.BitMask_OfLong_TestUtils.randomBitMask;
import static org.assertj.core.api.Assertions.assertThat;

class BitMask_OfLong_Serialization_Test {

    @Test
    void __(@TempDir final File tempDir) throws Exception {
        final var mask = randomBitMask();
        final var file = File.createTempFile("tmp", "tmp", tempDir);
        try (final var fos = new FileOutputStream(file);
             final var oos = new ObjectOutputStream(fos)) {
            oos.writeObject(mask);
            oos.flush();
        }
        try (final var fis = new FileInputStream(file);
             final var ois = new ObjectInputStream(fis)) {
            assertThat((BitMask.OfLong) ois.readObject()).isEqualTo(mask);
        }
    }
}
