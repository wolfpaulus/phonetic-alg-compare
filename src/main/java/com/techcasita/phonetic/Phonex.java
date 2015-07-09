package com.techcasita.phonetic;


import java.util.Set;

public interface Phonex {

    class BestMatch {
        /**
         * The best matching String based on phonnetc disatace algorithm
         */
        public String match;

        /**
         * Distance metric, how close is the match to the provided input.
         * 0..1, with 1 being a match
         */
        double confidence;

        public BestMatch(final String match, final double confidence) {
            this.match = match;
            this.confidence = confidence;
        }
    }

    /**
     * Find the best matching word in the given vocabulary.
     *
     * @param word       {@link String}
     * @param vocabulary {@link Set<String>}
     * @return {@link com.techcasita.phonetic.Phonex.BestMatch}
     */
    BestMatch bestMatch(final String word, final Set<String> vocabulary);
}
