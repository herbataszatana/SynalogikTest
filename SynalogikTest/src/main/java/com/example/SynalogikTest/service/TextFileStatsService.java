package com.example.SynalogikTest.service;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.SynalogikTest.model.TextFileStats;

@Service
public class TextFileStatsService {
    /*
    analyseTextFile method does the satistical operations based on the provided file.
     */
    public TextFileStats analyseTextFile(MultipartFile file) throws IOException {
        String fileContents = new String(file.getBytes());

        // Initialize variables to track stats
        int totalWords = 0;
        float totalChars = 0f;
        Map<Integer, Integer> wordLengthCounts = new HashMap<>();
        // StringBuilder to build up each word
        StringBuilder currentWord = new StringBuilder();
        // Loop through each character
        for (char c : fileContents.toCharArray()) {
            // If it's a letter, digit or allowed special char, add to current word
            if (Character.isLetterOrDigit(c) || c == '&' || c == '/' || c == '-') {
                currentWord.append(c);

            }
            // If we hit whitespace or punctuation, word is complete
            else if (currentWord.length() > 0) {
                // Increment stats for completed word
                int length = currentWord.length();
                totalWords++;
                totalChars += length;
                wordLengthCounts.put(length, wordLengthCounts.getOrDefault(length, 0) + 1);
                // Reset currentWord for next word
                currentWord.setLength(0);
            }
        }

        // Handle last word at end of file
        if (currentWord.length() > 0) {
            int length = currentWord.length();
            totalWords++;
            totalChars += length;
            wordLengthCounts.put(length, wordLengthCounts.getOrDefault(length, 0) + 1);
        }
        // Find most frequent word length
        int mostFrequentWordLength = 0;
        int maxCount = 0;
        for (Map.Entry<Integer, Integer> entry : wordLengthCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostFrequentWordLength = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        // Calculate average word length
        float averageWordLength = totalChars / totalWords;
        averageWordLength = Math.round(averageWordLength * 1000) / 1000f;
        // Create TextFileStats object
        TextFileStats stats = new TextFileStats();
        stats.setTotalWords(totalWords);
        stats.setAverageWordLength(averageWordLength);
        stats.setMostFrequentWordLength(mostFrequentWordLength);
        stats.setWordLengthCounts(wordLengthCounts);
        // Find all most frequent lengths
        List<Integer> mostFrequentLengths = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : wordLengthCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostFrequentLengths.clear();
                mostFrequentLengths.add(entry.getKey());
                maxCount = entry.getValue();

            } else if (entry.getValue() == maxCount) {
                mostFrequentLengths.add(entry.getKey());
            }
        }

        stats.setMostFrequentWordLengths(mostFrequentLengths);

        return stats;
    }
}