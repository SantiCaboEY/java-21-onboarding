package com.example.mscuentas.dto;

import com.example.mscuentas.enums.ErrorCode;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class ApiError{
    private final ErrorCode errorCode;
    private final String description;

    private final List<String> errors;

    public ApiError(String... errors) {
        this.errorCode = ErrorCode.EV000;
        this.description = errorCode.getDescription();
        this.errors = Arrays.asList(errors);
    }

    public ApiError(ErrorCode errorCode, String... errors) {
        this.errorCode = errorCode;
        this.description = errorCode.getDescription();
        this.errors = Arrays.asList(errors);
    }
}
