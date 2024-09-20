package com.vaishnavi.async.statement.download.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Builder
@Data
public class GenerateStatementResponse {

    private UUID reqId;
    private Long accountNo;
    private Long userId;
    private Date startDate;
    private Date endDate;
    private String status;

}
