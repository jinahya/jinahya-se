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

import java.awt.color.ICC_ColorSpace;
import java.util.List;

public final class JinahyaICC_ColorSpaceUtils {

    /**
     * Returns an unmodifiable list of {@link ICC_ColorSpace}s available on the system.
     *
     * @return an unmodifiable list of {@link ICC_ColorSpace}s.
     */
    public static List<ICC_ColorSpace> getIccColorSpaces() {
        return JinahyaICC_ProfileUtils.getIccProfiles()
                .stream()
                .map(ICC_ColorSpace::new)
                .toList();
    }

    // -----------------------------------------------------------------------------------------------------------------
    private JinahyaICC_ColorSpaceUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
