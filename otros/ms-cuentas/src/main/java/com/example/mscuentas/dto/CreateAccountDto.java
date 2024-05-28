package com.example.mscuentas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAccountDto(@NotNull Integer type,
                               @NotBlank String name,
                               @NotBlank String lastName,
                               @NotBlank String dni) {
}