package com.vaishnavi.async.statement.download.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Users {

    @Id
    private Long userId;
    private String firstName;
    private String lastName;
    private String country;

}
