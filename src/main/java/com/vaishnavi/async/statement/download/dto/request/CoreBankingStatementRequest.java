package com.vaishnavi.async.statement.download.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class CoreBankingStatementRequest {

    private int pageNo;
    private Long accountNo;
    private Date startDate;
    private Date endDate;
    private int perPageCount;

}
