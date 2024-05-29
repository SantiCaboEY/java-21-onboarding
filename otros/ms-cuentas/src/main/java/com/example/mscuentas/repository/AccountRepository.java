package com.example.mscuentas.repository;

import com.example.mscuentas.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
    Optional<Account> findByPersonNumber(Integer personNumber);
}
