package com.example.SynalogikTest.controller;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String analyseFile(@RequestParam("file") MultipartFile file) throws IOException {
        TextFileStats stats = statsService.analyseTextFile(file);
        return stats.toFormattedString();

    }

}