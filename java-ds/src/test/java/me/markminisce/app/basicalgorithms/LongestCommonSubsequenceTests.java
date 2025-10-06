package me.markminisce.app.basicalgorithms;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LongestCommonSubsequenceTests {

    private LongestCommonSubsequence testObj; 

    @Test
    void test1() {
        testObj = new LongestCommonSubsequence("Caravan", "Carve"); 
        int lcs = testObj.getLengthLongestCommonSubsequence(); 
        assertEquals(4, lcs); 
    }

    @Test
    void identicalWords() {
        testObj = new LongestCommonSubsequence("elephant", "elephant");
        assertEquals(8, testObj.getLengthLongestCommonSubsequence());
    }

    @Test
    void noCommonLetters() {
        testObj = new LongestCommonSubsequence("apple", "quiz");
        assertEquals(0, testObj.getLengthLongestCommonSubsequence());
    }

    @Test
    void partialOverlapLetters() {
        testObj = new LongestCommonSubsequence("mountain", "contain");
        assertEquals(6, testObj.getLengthLongestCommonSubsequence()); 
    }

    @Test
    void repeatedLetters() {
        testObj = new LongestCommonSubsequence("committee", "committee");
        assertEquals(9, testObj.getLengthLongestCommonSubsequence());
    }

    @Test
    void oneEmptyString() {
        testObj = new LongestCommonSubsequence("", "banana");
        assertEquals(0, testObj.getLengthLongestCommonSubsequence());
    }

    @Test
    void bothEmptyStrings() {
        testObj = new LongestCommonSubsequence("", "");
        assertEquals(0, testObj.getLengthLongestCommonSubsequence());
    }

    @Test
    void oneStringIsSubsequenceOfOther() {
        testObj = new LongestCommonSubsequence("subsequence", "sequence");
        assertEquals(8, testObj.getLengthLongestCommonSubsequence());
    }

    @Test
    void scatteredMatches() {
        testObj = new LongestCommonSubsequence("algorithm", "logarithm");
        assertEquals(7, testObj.getLengthLongestCommonSubsequence());
    }

    @Test
    void oneEmptyStringWithWords() {
        testObj = new LongestCommonSubsequence(
            "",
            "sun moon stars sky cloud rain snow wind"
        );
        assertEquals(0, testObj.getLengthLongestCommonSubsequence());
    }

    @Test
    void bothEmptyStringsWithWords() {
        testObj = new LongestCommonSubsequence("", "");
        assertEquals(0, testObj.getLengthLongestCommonSubsequence());
    }
}
