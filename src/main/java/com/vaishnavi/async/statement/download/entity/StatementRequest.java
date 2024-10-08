package com.vaishnavi.async.statement.download.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

/**
 * This is an entity class of database table STATEMENT_REQUEST
 *
 * @author Vaishnavi Bagal
 * @version 1.0
 * @since 1.0
 */
@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatementRequest {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID reqId;

    @NotNull(message = "Account Number is Mandatory")
    @Column(nullable = false, updatable = false)
    private Long accountNo;

    @NotNull(message = "User ID is Mandatory")
    @Column(nullable = false, updatable = false)
    private Long userId;

    @NotNull(message = "Start Date is Mandatory")
    @Column(nullable = false, updatable = false)
    private Date startDate;

    @NotNull(message = "End Date is Mandatory")
    @Column(nullable = false, updatable = false)
    private Date endDate;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private StatementRequestStatusCode status = StatementRequestStatusCode.CREATED;
    private Date updatedAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Date getCreatedDate() {
        return new Date();
    }
}
