package com.challange.familytree;

import com.challange.familytree.lineage.Family;
import com.challange.familytree.process.CommandProcessor;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Unit test for simple Launcher.
 */
public class LauncherTest {

	public static final String PERSON_NOT_FOUND = "PERSON_NOT_FOUND";

	public static final String CHILD_ADDITION_SUCCEEDED = "CHILD_ADDITION_SUCCEEDED";

	public static final String CHILD_ADDITION_FAILED = "CHILD_ADDITION_FAILED";

	public static final String NO_RELATION_FOUND = "NONE";

	public static final String ADD_SPOUSE_FAILED = "SPOUSE_ADDITION_FAILED";

	protected static Family family;

	@BeforeClass
	public static void setUpBeforeClass() throws FileNotFoundException {
		family = new Family();
		File inFile = new File("/InitialFamilyTree.txt");
		ClassLoader classLoader = Launcher.class.getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("inputs/InitialFamilyTree.txt" +
				".txt");

		CommandProcessor commandProcessor = new CommandProcessor();
		commandProcessor.processBaseFile(family, inputStream);
	}


	@Test
	public void addChildAllNullValues() {
		Assert.assertEquals(CHILD_ADDITION_FAILED, family.addChild(null, null, null));
	}

	@Test
	public void addChildNameNullValue() {
		Assert.assertEquals(CHILD_ADDITION_FAILED, family.addChild("SATYA", null,
				null));
	}

	@Test
	public void addChildGenderNullValue() {
		Assert.assertEquals(CHILD_ADDITION_FAILED, family.addChild("SATYA", "CHARAN",
				null));
	}

	@Test
	public void addChildWithFather() {
		Assert.assertEquals(PERSON_NOT_FOUND, family.addChild("Vyan", "CHARAN",
				"male"));
	}

	@Test
	public void addChildWithAbsentMember() {
		Assert.assertEquals(PERSON_NOT_FOUND, family.addChild("BHAGATh", "CHARAN",
				"male"));
	}

	@Test
	public void addChildSuccess() {
		Assert.assertEquals(CHILD_ADDITION_SUCCEEDED, family.addChild("SATYA",
				"CHARAN",
				"male"));
	}

	@Test
	public void getRelationAllParamsNull() {
		Assert.assertEquals(NO_RELATION_FOUND, family.getRelation(null, null));
	}

	@Test
	public void getRelationPersonNameNull() {
		Assert.assertEquals(NO_RELATION_FOUND, family.getRelation(null, "PATERNAL-UNCLE"
		));
	}

	@Test
	public void getRelationRelationValueNull() {
		Assert.assertEquals(NO_RELATION_FOUND, family.getRelation("SATYA", null));
	}
}
