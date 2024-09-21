package com.vaishnavi.async.statement.download.service;

import com.vaishnavi.async.statement.download.dto.request.CoreBankingStatementRequest;
import com.vaishnavi.async.statement.download.dto.response.CoreBankingStatementResponse;
import com.vaishnavi.async.statement.download.dto.response.TransactionDetail;
import com.vaishnavi.async.statement.download.entity.StatementRequest;
import com.vaishnavi.async.statement.download.entity.StatementRequestStatusCode;
import com.vaishnavi.async.statement.download.repository.StatementRequestRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;

@Component
@Slf4j
public class Scheduler {

    @Autowired
    private StatementRequestRecordRepository statementRequestRecordRepository;

    @Autowired
    private StatementGenerationService statementGenerationService;

    @Autowired
    private ExecutorService executorService;

    @Autowired
    private CoreBankingService coreBankingService;

    @Autowired
    private EmailService emailService;



    @Scheduled(fixedDelay = 1000)
    public void scheduler() throws InterruptedException {
        statementRequestRecordRepository.findAllEligibleForProcessing().stream().forEach(
                statementRequest -> {

                    Thread processThread = new Thread(() -> processRequest(statementRequest));
                    statementRequest.setStatus(StatementRequestStatusCode.IN_PROGRESS);
                    statementRequestRecordRepository.save(statementRequest);
                    executorService.submit(processThread);
                }
        );
    }

    public void processRequest(StatementRequest statementRequest){
        try{
            List<TransactionDetail> transactionDetails = new ArrayList<>();
            int pageNo = 0;
            int perPageCount = 200;
            int totalRecords = 0;
           do{

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
           }while(totalRecords > (perPageCount * pageNo));
        emailService.sendEmail( "vaishnavibagal1998@gmail.com",  "testing",  "Hello,\nPlease find the link of statement. \nAccount No : "+ statementRequest.getAccountNo() + ".\nFrom: " + statementRequest.getStartDate() + "\nTo: " + statementRequest.getEndDate());
        } catch (Exception e) {
            statementRequest.setStatus(StatementRequestStatusCode.FAILED);
            statementRequestRecordRepository.save(statementRequest);
        }
    }

}
