package com.techcasita.phonetic;

import org.apache.commons.codec.language.MatchRatingApproachEncoder;
import java.util.Set;


public class MatchRatingMatcher implements Phonex {

    @Override
    public BestMatch bestMatch(final String word, final Set<String> vocabulary) {
        final MatchRatingApproachEncoder matcher = new MatchRatingApproachEncoder();

        for (final String v : vocabulary) {
            if (matcher.isEncodeEquals(word.toLowerCase(), v.toLowerCase())) {
                return new Phonex.BestMatch(v, 1.0);
            }
        }
        return new Phonex.BestMatch("", 1 / 100.0);
    }
}
