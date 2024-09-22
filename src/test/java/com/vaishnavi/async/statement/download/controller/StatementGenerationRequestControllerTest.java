package com.vaishnavi.async.statement.download.controller;

import com.vaishnavi.async.statement.download.dto.request.GenerateStatementRequest;
import com.vaishnavi.async.statement.download.dto.response.GenerateStatementResponse;
import com.vaishnavi.async.statement.download.dto.response.Response;
import com.vaishnavi.async.statement.download.repository.StatementRequestRecordRepository;
import com.vaishnavi.async.statement.download.service.StatementGenerationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class StatementGenerationRequestControllerTest {

    @InjectMocks
    private StatementGenerationRequestController statementGenerationRequestController;

    @Mock
    private StatementGenerationService statementGenerationService;

    @Mock
    private StatementRequestRecordRepository statementRequestRecordRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateRequest_Success() {

        GenerateStatementRequest request = new GenerateStatementRequest();

        GenerateStatementResponse savedData = new GenerateStatementResponse();
        when(statementGenerationService.saveRequest(request)).thenReturn(savedData);

        ResponseEntity<Response> response = statementGenerationRequestController.createRequest(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Request Created Successfully", Objects.requireNonNull(response.getBody()).getMessage().getFirst());
        assertEquals(savedData, response.getBody().getData());
    }

    @Test
    void testCreateRequest_ValidationError() {

    }
}
