package com.vaishnavi.async.statement.download.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * This class represents core banking statement generation request
 *
 * @author Vaishnavi Bagal
 * @version 1.0
 * @since 1.0
 */
@Builder
@Data
public class CoreBankingStatementRequest {

    private int pageNo;
    private Long accountNo;
    private Date startDate;
    private Date endDate;
    private int perPageCount;
}
