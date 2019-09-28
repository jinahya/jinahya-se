package com.github.jinahya.imageio;

import javax.imageio.ImageIO;
import java.util.List;

import static javax.imageio.ImageIO.getReaderFormatNames;
import static javax.imageio.ImageIO.getWriterFormatNames;

/**
 * An image feature for informal format names.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 * @see ImageIO#getReaderFormatNames()
 * @see ImageIO#getWriterFormatNames()
 */
public class ImageIoFormatName extends ImageIoFeature {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns a list of all available features for format names.
     *
     * @return a list of image io format names.
     */
    public static List<ImageIoFormatName> availableImageIoFormatNames() {
        return list(ImageIoFormatName.class, getReaderFormatNames(), getWriterFormatNames());
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ImageIoFormatName)) {
            return false;
        }
        return fieldsEqual(obj);
    }
}