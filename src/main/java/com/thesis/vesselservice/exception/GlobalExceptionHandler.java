package com.thesis.vesselservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Setter
@Getter
@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler{
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerException(EntityNotFoundException e){
        ErrorResponse err = new ErrorResponse("that vessel not found", System.currentTimeMillis());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(EntityAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handlerException(EntityAlreadyExistException e){
        ErrorResponse err = new ErrorResponse("that vessel already exists", System.currentTimeMillis());
        return new ResponseEntity<>(err, HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerException(MethodArgumentNotValidException e){
        ErrorResponse err = new ErrorResponse("Invalid IMO", System.currentTimeMillis());
        return new ResponseEntity<>(err, HttpStatus.NOT_ACCEPTABLE);
    }
}
