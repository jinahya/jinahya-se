package com.github.jinahya.util;

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

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Locale;
import java.util.stream.Stream;

import static com.github.jinahya.util.LocaleUtils.valuesOfDisplayCountry;
import static com.github.jinahya.util.LocaleUtils.valuesOfDisplayCountryInEnglish;
import static com.github.jinahya.util.LocaleUtils.valuesOfDisplayLanguage;
import static com.github.jinahya.util.LocaleUtils.valuesOfDisplayLanguageInEnglish;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class LocaleUtilsTest {

    private static Stream<Locale> getLocaleStream() {
        return Stream.of(Locale.getAvailableLocales());
    }

    private static Stream<Locale> getLocaleWithNonBlankDisplayCountryStream() {
        return getLocaleStream()
                .filter(l -> !l.getDisplayCountry(Locale.ENGLISH).isBlank());
    }

    private static Stream<Locale> getLocaleWithNonBlankDisplayLanguageStream() {
        return getLocaleStream()
                .filter(l -> !l.getDisplayLanguage(Locale.ENGLISH).isBlank());
    }

    @MethodSource({"getLocaleWithNonBlankDisplayCountryStream"})
    @ParameterizedTest
    void valuesOfDisplayCountry__(final Locale locale) {
        final var country = locale.getDisplayCountry(locale);
        final var values = valuesOfDisplayCountry(country, locale);
        assertThat(values)
                .isNotEmpty()
                .extracting(v -> v.getDisplayCountry(locale))
                .containsOnly(country);
    }

    @MethodSource({"getLocaleWithNonBlankDisplayCountryStream"})
    @ParameterizedTest
    void valuesOfDisplayCountryInEnglish__(final Locale locale) {
        final var country = locale.getDisplayCountry(Locale.ENGLISH);
        final var values = valuesOfDisplayCountryInEnglish(country);
        assertThat(values)
                .isNotEmpty()
                .extracting(v -> v.getDisplayCountry(Locale.ENGLISH))
                .containsOnly(country);
    }

    @MethodSource({"getLocaleWithNonBlankDisplayLanguageStream"})
    @ParameterizedTest
    void valuesOfDisplayLanguage__(final Locale locale) {
        final var language = locale.getDisplayLanguage(locale);
        final var values = valuesOfDisplayLanguage(language, locale);
        assertThat(values)
                .isNotEmpty()
                .extracting(v -> v.getDisplayLanguage(locale))
                .containsOnly(language);
    }

    @MethodSource({"getLocaleWithNonBlankDisplayLanguageStream"})
    @ParameterizedTest
    void valuesOfDisplayLanguageInEnglish__(final Locale locale) {
        final var language = locale.getDisplayLanguage(Locale.ENGLISH);
        final var values = valuesOfDisplayLanguageInEnglish(language);
        assertThat(values)
                .isNotEmpty()
                .extracting(v -> v.getDisplayLanguage(Locale.ENGLISH))
                .containsOnly(language);
    }
}
