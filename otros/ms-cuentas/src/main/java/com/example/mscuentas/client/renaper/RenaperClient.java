package com.example.mscuentas.client.renaper;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name="renaperClient",
        url = "${personas.client.renaper.url}",
        path = "${personas.client.renaper.path}",
        primary = false)
public interface RenaperClient {

    @GetMapping("/{dni}")
    ResponseEntity<GetAuthorizationStatusResponseDto> getAuthorizationStatus(@PathVariable("dni") int id);


}
