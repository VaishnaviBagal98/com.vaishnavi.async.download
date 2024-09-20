package com.vaishnavi.async.statement.download.controller;

import com.vaishnavi.async.statement.download.dto.response.GenerateStatementResponse;
import com.vaishnavi.async.statement.download.dto.response.Response;
import com.vaishnavi.async.statement.download.repository.StatementRequestRecordRepository;
import com.vaishnavi.async.statement.download.dto.request.GenerateStatementRequest;
import com.vaishnavi.async.statement.download.service.StatementGenerationService;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/v1/statement-requests")
public class StatementGenerationRequestController {

    @Autowired
    private StatementGenerationService statementGenerationService;

    @Autowired
    private StatementRequestRecordRepository statementRequestRecordRepository;

    @PostMapping
    public ResponseEntity<Response> createRequest(@Valid @RequestBody GenerateStatementRequest generateStatementRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.builder()
                        .data(statementGenerationService.saveRequest(generateStatementRequest))
                        .message(Arrays.asList("Request Created Successfully")).build());
    }
}
