package com.example.mscuentas.client.worldsys;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name="worldsysClient",
        url = "${personas.client.worldsys.url}",
        path = "${personas.client.worldsys.path}",
        primary = false)
public interface WorldsysClient {

    @GetMapping("/{dni}")
    ResponseEntity<GetAntiTerrorismStatusResponseDto> getAntiTerrorismStatus(@PathVariable("dni") int id);
}
