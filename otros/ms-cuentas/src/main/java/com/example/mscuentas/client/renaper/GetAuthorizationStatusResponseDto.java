package com.example.mscuentas.client.renaper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GetAuthorizationStatusResponseDto(Integer dni, Boolean isAuthorize) {
}
