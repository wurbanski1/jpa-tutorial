package com.acme.data.petclinic.repository;

import org.springframework.data.repository.CrudRepository;

import com.acme.data.petclinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}