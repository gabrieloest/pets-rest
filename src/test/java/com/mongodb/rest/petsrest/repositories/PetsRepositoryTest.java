package com.mongodb.rest.petsrest.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mongodb.rest.petsrest.domain.Pets;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PetsRepositoryTest {

	@Autowired
	private PetsRepository petsRepository;

	@Before
	public void setUp() throws Exception {
		Pets pets1 = new Pets("Alice", "Dog", "Pitbull");
		Pets pets2 = new Pets("Tom", "Dog", "Labrador");
		// save product, verify has ID value after save
		assertNull(pets1.getId());
		assertNull(pets2.getId());// null before save
		this.petsRepository.save(pets1);
		this.petsRepository.save(pets2);
		assertNotNull(pets1.getId());
		assertNotNull(pets2.getId());
	}

	@Test
	public void testFetchData() {
		/* Test data retrieval */
		Pets petsA = petsRepository.findByName("Tom");
		assertNotNull(petsA);
		assertEquals("Dog", petsA.getSpecies());
		/* Get all products, list should only have two */
		Iterable<Pets> petss = petsRepository.findAll();
		int count = 0;
		for (Pets p : petss) {
			count++;
		}
		assertEquals(count, 2);
	}

	@Test
	public void testDataUpdate() {
		/* Test update */
		Pets petsB = petsRepository.findByName("Tom");
		petsB.setBreed("Boxer");
		petsRepository.save(petsB);
		Pets petsC = petsRepository.findByName("Tom");
		assertNotNull(petsC);
		assertEquals("Boxer", petsC.getBreed());
	}

	@After
	public void tearDown() throws Exception {
		this.petsRepository.deleteAll();
	}

}