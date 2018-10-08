package com.mongodb.rest.petsrest.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.rest.petsrest.domain.Pets;
import com.mongodb.rest.petsrest.repositories.PetsRepository;

@RestController
@RequestMapping("/pets")
public class PetsController {

	@Autowired
	private PetsRepository repository;

	@GetMapping(value = "")
	public List<Pets> getAllPets() {
		return repository.findAll();
	}

	@GetMapping(value = "/{id}")
	public Pets getPetById(@PathVariable("id") String id) {
		return repository.findById(id).orElse(null);
	}

	@PutMapping("/{id}")
	public void modifyPetById(@PathVariable("id") String id, @Valid @RequestBody Pets pets) {
		pets.setId(id);
		repository.save(pets);
	}

	@PostMapping("")
	public Pets createPet(@Valid @RequestBody Pets pets) {
		repository.save(pets);
		return pets;
	}

	@DeleteMapping("/{id}")
	public void deletePet(@PathVariable String id) {
		try {
			Pets pet = repository.findById(id).orElseThrow(() -> new Exception());
			repository.delete(pet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
