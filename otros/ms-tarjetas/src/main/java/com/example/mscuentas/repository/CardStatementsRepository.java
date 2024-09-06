package com.example.mscuentas.repository;

import com.example.mscuentas.model.CardStatements;
import com.example.mscuentas.model.CardStatementsId;
import org.springframework.data.repository.CrudRepository;

public interface CardStatementsRepository extends CrudRepository<CardStatements, CardStatementsId> {
}
