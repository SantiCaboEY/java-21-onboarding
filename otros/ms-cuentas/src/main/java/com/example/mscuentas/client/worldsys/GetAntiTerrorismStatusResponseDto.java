package com.example.mscuentas.client.worldsys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GetAntiTerrorismStatusResponseDto(@JsonProperty("dni") Integer dni,
                                                @JsonProperty("isTerrorist") Boolean isTerrorist) {
}
