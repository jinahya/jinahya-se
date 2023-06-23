package com.github.jinahya.util;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.Collections.synchronizedMap;
import static java.util.Collections.unmodifiableList;
import static java.util.Locale.getAvailableLocales;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

/**
 * Utilities related to {@link Locale}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public final class LocaleUtils {

    private static final Map<Locale, Map<String, List<Locale>>> DISPLAY_COUNTRIES_AND_LOCALES
            = synchronizedMap(new WeakHashMap<>());

    static List<Locale> valuesOfDisplayCountry(final String displayCountry, final Locale inLocale) {
        requireNonNull(displayCountry, "displayCountry is null");
        requireNonNull(inLocale, "inLocale is null");
        return unmodifiableList(
                DISPLAY_COUNTRIES_AND_LOCALES
                        .computeIfAbsent((Locale) inLocale.clone(), k -> new ConcurrentHashMap<>())
                        .computeIfAbsent(
                                displayCountry,
                                k -> stream(getAvailableLocales())
                                        .filter(l -> Objects.equals(l.getDisplayCountry(inLocale), k))
                                        .collect(toList())
                        )
        );
    }

    /**
     * Returns an unmodifiable list of locales which each {@link Locale#getDisplayCountry(Locale) displayCountry},
     * represented in {@link Locale#ENGLISH ENGLISH}, matches specified value.
     *
     * @param displayCountryInEnglish the value of {@link Locale#getDisplayLanguage(Locale) displayLanguage(ENGLISH)} to
     *                                match.
     * @return a list of matched values.
     */
    public static List<Locale> valuesOfDisplayCountryInEnglish(final String displayCountryInEnglish) {
        return valuesOfDisplayCountry(displayCountryInEnglish, Locale.ENGLISH);
    }

    private static final Map<Locale, Map<String, List<Locale>>> DISPLAY_LANGUAGES_AND_LOCALES
            = synchronizedMap(new WeakHashMap<>());

    static List<Locale> valuesOfDisplayLanguage(final String displayLanguage, final Locale inLocale) {
        requireNonNull(displayLanguage, "displayLanguage is null");
        requireNonNull(inLocale, "inLocale is null");
        return unmodifiableList(
                DISPLAY_LANGUAGES_AND_LOCALES
                        .computeIfAbsent((Locale) inLocale.clone(), k -> new ConcurrentHashMap<>())
                        .computeIfAbsent(
                                displayLanguage,
                                k -> stream(getAvailableLocales())
                                        .filter(l -> {
                                            final String v = l.getDisplayLanguage(inLocale);
                                            if (v.trim().isEmpty()) {
                                                return false;
                                            }
                                            return Objects.equals(v, k);
                                        })
                                        .collect(toList())
                        )
        );
    }

    /**
     * Returns an unmodifiable list of locales which each {@link Locale#getDisplayLanguage(Locale) displayLanguage},
     * represented in {@link Locale#ENGLISH ENGLISH}, matches specified value.
     *
     * @param displayLanguageInEnglish the value of {@link Locale#getDisplayLanguage(Locale) displayLanguage(ENGLISH)}
     *                                 to match.
     * @return an optional of matched value; {@link Optional#empty() empty} if none matches.
     */
    public static List<Locale> valuesOfDisplayLanguageInEnglish(final String displayLanguageInEnglish) {
        return valuesOfDisplayLanguage(displayLanguageInEnglish, Locale.ENGLISH);
    }

    private LocaleUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
