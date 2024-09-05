package com.example.mscuentas.client.veraz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GetScoreResponseDto(@JsonProperty("dni") Integer dni,
                                  @JsonProperty("score") Float score) {
}
