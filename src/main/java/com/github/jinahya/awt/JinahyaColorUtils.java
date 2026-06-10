package com.github.jinahya.awt;

/*-
 * #%L
 * jinahya-se
 * %%
 * Copyright (C) 2025 - 2026 Jinahya
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.awt.color.ColorSpace;
import java.util.Objects;
import java.util.function.IntFunction;
import java.util.regex.Pattern;

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
    public static final int MIN_COLOR = 0;

    public static final int MAX_COLOR = 255;

    static final int MIN_COMPONENT_INT = 0b0_00000000_00000000000000000000000;

    static final float MIN_COMPONENT = Float.intBitsToFloat(MIN_COMPONENT_INT); // +0.0f

    static final int MAX_COMPONENT_INT = 0b0_01111111_00000000000000000000000;

    static final float MAX_COMPONENT = Float.intBitsToFloat(MAX_COMPONENT_INT); // +1.0f

    // -----------------------------------------------------------------------------------------------------------------
    static int requireValidColor(final int color) {
        if (color < MIN_COLOR) {
            throw new IllegalArgumentException("color(" + color + ") < " + MIN_COLOR);
        }
        if (color > MAX_COLOR) {
            throw new IllegalArgumentException("color(" + color + ") > " + MAX_COLOR);
        }
        return color;
    }

    static float requireValidComponent(final float component) {
        if (Float.compare(component, MIN_COMPONENT) < 0) {
            throw new IllegalArgumentException("component(" + component + ") is less than " + MIN_COMPONENT);
        }
        if (Float.compare(component, MAX_COMPONENT) > 0) {
            throw new IllegalArgumentException("component(" + component + ") is greater than " + MAX_COMPONENT);
        }
        return component;
    }

    // -----------------------------------------------------------------------------------------------------------------
    static float toComponent(final int color) {
        if (color < MIN_COLOR) {
            throw new IllegalArgumentException("color(" + color + ") < " + MIN_COLOR);
        }
        if (color > MAX_COLOR) {
            throw new IllegalArgumentException("color(" + color + ") > " + MAX_COLOR);
        }
        return ((float) color) / MAX_COLOR;
    }

    static float[] toComponents(final int[] colors) {
        Objects.requireNonNull(colors, "colors is null");
        final var components = new float[colors.length];
        for (int i = 0; i < components.length; i++) {
            components[i] = toComponent(colors[i]);
        }
        return components;
    }

    static int toColor(final float component) {
        if (component < MIN_COMPONENT) {
            throw new IllegalArgumentException("component(" + component + ") < " + MIN_COMPONENT);
        }
        if (component > MAX_COMPONENT) {
            throw new IllegalArgumentException("component(" + component + ") > " + MAX_COMPONENT);
        }
        return (int) (component * MAX_COLOR);
    }

    static int[] toColors(final float[] components) {
        Objects.requireNonNull(components, "components is null");
        final var colors = new int[components.length];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = toColor(components[i]);
        }
        return colors;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns an array of {@code CIEXYZ} color components converted from specified {@code RGB} color components.
     *
     * @param rgbColorComponents the {@code RGB} color components.
     * @param rgbColorSpace      an auxiliary {@code RGB} color space.
     * @return an array of converted color components.
     */
    public static float[] toCiexyz(final float[] rgbColorComponents, final ColorSpace rgbColorSpace) {
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
    public static float[] toCmyk(final float[] rgbColorComponents, final ColorSpace rgbColorSpace,
                                 final ColorSpace cmykColorSpace) {
        Objects.requireNonNull(rgbColorComponents, "rgbColorComponents is null");
        Objects.requireNonNull(cmykColorSpace, "cmykColorSpace is null");
        if (rgbColorSpace == null) {
            return cmykColorSpace.fromRGB(rgbColorComponents);
        }
        return cmykColorSpace.fromCIEXYZ(toCiexyz(rgbColorComponents, rgbColorSpace));
    }

    /**
     * Converts specified {@code CMYK} color components, using specified color space, to {@code CIEXYZ} color
     * components.
     *
     * @param cmykColorComponents the {@code CMYK} color components to convert.
     * @param cmykColorSpace      the {@code CMYK} color space.
     * @return an array of converted color components.
     */
    public static float[] cmykToCiexyz(final float[] cmykColorComponents, final ColorSpace cmykColorSpace) {
        Objects.requireNonNull(cmykColorComponents, "cmykColorComponents is null");
        Objects.requireNonNull(cmykColorSpace, "cmykColorSpace is null");
        if (cmykColorSpace.getType() != ColorSpace.TYPE_CMYK) {
            throw new IllegalArgumentException(
                    "cmykColorSpace.type(" + cmykColorSpace.getType()
                    + ") != ColorSpace.TYPE.CMYK(" + ColorSpace.TYPE_CMYK + ")");
        }
        if (cmykColorComponents.length < cmykColorSpace.getNumComponents()) {
            throw new IllegalArgumentException(
                    "cmykColorComponents.length(" + cmykColorComponents.length
                    + ") < cmykColorSpace.numComponents(" + cmykColorSpace.getNumComponents() + ")");
        }
        return cmykColorSpace.toCIEXYZ(cmykColorComponents);
    }

    /**
     * Returns an array of {@code CMYK} color components converted from specified persistable color object.
     *
     * @param cmykColorComponents the persistable color object whose {@code RGBA} color components are converted.
     * @param cmykColorSpace      a target {@code CMYK} color space.
     * @param rgbColorSpace       an auxiliary {@code RGB} color space; may be {@code null}.
     * @return an array of converted color components.
     */
    public static float[] cmykToRgb(final float[] cmykColorComponents, final ColorSpace cmykColorSpace,
                                    final ColorSpace rgbColorSpace) {
        Objects.requireNonNull(cmykColorComponents, "cmykColorComponents is null");
        Objects.requireNonNull(cmykColorSpace, "cmykColorSpace is null");
        if (rgbColorSpace == null) {
            return cmykColorSpace.fromRGB(cmykColorComponents);
        }
        return cmykColorSpace.fromCIEXYZ(cmykToCiexyz(cmykColorComponents, rgbColorSpace));
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns a {@code 3}-long hexadecimal string representation of specified colors.
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
        return String.format(
                "%1$x%2$x%3$x",
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
        return toCssRgbHexadecimalNotation3(colors) +
               (colors.length > 3 ? String.format("%1$x", (colors[3] >> 4) & 0xF) : "0");
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
        return String.format(
                "%1$02x%2$02x%3$02x",
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
        return toCssRgbHexadecimalNotation6(toColors(components));
    }

    /**
     * Returns a {@code 8}-long hexadecimal string representation of specified colors.
     *
     * @return a hexadecimal notation current colors represented as {@code rrggbbaa}.
     * @see #toCssRgbHexadecimalNotation6(int[])
     * @see <a href="https://www.w3.org/TR/css-color-4/#hex-color">5.2. The RGB Hexadecimal Notations: '#RRGGBB'</a>
     * (CSS Color Module Level 4)
     */
    public static String toCssRgbHexadecimalNotation8(final int[] colors) {
        Objects.requireNonNull(colors, "colors is null");
        return toCssRgbHexadecimalNotation6(colors) +
               (colors.length > 3 ? String.format("%1$02x", colors[3] & 0xFF) : "00");
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
        return toCssRgbHexadecimalNotation8(toColors(components));
    }

    /**
     * Parses specified hexadecimal notation, applies parsed values to specified function, and returns the result.
     *
     * @param cssRgbHexadecimalNotation the hexadecimal notation to parse.
     * @param function                  the function to be applied with parsed color values.
     * @param <R>                       result type parameter
     * @return the result of the {@code function}.
     */
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
        if (cssRgbHexadecimalNotation.length() == 3 || cssRgbHexadecimalNotation.length() == 4) {
            final var r = Character.digit(cssRgbHexadecimalNotation.charAt(0), 16);
            final var g = Character.digit(cssRgbHexadecimalNotation.charAt(1), 16);
            final var b = Character.digit(cssRgbHexadecimalNotation.charAt(2), 16);
            final var a = cssRgbHexadecimalNotation.length() == 4 ?
                    Character.digit(cssRgbHexadecimalNotation.charAt(3), 16) : 0;
            return function.apply(r).apply(g).apply(b).apply(a);
        }
        assert cssRgbHexadecimalNotation.length() == 6 || cssRgbHexadecimalNotation.length() == 8;
        final var r = Integer.parseInt(cssRgbHexadecimalNotation, 0, 2, 16);
        final var g = Integer.parseInt(cssRgbHexadecimalNotation, 2, 4, 16);
        final var b = Integer.parseInt(cssRgbHexadecimalNotation, 4, 6, 16);
        final var a = cssRgbHexadecimalNotation.length() == 8 ?
                Integer.parseInt(cssRgbHexadecimalNotation, 6, 8, 16) : 0;
        return function.apply(r).apply(g).apply(b).apply(a);
    }

    // -----------------------------------------------------------------------------------------------------------------

    private JinahyaColorUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
