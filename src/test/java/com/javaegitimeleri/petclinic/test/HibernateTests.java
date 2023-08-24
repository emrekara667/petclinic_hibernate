package com.javaegitimeleri.petclinic.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.javaegitimleri.petclinic.config.HibernateConfig;
import com.javaegitimleri.petclinic.model.Address;
import com.javaegitimleri.petclinic.model.City;
import com.javaegitimleri.petclinic.model.Image;
import com.javaegitimleri.petclinic.model.Owner;
import com.javaegitimleri.petclinic.model.OwnerWithCompositePK;
import com.javaegitimleri.petclinic.model.OwnerWithCompositePK.OwnerId;
import com.javaegitimleri.petclinic.model.Person;
import com.javaegitimleri.petclinic.model.Pet;
import com.javaegitimleri.petclinic.model.PetType;
import com.javaegitimleri.petclinic.model.Rating;
import com.javaegitimleri.petclinic.model.Vet;
import com.javaegitimleri.petclinic.model.Visit;

public class HibernateTests {
	
	@Test
	public void testHibernateSetup() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		tx.commit();
		session.close();
		HibernateConfig.getSessionFactory().close();
	}
	
	@Test
	public void testCreateEntity() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Pet pet = new Pet();
		//pet.setId(1L);
		pet.setName("kedicik");
		
		session.persist(pet);
		
		tx.commit();
		session.close();
	}
	
	@Test
	public void testFieldLevelAccess() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Pet pet = new Pet("kedicik", new Date());
		pet.setId(1L);
		
		session.persist(pet);
		
		tx.commit();
		session.close();
	
		session = HibernateConfig.getSessionFactory().openSession();
		
		Pet pet2 = session.get(Pet.class, 1L);
		
		System.out.println(pet2);
	}
	
	@Test
	public void testWithoutTX() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.getTransaction();
	    tx.begin();
		
		Pet pet = new Pet("kedicik", new Date());
		pet.setId(1L);
		
		session.persist(pet);
		//session.flush();
		tx.commit();
		
		session.close();
		
	}
	
	@Test
	public void testCheckNullability() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.getTransaction();
	    tx.begin();
	    
	    Pet pet = new Pet();
	    //pet.setId(1L);
	    
	    session.persist(pet);
	    tx.commit();
	    session.close();
	}
	
	@Test
	public void testCompositePk() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.getTransaction();
	    tx.begin();
		
		
		OwnerWithCompositePK owner = new OwnerWithCompositePK();
		
		OwnerId id = new OwnerId();
		id.setFirstName("Emre");
		id.setLastName("Kara");
		
		owner.setId(id);
		
		session.persist(owner);
		
		tx.commit();
		
		session.close();
		
	}
	
	@Test
	public void testEmbedable() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.getTransaction();
	    tx.begin();
		
		Owner owner = new Owner();
		owner.setFirstName("Emre");
		owner.setLastName("KARA");
		owner.setRating(Rating.PREMIUM);
		
		Address address = new Address();
		address.setStreet("atayolu caddesi");
		address.setPhone("05075120406");
		
		owner.setAddress(address);

	    session.persist(owner);
	    
	    tx.commit();
	    
	    session.close();
	}
	
	@Test
	public void testTemporalType() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.getTransaction();
	    tx.begin();
	    
	    
		Visit visit = new Visit();
		visit.setVisitDate(new Date());
		
        session.persist(visit);
		    
	    tx.commit();
		    
	    session.close();
		
	}
	
	@Test
	public void testImageSeq() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.getTransaction();
	    tx.begin();
	    
	    City city = new City();
	    city.setName("Istanbul");
	    session.persist(city);
	    
		Image image = new Image();
		image.setHeight(20);
        session.persist(image);
        
        Pet pet = new Pet();
        pet.setName("pamuk");
        
        session.persist(pet);
		    
	    tx.commit();
		    
	    session.close();
		
	}
	
	@Test
	public void testPetToPetTypeManyToOne() throws ParseException {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.getTransaction();
	    tx.begin();
	    
	    PetType petType = new PetType();
	    petType.setName("kopek");
	    
	  
	    Pet pet = new Pet();
	    pet.setName("Karabas");
	    pet.setType(petType);
	    pet.setBirthDate(new Date());
	    
	    Pet pet2 = new Pet();
	    pet2.setType(petType);
	    pet2.setName("boncuk");
	    
	    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String dateString = "2022-01-01";
	    Date dateObject = sdf.parse(dateString);
	    pet2.setBirthDate(dateObject);
	    
	    session.persist(petType);
        session.persist(pet);
        session.persist(pet2);
		    
	    tx.commit();
		    
	    session.close();
		
	}
	
	
	@Test
	public void testCitySequence() throws ParseException {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.getTransaction();
	    tx.begin();
	    City city1 = new City();
	    city1.setName("istanbul");
	    City city2 = new City();
	    city2.setName("ankara");
	    
	    Vet vet1 = new Vet();
	    vet1.setFirstName("Tarkan");
	    
	 
	    session.persist(vet1);
	    session.persist(city1);
        session.persist(city2);
		    
	    tx.commit();
		    
	    session.close();
		
	}
	
	@Test
	public void testMappedBy() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		
		Owner owner = session.get(Owner.class, 1L);
		Pet pet = session.get(Pet.class, 101L);
		
		//owner.getPets().add(pet); Owner üzerinden ilişki kurulamaz.
		//pet.setOwner(owner); Pet üzerinden ilişki kurulabilir.
		//owner.getPets().remove(pet); Owner üzerinden ilişki silinemez.
		pet.setOwner(null);
	
		
		//session.update(owner); //no relationship
		//session.merge(owner); //no relationship
		
		tx.commit();
		session.close();
	}
	
	@Test
	public void testUpdateableInsertable() throws ParseException {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		
		 PetType petType = new PetType();
		    petType.setName("kopek");
		    
		  
		    Pet pet = new Pet();
		    pet.setName("Karabas");
		    pet.setType(petType);
		    pet.setBirthDate(new Date());
		    
		    Pet pet2 = new Pet();
		    pet2.setType(petType);
		    pet2.setName("boncuk");
		    
		    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    String dateString = "2022-01-01";
		    Date dateObject = sdf.parse(dateString);
		    pet2.setBirthDate(dateObject);
		    
		    
		    Visit visit = new Visit(); 
		    visit.setVisitDate(new Date()); 
		    visit.setVisitDescription("emre kara ziyarete geldi");
		   //visit.setPet(pet2);
		   //Visit de pet insertable false oldugu ıcın visit e pet set etsemde null olarak goruluyor.
		   //insertable false u true yapınca visit in ıcıne pet set edebiliyosun.
		    
		    pet.getVisits().add(visit);
		  
		    session.persist(visit);
		    session.persist(petType);
		    session.persist(pet);
		    session.persist(pet2);
		    
		    
		
		tx.commit();
		session.close();
	}
	
	@Test
	public void testParentChildAssoc() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		
		Pet pet = session.get(Pet.class, 1L);
		Visit visit = session.get(Visit.class, 101L);
		Image image = session.get(Image.class, 1001L);
		
		pet.getVisits().remove(visit);
		pet.getImagesByFilePath().remove("/myimage");
		
		
		tx.commit();
		session.close();
	}
	
	@Test
	public void testLazyEagerAcces() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		
		Pet pet = session.get(Pet.class, 101L);
		System.out.println("---pet loaded---");
		System.out.println("visits size: " + pet.getVisits().size());
		System.out.println("---");
		System.out.println("pet type name : " + pet.getType().getName());
		System.out.println(pet.getType().getClass());
		tx.commit();
		session.close();
		
	}
	
	@Test
	public void testOneToOneLazyProblem() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		
		
		Image image = session.get(Image.class, 1L);
		System.out.println("image loaded");
		System.out.println(new String(image.getImageContent().getContent()));
		System.out.println(image.getImageContent().getClass());
		
		
		tx.commit();
		session.close();
	}
	

}
