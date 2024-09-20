package com.vaishnavi.async.statement.download.controller;


import com.vaishnavi.async.statement.download.dboperation.StatementRequestRecordRepository;
import com.vaishnavi.async.statement.download.entity.StatementRequestRecord;
import com.vaishnavi.async.statement.download.service.ProduceRequestDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestSaveController {

    @Autowired
    private ProduceRequestDetailsService produceRequestDetailsService;

    @Autowired
    private StatementRequestRecordRepository statementRequestRecordRepository;

    @PostMapping("/requestSave")
    public ResponseEntity<String> createRequest(@RequestBody StatementRequestRecord statementRequestRecord) {
        StatementRequestRecord savedRequest = statementRequestRecordRepository.save(statementRequestRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRequest.toString());
    }
}
