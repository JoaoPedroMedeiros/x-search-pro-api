package com.jpcami.tads.xsearchpro.api.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ValidationAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<SimpleResult> handleJsrValidation(MethodArgumentNotValidException e) {
        List<String> messages = new ArrayList<String>();
        for (FieldError field: e.getBindingResult().getFieldErrors()) {
            messages.add("Campo [" + field.getField() + "] " + field.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(SimpleResult.validation(messages));
    }
}
