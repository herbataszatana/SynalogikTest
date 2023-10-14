package com.example.SynalogikTest.model;

import java.util.List;
import java.util.Map;

public class TextFileStats {
    private int totalWords;
    private float averageWordLength;
    private int mostFrequentWordLength;
    private Map<Integer, Integer> wordLengthCounts;
    private List<Integer> mostFrequentWordLengths;

    /*
    Setter and getter methods for the TextFileStats
     */
    public void setTotalWords(int totalWords) {
        this.totalWords = totalWords;
    }
    public void setAverageWordLength(float averageWordLength) {
        this.averageWordLength = averageWordLength;
    }
    public void setMostFrequentWordLength(int mostFrequentWordLength) {
        this.mostFrequentWordLength = mostFrequentWordLength;
    }
    public void setWordLengthCounts(Map<Integer, Integer> wordLengthCounts) {
        this.wordLengthCounts = wordLengthCounts;
    }
    public void setMostFrequentWordLengths(List<Integer> mostFrequentWordLengths) {
        this.mostFrequentWordLengths = mostFrequentWordLengths;
    }
    public List<Integer> getMostFrequentWordLengths() {
        return mostFrequentWordLengths;
    }

    public int getMostFrequentWordLengthsSize(){
        return mostFrequentWordLengths.size();
    }
    public int getTotalWords() {
        return totalWords;
    }
    public float getAverageWordLength() {
        return averageWordLength;
    }
    public int getMostFrequentWordLength() {
        return mostFrequentWordLength;
    }
    public Map getWordLengthCounts() {
        return wordLengthCounts ;
    }

    /*
    toFormattedString() generates formated sting with the statistics that is displayed as response
     */
    public String toFormattedString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Word count = ").append(totalWords).append("\n");
        builder.append("Average word length = ").append(String.format("%.3f", averageWordLength)).append("\n");
        builder.append("Number of words of each length:\n");
        for (Map.Entry<Integer, Integer> entry : wordLengthCounts.entrySet()) {
            builder.append("Number of words of length ").append(entry.getKey()).append(" is ").append(entry.getValue()).append("\n");
        }
        int maxCount = wordLengthCounts.values().stream().max(Integer::compareTo).orElse(0);
        builder.append("The most frequently occurring word length is ").append(mostFrequentWordLengths.size()).append(", for word lengths of ");

        boolean first = true;
        for (Map.Entry<Integer, Integer> entry : wordLengthCounts.entrySet()) {
            if (entry.getValue() == maxCount) {
                if (!first) {
                    builder.append(" & ");
                }
                builder.append(entry.getKey());
                first = false;
            }
        }
        builder.append("\n");

        return builder.toString();
    }


}
