package com.vaishnavi.async.statement.download.service;


import com.vaishnavi.async.statement.download.repository.StatementRequestRecordRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
public class StatementGenerationServiceIT {

    @Autowired
    private StatementGenerationService statementGenerationService;

    @Autowired
    private StatementRequestRecordRepository statementRequestRecordRepository;

}
