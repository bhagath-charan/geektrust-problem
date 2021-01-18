package com.challange.familytree;

/**
 * @author Bhagath Charan created on 11-01-2021
 */
public class StringUtils {

	/**
	 * To check if a String is empty or null
	 *
	 * @param str String that needs to be validated
	 * @return true if the give string is null or empty
	 */
	public static boolean isNullOrEmpty(String str) {
		return str == null ||str==""|| str.trim().isEmpty() || str.equalsIgnoreCase(
				"NULL");
	}
}
