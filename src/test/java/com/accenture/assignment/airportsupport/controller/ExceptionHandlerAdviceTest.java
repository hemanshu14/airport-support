package com.accenture.assignment.airportsupport.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.accenture.assignment.airportsupport.exception.DataNotFoundException;
import com.accenture.assignment.airportsupport.model.ErrorObject;

@ExtendWith(MockitoExtension.class)
class ExceptionHandlerAdviceTest {

    @InjectMocks
    private ExceptionHandlerAdvice exceptionHandlerAdvice;

    private static final DataNotFoundException DATA_NOT_FOUND_EXCEPTION = new DataNotFoundException("Data not found for this particular request!!");

    @Test
    void calling_handleDataNotFoundException_expect_error_response() {

        final ResponseEntity<ErrorObject> errorResponse = exceptionHandlerAdvice.handleDataNotFoundException(DATA_NOT_FOUND_EXCEPTION);

        assertThat(errorResponse).isNotNull();
        assertThat(errorResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
