package com.example.mscuentas.client.worldsys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GetAntiTerrorismStatusResponseDto(Integer dni, Boolean isTerrrorist) {
}
