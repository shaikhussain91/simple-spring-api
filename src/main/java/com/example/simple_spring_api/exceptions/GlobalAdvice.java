package com.example.simple_spring_api.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalAdvice {

    /**
     * Handles TaskNotFoundException and returns a custom error response.
     *
     * @param ex the exception
     * @return a ResponseEntity with an ErrorResponse and HTTP status
     */
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTaskNotFoundException(TaskNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                ex.getMessage(),
                "Task not found in the system");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
