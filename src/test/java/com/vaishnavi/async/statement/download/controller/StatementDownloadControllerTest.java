package com.vaishnavi.async.statement.download.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.openMocks;

class StatementDownloadControllerTest {


    private StatementDownloadController statementDownloadController;

    @BeforeEach
    void setUp() {
        openMocks(this);
        statementDownloadController = new StatementDownloadController();
    }

    @Test
    void testDownloadFile_Success() throws IOException {

        String filename = "test.txt";
        String userDir = System.getProperty("user.dir");
        File file = new File(userDir, filename);

        file.deleteOnExit();
        file.createNewFile();

        ResponseEntity<FileSystemResource> response = statementDownloadController.downloadFile(filename);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("attachment; filename=test.txt", response.getHeaders().getFirst(HttpHeaders.CONTENT_DISPOSITION));
        assertEquals(new FileSystemResource(file), response.getBody());
    }

    @Test
    void testDownloadFile_FileNotFound() {
        String filename = "abc.txt";

        ResponseEntity<FileSystemResource> response = statementDownloadController.downloadFile(filename);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}