package com.exceptionhandler;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception, WebRequest request, HttpServletResponse response) {
        return sendResponseEntity(exception, response);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception exception, HttpServletResponse response) {
        return sendResponseEntity(exception, response);
    }

    private ResponseEntity<Object> sendResponseEntity(Exception exception, HttpServletResponse response) {
        response.setHeader("Content-Type", "application/json");
        final String exceptionName = exception.getClass().getSimpleName();
        final int statusCode = mapCodeFromException(exceptionName);
        response.setStatus(statusCode);
        return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), statusCode);
    }

    private int mapCodeFromException(String exceptionClassName) {
        int statusCode;
        switch (exceptionClassName) {
            case "HeaderNotFoundException", "MalformedJwtException", "ExpiredJwtException":
                statusCode = 400;
                break;
            case "AccessDeniedException":
                statusCode = 403;
                break;
            default:
                statusCode = 500;
        }
        return statusCode;
    }
}
