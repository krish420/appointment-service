package com.hackathon.appointments.exception;


import com.hackathon.appointments.payload.ErrorResponse;
import com.hackathon.appointments.payload.StatusMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception){
        log.error(exception.getMessage());
        ErrorResponse response = ErrorResponse.builder()
                .message(exception.getMessage())
                .status(StatusMessages.NOT_FOUND)
                .httpStatus(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateEntryException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateEntryException(DuplicateEntryException exception){
        log.error(exception.getMessage());
        ErrorResponse response = ErrorResponse.builder()
                .message(exception.getMessage())
                .status(StatusMessages.CONFLICT)
                .httpStatus(HttpStatus.CONFLICT)
                .build();
        return new ResponseEntity<>(response,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotValidException(MethodArgumentNotValidException exception){
        log.error(exception.getMessage());
        // getting the errors
        BindingResult bindingResult = exception.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        Map<String,String> errors = new TreeMap<>();
        fieldErrors.stream().forEach(fieldError ->
                errors.put(fieldError.getField(),fieldError.getDefaultMessage()));

        log.error(errors.toString());
        ErrorResponse response = ErrorResponse.builder()
                .message(errors.toString())
                .status(StatusMessages.BAD_REQUEST)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handleIllegalStateException(IllegalStateException exception){
        ErrorResponse response = ErrorResponse.builder()
                .message("Service currently unavailable")
                .status(StatusMessages.SERVICE_UNAVAILABLE)
                .httpStatus(HttpStatus.SERVICE_UNAVAILABLE)
                .build();
        return new ResponseEntity<>(response,HttpStatus.SERVICE_UNAVAILABLE);
    }

}
