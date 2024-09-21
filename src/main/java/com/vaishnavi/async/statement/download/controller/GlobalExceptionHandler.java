package com.vaishnavi.async.statement.download.controller;

import com.vaishnavi.async.statement.download.dto.response.Response;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)

    public ResponseEntity<Response> handleValidationExceptions(ConstraintViolationException ex) {
        List<String> errors = new ArrayList<>();
        ex.getConstraintViolations().forEach(
                error -> errors.add(error.getMessage()));

        return new ResponseEntity<>(
                Response.builder().errorMessage(errors).build(), HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleValidationErrors(MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());

        return new ResponseEntity<>(Response.builder().errorMessage(errors).build(), new HttpHeaders(),
                HttpStatus.BAD_REQUEST
        );

    }

}