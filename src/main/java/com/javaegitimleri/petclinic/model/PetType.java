package com.javaegitimleri.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_pet_type")
public class PetType extends BaseEntity {

	@Column(name = "name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
}
