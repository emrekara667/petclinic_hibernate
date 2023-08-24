package com.javaegitimeleri.petclinic.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Test;

import com.javaegitimleri.petclinic.config.JpaConfig;
import com.javaegitimleri.petclinic.model.Owner;
import com.javaegitimleri.petclinic.model.Pet;
import com.javaegitimleri.petclinic.model.Rating;

public class JpaTests {

	@Test
	public void testJpaSetup() {
	   EntityManager entityManager= JpaConfig.getEntityManagerFactory().createEntityManager();
	   EntityTransaction tx = entityManager.getTransaction();
	   tx.begin();
	   tx.commit();
	   entityManager.close();
	   JpaConfig.getEntityManagerFactory().close();
	}
	
	@Test
	public void testWithoutTX() {
		EntityManager entityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		
		Pet pet = new Pet();
		pet.setId(1l);
		pet.setName("kedicik");
		
		entityManager.persist(pet);
		
		//entityManager.flush();
		tx.commit();
		
		
		entityManager.close();
	}
	
	@Test
	public void testRating() {
		EntityManager entityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		
		Owner owner = new Owner();
		owner.setFirstName("Emre");
		owner.setLastName("KARA");
		owner.setRating(Rating.STANDART);
		
		
		entityManager.persist(owner);
		
		tx.commit();
		entityManager.close();
	}

}
