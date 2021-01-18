package com.challange.familytree.process;

import com.challange.familytree.constants.Command;
import com.challange.familytree.constants.Messages;
import com.challange.familytree.lineage.Family;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Bhagath Charan created on 23-12-2020
 */
public class CommandProcessor {

	/**
	 * To process the Give File and the commands in it
	 *
	 * @param family      Family
	 * @param inFile      Input file
	 * @param isBaseSetUp To check if it is basic setup file
	 */
	public void processInput(Family family, File inFile, boolean isBaseSetUp) {

		try {
			Scanner sc = new Scanner(inFile);

			while (sc.hasNextLine()) {
				String request = sc.nextLine();
				String[] commandParams;

				if (!request.isEmpty()) {

					request = request.toUpperCase().replaceAll("\\s", " ");

					if (isBaseSetUp)
						commandParams = request.split(":");
					else
						commandParams = request.split(" ");

					processCommand(family, commandParams, isBaseSetUp);

				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Please provide the input file");
		}
	}

	/**
	 * To process the commands
	 *
	 * @param family        Family
	 * @param commandParams Commands array that needs to be processed
	 * @param isBaseSetUp To check if it is basic setup file
	 */
	public void processCommand(Family family, String[] commandParams,
	                           boolean isBaseSetUp) {
		String message = null;
		switch (commandParams[0]) {
			case Command.ADD_FAMILY_HEAD:
				family.addFamilyHead(commandParams[1], commandParams[2]);
				break;
			case Command.ADD_SPOUSE:

				if (commandParams.length != 4) {
					message = Messages.ADD_SPOUSE_FAILED;
					break;
				}

				family.addSpouse(commandParams[1], commandParams[2],
						commandParams[3]);
				break;
			case Command.ADD_CHILD:

				if (commandParams.length != 4) {
					message = Messages.CHILD_ADDITION_FAILED;
					break;
				}

				message = family.addChild(commandParams[1], commandParams[2],
						commandParams[3]);
				break;
			case Command.GET_RELATIONSHIP:

				if (commandParams.length != 3) {
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
