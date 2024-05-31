package com.example.mscuentas.dto;

import com.example.mscuentas.enums.AccountStatus;
import com.example.mscuentas.enums.SymbolMoney;

import java.math.BigDecimal;

public record GetAccountDto(String id,
                            Integer personNumber,
                            AccountStatus status,
                            SymbolMoney moneySymbol,
                            BigDecimal balance) {
}
