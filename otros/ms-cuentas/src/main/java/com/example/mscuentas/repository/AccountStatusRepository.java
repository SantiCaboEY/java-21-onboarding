package com.example.mscuentas.repository;

import com.example.mscuentas.model.AccountStatusModel;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountStatusRepository extends CrudRepository<AccountStatusModel, String> {

    //@Cacheable("statusCache")
    //@CacheEvict(value = "statusCache", allEntries = true)
    Optional<AccountStatusModel> findByDetail(String detail);
}
