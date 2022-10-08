package com.DemoSocialMediaApplication.exception;

import com.DemoSocialMediaApplication.model.CustomError;
import org.hibernate.exception.SQLGrammarException;
import org.postgresql.util.PSQLException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<CustomError> handleAllException(Exception ex, WebRequest request) throws Exception {
        CustomError customError = getCustomError(
                "An error has been occurred",
                request.getDescription(false));

        return new ResponseEntity<CustomError>(
                customError,
                HttpStatus.REQUEST_TIMEOUT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<CustomError> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) throws Exception {
        CustomError customError = getCustomError(
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<CustomError>(
                customError,
                HttpStatus.NOT_FOUND
                );
    }

    @ExceptionHandler(InvalidDataAccessResourceUsageException.class)
    public final ResponseEntity<CustomError> handleDataAccessException(InvalidDataAccessResourceUsageException ex, WebRequest request) throws Exception {
        CustomError customError = getCustomError(
                ex.getRootCause().getMessage(),
                request.getDescription(false));

        return new ResponseEntity<CustomError>(
                customError,
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(ResourceAlreadyExist.class)
    public final ResponseEntity<CustomError> handleResourceAlreadyExistException(ResourceAlreadyExist ex, WebRequest request) throws Exception {
        CustomError customError = getCustomError(
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<CustomError>(
                customError,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(PSQLException.class)
    public final ResponseEntity<CustomError> handleSQLException(PSQLException ex, WebRequest request) throws Exception {
        CustomError customError = getCustomError(
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<CustomError>(
                customError,
                HttpStatus.BAD_REQUEST
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        CustomError customError = getCustomError(
                ex.getFieldError().getDefaultMessage(),
                request.getDescription(false)
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customError);
    }

    public final static CustomError getCustomError(
            String message,
            String description
    ) {
        CustomError customError = CustomError
                .builder()
                .timestamp(LocalDateTime.now())
                .message(message)
                .details(description)
                .build();
        return customError;
    }

}
