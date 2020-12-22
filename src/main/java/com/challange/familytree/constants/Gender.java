package com.challange.familytree.constants;

/**
 * @author Bhagath Charan created on 19-12-2020
 */
public enum Gender {
	MALE("MALE"), FEMALE("FEMALE");

	String value;

	Gender(String value) {
		this.value = value;
	}

	public String getValue() {
		return MALE.value;
	}

	public static Gender getGender(String gender) {
		return FEMALE.value.equalsIgnoreCase(gender) ? Gender.FEMALE : Gender.MALE;
	}
}
