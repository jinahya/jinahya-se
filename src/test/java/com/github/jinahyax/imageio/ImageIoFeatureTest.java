package com.github.jinahyax.imageio;

import java.util.Objects;

abstract class ImageIoFeatureTest<T extends ImageIoFeature> {

    ImageIoFeatureTest(final Class<T> featureClass) {
        super();
        this.featureClass = Objects.requireNonNull(featureClass, "featureClass is null");
    }

    final Class<T> featureClass;
}
