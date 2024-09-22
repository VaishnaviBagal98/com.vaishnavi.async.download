package com.vaishnavi.async.statement.download.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * This class represents the response from mocked core banking service
 *
 * @author Vaishnavi Bagal
 * @version 1.0
 * @since 1.0
 */
@Builder
@Data
public class CoreBankingStatementResponse {

    private int pageNo;
    private int totalCount;
    private List<TransactionDetail> transactions;
    private int perPageCount;
}
