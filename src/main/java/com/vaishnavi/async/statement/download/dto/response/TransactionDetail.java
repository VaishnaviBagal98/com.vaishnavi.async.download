package com.vaishnavi.async.statement.download.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * This class represents details of each transaction from the mocked core banking service
 *
 * @author Vaishnavi Bagal
 * @version 1.0
 * @since 1.0
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionDetail {

    private Long transactionId;
    private BigDecimal amount;
    private String type;
    private String description;

}
