package com.example.mscuentas.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {
    EV001("Cannot add new active Person "),
    EV002("Invalid Body"),
    EV003("Person not found"),
    EV000("Generic Error");

    private final String description;

    ErrorCode(String description){
        this.description = description;
    }
}
