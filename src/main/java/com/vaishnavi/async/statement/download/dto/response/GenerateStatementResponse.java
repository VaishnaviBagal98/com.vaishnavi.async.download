package com.vaishnavi.async.statement.download.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * This class represents statement generation response to the client.
 *
 * @author Vaishnavi Bagal
 * @version 1.0
 * @since 1.0
 */
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
