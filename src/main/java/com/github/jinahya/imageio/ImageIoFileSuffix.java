package com.github.jinahya.imageio;

import javax.imageio.ImageIO;
import java.util.List;

import static javax.imageio.ImageIO.getReaderFileSuffixes;
import static javax.imageio.ImageIO.getWriterFileSuffixes;

/**
 * An image feature for file suffixes.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 * @see ImageIO#getReaderFileSuffixes()
 * @see ImageIO#getWriterFileSuffixes()
 */
public class ImageIoFileSuffix extends ImageIoFeature {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns a list of all available features for file suffixes.
     *
     * @return a list of image io file suffixes.
     */
    public static List<ImageIoFileSuffix> availableImageIoFileSuffixes() {
        return list(ImageIoFileSuffix.class, getReaderFileSuffixes(), getWriterFileSuffixes());
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ImageIoFileSuffix)) {
            return false;
        }
        return fieldsEqual(obj);
    }
}