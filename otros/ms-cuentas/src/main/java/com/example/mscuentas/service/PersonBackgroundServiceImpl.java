package com.example.mscuentas.service;

import com.example.mscuentas.client.renaper.RenaperClient;
import com.example.mscuentas.client.veraz.VerazClient;
import com.example.mscuentas.client.worldsys.WorldsysClient;
import org.springframework.scheduling.annotation.Async;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;


public class PersonBackgroundServiceImpl implements PersonBackgroundService{
    private final RenaperClient renaperClient;
    private final WorldsysClient worldsysClient;
    private final VerazClient verazClient;

    public PersonBackgroundServiceImpl(final RenaperClient renaperClient,
                                       final WorldsysClient worldsysClient,
                                       final VerazClient verazClient) {
        this.renaperClient = renaperClient;
        this.worldsysClient = worldsysClient;
        this.verazClient = verazClient;
    }

    @Override
    @Async
    public CompletableFuture<Float> getScore(String dni) {
        var result = Objects.requireNonNull(
                verazClient.getScore(Integer.decode(dni)).getBody()
        ).score();
        return CompletableFuture.completedFuture(result);
    }

    @Override
    @Async
    public CompletableFuture<Boolean> getAntiterrorismStatus(String dni) {
        var result = Objects.requireNonNull(
                worldsysClient.getAntiTerrorismStatus(Integer.decode(dni)).getBody()
        ).isTerrrorist();
        return CompletableFuture.completedFuture(result);
    }

    @Override
    @Async
    public CompletableFuture<Boolean> getBlacklistedStatus(String dni) {
        var result = Objects.requireNonNull(
                renaperClient.getAuthorizationStatus(Integer.decode(dni)).getBody()
        ).isAuthorize();
        return CompletableFuture.completedFuture(result);
    }
}
