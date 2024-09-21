package com.vaishnavi.async.statement.download.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionDetail {

    private Long transactionId;
    private BigDecimal amount ;
    private String type;
    private String description;

}
