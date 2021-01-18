package com.challange.familytree;

import com.challange.familytree.lineage.Family;
import com.challange.familytree.process.CommandProcessor;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

/**
 * @author Bhagath Charan
 */
public class Launcher {

	public static void main(String[] args) {

		Family family = new Family();

		try {


			processFile(family, true,
					Paths.get("inputs/InitialFamilyTree.txt").toAbsolutePath().toString());
			processFile(family, false, args[0]);
		} catch (ArrayIndexOutOfBoundsException aiobe) {
			System.out.println("Please pass the File path as input");
		} catch (
				FileNotFoundException fnfe) {
			System.out.println("Please provide Valid file path");
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
		File inFile = new File(inputFilePath);

		commandProcessor.processInput(family, inFile, isBaseSetUp);
	}

}
