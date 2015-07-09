package com.techcasita.phonetic;


import java.util.Set;

public class JavaCompareTo implements Phonex {
    @Override
    public BestMatch bestMatch(final String word, final Set<String> vocabulary) {
        int min = Integer.MAX_VALUE;
        String match = "";
        final String s = word.toLowerCase();

        for (final String v : vocabulary) {
            final int k= Math.abs(s.compareTo(v.toLowerCase()));
            if (min > k) {
                min = k;
                match = v;
            }
        }
        return new Phonex.BestMatch(match, 1.0 - (double) min / Math.max(s.length(),match.length()));
    }
}
