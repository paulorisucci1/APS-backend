package com.notflix.demo.handlers;

import com.notflix.demo.exceptions.EntityNotFoundException;
import com.notflix.demo.exceptions.EntityAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFound(Exception exception) {

        final var httpStatus = HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(new ErrorResponse(httpStatus, exception.getMessage()), httpStatus);
    }

    @ExceptionHandler(EntityAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleUserEmailAlreadyExists(Exception exception) {

        final var httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        return new ResponseEntity<>(new ErrorResponse(httpStatus, exception.getMessage()), httpStatus);
    }
}
