package com.example.mscuentas.enums;

import java.util.NoSuchElementException;

public enum SymbolMoney {
    USD(1),
    EUR(2),
    JPY(3),
    GBP(4),
    AUD(5);

    private final int value;

    SymbolMoney(int value){
        this.value = value;
    }

    public static SymbolMoney getByValue(String value){
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
