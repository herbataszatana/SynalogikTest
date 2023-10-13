package com.example.SynalogikTest.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import com.example.SynalogikTest.service.TextFileStatsService;
import com.example.SynalogikTest.model.TextFileStats;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class TextFileStatsController {
    @Autowired
    private TextFileStatsService statsService;
    @PostMapping("/analyse")
    @ResponseBody
    public ResponseEntity<String> analyseFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (isTextFile(file)) {
            TextFileStats stats = statsService.analyseTextFile(file);
            return ResponseEntity.ok(stats.toFormattedString());
        } else {
            return ResponseEntity.badRequest().body("Invalid file provided");
        }
    }

    private boolean isTextFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("text");
    }
}