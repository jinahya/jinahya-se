package com.github.jinahya.awt;

import java.awt.color.ColorSpace;
import java.util.Objects;
import java.util.function.IntFunction;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Utilities for colors.
 *
 * @see <a href="https://www.ibm.com/docs/en/i/7.3?topic=concepts-color-spaces-icc-profiles">Color spaces and ICC
 * profiles</a> (ibm.com / IBM i / 7.3)
 * @see <a href="https://www.ibm.com/docs/en/i/7.5?topic=concepts-color-spaces-icc-profiles">Color spaces and ICC
 * profiles</a> (ibm.com / IBM i / 7.5)
 */
@SuppressWarnings({
        "java:S101" // _Persistable...
})
public final class JinahyaColorUtils {

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_RED = "red";

    public static final String COLUMN_NAME_GREEN = "green";

    public static final String COLUMN_NAME_BLUE = "blue";

    public static final String COLUMN_NAME_ALPHA = "alpha";

    // -----------------------------------------------------------------------------------------------------------------
    private static final String PATTERN_HEX_CHAR = "[0-9a-f]";

    private static final String REGEXP_CSS_HEXADECIMAL_NOTATION3 = PATTERN_HEX_CHAR + "{3}";

    static final Pattern PATTERN_CSS_HEXADECIMAL_NOTATION3 = Pattern.compile(REGEXP_CSS_HEXADECIMAL_NOTATION3);

    private static final String REGEXP_CSS_HEXADECIMAL_NOTATION4 = PATTERN_HEX_CHAR + "{4}";

    static final Pattern PATTERN_CSS_HEXADECIMAL_NOTATION4 = Pattern.compile(REGEXP_CSS_HEXADECIMAL_NOTATION4);

    private static final String REGEXP_CSS_HEXADECIMAL_NOTATION6 = PATTERN_HEX_CHAR + "{6}";

    static final Pattern PATTERN_CSS_HEXADECIMAL_NOTATION6 = Pattern.compile(REGEXP_CSS_HEXADECIMAL_NOTATION6);

    private static final String REGEXP_CSS_HEXADECIMAL_NOTATION8 = PATTERN_HEX_CHAR + "{8}";

    static final Pattern PATTERN_CSS_HEXADECIMAL_NOTATION8 = Pattern.compile(REGEXP_CSS_HEXADECIMAL_NOTATION8);

    // https://stackoverflow.com/q/47633735/330457
    private static final String REGEXP_CSS_HEXADECIMAL_NOTATION =
            REGEXP_CSS_HEXADECIMAL_NOTATION3 + '|' +
            REGEXP_CSS_HEXADECIMAL_NOTATION4 + '|' +
            REGEXP_CSS_HEXADECIMAL_NOTATION6 + '|' +
            REGEXP_CSS_HEXADECIMAL_NOTATION8;

    static final Pattern PATTERN_CSS_HEXADECIMAL_NOTATION = Pattern.compile(REGEXP_CSS_HEXADECIMAL_NOTATION);

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns an array of {@code CIEXYZ} color components converted from specified {@code RGB} color components.
     *
     * @param rgbColorComponents the {@code RGB} color components.
     * @param rgbColorSpace      an auxiliary {@code RGB} color space.
     * @return an array of converted color components.
     */
    public static float[] toCIEXYZ(final float[] rgbColorComponents, final ColorSpace rgbColorSpace) {
        Objects.requireNonNull(rgbColorComponents, "rgbColorComponents is null");
        Objects.requireNonNull(rgbColorSpace, "rgbColorSpace is null");
        return rgbColorSpace.toCIEXYZ(rgbColorComponents);
    }

