package com.vaishnavi.async.statement.download.service;

import com.vaishnavi.async.statement.download.dto.request.CoreBankingStatementRequest;
import com.vaishnavi.async.statement.download.dto.response.CoreBankingStatementResponse;
import com.vaishnavi.async.statement.download.dto.response.TransactionDetail;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CoreBankingService {

    private Random randomGenerator = new Random();
    private int mockTotalCount = 100000;

    public CoreBankingStatementResponse getTransactions(CoreBankingStatementRequest coreBankingStatementRequest) {
        List<TransactionDetail> mockTransactions = new ArrayList<>(coreBankingStatementRequest.getPerPageCount());
        for (int mockCount = 0; mockCount < coreBankingStatementRequest.getPerPageCount(); mockCount++) {
            mockTransactions.add(mockTransactionDetail());
        }
        return CoreBankingStatementResponse.builder()
                .transactions(mockTransactions)
                .perPageCount(coreBankingStatementRequest.getPerPageCount())
                .totalCount(mockTotalCount)
                .pageNo(coreBankingStatementRequest.getPageNo())
                .build();
    }

    private TransactionDetail mockTransactionDetail() {
        return TransactionDetail.builder()
                .transactionId(randomGenerator.nextLong(20001, 99999))
                .amount(new BigDecimal(randomGenerator.nextLong(1, 9999999)))
                .type(randomGenerator.nextBoolean() ? "CR" : "DR")
                .description("mock")
                .build();
    }
}
