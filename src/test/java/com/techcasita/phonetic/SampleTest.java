package com.techcasita.phonetic;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;


public class SampleTest {
    final String[][] mTuples = new String[][]{
            {"Bland", "blend"},
            {"Bra", "brush"},
            {"Player", "blur"},
            {"Price", "brush"},
            {"Diane", "cyan"},
            {"Boston", "emboss"},
            {"in Boston", "emboss pen"},
            {"sheep", "jeep"},
            {"cheap", "jeep"},
            {"their", "there"},
            {"they are", "there"}
    };

    final Set<String> mVocab = new HashSet<>();


    @Before
    public void setup() {
        mVocab.addAll(Arrays.asList(ArtistVocab.Tasks));
        mVocab.addAll(Arrays.asList(ArtistVocab.Tools));
        mVocab.addAll(Arrays.asList(ArtistVocab.Styles));
        mVocab.addAll(Arrays.asList(ArtistVocab.ColorNames));
        mVocab.addAll(Arrays.asList(ArtistVocab.Combinations));
        mVocab.addAll(Arrays.asList(ArtistVocab.Homophones));
        mVocab.addAll(Arrays.asList(ArtistVocab.Synophones));
        System.out.println("=== Vocab Size === " + mVocab.size());

    }

    public Phonex.BestMatch lookup(final Phonex phonex, final String input, final Set<String> vocab) {
        return phonex.bestMatch(input, vocab);
    }


    @Test
    public void testSoundex() {
        Class[] phoneticMatchers = new Class[]{
                SoundexMatcher.class,
                RefinedSoundexMatcher.class,
                BMMatcher.class,
                LevensteinMatcher.class,
                MetaphoneMatcher.class,
                DMetaphoneMatcher.class,
                NysiisMatcher.class,
                MatchRatingMatcher.class,
                Caverphone1Matcher.class,
                Caverphone2Matcher.class,
                JavaCompareTo.class,
                JaroWinklerMatcher.class,
                FuzzyMatcher.class
        };


        for (Class cls : phoneticMatchers) {
            try {
                Phonex phonex = (Phonex) cls.newInstance();
                System.out.println("=== " + phonex.getClass());

                int errors = 0;
                for (String[] tupple : mTuples) {
                    Phonex.BestMatch bm = lookup(phonex, tupple[0], mVocab);
                    try {
                        assertEquals(tupple[1], bm.match);
                    } catch (org.junit.ComparisonFailure e) {
                        errors++;
                        //System.out.println("for input: " + tupple[0] + " " + e.getMessage());
                    }
                }
                System.out.println("Correction Rate [%]: " + (100 - 100 * errors / mTuples.length));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
