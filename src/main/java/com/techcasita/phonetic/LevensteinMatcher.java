package com.techcasita.phonetic;


import org.apache.commons.lang3.StringUtils;

import java.util.Set;

public class LevensteinMatcher implements Phonex {
    @Override
    public BestMatch bestMatch(final String word, final Set<String> vocabulary) {
        int diff = Integer.MAX_VALUE;
        String match = null;

        for (final String v : vocabulary) {
            final int k = StringUtils.getLevenshteinDistance(word.toLowerCase(), v.toLowerCase());
            if (diff > k) {
                diff = k;
                match = v;
            }
        }
        return new Phonex.BestMatch(match, diff / word.length());
    }
}
