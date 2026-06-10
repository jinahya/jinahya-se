package com.github.jinahya.awt.color;

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

import com.github.jinahya.lang.IntFieldEnum;
import com.github.jinahya.lang.IntFieldEnumTest;
import org.junit.jupiter.api.Test;

import java.awt.color.ColorSpace;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class JinahyaColorSpaceTypeTest
        extends IntFieldEnumTest<JinahyaColorSpaceType> {

    JinahyaColorSpaceTypeTest() {
        super(JinahyaColorSpaceType.class);
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
                    assertThat(JinahyaColorSpaceType.valueOfFieldValue(fv))
                            .isNotNull()
                            .extracting(IntFieldEnum::getFieldValue).isEqualTo(fv);
                });
        ;
    }
}
