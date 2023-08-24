package com.javaegitimleri.petclinic.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name ="t_clinic")
public class Clinic extends BaseEntity{
	
	@Column(name = "name")
	private String name;
	
	@OneToMany
    @JoinTable(name = "t_clinic_owner", joinColumns = @JoinColumn(name = "clinic_id_123"),
	inverseJoinColumns = @JoinColumn(name = "owner_id_456"))
	private Set<Owner> owners = new HashSet();
	
	@OneToMany
	@JoinTable(name = "t_clinic_vet", joinColumns = @JoinColumn(name = "clinic_id_123"),
	inverseJoinColumns = @JoinColumn(name = "vet_id_456"))
	private Set<Vet> vets = new HashSet();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
