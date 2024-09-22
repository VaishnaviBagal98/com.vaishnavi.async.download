package com.vaishnavi.async.statement.download.service;

import com.vaishnavi.async.statement.download.dto.request.CoreBankingStatementRequest;
import com.vaishnavi.async.statement.download.dto.request.GenerateStatementRequest;
import com.vaishnavi.async.statement.download.dto.response.CoreBankingStatementResponse;
import com.vaishnavi.async.statement.download.dto.response.GenerateStatementResponse;
import com.vaishnavi.async.statement.download.dto.response.TransactionDetail;
import com.vaishnavi.async.statement.download.entity.StatementRequest;
import com.vaishnavi.async.statement.download.entity.StatementRequestStatusCode;
import com.vaishnavi.async.statement.download.repository.StatementRequestRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;

/**
 * This is a service class which generate statement.
 *
 * @author Vaishnavi Bagal
 * @version 1.0
 * @since 1.0
 */
@Service
@Slf4j
public class StatementGenerationService {

    @Autowired
    private StatementRequestRecordRepository statementRequestRecordRepository;

    @Autowired
    private ExecutorService executorService;

    @Autowired
    private CoreBankingService coreBankingService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PDFGenerationService pdfGenerationService;

    /**
     * This method maps GenerateStatementRequest to StatementRequest
     *
     * @param generateStatementRequest request coming from UI
     * @return StatementRequest is database entity .
     */
    public static StatementRequest mapGenerateStatementRequestToStatementRequest(GenerateStatementRequest generateStatementRequest) {
        log.info("Mapping started from GenerateStatementRequest to StatementRequest");

        return StatementRequest.builder()
                .userId(generateStatementRequest.getUserId())
                .accountNo(generateStatementRequest.getAccountNo())
                .startDate(generateStatementRequest.getStartDate())
                .endDate(generateStatementRequest.getEndDate())
                .build();
    }


    /**
     * This method maps StatementRequest to GenerateStatementResponse
     *
     * @param statementRequest database entity
     * @return GenerateStatementResponse response for the UI
     */
    public static GenerateStatementResponse mapStatementRequestToGenerateStatementResponse(StatementRequest statementRequest) {
        log.info("Mapping started from StatementRequest to GenerateStatementResponse");

        return GenerateStatementResponse.builder()
                .reqId(statementRequest.getReqId())
                .userId(statementRequest.getUserId())
                .accountNo(statementRequest.getAccountNo())
                .endDate(statementRequest.getEndDate())
                .startDate(statementRequest.getStartDate())
                .status(statementRequest.getStatus().name())
                .build();
    }


    /**
     * This method save request to the database
     *
     * @param generateStatementRequest request from UI
     * @return GenerateStatementResponse result of request save.
     */
    public GenerateStatementResponse saveRequest(GenerateStatementRequest generateStatementRequest) {
        log.info("saving into database");
        return mapStatementRequestToGenerateStatementResponse(statementRequestRecordRepository.save(mapGenerateStatementRequestToStatementRequest(generateStatementRequest)));
    }


    /**
     * These methods finds all the requests which are eligible for the processing
     * and submits to the executor pool for async statement generation
     * Note : Eligible requests are those when status is CREATED or FAILED and retry count is < 3
     */
    @Scheduled(fixedDelay = 1000)
    public void findAndSubmitEligibleRequest() {
        log.info("Async process is started");

        statementRequestRecordRepository.findAllEligibleRequestForProcessing().forEach(
                statementRequest -> {
                    Thread processThread = new Thread(() -> processStatementRequest(statementRequest));
                    statementRequest.setStatus(StatementRequestStatusCode.IN_PROGRESS);
                    statementRequestRecordRepository.save(statementRequest);
                    executorService.submit(processThread);
                }
        );
    }


    /**
     * This method fetches all the transaction between the period from the
     * mocked core banking service and sends document generation request and updates
     * the request status in the database .And notify to the user
     *
     * @param statementRequest details
     */
    public void processStatementRequest(StatementRequest statementRequest) {
        try {
            List<TransactionDetail> transactionDetails = new ArrayList<>();
            int pageNo = 0;
            int perPageCount = 100;
            int totalRecords = 0;
            do {
                CoreBankingStatementResponse coreBankingStatementResponse = coreBankingService.getTransactions(CoreBankingStatementRequest.builder()
                        .perPageCount(perPageCount)
                        .pageNo(pageNo)
                        .endDate(statementRequest.getEndDate())
                        .startDate(statementRequest.getStartDate())
                        .accountNo(statementRequest.getAccountNo())
                        .build());
                if (Objects.nonNull(coreBankingStatementResponse) && Objects.nonNull(coreBankingStatementResponse.getTransactions())) {
                    totalRecords = coreBankingStatementResponse.getTotalCount();
                    transactionDetails.addAll(coreBankingStatementResponse.getTransactions());
                }
                pageNo++;
            } while (totalRecords > (perPageCount * pageNo));

            pdfGenerationService.createPDF(transactionDetails, statementRequest.getReqId().toString());
            statementRequest.setStatus(StatementRequestStatusCode.COMPLETED);
            statementRequestRecordRepository.save(statementRequest);

            log.info("Email sending service started");

            emailService.sendEmail("vaishnavibagal1998@gmail.com", "testing", "Hello,\nPlease find the link of statement. \nAccount No : " + statementRequest.getAccountNo() +
                    ".\nFrom: " + statementRequest.getStartDate() + "\nTo: " + statementRequest.getEndDate()
                    + "\nDownload Link: " + "http://localhost:9197/v1/download/" + statementRequest.getReqId() + ".pdf");
        } catch (Exception e) {
            statementRequest.setStatus(StatementRequestStatusCode.FAILED);
            statementRequestRecordRepository.save(statementRequest);
        }
            log.info("Email sends successfully");
    }


}