    /**
     * Returns an array of {@code CMYK} color components converted from specified persistable color object.
     *
     * @param rgbColorComponents the persistable color object whose {@code RGBA} color components are converted.
     * @param rgbColorSpace      an auxiliary {@code RGB} color space; may be {@code null}.
     * @param cmykColorSpace     a target {@code CMYK} color space.
     * @return an array of converted color components.
     */
    public static float[] toCMYK(final float[] rgbColorComponents, final ColorSpace rgbColorSpace,
                                 final ColorSpace cmykColorSpace) {
        Objects.requireNonNull(rgbColorComponents, "rgbColorComponents is null");
        Objects.requireNonNull(cmykColorSpace, "cmykColorSpace is null");
        if (rgbColorSpace == null) {
            return cmykColorSpace.fromRGB(rgbColorComponents);
        }
        return cmykColorSpace.fromCIEXYZ(toCIEXYZ(rgbColorComponents, rgbColorSpace));
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static final int MIN_COLOR = 0;

    public static final int MAX_COLOR = 255;

    static final float MIN_COMPONENT = Float.intBitsToFloat(MIN_COLOR);

    static final float MAX_COMPONENT = Float.intBitsToFloat(0b0_01111111_00000000000000000000000);

    private static int requireValidColor(final int color) {
        if (color < MIN_COLOR) {
            throw new IllegalArgumentException("color(" + color + ") < " + MIN_COLOR);
        }
        if (color > MAX_COLOR) {
            throw new IllegalArgumentException("color(" + color + ") > " + MAX_COLOR);
        }
        return color;
    }

    static float requireValidComponent(final float component) {
        return toComponent(requireValidColor(toColor(component)));
    }

    static float toComponent(final int color) {
        return requireValidColor(color) / (float) MAX_COLOR;
    }

    static float[] toComponents(final int[] colors) {
        Objects.requireNonNull(colors, "colors is null");
        final var components = new float[colors.length];
        for (int i = 0; i < components.length; i++) {
            components[i] = toComponent(colors[i]);
        }
        return components;
    }

    private static int toColor(final float component) {
        return (int) (requireValidComponent(component) * MAX_COLOR);
    }

    private static int[] toColors(final float[] components) {
        final var colors = new int[components.length];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = toColor(components[i]);
        }
        return colors;
    }

    /**
     * Returns a {@code 3}-long hexadecimal string representation of specified colors colors.
     *
     * @param colors colors to be printed whose length should be greater than or equal to {@code 3}.
     * @return a string representation of {@code colors}.
     * @see #toCssRgbHexadecimalNotation4(int[])
     * @see <a href="https://www.w3.org/TR/css-color-4/#hex-color">5.2. The RGB Hexadecimal Notations: '#RRGGBB'</a>
     * (CSS Color Module Level 4)
     */
    public static String toCssRgbHexadecimalNotation3(final int[] colors) {
        Objects.requireNonNull(colors, "colors is null");
        if (colors.length < 3) {
            throw new IllegalArgumentException("colors.length(" + colors.length + ") < 3");
        }
        return String.format("%1$x%2$x%3$x",
                             (colors[0] >> 4) & 0xF,
                             (colors[1] >> 4) & 0xF,
                             (colors[2] >> 4) & 0xF
        );
    }

    /**
     * Returns a {@code 3}-long hexadecimal string representation of color components.
     *
     * @param components color components to be printed whose length should be greater than or equal to {@code 3}.
     * @return a string representation of {@code rgb}.
     * @see #toCssRgbHexadecimalNotation4(int[])
     * @see <a href="https://www.w3.org/TR/css-color-4/#hex-color">5.2. The RGB Hexadecimal Notations: '#RRGGBB'</a>
     * (CSS Color Module Level 4)
     */
    public static String toCssRgbHexadecimalNotation3(final float[] components) {
        Objects.requireNonNull(components, "components is null");
        if (components.length < 3) {
            throw new IllegalArgumentException("components.length(" + components.length + ") < 3");
        }
        return toCssRgbHexadecimalNotation3(toColors(components));
    }

    /**
     * Returns a {@code 4}-long hexadecimal string representation of colors.
     *
     * @param colors the color values to be printed.
     * @return a string representation of {@code rgba}.
     * @see #toCssRgbHexadecimalNotation3(int[])
     * @see <a href="https://www.w3.org/TR/css-color-4/#hex-color">5.2. The RGB Hexadecimal Notations: '#RRGGBB'</a>
     * (CSS Color Module Level 4)
     */
    public static String toCssRgbHexadecimalNotation4(final int[] colors) {
        Objects.requireNonNull(colors, "colors is null");
        if (colors.length < 4) {
            throw new IllegalArgumentException("colors.length(" + colors.length + ") < 4");
        }
        return toCssRgbHexadecimalNotation3(colors) + String.format("%1$x", (colors[3] >> 4) & 0xF);
    }

    /**
     * Returns a {@code 4}-long hexadecimal string representation of components.
     *
     * @param components the color values to be printed.
     * @return a string representation of {@code rgba}.
     * @see #toCssRgbHexadecimalNotation3(int[])
     * @see <a href="https://www.w3.org/TR/css-color-4/#hex-color">5.2. The RGB Hexadecimal Notations: '#RRGGBB'</a>
     * (CSS Color Module Level 4)
     */
    public static String toCssRgbHexadecimalNotation4(final float[] components) {
        Objects.requireNonNull(components, "components is null");
        if (components.length < 4) {
            throw new IllegalArgumentException("components.length(" + components.length + ") < 4");
        }
        return toCssRgbHexadecimalNotation4(toColors(components));
    }

