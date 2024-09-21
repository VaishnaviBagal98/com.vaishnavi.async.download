package com.vaishnavi.async.statement.download.service;

import com.vaishnavi.async.statement.download.dto.request.CoreBankingStatementRequest;
import com.vaishnavi.async.statement.download.dto.response.CoreBankingStatementResponse;
import com.vaishnavi.async.statement.download.dto.response.TransactionDetail;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This is a service class which is mocked for Core Banking service
 *
 * @author Vaishnavi Bagal
 * @version 1.0
 * @since 1.0
 */
@Service
public class CoreBankingService {

    private final Random randomGenerator = new Random();

    /**
     * This method returns all the transactions for given account in specified period
     * Note this is mocked method.
     *
     * @param coreBankingStatementRequest this request consist of all the required fields to get the transactions.
     * @return CoreBankingStatementResponse List of all the transactions in pageable format .
     */
    public CoreBankingStatementResponse getTransactions(CoreBankingStatementRequest coreBankingStatementRequest) {
        int mockTotalCount = 10000;

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

    /**
     * Generates the mock transaction details
     *
     * @return TransactionDetail all the details related to the transactions
     */
    private TransactionDetail mockTransactionDetail() {
        return TransactionDetail.builder()
                .transactionId(randomGenerator.nextLong(20001, 99999))
                .amount(new BigDecimal(randomGenerator.nextLong(1, 9999999)))
                .type(randomGenerator.nextBoolean() ? "CR" : "DR")
                .description("mock")
                .build();
    }
}
