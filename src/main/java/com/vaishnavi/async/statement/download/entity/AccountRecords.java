package com.vaishnavi.async.statement.download.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class AccountRecords {

    @Id
    private Long accountNo;

    private Long userId;
}
