package com.example.SynalogikTest.service;

import com.example.SynalogikTest.model.TextFileStats;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TextFileStatsServiceTests {
    @Test
    public void testAnalyzeTextFile() throws IOException {

        String input = "Hello world & good morning. The date is 18/05/2016";
        MultipartFile file = createTestFile(input);

        TextFileStatsService service = new TextFileStatsService();
        TextFileStats stats = service.analyseTextFile(file);

        assertEquals(9, stats.getTotalWords(), "Total words do not match");
        assertEquals( 4.556f, stats.getAverageWordLength());
        assertEquals(4,stats.getMostFrequentWordLength());

        Map<Integer, Integer> expectedWordLengthCounts = new HashMap<>();
        expectedWordLengthCounts.put(1, 1);
        expectedWordLengthCounts.put(2, 1);
        expectedWordLengthCounts.put(3, 1);
        expectedWordLengthCounts.put(4, 2);
        expectedWordLengthCounts.put(5, 2);
        expectedWordLengthCounts.put(7, 1);
        expectedWordLengthCounts.put(10, 1);

        assertEquals(expectedWordLengthCounts, stats.getWordLengthCounts());

        String formattedString = stats.toFormattedString();
        assertTrue(formattedString.contains("for word lengths of 4 & 5"));
    }

    private MultipartFile createTestFile(String content) {
        return new MockMultipartFile("test.txt", content.getBytes());
    }
}

