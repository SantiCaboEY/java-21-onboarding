package com.example.mscuentas.client.renaper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GetAuthorizationStatusResponseDto(@JsonProperty("dni") Integer dni,
                                                @JsonProperty("isAuthorize") Boolean isAuthorize) {
}
