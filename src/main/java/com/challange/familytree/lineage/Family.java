package com.challange.familytree.lineage;

import com.challange.familytree.constants.Gender;
import com.challange.familytree.constants.Messages;
import com.challange.familytree.constants.Relation;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Bhagath Charan created on 20-12-2020
 */
public class Family {

	Person familyHead;

	Map<String, Person> personMap = new HashMap<>();

	public void addFamilyHead(String name, String gender) {
		this.familyHead = new Person(name, Gender.getGender(gender), null,
				null);
		personMap.put(name.toUpperCase(), familyHead);
	}

	public void addSpouse(String personName, String spouseName,
	                      String gender) {

		if (!personMap.isEmpty() && personMap.get(personName) != null) {
			Person spouse = new Person(spouseName, Gender.getGender(gender),
					null, null);
			personMap.get(personName.toUpperCase()).addSpouse(spouse);
			personMap.put(spouseName.toUpperCase(), spouse);
		}
	}

	public String addChild(String motherName, String childName,
	                       String gender) {

		Person mother = personMap.get(motherName);

		if (mother != null) {
			Person child = new Person(childName, Gender.getGender(gender), mother.spouse
					, mother);
			mother.addChild(child);
			personMap.put(childName, child);
			return Messages.CHILD_ADDITION_SUCCEEDED;
		} else
			return Messages.CHILD_ADDITION_FAILED;
	}


	public String getRelation(String personName, String relation) {

		switch (relation) {
			case Relation.SON:
				return getChildren(personName, true);

			case Relation.DAUGHTER:
				return getChildren(personName, false);

			case Relation.SIBLINGS:
				return getSiblings(personName);

			case Relation.PATERNAL_UNCLE:
				return getPaternalUncle(personName);

			case Relation.MATERNAL_UNCLE:
				return getMaternalUncle(personName);

			case Relation.PATERNAL_AUNT:
				return getPaternaAunt(personName);

			case Relation.MATERNAL_AUNT:
				return getMaternalAunt(personName);

			case Relation.SISTER_IN_LAW:
				return getSisterInLaws(personName);

			case Relation.BROTHER_IN_LAW:
				return getBrotherInLaws(personName);

			default:
				return Messages.NO_RELATION_FOUND;
		}
	}

	public String getChildren(String personName, boolean isSon) {

		if (personMap.isEmpty())
			return Messages.PERSON_NOT_FOUND;

		Person parent = personMap.get(personName);

		if (parent == null || (parent.gender.equals(Gender.MALE) && parent.spouse == null))
			return Messages.PERSON_NOT_FOUND;

		return isSon ? parent.getSons() : parent.getDaughters();

	}

	public String getSiblings(String personName) {

		if (personMap.isEmpty())
			return Messages.PERSON_NOT_FOUND;

		Person person = personMap.get(personName);

		if (person == null)
			return Messages.PERSON_NOT_FOUND;

		return person.getSiblings();

	}

	public String getPaternalUncle(String personName) {
		if (personMap.isEmpty())
			return Messages.PERSON_NOT_FOUND;

		Person person = personMap.get(personName);

		if (person == null)
			return Messages.PERSON_NOT_FOUND;

		return person.getPaternalUncle();
	}

	public String getMaternalUncle(String personName) {
		if (personMap.isEmpty())
			return Messages.PERSON_NOT_FOUND;

		Person person = personMap.get(personName);

		if (person == null)
			return Messages.PERSON_NOT_FOUND;

		return person.getMaternalUncle();
	}

	public String getPaternaAunt(String personName) {
		if (personMap.isEmpty())
			return Messages.PERSON_NOT_FOUND;

		Person person = personMap.get(personName);

		if (person == null)
			return Messages.PERSON_NOT_FOUND;

		return person.getPaternaAunt();
	}

	public String getMaternalAunt(String personName) {
		if (personMap.isEmpty())
			return Messages.PERSON_NOT_FOUND;

		Person person = personMap.get(personName);

		if (person == null)
			return Messages.PERSON_NOT_FOUND;

		return person.getMaternalAunt();
	}

	public String getSisterInLaws(String personName) {
		if (personMap.isEmpty())
			return Messages.PERSON_NOT_FOUND;

		Person person = personMap.get(personName);

		if (person == null)
			return Messages.PERSON_NOT_FOUND;

		return person.getSisterInLaws();
	}

	public String getBrotherInLaws(String personName) {
		if (personMap.isEmpty())
			return Messages.PERSON_NOT_FOUND;

		Person person = personMap.get(personName);

		if (person == null)
			return Messages.PERSON_NOT_FOUND;

		return person.getBrotherInLaws();
	}

}
