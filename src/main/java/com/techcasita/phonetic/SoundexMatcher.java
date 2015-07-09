package com.techcasita.phonetic;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.language.Soundex;

import java.util.Set;

public class SoundexMatcher implements Phonex {

    /**
     * Soundex difference ranges from 0 through 4:
     * 0 indicates little or no similarity,
     * and 4 indicates strong similarity or identical values.
     *
     * @param s          {@link String} the word to match
     * @param vocabulary {@link Set<String>}
     * @return {@link com.techcasita.phonetic.Phonex.BestMatch}
     */
    public Phonex.BestMatch bestMatch(final String s, final Set<String> vocabulary) {
        final Soundex soundex = new Soundex();

        int diff = Integer.MIN_VALUE;
        String match = null;
        for (final String v : vocabulary) {
            try {
                final int k = soundex.difference(s.toLowerCase(), v.toLowerCase());
                if (diff < k) {
                    diff = k;
                    match = v;
                }
            } catch (EncoderException e) {
                //
            }
        }
        return new Phonex.BestMatch(match, diff / 4.0);
    }
}
