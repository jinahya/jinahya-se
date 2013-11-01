/*
 * Copyright 2012 Jin Kwon <jinahya at gmail.com>.
 *
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
 */


package com.github.jinahya.xml.bind;


import com.github.jinahya.xml.bind.WrappedNormalizedString;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.RandomStringUtils;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class WrappedNormalizedStringTest
    extends WrappedValueTest<WrappedNormalizedString, String> {


    public WrappedNormalizedStringTest() {

        super(WrappedNormalizedString.class);
    }


    @Override
    protected String generateRawValue() {

        final Random random = ThreadLocalRandom.current();

        if (random.nextBoolean()) {
            return null;
        }

        return RandomStringUtils.randomAlphanumeric(random.nextInt(128));
    }


}
