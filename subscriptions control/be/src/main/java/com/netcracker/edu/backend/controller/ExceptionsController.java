package com.netcracker.edu.backend.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionsController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity handleConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse1 = "Incorrect database expression";
        return handleExceptionInternal(ex, bodyOfResponse1,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value
            = { NoSuchElementException.class })
    protected ResponseEntity noElementConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse2 = "No such element";
        return handleExceptionInternal(ex, bodyOfResponse2,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }


}
