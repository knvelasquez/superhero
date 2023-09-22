package com.superhero.lab.exceptionhandler;

import com.filterlibrary.exception.JwtBasedAuthenticationException;
import com.filterlibrary.model.ErrorResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class DefaultControllerAdviceExceptionHandler {
    private static final Logger logger = LogManager.getLogger(DefaultControllerAdviceExceptionHandler.class);

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleMethodNotAllowed(RuntimeException ex) {
        return sendResponse(ex.getClass().toString(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
        return sendResponse(ex.getClass().toString(), ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleBadRequest(HttpMessageNotReadableException ex) {
        return sendResponse(ex.getClass().toString(), ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return sendResponse(ex.getClass().toString(), errors.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SuperHeroHeaderNotFoundException.class)
    public ResponseEntity<Object> handleSuperHeroHeaderNotFoundException(SuperHeroHeaderNotFoundException ex) {
        logger.error(ex.getMessage());
        return sendResponse(ex.getClass().toString(), ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JwtBasedAuthenticationException.class)
    public ResponseEntity<Object> handleJwtBasedAuthenticationException(JwtBasedAuthenticationException ex) {
        return sendResponse(ex.getClass().toString(), ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }


    private ResponseEntity<Object> sendResponse(String message, String description, HttpStatus status) {
        ErrorResponse errorResponse = new ErrorResponse(message, description, "CurrentDateTime.defaultFormat()", status.value());
        return new ResponseEntity<>(errorResponse, status);
    }

}
