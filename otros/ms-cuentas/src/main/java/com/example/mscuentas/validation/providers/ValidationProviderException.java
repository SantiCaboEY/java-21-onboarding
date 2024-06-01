package com.example.mscuentas.validation.providers;

import lombok.Getter;

@Getter
public class ValidationProviderException extends RuntimeException {

    private final String providerName;

    public ValidationProviderException(String providerName, String message, Throwable cause){
        super(message, cause);
        this.providerName = providerName;
    }
}
