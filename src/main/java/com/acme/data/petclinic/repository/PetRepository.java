package com.acme.data.petclinic.repository;

import org.springframework.data.repository.CrudRepository;

import com.acme.data.petclinic.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Long> {
}