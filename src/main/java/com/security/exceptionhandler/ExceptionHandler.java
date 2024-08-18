package com.security.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler(value = CustomExceptionClass.class)
    public ResponseEntity<ExceptionPayload> notFoundHandler(CustomExceptionClass ex) {
        ExceptionPayload exceptionPayload = new ExceptionPayload(ex.getMessage(), ex.getCause(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(exceptionPayload, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> internalServerHandler(MethodArgumentNotValidException ex) {
        Map<String, String> map = new HashMap<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            map.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionPayload> globalExceptionHandler(Exception ex) {
        ExceptionPayload exceptionPayload = new ExceptionPayload(ex.getMessage(), ex.getCause(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(exceptionPayload, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
