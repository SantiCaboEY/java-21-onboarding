package com.example.mscuentas.enums;

import lombok.Getter;

@Getter
public enum AccountStatus {
    ACTIVO(1, "Client already exists"),
    INACTIVO(2, "Client already exists"),
    SUSPENDIDO(3, "Client is suspended"),
    BLOQUEADO(4,"Client is blocked"),
    CANCELADO(5, "");

    private final int value;
    private final String createError;

    AccountStatus(int value, String createError){
        this.value = value;
        this.createError = createError;
    }

}
