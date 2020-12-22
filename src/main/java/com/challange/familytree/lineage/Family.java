package com.challange.familytree.lineage;

import com.challange.familytree.constants.Gender;
import com.challange.familytree.constants.Messages;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Bhagath Charan created on 20-12-2020
 */
public class Family {

	Person familyHead;

	Map<String, Person> personMap = new HashMap<>();

	public void addFamilyHead(String name, String gender) {
		this.familyHead = new Person(name, Gender.getGender(gender), null, null);
		personMap.put(name.toUpperCase(), familyHead);
	}

	public void addSpouse(String personName, String spouseName, String gender) {

		if (!personMap.isEmpty() && personMap.get(personName) != null) {
			Person spouse = new Person(spouseName, Gender.getGender(gender), null, null);
			personMap.get(personName.toUpperCase()).addSpouse(spouse);
			personMap.put(spouseName.toUpperCase(), spouse);
		}
	}

	public String addChild(String motherName, String childName, String gender) {

		Person mother = personMap.get(motherName);

		if (mother != null) {
			Person child = new Person(childName, Gender.getGender(gender), mother.spouse, mother);
			mother.addChild(child);
			personMap.put(childName, child);
			return Messages.CHILD_ADDITION_SUCCEEDED;
		} else
			return Messages.CHILD_ADDITION_FAILED;
	}

}
