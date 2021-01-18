package com.challange.familytree.lineage;

import com.challange.familytree.StringUtils;
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

	/**
	 * To add Family Head
	 *
	 * @param name   Name of Family head
	 * @param gender Gender of head
	 */
	public void addFamilyHead(String name, String gender) {
		this.familyHead = new Person(name, Gender.getGender(gender), null,
				null);
		personMap.put(name.toUpperCase(), familyHead);
	}

	/**
	 * To Add Spouse with given Person Name, Spouse Name and Gender
	 *
	 * @param personName Person Name to whom the Spouse needs to be added
	 * @param spouseName Spouse Name
	 * @param gender     gender
	 */
	public void addSpouse(String personName, String spouseName,
	                      String gender) {

		if (!personMap.isEmpty() && personMap.get(personName) != null) {
			Person spouse = new Person(spouseName, Gender.getGender(gender),
					null, null, personMap.get(personName));
			personMap.get(personName.toUpperCase()).addSpouse(spouse);
			personMap.put(spouseName.toUpperCase(), spouse);
		}
	}

	/**
	 * To add Child to a Mother with Person Name,Child Name and Gender of Child
	 *
	 * @param motherName Mother Name
	 * @param childName  Child Name
	 * @param gender     Gender
	 * @return CHILD_ADDITION_SUCCEEDED id addition is success,CHILD_ADDITION_FAILED if
	 * addition is failed, PERSON_NOT_FOUND if the Given Mother name is not present in
	 * Family tree
	 */
	public String addChild(String motherName, String childName,
	                       String gender) {

		if (!StringUtils.isNullOrEmpty(motherName) && !StringUtils.isNullOrEmpty(childName)
				&& !StringUtils.isNullOrEmpty(gender)) {

			if (personMap.isEmpty())
				return Messages.PERSON_NOT_FOUND;

			Person mother = personMap.get(motherName);

			if (mother == null)
				return Messages.PERSON_NOT_FOUND;

			if (mother.gender.equals(Gender.MALE))
				return Messages.CHILD_ADDITION_FAILED;


			Person child = new Person(childName, Gender.getGender(gender), mother.spouse
					, mother);
			mother.addChild(child);
			personMap.put(childName, child);
			return Messages.CHILD_ADDITION_SUCCEEDED;
		} else
			return Messages.CHILD_ADDITION_FAILED;
	}

	/**
	 * To get Relationship of a Person by name
	 *
	 * @param personName Person name to whom the relations needs to be provided
	 * @param relation   Relation
	 * @return Names of Related persons
	 */
	public String getRelation(String personName, String relation) {

		if (StringUtils.isNullOrEmpty(personName) || StringUtils.isNullOrEmpty(relation))
			return Messages.NO_RELATION_FOUND;

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

	/**
	 * To get Children of a Person using person name
	 *
	 * @param personName Name of the person who's children are to be fetched
	 * @param isSon      If true Sons are fetch else Daughters
	 * @return Names of Children
	 */
	public String getChildren(String personName, boolean isSon) {

		if (personMap.isEmpty())
			return Messages.PERSON_NOT_FOUND;

		Person parent = personMap.get(personName);

		if (parent == null || (parent.gender.equals(Gender.MALE) && parent.spouse == null))
			return Messages.PERSON_NOT_FOUND;

		return isSon ? parent.getSons() : parent.getDaughters();

	}

	/**
	 * To get all the Siblingd of a person using Person Name
	 *
	 * @param personName Person name
	 * @return Names of Siblings
	 */
	public String getSiblings(String personName) {

		if (personMap.isEmpty())
			return Messages.PERSON_NOT_FOUND;

		Person person = personMap.get(personName);

		if (person == null)
			return Messages.PERSON_NOT_FOUND;

		return person.getSiblings();

	}

	/**
	 * To get names of Paternal Uncle (Father’s brothers) of a Person using name
	 *
	 * @param personName Person Name
	 * @return Names of Paternal Uncle's
	 */
	public String getPaternalUncle(String personName) {

		if (personMap.isEmpty())
			return Messages.PERSON_NOT_FOUND;

		Person person = personMap.get(personName);

		if (person == null)
			return Messages.PERSON_NOT_FOUND;

		return person.getPaternalUncle();
	}

	/**
	 * To get names of Maternal Uncle (Mother’s brothers) of a Person using name
	 *
	 * @param personName Person Name
	 * @return Names of Maternal Uncle's
	 */
	public String getMaternalUncle(String personName) {
		if (personMap.isEmpty())
			return Messages.PERSON_NOT_FOUND;

		Person person = personMap.get(personName);

		if (person == null)
			return Messages.PERSON_NOT_FOUND;

		return person.getMaternalUncle();
	}

	/**
	 * To get names of Paternal-Aunt(Father’s sisters) of a Person using name
	 *
	 * @param personName Person Name
	 * @return Names of Paternal Aunt's
	 */
	public String getPaternaAunt(String personName) {
		if (personMap.isEmpty())
			return Messages.PERSON_NOT_FOUND;

		Person person = personMap.get(personName);

		if (person == null)
			return Messages.PERSON_NOT_FOUND;

		return person.getPaternaAunt();
	}

	/**
	 * To get names of Maternal Aunt(Mother’s sisters) of a Person using name
	 *
	 * @param personName Person Name
	 * @return Names of Maternal Aunt's
	 */
	public String getMaternalAunt(String personName) {
		if (personMap.isEmpty())
			return Messages.PERSON_NOT_FOUND;

		Person person = personMap.get(personName);

		if (person == null)
			return Messages.PERSON_NOT_FOUND;

		return person.getMaternalAunt();
	}

	/**
	 * To get names of Sister-In-Law(Spouse’s sisters or Wives of siblings) of a Person
	 * using name
	 *
	 * @param personName Person Name
	 * @return Names of Sister-In-Law
	 */
	public String getSisterInLaws(String personName) {
		if (personMap.isEmpty())
			return Messages.PERSON_NOT_FOUND;

		Person person = personMap.get(personName);

		if (person == null)
			return Messages.PERSON_NOT_FOUND;

		return person.getSisterInLaws();
	}

	/**
	 * To get names of Brother-In-Law(Spouse’s brothers, Husbands of siblings)
	 *
	 * @param personName Person Name
	 * @return Names of Brother-In-Law
	 */
	public String getBrotherInLaws(String personName) {
		if (personMap.isEmpty())
			return Messages.PERSON_NOT_FOUND;

		Person person = personMap.get(personName);

		if (person == null)
			return Messages.PERSON_NOT_FOUND;

		return person.getBrotherInLaws();
	}

}
