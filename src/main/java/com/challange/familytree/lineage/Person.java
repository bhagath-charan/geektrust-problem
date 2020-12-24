package com.challange.familytree.lineage;

import com.challange.familytree.constants.Gender;
import com.challange.familytree.constants.Messages;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

	private List<Person> children;

	/**
	 * Constructor with which we can create a Person,Once the member is created, wife and
	 * children are added later so they are just initialized.
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

	public List<Person> getChildren() {
		return children;
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
		this.getChildren().add(child);
	}

	public String getSons() {

		if (this.getChildren().isEmpty() || (this.gender.equals(Gender.MALE) &&
				this.spouse.getChildren().isEmpty()))
			return Messages.NO_RELATION_FOUND;

		if (Gender.MALE.equals(this.gender)) {
			String children = this.spouse.getChildren().stream()
					.filter(child -> child.gender.equals(Gender.MALE))
					.map(child -> child.name)
					.collect(Collectors.joining(","));
			return children.equals("") ? Messages.NO_RELATION_FOUND : children;
		} else {
			String children = this.getChildren().stream()
					.filter(child -> child.gender.equals(Gender.MALE))
					.map(child -> child.name)
					.collect(Collectors.joining(","));
			return children.equals("") ? Messages.NO_RELATION_FOUND : children;
		}
	}

	public String getDaughters() {

		if (this.getChildren().isEmpty() || (this.gender.equals(Gender.MALE) &&
				this.spouse.getChildren().isEmpty()))
			return Messages.NO_RELATION_FOUND;

		if (Gender.MALE.equals(this.gender)) {
			String children = this.spouse.getChildren().stream()
					.filter(child -> child.gender.equals(Gender.FEMALE))
					.map(child -> child.name)
					.collect(Collectors.joining(","));
			return children.equals("") ? Messages.NO_RELATION_FOUND : children;
		} else {
			String children = this.getChildren().stream()
					.filter(child -> child.gender.equals(Gender.FEMALE))
					.map(child -> child.name)
					.collect(Collectors.joining(","));
			return children.equals("") ? Messages.NO_RELATION_FOUND : children;
		}


	}

	public String getSiblings() {

		if (this.mother == null)
			return Messages.NO_RELATION_FOUND;

		if (this.mother.getChildren().isEmpty())
			return Messages.NO_RELATION_FOUND;

		return this.mother.getChildren().stream()
				.map(child -> child.name)
				.collect(Collectors.joining(","));
	}

	public String getPaternalUncle() {

		if (this.father == null)
			return Messages.NO_RELATION_FOUND;

		if (this.father.mother == null)
			return Messages.NO_RELATION_FOUND;

		return this.father.mother.getSons();

	}

	public String getMaternalUncle() {

		if (this.mother == null)
			return Messages.NO_RELATION_FOUND;

		if (this.mother.mother == null)
			return Messages.NO_RELATION_FOUND;

		return this.mother.mother.getSons();

	}

}
