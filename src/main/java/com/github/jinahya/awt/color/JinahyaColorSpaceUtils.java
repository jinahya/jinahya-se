package com.github.jinahya.awt.color;

import java.awt.color.ColorSpace;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Objects;

public final class JinahyaColorSpaceUtils {

    public static String nameOfColorSpaceType(final int colorSpaceType) {
        return Arrays.stream(ColorSpace.class.getFields())
                .filter(f -> {
                    final int modifiers = f.getModifiers();
                    return Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers);
                })
                .filter(f -> f.getType() == int.class)
                .filter(f -> f.getName().startsWith("TYPE_"))
                .filter(f -> {
                    try {
                        return f.getInt(null) == colorSpaceType;
                    } catch (final IllegalAccessException iae) {
                        throw new RuntimeException("failed to get value of " + f, iae);
                    }
                })
                .findFirst()
                .map(Field::getName)
                .orElse(null)
                ;
    }

    public static String nameOfColorSpaceType(final ColorSpace colorSpace) {
        Objects.requireNonNull(colorSpace, "colorSpace is null");
        return nameOfColorSpaceType(colorSpace.getType());
    }

    /**
     * Returns an array of {@code CIEXYZ} color components converted from specified {@code RGB} color components.
     *
     * @param colorSpace      a {@link ColorSpace#TYPE_RGB RGB} color space.
     * @param colorComponents the {@code RGB} color components to convert.
     * @return an array of converted color components.
     */
    public static float[] rgbToCiexyz(final ColorSpace colorSpace, final float[] colorComponents) {
        Objects.requireNonNull(colorSpace, "colorSpace is null");
        final int colorSpaceType = colorSpace.getType();
        if (colorSpaceType != ColorSpace.TYPE_RGB) {
            throw new IllegalArgumentException(
                    "colorSpace.type(" + colorSpaceType + ")"
                    + " != ColorSpace.TYPE_RGB(" + ColorSpace.TYPE_RGB + ")"
            );
        }
        Objects.requireNonNull(colorComponents, "colorComponents is null");
        final var numComponents = colorSpace.getNumComponents();
        if (colorComponents.length < numComponents) {
            throw new IllegalArgumentException(
                    "colorComponents.length(" + colorComponents.length + ")"
                    + " < colorSpace.numComponents(" + numComponents + ")"
            );
        }
        return colorSpace.toCIEXYZ(Arrays.copyOf(colorComponents, numComponents));
    }

    /**
     * Returns an array of {@code CMYK} color components converted from specified {@code RGB} color components.
     *
     * @param cmykColorSpace     a {@code CMYK} color space.
     * @param rgbColorComponents the {@code RGB} color components to convert.
     * @param rgbColorSpace      an auxiliary {@code RGB} color space; may be {@code null}.
     * @return an array of converted color components.
     */
    public static float[] rgbToCmyk(final ColorSpace cmykColorSpace, final float[] rgbColorComponents,
                                    final ColorSpace rgbColorSpace) {
        Objects.requireNonNull(cmykColorSpace, "cmykColorSpace is null");
        final int cmykColorSpaceType = cmykColorSpace.getType();
        if (cmykColorSpaceType != ColorSpace.TYPE_CMYK) {
            throw new IllegalArgumentException(
                    "cmykColorSpace.type(" + cmykColorSpaceType + ")"
                    + " != ColorSpace.TYPE_CMYK(" + ColorSpace.TYPE_CMYK + ")"
            );
        }
        Objects.requireNonNull(rgbColorComponents, "rgbColorComponents is null");
        if (rgbColorSpace == null) {
            return cmykColorSpace.fromRGB(rgbColorComponents);
        }
        return cmykColorSpace.fromCIEXYZ(rgbToCiexyz(rgbColorSpace, rgbColorComponents));
    }

    /**
     * Converts specified {@code CMYK} color components, using specified color space, to {@code CIEXYZ} color
     * components.
     *
     * @param colorSpace      the {@code CMYK} color space.
     * @param colorComponents the {@code CMYK} color components to convert.
     * @return an array of converted color components.
     */
    public static float[] cmykToCiexyz(final ColorSpace colorSpace, final float[] colorComponents) {
        Objects.requireNonNull(colorSpace, "colorSpace is null");
        final var ColorSpaceType = colorSpace.getType();
        if (ColorSpaceType != ColorSpace.TYPE_CMYK) {
            throw new IllegalArgumentException(
                    "colorSpace.type(" + ColorSpaceType + ")"
                    + " != ColorSpace.TYPE.CMYK(" + ColorSpace.TYPE_CMYK + ")"
            );
        }
        Objects.requireNonNull(colorComponents, "colorComponents is null");
        final var colorSpaceNumComponents = colorSpace.getNumComponents();
        if (colorComponents.length < colorSpaceNumComponents) {
            throw new IllegalArgumentException(
                    "colorComponents.length(" + colorComponents.length + ")"
                    + " < colorSpace.numComponents(" + colorSpaceNumComponents + ")"
            );
        }
        return colorSpace.toCIEXYZ(Arrays.copyOf(colorComponents, colorSpaceNumComponents));
    }

    /**
     * Returns an array of {@code CMYK} color components converted from specified {@code RGB} color object.
     *
     * @param cmykColorSpace      a {@code CMYK} color space.
     * @param cmykColorComponents the {@code CMYK} color components to convert.
     * @param rgbColorSpace       a {@code RGB} color space; may be {@code null}.
     * @return an array of converted color components.
     */
    public static float[] cmykToRgb(final ColorSpace cmykColorSpace, final float[] cmykColorComponents,
                                    final ColorSpace rgbColorSpace) {
        Objects.requireNonNull(cmykColorComponents, "cmykColorComponents is null");
        Objects.requireNonNull(cmykColorSpace, "cmykColorSpace is null");
        if (rgbColorSpace != null) {
            final var rgbColorSpaceType = rgbColorSpace.getType();
            if (rgbColorSpaceType != ColorSpace.TYPE_RGB) {
                throw new IllegalArgumentException(
                        "rgbColorSpace.type(" + rgbColorSpaceType + ")"
                        + " != ColorSpace.TYPE_RGB(" + ColorSpace.TYPE_RGB + ")"
                );
            }
        }
        if (rgbColorSpace == null) {
            return cmykColorSpace.toRGB(cmykColorComponents);
        }
        return rgbColorSpace.fromCIEXYZ(cmykToCiexyz(cmykColorSpace, cmykColorComponents));
    }

    // -----------------------------------------------------------------------------------------------------------------
    private JinahyaColorSpaceUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
