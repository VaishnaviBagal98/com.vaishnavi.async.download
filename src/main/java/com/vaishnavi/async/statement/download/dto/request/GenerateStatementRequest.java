package com.vaishnavi.async.statement.download.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

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
