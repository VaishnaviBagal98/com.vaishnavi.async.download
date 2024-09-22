package com.vaishnavi.async.statement.download.service;

import com.vaishnavi.async.statement.download.dto.request.GenerateStatementRequest;
import com.vaishnavi.async.statement.download.dto.response.CoreBankingStatementResponse;
import com.vaishnavi.async.statement.download.dto.response.GenerateStatementResponse;
import com.vaishnavi.async.statement.download.dto.response.TransactionDetail;
import com.vaishnavi.async.statement.download.entity.StatementRequest;
import com.vaishnavi.async.statement.download.entity.StatementRequestStatusCode;
import com.vaishnavi.async.statement.download.repository.StatementRequestRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class StatementGenerationServiceTest {

    @InjectMocks
    private StatementGenerationService statementGenerationService;

    @Mock
    private StatementRequestRecordRepository statementRequestRecordRepository;

    @Mock
    private ExecutorService executorService;

    @Mock
    private CoreBankingService coreBankingService;

    @Mock
    private EmailService emailService;

    @Mock
    private PDFGenerationService pdfGenerationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveRequest() {

        GenerateStatementRequest request = new GenerateStatementRequest();
        request.setUserId(123L);
        request.setAccountNo(123456789L);
        request.setStartDate(new Date());
        request.setEndDate(new Date());

        StatementRequest statementRequest = new StatementRequest();
        statementRequest.setReqId(UUID.randomUUID());
        statementRequest.setUserId(request.getUserId());
        statementRequest.setAccountNo(request.getAccountNo());
        statementRequest.setStartDate(request.getStartDate());
        statementRequest.setEndDate(request.getEndDate());
        statementRequest.setStatus(StatementRequestStatusCode.CREATED);

        when(statementRequestRecordRepository.save(any())).thenReturn(statementRequest);

        GenerateStatementResponse response = statementGenerationService.saveRequest(request);
        assertEquals(statementRequest.getReqId(), response.getReqId());
        assertEquals(StatementRequestStatusCode.CREATED.name(), response.getStatus());
        verify(statementRequestRecordRepository).save(any());
    }

    @Test
    void testFindAndSubmitEligibleRequest() {

        StatementRequest statementRequest = new StatementRequest();
        statementRequest.setReqId(UUID.randomUUID());
        statementRequest.setStatus(StatementRequestStatusCode.CREATED);

        when(statementRequestRecordRepository.findAllEligibleRequestForProcessing()).thenReturn(Arrays.asList(statementRequest));

        statementGenerationService.findAndSubmitEligibleRequest();

        verify(statementRequestRecordRepository).save(statementRequest);
        verify(executorService).submit((any(Thread.class)));
    }

    @Test
    void testProcessStatementRequest_Success() throws FileNotFoundException {

        StatementRequest statementRequest = new StatementRequest();
        statementRequest.setReqId(UUID.randomUUID());
        statementRequest.setAccountNo(456L);
        statementRequest.setStartDate(new Date());
        statementRequest.setEndDate(new Date());

        TransactionDetail transactionDetail = new TransactionDetail(1L, new BigDecimal(100), "Credit", "Payment Received");
        CoreBankingStatementResponse coreBankingResponse = new CoreBankingStatementResponse();
        coreBankingResponse.setTotalCount(1);
        coreBankingResponse.setTransactions(List.of(transactionDetail));

        when(coreBankingService.getTransactions(any())).thenReturn(coreBankingResponse);
        doNothing().when(pdfGenerationService).createPDF(any(), any());
        doNothing().when(emailService).sendEmail(any(), any(), any());

        statementGenerationService.processStatementRequest(statementRequest);

        assertEquals(StatementRequestStatusCode.COMPLETED, statementRequest.getStatus());
        verify(statementRequestRecordRepository).save(statementRequest);
        verify(emailService).sendEmail(any(), any(), any());
    }

    @Test
    void testProcessStatementRequest_Failure() {
        StatementRequest statementRequest = new StatementRequest();
        statementRequest.setReqId(UUID.randomUUID());
        statementRequest.setAccountNo(1234456L);
        statementRequest.setStartDate(new Date());
        statementRequest.setEndDate(new Date());

        when(coreBankingService.getTransactions(any())).thenThrow(new RuntimeException("Service error"));

        statementGenerationService.processStatementRequest(statementRequest);

        assertEquals(StatementRequestStatusCode.FAILED, statementRequest.getStatus());
        verify(statementRequestRecordRepository).save(statementRequest);
    }
}
