package com.example.mspersonas.dto;

import com.example.mspersonas.enums.ErrorCode;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException{
    private final transient ApiError error;

    public ApiException(ErrorCode errorCode, String... errors){
        this.error = new ApiError(errorCode, errors);
    }
    public ApiException(String... errors){
        this.error = new ApiError(errors);
    }
    public ApiException(ErrorCode errorCode,  Throwable e, String... errors){
        super(errorCode.getDescription(), e);
        this.error = new ApiError(errorCode, errors);
    }

    public ApiException(Throwable e, String... errors){
        super(ErrorCode.EV000.getDescription(), e);
        this.error = new ApiError(errors);
    }
}
