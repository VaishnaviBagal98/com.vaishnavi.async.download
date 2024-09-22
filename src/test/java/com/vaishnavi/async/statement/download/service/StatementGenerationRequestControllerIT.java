package com.vaishnavi.async.statement.download.service;

import com.vaishnavi.async.statement.download.controller.StatementGenerationRequestController;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class StatementGenerationRequestControllerIT {

    @InjectMocks
    private StatementGenerationRequestController statementGenerationRequestController;

    @Mock
    private StatementGenerationService statementGenerationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
}

