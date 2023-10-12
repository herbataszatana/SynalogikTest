package com.example.SynalogikTest.service;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.example.SynalogikTest.model.TextFileStats;

@Service
public class TextFileStatsService {
    public TextFileStats analyseTextFile(MultipartFile file) throws IOException {
        String fileContents = new String(file.getBytes());

        int totalWords = 0;
        int totalChars = 0;
        Map<Integer, Integer> wordLengthCounts = new HashMap<>();

        StringBuilder currentWord = new StringBuilder();

        for (char c : fileContents.toCharArray()) {
            if (Character.isLetterOrDigit(c) || c == '&' || c == '/' || c == '-') {
                currentWord.append(c);
            } else if (currentWord.length() > 0) {
                int length = currentWord.length();
                totalWords++;
                totalChars += length;
                wordLengthCounts.put(length, wordLengthCounts.getOrDefault(length, 0) + 1);
                currentWord.setLength(0);
            }
        }

        if (currentWord.length() > 0) {
            int length = currentWord.length();
            totalWords++;
            totalChars += length;
            wordLengthCounts.put(length, wordLengthCounts.getOrDefault(length, 0) + 1);
        }

        int mostFrequentWordLength = 0;
        int maxCount = 0;
        for (Map.Entry<Integer, Integer> entry : wordLengthCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostFrequentWordLength = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        float averageWordLength = (float) totalChars / totalWords;

        TextFileStats stats = new TextFileStats();
        stats.setTotalWords(totalWords);
        stats.setAverageWordLength(averageWordLength);
        stats.setMostFrequentWordLength(mostFrequentWordLength);
        stats.setWordLengthCounts(wordLengthCounts);

        return stats;
    }
}
