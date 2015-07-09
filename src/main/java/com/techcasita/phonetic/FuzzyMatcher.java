package com.techcasita.phonetic;

import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.Set;

public class FuzzyMatcher  implements Phonex {
    @Override
    public BestMatch bestMatch(final String word, final Set<String> vocabulary) {
        int diff = 0;
        String match = null;

        for (final String v : vocabulary) {
            final int k = StringUtils.getFuzzyDistance(word.toLowerCase(), v.toLowerCase(), Locale.US);
            if (diff < k) {
                diff = k;
                match = v;
            }
        }
        return new Phonex.BestMatch(match, diff / word.length());
    }
}
