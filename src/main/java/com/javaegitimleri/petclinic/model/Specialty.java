package com.javaegitimleri.petclinic.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_specialty")
public class Specialty extends BaseEntity {

	@Column(name = "name")
	private String name;
	
	@ManyToMany(mappedBy = "specialties")
	private Set<Vet> vets = new HashSet<>(); 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
