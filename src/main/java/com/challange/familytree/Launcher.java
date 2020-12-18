package com.challange.familytree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Bhagath Charan
 */
public class Launcher {
	public static void main(String[] args) {

		try {
			String filePath = args[0];
			File inFile = new File(filePath);
			Scanner sc = new Scanner(inFile);
		} catch (FileNotFoundException fnfe) {
			System.out.println("Please provide Valid file path");
		} catch (ArrayIndexOutOfBoundsException aiobe) {
			System.out.println("Please pass the File path as input");
		}
	}
}
