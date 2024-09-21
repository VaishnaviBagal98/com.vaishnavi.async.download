package com.vaishnavi.async.statement.download.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

/**
 * This class holds the representation of statement generation request
 * from client
 *
 * @author Vaishnavi Bagal
 * @version 1.0
 * @since 1.0
 */
@Data
public class GenerateStatementRequest {

    @NotNull(message = "Account No is Mandatory")
    private Long accountNo;

    @NotNull(message = "User Id is Mandatory")
    private Long userId;

    @NotNull(message = "Start Date is Mandatory")
    private Date startDate;

    @NotNull(message = "End Date is Mandatory")
    private Date endDate;

}
