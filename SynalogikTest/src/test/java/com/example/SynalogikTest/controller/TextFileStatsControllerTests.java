package com.example.SynalogikTest.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TextFileStatsControllerTests {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testAnalyzeEndpoint() throws Exception {

        MockMultipartFile file = new MockMultipartFile(
                "file",
                "originalFilename",
                MediaType.TEXT_PLAIN_VALUE,
                "content".getBytes()
        );

        MvcResult result = mvc.perform(multipart("/analyse").file(file))
                .andExpect(status().isOk())
                .andReturn();

        // assert response
    }

}
