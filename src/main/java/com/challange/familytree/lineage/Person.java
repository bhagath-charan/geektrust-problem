package com.challange.familytree.lineage;

import com.challange.familytree.constants.Gender;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to collect the Details of a Family member
 *
 * @author Bhagath Charan created on 19-12-2020
 */
public class Person {

	String name;

	Gender gender;

	Person father;

	Person mother;

	Person spouse;

	List<Person> children;

	/**
	 * Constructor with which we can create a Person,Once the member is created, wife and children are added later so
	 * they are just initialized.
	 *
	 * @param name   name of the family member
	 * @param gender Gender of the family member
	 * @param father Father of the family member
	 * @param mother Mother of the family member
	 */
	public Person(String name, Gender gender, Person father, Person mother) {
		this.name = name;
		this.gender = gender;
		this.father = father;
		this.mother = mother;
		this.spouse = null;
		this.children = new ArrayList<>();
	}

	/**
	 * To add wife of the member
	 *
	 * @param spouse Spouse to be added
	 */
	public void addSpouse(Person spouse) {
		this.spouse = spouse;
	}

	/**
	 * To add child
	 *
	 * @param child Child to be added
	 */
	public void addChild(Person child) {
		this.children.add(child);
	}

	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", gender=" + gender +
				", father=" + father +
				", mother=" + mother +
				", spouse=" + spouse +
				", children=" + children +
				'}';
	}
}
