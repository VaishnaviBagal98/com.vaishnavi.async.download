package com.vaishnavi.async.statement.download.service;

import com.vaishnavi.async.statement.download.entity.StatementRequestRecord;
import org.springframework.stereotype.Service;

@Service
public class ProduceRequestDetailsService {

    public String processStatementRequest(StatementRequestRecord statementRequestRecord) {
        return "User " + statementRequestRecord.getReqId() + " processed successfully!";
    }
}
