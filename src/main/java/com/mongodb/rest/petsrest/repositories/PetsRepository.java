package com.mongodb.rest.petsrest.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongodb.rest.petsrest.domain.Pets;

public interface PetsRepository extends MongoRepository<Pets, String> {

	Pets findByName(String name);
}
