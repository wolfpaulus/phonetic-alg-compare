package com.techcasita.phonetic;

import org.apache.commons.codec.language.Metaphone;
import java.util.Set;

public class MetaphoneMatcher implements Phonex {

    @Override
    public BestMatch bestMatch(final String word, final Set<String> vocabulary) {
        final Metaphone metaphone = new Metaphone();

        for (final String v : vocabulary) {
            if (metaphone.isMetaphoneEqual(word.toLowerCase(), v.toLowerCase())) {
                return new Phonex.BestMatch(v, 1.0);
            }
        }
        return new Phonex.BestMatch("", 1 / 100.0);
    }
}
