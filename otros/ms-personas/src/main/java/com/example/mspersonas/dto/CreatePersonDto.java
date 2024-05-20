package com.example.mspersonas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePersonDto(@NotNull Integer type,
                              @NotBlank String name,
                              @NotBlank String lastName,
                              @NotBlank String dni) {
}