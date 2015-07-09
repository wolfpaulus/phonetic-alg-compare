package com.techcasita.phonetic;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.language.Caverphone2;

import java.util.Set;

public class Caverphone2Matcher implements Phonex {

    @Override
    public BestMatch bestMatch(final String word, final Set<String> vocabulary) {
        final Caverphone2 caverphone = new Caverphone2();

        for (final String v : vocabulary) {
            try {
                if (caverphone.isEncodeEqual(word.toLowerCase(), v.toLowerCase())) {
                    return new BestMatch(v, 1.0);
                }
            } catch (EncoderException e) {
                e.printStackTrace();
            }
        }
        return new Phonex.BestMatch("", 1 / 100.0);
    }
}