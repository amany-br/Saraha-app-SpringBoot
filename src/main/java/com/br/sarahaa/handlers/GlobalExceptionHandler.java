package com.br.sarahaa.handlers;

import com.br.sarahaa.exceptions.ExceptionRepresentation;
import com.br.sarahaa.exceptions.ObjectValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ObjectValidationException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(ObjectValidationException exception) {
        ExceptionRepresentation exceptionRepresentation = ExceptionRepresentation.builder()
                .errorMessage("Object not valid Exception")
                .errorSource(exception.getValidationSource())
                .validationErrors(exception.getViolations())
                .build();
        return ResponseEntity.badRequest().body(exceptionRepresentation);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionRepresentation> handleException() {
        ExceptionRepresentation exceptionRepresentation = ExceptionRepresentation.builder()
                .errorMessage("Object not found Exception")
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionRepresentation);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(BadCredentialsException exception) {
        ExceptionRepresentation exceptionRepresentation = ExceptionRepresentation.builder()
                .errorMessage("Login and / or password is incorrect")
                .build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionRepresentation);
    }
}