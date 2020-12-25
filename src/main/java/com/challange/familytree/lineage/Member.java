package com.challange.familytree.lineage;

/**
 * @author Bhagath Charan created on 25-12-2020
 */
public interface Member {

	/**
	 * To get Son's names
	 *
	 * @return Names of sons separated by coma
	 */
	public String getSons();


	/**
	 * To get Daughter's Names
	 *
	 * @return Names of daughters separated by coma
	 */
	public String getDaughters();

	/**
	 * To get names of Siblings
	 *
	 * @return Names of Siblings separated by coma
	 */
	public String getSiblings();

	/**
	 * To get name of Paternal Uncle(Brothers of Father).
	 *
	 * @return Name of Paternal Uncle
	 */
	public String getPaternalUncle();

	/**
	 * To get name of Maternal uncle(Brothers of Mother)
	 *
	 * @return
	 */
	public String getMaternalUncle();
}
