package com.vaishnavi.async.statement.download.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This class represents the response from mocked core banking service
 *
 * @author Vaishnavi Bagal
 * @version 1.0
 * @since 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CoreBankingStatementResponse {

    private int pageNo;
    private int totalCount;
    private List<TransactionDetail> transactions;
    private int perPageCount;
}
