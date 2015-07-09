package com.techcasita.phonetic;

import org.apache.commons.codec.language.DoubleMetaphone;
import java.util.Set;

public class DMetaphoneMatcher implements Phonex {

    @Override
    public BestMatch bestMatch(final String word, final Set<String> vocabulary) {
        final DoubleMetaphone metaphone = new DoubleMetaphone();

        for (final String v : vocabulary) {
            if (metaphone.isDoubleMetaphoneEqual(word.toLowerCase(), v.toLowerCase())) {
                return new Phonex.BestMatch(v, 1.0);
            }
        }
        return new Phonex.BestMatch("", 1 / 100.0);
    }
}

