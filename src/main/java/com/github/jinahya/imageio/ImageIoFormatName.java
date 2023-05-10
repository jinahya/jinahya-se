package com.github.jinahya.imageio;

import javax.imageio.ImageIO;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static javax.imageio.ImageIO.getReaderFormatNames;
import static javax.imageio.ImageIO.getWriterFormatNames;

/**
 * An image io feature for format names.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 * @see ImageIO#getReaderFormatNames()
 * @see ImageIO#getWriterFormatNames()
 */
public final class ImageIoFormatName extends ImageIoFeature {

    /**
     * Returns a list of all available features for format names.
     *
     * @return a list of image io format names.
     */
    public static List<ImageIoFormatName> availableImageIoFormatNames() {
        return list(
                ImageIoFormatName.class,
                new HashSet<>(Arrays.asList(getReaderFormatNames())),
                new HashSet<>(Arrays.asList(getWriterFormatNames()))
        );
    }

    private ImageIoFormatName(final String value, final boolean readable, final boolean writable) {
        super(value, readable, writable);
    }
}
