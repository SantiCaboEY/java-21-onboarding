package com.example.mscuentas.repository;

import com.example.mscuentas.model.CardStatusModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardStatusModelRepository extends CrudRepository<CardStatusModel, Integer> {
}
