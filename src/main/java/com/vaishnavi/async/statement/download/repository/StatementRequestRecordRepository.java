package com.vaishnavi.async.statement.download.repository;

import com.vaishnavi.async.statement.download.entity.StatementRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

/**
 * This interface for all database operation
 *
 * @author Vaishnavi Bagal
 * @version 1.0
 * @since 1.0
 */
public interface StatementRequestRecordRepository extends JpaRepository<StatementRequest, UUID> {

    /**
     * This method finds all the requests that needs to be processed from the database
     *
     * @return list of retrieve statement request from the database
     */
    @Query("from StatementRequest where status in ('CREATED','FAILED')")
    List<StatementRequest> findAllEligibleRequestForProcessing();


}
