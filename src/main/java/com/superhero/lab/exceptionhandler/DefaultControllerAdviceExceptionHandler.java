package com.superhero.lab.exceptionhandler;

import com.filterlibrary.model.ErrorResponse;
import com.superhero.lab.user.cli.CliUserComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class DefaultControllerAdviceExceptionHandler {
    private static final Logger logger = LogManager.getLogger(CliUserComponent.class);

    @ExceptionHandler(SuperHeroHeaderNotFoundException.class)
    public ResponseEntity<Object> handleSuperHeroHeaderNotFoundExceptionException(SuperHeroHeaderNotFoundException ex) {
        logger.error(ex.getMessage());
        return sendResponse(ex.getClass().toString(), ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> sendResponse(String message, String description, HttpStatus status) {
        ErrorResponse errorResponse = new ErrorResponse(message, description, "CurrentDateTime.defaultFormat()", status.value());
        return new ResponseEntity<>(errorResponse, status);
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
}
