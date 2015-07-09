package com.techcasita.phonetic;

import org.apache.commons.lang3.StringUtils;

import java.util.Set;


public class JaroWinklerMatcher implements Phonex {
    @Override
    public BestMatch bestMatch(final String word, final Set<String> vocabulary) {
        double diff = 0;
        String match = null;

        for (final String v : vocabulary) {
            final double k = StringUtils.getJaroWinklerDistance(word.toLowerCase(), v.toLowerCase());
            if (diff < k) {
                diff = k;
                match = v;
            }
        }
        return new Phonex.BestMatch(match, diff / word.length());
    }
}
