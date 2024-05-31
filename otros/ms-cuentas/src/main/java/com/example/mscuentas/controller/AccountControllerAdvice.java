package com.example.mscuentas.controller;

import com.example.mscuentas.dto.ApiError;
import com.example.mscuentas.dto.ApiException;
import com.example.mscuentas.enums.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AccountControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationException(
            MethodArgumentNotValidException e, WebRequest request){
        var errorString =  e.getBindingResult().getFieldErrors().stream()
                        .map(error -> error.getField() + error.getDefaultMessage()).toList();
        return new ResponseEntity<>(new ApiError(ErrorCode.EV002, errorString.toArray(new String[0])),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiError> handleApiError(
            ApiException e, WebRequest request){
        return new ResponseEntity<>(e.getError(), HttpStatus.BAD_REQUEST);
    }
}
