package com.vaishnavi.async.statement.download.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class StatementRequestRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID reqId;
    private Long accountNo;
    private Long userId;
    private Date startDate;
    private Date endDate;
    private String status;
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;
    private Date reqUpdateDate;

    public StatementRequestRecord() {
        this.status = "CREATED"; // Default status
    }

    @PrePersist
    protected void onCreate() {
        createdAt = new Date(); // Set current date
    }
}
