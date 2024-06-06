package com.example.mscuentas.repository;

import com.example.mscuentas.model.CurrencyModel;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CurrencyRepository extends CrudRepository<CurrencyModel, String> {

    @Cacheable("currencyCache")
    @CacheEvict(value = "currencyCache", allEntries = true)
    Optional<CurrencyModel> findBySymbol(String symbol);
}
