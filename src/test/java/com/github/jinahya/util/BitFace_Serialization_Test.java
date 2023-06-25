package com.github.jinahya.util;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.assertj.core.api.Assertions.assertThat;

class BitFace_Serialization_Test {

    @RepeatedTest(1024)
    void __(@TempDir final File tempDir) throws Exception {
        final var face = BitFace.of(BitMask_TestUtils.randomBitMaskStream());
        final var file = File.createTempFile("tmp", "tmp", tempDir);
        try (final var fos = new FileOutputStream(file);
             final var oos = new ObjectOutputStream(fos)) {
            oos.writeObject(face);
            oos.flush();
        }
        try (final var fis = new FileInputStream(file);
             final var ois = new ObjectInputStream(fis)) {
            assertThat((BitFace) ois.readObject()).isSameAs(face);
        }
    }
}
