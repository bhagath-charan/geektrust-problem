package com.challange.familytree.process;

import com.challange.familytree.constants.Command;
import com.challange.familytree.lineage.Family;

/**
 * @author Bhagath Charan created on 23-12-2020
 */
public class CommandProcessor {

	public void processCommand(Family family, String[] commandParams, boolean isBaseStUp) {
		String message = null;
		switch (commandParams[0]) {
			case Command.ADD_FAMILY_HEAD:
				family.addFamilyHead(commandParams[1], commandParams[2]);
				break;
			case Command.ADD_SPOUSE:
				family.addSpouse(commandParams[1], commandParams[2], commandParams[3]);
				break;
			case Command.ADD_CHILD:
				message = family.addChild(commandParams[1], commandParams[2], commandParams[3]);
				break;
			case Command.GET_RELATIONSHIP:
				message = family.addChild(commandParams[1], commandParams[2], commandParams[3]);
			default:
				message = "INVALID INIT COMMAND!";
				break;
		}
		if (!isBaseStUp && message != null)
			System.out.println(message);
	}

}
