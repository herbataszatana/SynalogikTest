package com.example.SynalogikTest.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import com.example.SynalogikTest.service.TextFileStatsService;
import com.example.SynalogikTest.model.TextFileStats;
import org.springframework.web.multipart.MultipartFile;

/*
Below is the REST controller that analyses a text file and returns statistics.
It handles POST request and takes a MultipartFile as a request parameter named "file".
It first calls the isTextFile method to check if the file is a text file.
If it is a text file, it calls the statsService to analyze the text file which returns a
TextFileStats object. It converts the TextFileStats to a formatted string and returns it
in the response with 200 OK status.
If the file is not a text file, it returns a 400 Bad Request response with an error message.
 */
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
    /*
    itTextFile() checks if the uploaded file is not null and it's content type is text.
    */
    private boolean isTextFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("text");
    }
}