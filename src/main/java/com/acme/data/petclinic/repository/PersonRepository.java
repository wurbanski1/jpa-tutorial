package com.acme.data.petclinic.repository;

import org.springframework.data.repository.CrudRepository;

import com.acme.data.petclinic.model.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {
}