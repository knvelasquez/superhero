package com.superhero.lab.config;

import com.filterlibrary.model.ErrorResponse;
import com.superhero.lab.config.exception.SuperHeroNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;


@RestControllerAdvice
public class DefaultRestControllerAdviceExceptionHandler {
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(HttpServletResponse response) throws IOException {
        return sendResponse(response.getClass().toString(), "No estás autenticado o tu sesión ha expirado.", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(HttpServletResponse response) {
        return sendResponse(response.getClass().toString(), "No tienes los privilegios necesarios para acceder a este recurso.", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(SuperHeroNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSuperHeroNotFoundException(SuperHeroNotFoundException ex) {
        return sendResponse(ex.getClass().toString(), ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<ErrorResponse> sendResponse(String message, String description, HttpStatus status) {
        ErrorResponse errorResponse = new ErrorResponse(message, description, "CurrentDateTime.defaultFormat()", status.value());
        return new ResponseEntity<>(errorResponse, status);
    }
}
