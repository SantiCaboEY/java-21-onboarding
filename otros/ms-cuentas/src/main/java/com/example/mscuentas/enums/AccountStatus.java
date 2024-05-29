package com.example.mscuentas.enums;

import lombok.Getter;
import org.checkerframework.checker.units.qual.A;

import java.util.Arrays;
import java.util.NoSuchElementException;

@Getter
public enum AccountStatus {
    ACTIVA(1),
    INACTIVA(2),
    SUSPENDIDA(3),
    CANCELADA(4),
    BLOQUEADA(5);

    private final int value;

    AccountStatus(int value){
        this.value = value;
    }

    public static AccountStatus getByValue(String value){
        return switch (value){
            case "1" -> ACTIVA;
            case "2" -> INACTIVA;
            case "3" -> SUSPENDIDA;
            case "4" -> CANCELADA;
            case "5" -> BLOQUEADA;
            default -> throw new NoSuchElementException("[" + value + "] is not a valid account Status");
        };
    }
}
