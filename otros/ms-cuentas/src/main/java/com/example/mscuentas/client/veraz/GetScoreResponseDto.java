package com.example.mscuentas.client.veraz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GetScoreResponseDto(Integer dni, Float score) {
}
