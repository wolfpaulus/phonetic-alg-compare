package com.techcasita.phonetic;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.language.bm.BeiderMorseEncoder;

import java.util.Set;

public class BMMatcher implements Phonex {

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
        final StringEncoder bm = new BeiderMorseEncoder();
        int diff = Integer.MAX_VALUE;
        String match = null;
        try {
            final String encodedInput = bm.encode(s.toLowerCase());
            for (final String v : vocabulary) {
                final String encodedWord = bm.encode(v.toLowerCase());
                int k = encodedWord.compareTo(encodedInput);
                if (diff > k) {
                    diff = k;
                    match = v;
                }
            }
        } catch (EncoderException e) {
            //
        }
        return new Phonex.BestMatch(match, 1 / (diff + 1.0));
    }
}