    /**
     * Returns a {@code 6}-long hexadecimal string representation of color components.
     *
     * @return a string representation of {@code rrggbb}.
     * @see #toCssRgbHexadecimalNotation8(int[])
     * @see <a href="https://www.w3.org/TR/css-color-4/#hex-color">5.2. The RGB Hexadecimal Notations: '#RRGGBB'</a>
     * (CSS Color Module Level 4)
     */
    public static String toCssRgbHexadecimalNotation6(final int[] colors) {
        Objects.requireNonNull(colors, "colors is null");
        if (colors.length < 3) {
            throw new IllegalArgumentException("colors.length(" + colors.length + ") < 3");
        }
        return String.format("%1$02x%2$02x%3$02x",
                             colors[0] & 0xFF,
                             colors[1] & 0xFF,
                             colors[2] & 0xFF
        );
    }

    /**
     * Returns a {@code 6}-long hexadecimal string representation of color components.
     *
     * @return a string representation of {@code rrggbb}.
     * @see #toCssRgbHexadecimalNotation8(int[])
     * @see <a href="https://www.w3.org/TR/css-color-4/#hex-color">5.2. The RGB Hexadecimal Notations: '#RRGGBB'</a>
     * (CSS Color Module Level 4)
     */
    public static String toCssRgbHexadecimalNotation6(final float[] components) {
        Objects.requireNonNull(components, "components is null");
        if (components.length < 3) {
            throw new IllegalArgumentException("components.length(" + components.length + ") < 3");
        }
        return toCssRgbHexadecimalNotation6(toColors(components));
    }

    /**
     * Returns a {@code 8}-long hexadecimal string representation of color components.
     *
     * @return a string representation of {@code rrggbbaa}.
     * @see #toCssRgbHexadecimalNotation6(int[])
     * @see <a href="https://www.w3.org/TR/css-color-4/#hex-color">5.2. The RGB Hexadecimal Notations: '#RRGGBB'</a>
     * (CSS Color Module Level 4)
     */
    public static String toCssRgbHexadecimalNotation8(final int[] colors) {
        Objects.requireNonNull(colors, "colors is null");
        if (colors.length < 4) {
            throw new IllegalArgumentException("colors.length(" + colors.length + ") < 4");
        }
        return toCssRgbHexadecimalNotation6(colors) + String.format("%1$02x", colors[3] & 0xFF);
    }

    /**
     * Returns a {@code 6}-long hexadecimal string representation of color components.
     *
     * @return a string representation of {@code rrggbbaa}.
     * @see #toCssRgbHexadecimalNotation8(int[])
     * @see <a href="https://www.w3.org/TR/css-color-4/#hex-color">5.2. The RGB Hexadecimal Notations: '#RRGGBB'</a>
     * (CSS Color Module Level 4)
     */
    public static String toCssRgbHexadecimalNotation8(final float[] components) {
        Objects.requireNonNull(components, "components is null");
        if (components.length < 4) {
            throw new IllegalArgumentException("components.length(" + components.length + ") < 4");
        }
        return toCssRgbHexadecimalNotation8(toColors(components));
    }

    public static <R> R parseCssRgbHexadecimalNotation(
            final CharSequence cssRgbHexadecimalNotation,
            final IntFunction<
                    ? extends IntFunction<
                            ? extends IntFunction<
                                    ? extends IntFunction<? extends R>>>> function) {
        Objects.requireNonNull(cssRgbHexadecimalNotation, "cssRgbHexadecimalNotation is null");
        Objects.requireNonNull(function, "function is null");
        if (!PATTERN_CSS_HEXADECIMAL_NOTATION.matcher(cssRgbHexadecimalNotation).matches()) {
            throw new IllegalArgumentException("invalid CSS RGB Hexadecimal Notation: " + cssRgbHexadecimalNotation);
        }
        final var nibbles = cssRgbHexadecimalNotation.chars()
                .map(c -> Character.digit(c, 16))
                .boxed()
                .collect(Collectors.toList());
        if (nibbles.size() == 3 || nibbles.size() == 4) {
            final var r = nibbles.remove(0);
            final var g = nibbles.remove(0);
            final var b = nibbles.remove(0);
            final int a;
            if (nibbles.isEmpty()) {
                a = 0;
            } else {
                a = nibbles.remove(0);
            }
            return function.apply(r).apply(g).apply(b).apply(a);
        }
        assert nibbles.size() == 6 || nibbles.size() == 8;
        final var r = (nibbles.remove(0) << 4) | nibbles.remove(0);
        final var g = (nibbles.remove(0) << 4) | nibbles.remove(0);
        final var b = (nibbles.remove(0) << 4) | nibbles.remove(0);
        final int a;
        if (nibbles.isEmpty()) {
            a = 0;
        } else {
            a = (nibbles.remove(0) << 4) | nibbles.remove(0);
        }
        return function.apply(r).apply(g).apply(b).apply(a);
    }

    // -----------------------------------------------------------------------------------------------------------------

    private JinahyaColorUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
