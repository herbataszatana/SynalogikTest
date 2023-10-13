package com.example.SynalogikTest.model;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextFileStatsTests {
    @Test
    public void testToFormattedString() {

        //For the sentence "Hello world & good morning. The date is 18/05/2016
        TextFileStats stats = new TextFileStats();
        stats.setTotalWords(9);
        Map<Integer, Integer> wordLengthCounts = new HashMap<>();
        wordLengthCounts.put(1, 1);
        wordLengthCounts.put(2, 1);
        wordLengthCounts.put(3, 1);
        wordLengthCounts.put(4, 2);
        wordLengthCounts.put(5, 2);
        wordLengthCounts.put(7, 1);
        wordLengthCounts.put(10, 1);
        stats.setWordLengthCounts(wordLengthCounts);
        stats.setAverageWordLength(4.556f);
        stats.setMostFrequentWordLength(4);

        String expected = "Word count = 9\n" +
                "Average word length = 4.556\n" +
                "Number of words of each length:\n" +
                "Number of words of length 1 is 1\n" +
                "Number of words of length 2 is 1\n" +
                "Number of words of length 3 is 1\n" +
                "Number of words of length 4 is 2\n" +
                "Number of words of length 5 is 2\n" +
                "Number of words of length 7 is 1\n" +
                "Number of words of length 10 is 1\n" +
                "The most frequently occurring word length is 4, for word lengths of 4 & 5\n";

        assertEquals(expected, stats.toFormattedString());
    }
}
