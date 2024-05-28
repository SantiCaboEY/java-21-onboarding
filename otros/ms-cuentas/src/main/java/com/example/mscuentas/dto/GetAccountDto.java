package com.example.mscuentas.dto;

import com.example.mscuentas.enums.AccountStatus;

public record GetAccountDto(Integer id,
                            String name,
                            String lastName,
                            String dni,
                            AccountStatus status,
                            Integer type) {
}
