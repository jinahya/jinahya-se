package com.github.jinahya.awt.color;

import java.awt.color.ICC_Profile;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings({
        "java:S101" // JinahyaICC_...
})
public final class JinahyaICC_ProfileUtils {

    public static String nameOfProfileClass(final int profileClass) {
        return Arrays.stream(ICC_Profile.class.getFields())
                .filter(f -> {
                    final int modifiers = f.getModifiers();
                    return Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers);
                })
                .filter(f -> f.getType() == int.class)
                .filter(f -> f.getName().startsWith("CLASS_"))
                .filter(f -> {
                    try {
                        return f.getInt(null) == profileClass;
                    } catch (final IllegalAccessException iae) {
                        throw new RuntimeException("failed to get value of " + f, iae);
                    }
                })
                .findFirst()
                .map(Field::getName)
                .orElse(null)
                ;
    }

    public static String nameOfProfileClass(final ICC_Profile iccProfile) {
        Objects.requireNonNull(iccProfile, "iccProfile is null");
        return nameOfProfileClass(iccProfile.getProfileClass());
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static ICC_Profile getIccProfile(final Path path) throws IOException {
        Objects.requireNonNull(path, "path is null");
        if (!Files.isRegularFile(path)) {
            throw new IllegalArgumentException("not a regular file: " + path);
        }
        try (var stream = new FileInputStream(path.toFile())) {
            return ICC_Profile.getInstance(stream);
        }
    }

    private static List<ICC_Profile> getIccProfiles(final Path... paths) {
        Objects.requireNonNull(paths, "paths is null");
        return Arrays.stream(paths)
                .filter(Objects::nonNull)
                .filter(Files::isDirectory)
                .flatMap(p -> {
                    try {
                        return Files.walk(p, FileVisitOption.FOLLOW_LINKS);
                    } catch (final IOException ioe) {
                        throw new UncheckedIOException("failed to walk on " + p, ioe);
                    }
                })
                .filter(Files::isRegularFile)
                .filter(Files::isReadable)
                .filter(p -> p.toFile().getName().endsWith(".icc"))
                .map(p -> {
                    try {
                        return getIccProfile(p);
                    } catch (final IOException ioe) {
                        throw new UncheckedIOException("failed to get ICC_Profile from " + p, ioe);
                    }
                })
                .toList();
    }

    private static List<ICC_Profile> getIccProfilesMac() {
        return getIccProfiles(
                Paths.get("/Library/ColorSync/Profiles"),
                Optional.ofNullable(System.getProperty("user.home"))
                        .map(v -> Paths.get(v).resolve(Paths.get("Library", "ColorSync", "Profiles")))
                        .orElse(null)
        );
    }

    private static List<ICC_Profile> getIccProfilesWindows() {
        return getIccProfiles(
                Optional.ofNullable(System.getenv("SYSTEMROOT"))
                        .map(Paths::get)
                        .orElseGet(() -> Paths.get("C:\\windows\\system32\\spool\\drivers\\color"))
        );
    }

    private static List<ICC_Profile> getIccProfilesLinux() {
        return getIccProfiles(
                Paths.get("/usr/share/color/icc")
        );
    }

    /**
     * Returns all installed {@link ICC_Profile}s.
     *
     * @see <a href="https://mrserge.lv/2023/how-to-install-icc-profiles-on-pc-and-mac-and-where-to-find-them/">How to
     * install ICC profiles on PC and MAC and where to find them</a>
     */
    public static List<ICC_Profile> getIccProfiles() {
        final var osName = System.getProperty("os.name");
        if (osName == null) {
            return Collections.emptyList();
        }
        if (osName.toLowerCase().startsWith("linux")) {
            return getIccProfilesLinux();
        }
        if (osName.toLowerCase().startsWith("mac")) {
            return getIccProfilesMac();
        }
        if (osName.toLowerCase().startsWith("windows")) {
            return getIccProfilesWindows();
        }
        return Collections.emptyList();
    }

    // -----------------------------------------------------------------------------------------------------------------
    private JinahyaICC_ProfileUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
