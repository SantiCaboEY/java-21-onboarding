package com.example.mscuentas.enums;

import java.util.NoSuchElementException;

public enum Currency {
    USD(1),
    EUR(2),
    JPY(3),
    GBP(4),
    AUD(5);

    private final int value;

    Currency(int value){
        this.value = value;
    }

    public static Currency getByValue(String value){
        return switch (value){
            case "1" -> USD;
            case "2" -> EUR;
            case "3" -> JPY;
            case "4" -> GBP;
            case "5" -> AUD;
            default -> throw new NoSuchElementException("[" + value + "] is not a valid ,money Symbol");
        };
    }
}
