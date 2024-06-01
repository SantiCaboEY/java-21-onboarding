package com.example.mscuentas.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public interface PersonBackgroundService {

    @Async
    CompletableFuture<Float> getScore(String dni);

    @Async
    CompletableFuture<Boolean> getAntiterrorismStatus(String dni);

    @Async
    CompletableFuture<Boolean> getBlacklistedStatus(String dni);
}
