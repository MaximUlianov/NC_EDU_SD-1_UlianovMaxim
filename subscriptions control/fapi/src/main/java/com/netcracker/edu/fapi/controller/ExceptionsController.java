package com.netcracker.edu.fapi.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionsController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value
            = { HttpClientErrorException.class})
    protected ResponseEntity handleConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Incorrect data";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value
            = { InternalAuthenticationServiceException.class})
    protected ResponseEntity handleInvokation(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Incorrect data";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }
}
