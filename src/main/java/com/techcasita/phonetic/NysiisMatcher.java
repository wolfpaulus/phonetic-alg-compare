package com.techcasita.phonetic;

import org.apache.commons.codec.language.Nysiis;
import java.util.Set;

public class NysiisMatcher implements Phonex {

    public Phonex.BestMatch bestMatch(final String word, final Set<String> vocabulary) {
        final Nysiis nysiis = new Nysiis(true);

        final String s = nysiis.encode(word.toLowerCase());
        for (final String v : vocabulary) {
            final String t = nysiis.encode(v.toLowerCase());
            if (s.equals(t)) {
                return new Phonex.BestMatch(v, 1.0);
            }

        }
        return new Phonex.BestMatch("", 1 / 100.0);
    }
}



