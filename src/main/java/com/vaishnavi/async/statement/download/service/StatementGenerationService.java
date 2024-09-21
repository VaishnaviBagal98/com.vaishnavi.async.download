package com.vaishnavi.async.statement.download.service;

import com.vaishnavi.async.statement.download.dto.request.GenerateStatementRequest;
import com.vaishnavi.async.statement.download.dto.response.GenerateStatementResponse;
import com.vaishnavi.async.statement.download.entity.StatementRequest;
import com.vaishnavi.async.statement.download.entity.StatementRequestStatusCode;
import com.vaishnavi.async.statement.download.repository.StatementRequestRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatementGenerationService {

    @Autowired
    private StatementRequestRecordRepository statementRequestRecordRepository;

    public static StatementRequest mapGenerateStatementRequestToStatementRequest(GenerateStatementRequest generateStatementRequest) {
        return StatementRequest.builder()
                .userId(generateStatementRequest.getUserId())
                .accountNo(generateStatementRequest.getAccountNo())
                .startDate(generateStatementRequest.getStartDate())
                .endDate(generateStatementRequest.getEndDate())
                .build();
    }

    public static GenerateStatementResponse mapStatementRequestToGenerateStatementResponse(StatementRequest statementRequest) {
        return GenerateStatementResponse.builder()
                .reqId(statementRequest.getReqId())
                .userId(statementRequest.getUserId())
                .accountNo(statementRequest.getAccountNo())
                .endDate(statementRequest.getEndDate())
                .startDate(statementRequest.getStartDate())
                .status(statementRequest.getStatus().name())
                .build();
    }

    public GenerateStatementResponse saveRequest(GenerateStatementRequest generateStatementRequest) {
        return mapStatementRequestToGenerateStatementResponse(statementRequestRecordRepository.save(mapGenerateStatementRequestToStatementRequest(generateStatementRequest)));
    }

//    public List<StatementRequest> getStatementRequestRecordByStatus(StatementRequestStatusCode status) {
//       // return statementRequestRecordRepository.findByStatus(status);
//    }
}
