package com.vaishnavi.async.statement.download.entity;

/**
 * Status that can be used for statement request
 * {@link #CREATED}
 * {@link #FAILED}
 * {@link #IN_PROGRESS}
 * {@link #COMPLETED}
 *
 * @author Vaishnavi Bagal
 * @version 1.0
 * @since 1.0
 */
public enum StatementRequestStatusCode {
    /**
     * This indicates request is created and yet to process
     */
    CREATED,
    /**
     * This indicates request is submitted in the queue and yet to processed
     */
    IN_PROGRESS,
    /**
     * This indicates request is failed and may need a retry
     */
    FAILED,
    /**
     * This indicate that request is completed and ready for download
     */
    COMPLETED
}
