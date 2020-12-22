package com.challange.familytree;

import com.challange.familytree.lineage.Family;
import com.challange.familytree.process.CommandProcessor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Bhagath Charan
 */
public class Launcher {
	public static void main(String[] args) {

		Family family = new Family();
		CommandProcessor commandProcessor = new CommandProcessor();
		try {
			processInitialTreeCommands(family, commandProcessor);
		} catch (
				ArrayIndexOutOfBoundsException aiobe) {
			System.out.println("Please pass the File path as input");
		} catch (
				FileNotFoundException fnfe) {
			System.out.println("Please provide Valid file path");
		}

	}

	public static void processInitialTreeCommands(Family family, CommandProcessor commandProcessor) throws FileNotFoundException {
		File inFile = new File(System.getProperty("user.dir") + "/inputs/InitialFamilyTree.txt");
		Scanner sc = new Scanner(inFile);

		while (sc.hasNextLine()) {
			String request = sc.nextLine();

			if (!request.isEmpty()) {

				request = request.replaceAll("\\s", " ");

				System.out.println(request);

				String[] commandParams = request.toUpperCase().split(":");

				commandProcessor.processCommand(family, commandParams, false);

			}
		}
	}
}
