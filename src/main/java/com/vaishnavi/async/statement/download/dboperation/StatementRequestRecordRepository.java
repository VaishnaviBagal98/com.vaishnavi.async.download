package com.vaishnavi.async.statement.download.dboperation;

import com.vaishnavi.async.statement.download.entity.StatementRequestRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatementRequestRecordRepository extends JpaRepository<StatementRequestRecord,String> {

}
