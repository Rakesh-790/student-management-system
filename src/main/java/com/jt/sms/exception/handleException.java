package com.jt.sms.exception;

import java.time.LocalDateTime;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.NoSuchElementException;
import java.util.StringJoiner;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleException {
    // Traditionally Exception Handle.
    // @ExceptionHandler(NoSuchElementException.class)
    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    // public Map<String, String> handleNoSuchElementException(NoSuchElementException e) {
    //     var map = new HashMap<String, String>();
    //     map.put("error", "No such element found");
    //     map.put("message", e.getMessage());
    //     map.put("timestamp", LocalDateTime.now().toString());
    //     return map;
    // }
    // @ExceptionHandler(StudentNotFoundException.class)
    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    // public Map<String, String> handleNoSuchElementException(StudentNotFoundException e) {
    //     var map = new HashMap<String, String>();
    //     map.put("error", "No such element found");
    //     map.put("message", e.getMessage());
    //     map.put("timestamp", LocalDateTime.now().toString());
    //     return map;
    // }

    // @ExceptionHandler(StudentNotFoundException.class)
    // public ProblemDetail handleNoSuchElementException(StudentNotFoundException e) {
    //     ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
    //     problemDetail.setTitle("Student Not Found");
    //     problemDetail.setDetail(e.getMessage());
    //     problemDetail.setProperty("timestamp", LocalDateTime.now().toString());
    //     return problemDetail;
    // }

    @ExceptionHandler(StudentNotFoundException.class)
    public ProblemDetail handleNoSuchElementException(StudentNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setTitle("Student Not Found");
        problemDetail.setProperty("timestamp", LocalDateTime.now().toString());
        return problemDetail;
    }

    //for Post Method Not Supported Exception
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ProblemDetail handleRequestNotSupportedException(HttpRequestMethodNotSupportedException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage());
        problemDetail.setTitle("Method Not Allowed");
        problemDetail.setProperty("timestamp", LocalDateTime.now().toString());
        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var details = new StringJoiner(", ");

        e.getAllErrors().forEach(error -> {
            var errorMessage = error.getDefaultMessage();
            var fieldName = ((FieldError)error).getField();
            details.add(fieldName + "-> " + errorMessage);
        });     

        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_ENTITY, details.toString());
        problemDetail.setTitle("Invalid data");
        problemDetail.setProperty("timestamp", LocalDateTime.now().toString());
        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}

