package com.superhero.lab.exceptionhandler;

import com.filterlibrary.model.ErrorResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(SuperHeroHeaderNotFoundException.class)
    public ResponseEntity<Object> handleSuperHeroHeaderNotFoundExceptionException(SuperHeroHeaderNotFoundException ex) {
        logger.error(ex.getMessage());
        return sendResponse(ex.getClass().toString(), ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> sendResponseEntity(Exception exception, HttpServletResponse response) {
        response.setHeader("Content-Type", "application/json");
        final String exceptionName = exception.getClass().getSimpleName();
        final int statusCode = mapCodeFromException(exceptionName);
        response.setStatus(statusCode);
        return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), statusCode);
    }

    private ResponseEntity<Object> sendResponse(String message, String description, HttpStatus status) {
        ErrorResponse errorResponse = new ErrorResponse(message, description, "CurrentDateTime.defaultFormat()", status.value());
        return new ResponseEntity<>(errorResponse, status);
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
            case "SuperHeroNotFoundIdException":
                statusCode = 404;
                break;
            case "SuperHeroNotFoundNameException":
                statusCode = 406;
                break;
            default:
                statusCode = 500;
        }
        return statusCode;
    }
}
