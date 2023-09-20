package com.superhero.lab.exceptionhandler;

import com.filterlibrary.exception.JwtBasedAuthenticationException;
import com.filterlibrary.model.ErrorResponse;
import com.superhero.lab.user.cli.CliUserComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultControllerAdviceExceptionHandler {
    private static final Logger logger = LogManager.getLogger(CliUserComponent.class);

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
