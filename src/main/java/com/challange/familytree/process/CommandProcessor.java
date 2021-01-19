package com.challange.familytree.process;

import com.challange.familytree.constants.Command;
import com.challange.familytree.constants.Messages;
import com.challange.familytree.lineage.Family;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author Bhagath Charan created on 23-12-2020
 */
public class CommandProcessor {

	/**
	 * To process the Give File and the commands in it
	 *
	 * @param family Family
	 * @param inFile Input file
	 */
	public void processInput(Family family, File inFile) {

		try {
			Scanner sc = new Scanner(inFile);

			while (sc.hasNextLine()) {
				String request = sc.nextLine();
				String[] commandParams;

				if (!request.isEmpty()) {

					request = request.toUpperCase().replaceAll("\\s", " ");

					commandParams = request.split(" ");

					processCommand(family, commandParams, false);

				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Please provide the input file");
		}
	}

	public void processBaseFile(Family family, InputStream is) {

		try (InputStreamReader streamReader =
				     new InputStreamReader(is, StandardCharsets.UTF_8);
		     BufferedReader reader = new BufferedReader(streamReader)) {

			String request;
			String[] commandParams;
			while ((request = reader.readLine()) != null) {
				request = request.toUpperCase().replaceAll("\\s", " ");

				commandParams = request.split(":");

				processCommand(family, commandParams, true);
			}

		} catch (IOException e) {
			System.out.println("Failed to Read Base File");
		}

	}

	/**
	 * To process the commands
	 *
	 * @param family        Family
	 * @param commandParams Commands array that needs to be processed
	 * @param isBaseSetUp   To check if it is basic setup file
	 */
	public void processCommand(Family family, String[] commandParams,
	                           boolean isBaseSetUp) {
		String message = null;
		switch (commandParams[0]) {
			case Command.ADD_FAMILY_HEAD:
				family.addFamilyHead(commandParams[1], commandParams[2]);
				break;
			case Command.ADD_SPOUSE:

				if (commandParams.length != Command.MIN_COMMAND_ARGS) {
					message = Messages.ADD_SPOUSE_FAILED;
					break;
				}

				family.addSpouse(commandParams[1], commandParams[2],
						commandParams[3]);
				break;
			case Command.ADD_CHILD:

				if (commandParams.length != Command.MIN_COMMAND_ARGS) {
					message = Messages.CHILD_ADDITION_FAILED;
					break;
				}

				message = family.addChild(commandParams[1], commandParams[2],
						commandParams[3]);
				break;
			case Command.GET_RELATIONSHIP:

				if (commandParams.length != Command.MIN_COMMAND_ARGS - 1) {
					message = Messages.NO_RELATION_FOUND;
					break;
				}

				message = family.getRelation(commandParams[1], commandParams[2]);
				break;
			default:
				message = "INVALID INIT COMMAND!";
				break;
		}

		if (!isBaseSetUp && message != null)
			System.out.println(message);
	}

}
