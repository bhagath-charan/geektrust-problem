# geektrust-problem Family Tree Challenge
Problem Context
-------
Our story is set in the planet of Lengaburu......in the distant, distant galaxy
of Tara B. And our protagonists are King Shan, Queen Anga & their family.
King Shan is the emperor of Lengaburu and has been ruling the planet for
the last 350 years (they have long lives in Lengaburu, you see!). Let's write
some code to get to know the family.

NOTE: I assumed that the names are unique and are one words

Steps to Test
----

> - Unzip Family.zip
> - Open cmd in geektrust-problem folder.
> - Run : **mvn clean**
> - Run : **mvn install**
> - Run : **java -jar java -jar target/geektrust.jar <absolute_path_to_input_file>**

Defining content in Test File:
----
The test file should contain command followed by the values which are seperated with space.
> Eg: 
> - To Add Child    :  **ADD_CHILD "Mother’s-Name" "Child's-Name" "Gender"**
> - To Get Relation :  **GET_RELATIONSHIP "Name" "Relationship"**


Please refer to file at *.inputs/InitialFamilyTree.txt* for initial Family tree.

Please refer to file at *.inputs/TestFile.txt* for sample test file.
