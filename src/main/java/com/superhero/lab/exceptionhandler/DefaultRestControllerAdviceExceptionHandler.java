package com.superhero.lab.exceptionhandler;

import com.filterlibrary.model.ErrorResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;


@RestControllerAdvice
public class DefaultRestControllerAdviceExceptionHandler /*extends ResponseEntityExceptionHandler*/ {
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(HttpServletResponse response) throws IOException {
        return sendResponse(response.getClass().toString(), "No estás autenticado o tu sesión ha expirado.", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(HttpServletResponse response) {
        return sendResponse(response.getClass().toString(), "No tienes los privilegios necesarios para acceder a este recurso.", HttpStatus.FORBIDDEN);
    }

    private ResponseEntity<Object> sendResponse(String message, String description, HttpStatus status) {
        ErrorResponse errorResponse = new ErrorResponse(message, description, "CurrentDateTime.defaultFormat()", status.value());
        return new ResponseEntity<>(errorResponse, status);
    }
}
