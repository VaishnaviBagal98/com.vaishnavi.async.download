package com.vaishnavi.async.statement.download.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CoreBankingStatementResponse {

    private int pageNo;
    private int totalCount;
    private List<TransactionDetail> transactions ;
    private int perPageCount;
}
