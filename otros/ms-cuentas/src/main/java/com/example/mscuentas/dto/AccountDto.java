package com.example.mscuentas.dto;

import com.example.mscuentas.enums.AccountStatus;
import com.example.mscuentas.enums.Currency;

import java.math.BigDecimal;

public record AccountDto(String id,
                         Integer personNumber,
                         Currency moneySymbol,
                         AccountStatus status,
                         BigDecimal balance) {
}
