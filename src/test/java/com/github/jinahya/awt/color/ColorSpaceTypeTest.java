package com.github.jinahya.awt.color;

import com.github.jinahya.lang.IntFieldEnum;
import com.github.jinahya.lang.IntFieldEnumTest;
import org.junit.jupiter.api.Test;

import java.awt.color.ColorSpace;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class ColorSpaceTypeTest
        extends IntFieldEnumTest<ColorSpaceType> {

    ColorSpaceTypeTest() {
        super(ColorSpaceType.class);
    }

    @Test
    void __() {
        Arrays.stream(ColorSpace.class.getFields())
                .filter(f -> {
                    final int modifiers = f.getModifiers();
                    return Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers);
                })
                .filter(f -> f.getType() == int.class)
                .filter(f -> f.getName().startsWith("TYPE_"))
                .mapToInt(f -> {
                    try {
                        return f.getInt(null);
                    } catch (final IllegalAccessException iae) {
                        throw new RuntimeException("failed to get value of " + f, iae);
                    }
                })
                .forEach(fv -> {
                    assertThat(ColorSpaceType.valueOfFieldValue(fv))
                            .isNotNull()
                            .extracting(IntFieldEnum::getFieldValue).isEqualTo(fv);
                });
        ;
    }
}
