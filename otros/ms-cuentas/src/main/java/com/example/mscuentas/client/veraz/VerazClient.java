package com.example.mscuentas.client.veraz;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name="verazClient",
        url = "${personas.client.veraz.url}",
        path = "${personas.client.veraz.path}",
        primary = false)
public interface VerazClient {

    @GetMapping("/{dni}")
    ResponseEntity<GetScoreResponseDto> getScore(@PathVariable("dni") int id);
}
