package com.vaishnavi.async.statement.download.controller;

import com.vaishnavi.async.statement.download.dto.request.GenerateStatementRequest;
import com.vaishnavi.async.statement.download.dto.response.Response;
import com.vaishnavi.async.statement.download.repository.StatementRequestRecordRepository;
import com.vaishnavi.async.statement.download.service.StatementGenerationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This controller contains statement-request end point
 *
 * @author Vaishnavi Bagal
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/v1/statement-requests")
@Slf4j
public class StatementGenerationRequestController {

    @Autowired
    private StatementGenerationService statementGenerationService;

    @Autowired
    private StatementRequestRecordRepository statementRequestRecordRepository;

    @PostMapping
    public ResponseEntity<Response> createRequest(@Valid @RequestBody GenerateStatementRequest generateStatementRequest) {
        log.info("Inside the StatementGenerationService");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.builder()
                        .data(statementGenerationService.saveRequest(generateStatementRequest))
                        .message(List.of("Request Created Successfully")).build());
    }
}
