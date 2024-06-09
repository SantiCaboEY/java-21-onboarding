package com.example.mscuentas.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public interface PersonBackgroundService {

    @Async
    CompletableFuture<Float> getScore(String dni);

    @Async
    CompletableFuture<Boolean> getAntiterrorismStatus(String dni);

    @Async
    CompletableFuture<Boolean> getBlacklistedStatus(String dni);
}
