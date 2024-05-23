package com.example.mspersonas.repository;

import com.example.mspersonas.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, String> {
    Optional<Person> findByDni(String dni);
}
