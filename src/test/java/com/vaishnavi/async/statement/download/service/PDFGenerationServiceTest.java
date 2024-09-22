package com.vaishnavi.async.statement.download.service;

import com.vaishnavi.async.statement.download.dto.response.TransactionDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

class PDFGenerationServiceTest {

    private PDFGenerationService pdfGenerationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pdfGenerationService = new PDFGenerationService();
    }

    @Test
    void testCreatePDF_Success() throws FileNotFoundException {
        List<TransactionDetail> transactionDetails = Arrays.asList(
                new TransactionDetail(1L, new BigDecimal("100.0"), "Credit", "Payment Received"),
                new TransactionDetail(2L, new BigDecimal("50.0"), "Debit", "Payment Sent")
        );
        String requestId = "testRequest";

        pdfGenerationService.createPDF(transactionDetails, requestId);

    }
}