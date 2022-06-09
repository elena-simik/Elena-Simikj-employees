package com.sirma.employee.web.base.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

public class Handler {

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<Map<String, String>> onDateTimeException() {
        final Map<String, String> responseEntity = new HashMap<>();
        responseEntity.put("message", "Please provide a valid date format");
        return ResponseEntity.badRequest().body(responseEntity);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> onDateIllegalArgumentException() {
        final Map<String, String> responseEntity = new HashMap<>();
        responseEntity.put("message", "Please provide a valid date format");
        return ResponseEntity.badRequest().body(responseEntity);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> onValidationException(RuntimeException ex) {
        final Map<String, String> responseEntity = new HashMap<>();
        responseEntity.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(responseEntity);
    }

}
