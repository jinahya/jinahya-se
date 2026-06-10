package com.github.jinahyax.imageio;

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

import javax.imageio.ImageIO;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static javax.imageio.ImageIO.getReaderFileSuffixes;
import static javax.imageio.ImageIO.getWriterFileSuffixes;

/**
 * An image io feature for file suffixes.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 * @see ImageIO#getReaderFileSuffixes()
 * @see ImageIO#getWriterFileSuffixes()
 */
public final class ImageIoFileSuffix extends ImageIoFeature {

    /**
     * Returns a list of all available features for file suffixes.
     *
     * @return a list of image io file suffixes.
     */
    public static List<ImageIoFileSuffix> availableImageIoFileSuffixes() {
        return list(
                ImageIoFileSuffix.class,
                new HashSet<>(Arrays.asList(getReaderFileSuffixes())),
                new HashSet<>(Arrays.asList(getWriterFileSuffixes()))
        );
    }

    private ImageIoFileSuffix(final String value, final boolean readable, final boolean writable) {
        super(value, readable, writable);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ImageIoFileSuffix)) return false;
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
