package com.vaishnavi.async.statement.download.repository;

import com.vaishnavi.async.statement.download.entity.StatementRequest;
import com.vaishnavi.async.statement.download.entity.StatementRequestStatusCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StatementRequestRecordRepository extends JpaRepository<StatementRequest, String> {

    @Query("from StatementRequest where status in ('CREATED','FAILED')")
    List<StatementRequest> findAllEligibleForProcessing();


}
