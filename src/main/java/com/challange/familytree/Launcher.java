package com.challange.familytree;

import com.challange.familytree.lineage.Family;
import com.challange.familytree.process.CommandProcessor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author Bhagath Charan
 */
public class Launcher {

	public static void main(String[] args) {

		Family family = new Family();

		try {
			processFile(family, true, "inputs/InitialFamilyTree.txt");
			processFile(family, false, args[0]);
		} catch (
				FileNotFoundException fnfe) {
			System.out.println("Please provide Valid file path");
		} catch (ArrayIndexOutOfBoundsException AIOBE) {
			System.out.println("Please provide the Input File Path");
		}

	}

	/**
	 * To process the In file
	 *
	 * @param family        Initial Family Object to which all the operation are
	 *                      performed
	 * @param isBaseSetUp   to check if it is basic setup
	 * @param inputFilePath File Path
	 * @throws FileNotFoundException
	 */
	private static void processFile(Family family, boolean isBaseSetUp,
	                                String inputFilePath) throws FileNotFoundException {
		CommandProcessor commandProcessor = new CommandProcessor();

		if (isBaseSetUp) {
			ClassLoader classLoader = Launcher.class.getClassLoader();
			InputStream inputStream = classLoader.getResourceAsStream(inputFilePath);
			commandProcessor.processBaseFile(family, inputStream);
		} else {

			File inFile = new File(inputFilePath);
			commandProcessor.processInput(family, inFile);
		}


	}

}
