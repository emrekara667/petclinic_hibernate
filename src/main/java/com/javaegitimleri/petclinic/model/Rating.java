package com.javaegitimleri.petclinic.model;

public enum Rating {
	STANDART(100), PREMIUM(300);
	
	
	private int value;
	
	private Rating(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
