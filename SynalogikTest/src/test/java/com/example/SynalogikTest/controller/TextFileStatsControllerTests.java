package com.example.SynalogikTest.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TextFileStatsControllerTests {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testAnalyseEndpoint() throws Exception  {

        MockMultipartFile file = new MockMultipartFile(
                "file",
                "originalFilename",
                MediaType.TEXT_PLAIN_VALUE,
                "content".getBytes()
        );
        MvcResult result = mvc.perform(multipart("/analyse").file(file))
                .andExpect(status().isOk())
                .andReturn();
        String responseContent = result.getResponse().getContentAsString();
    }

    @Test
    public void testAnalyzeInvalidFile() throws Exception {

        MockMultipartFile file = new MockMultipartFile("file", "data.pdf",
                MediaType.APPLICATION_PDF_VALUE, "invalid".getBytes());

        mvc.perform(multipart("/analyse").file(file))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void testAnalyzeNoFile() throws Exception {

        mvc.perform(multipart("/analyse"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

}
