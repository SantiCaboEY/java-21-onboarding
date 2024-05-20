package com.example.mspersonas.dto;

import com.example.mspersonas.enums.PersonStatus;

public record GetPersonDto(Integer id,
                           String name,
                           String lastName,
                           String dni,
                           PersonStatus status,
                           Integer type) {
}
