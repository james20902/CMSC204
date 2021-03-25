package Assignments.Assignment4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CourseDBManager_STUDENT_Test {
	private CourseDBManagerInterface dataMgr = new CourseDBManager();

	@Before
	public void setUp() throws Exception {
		dataMgr = new CourseDBManager();
	}

	@After
	public void tearDown() throws Exception {
		dataMgr = null;
	}

	@Test
	public void testAddToDB() {
		try {
			dataMgr.add("CMSC203",99999,4,"Distance-Learning","Gregory Grinberg");
		}
		catch(Exception e) {
			fail("This should not have caused an Exception");
		}
	}

	@Test
	public void testGetFromDB(){
		dataMgr.add("CMSC203",99999,4,"Distance-Learning","Gregory Grinberg");
		assertEquals(dataMgr.get(99999).CourseID, "CMSC203");
	}

	@Test
	public void testShowAll() {
		dataMgr.add("CHEM135", 12345 ,4, "Distance-Learning", "Abner Mintz");
		dataMgr.add("MATH182",54231, 4, "Distance-Learning", "Jason Lee");
		dataMgr.add("ENES100", 33456, 3, "Distance-Learning", "Craig Garrison-Mogren");
		dataMgr.add("ENGL102", 91283, 3, "Distance-Learning", "Megan Howard");
		ArrayList<String> list = dataMgr.showAll();
		assertEquals(list.get(0),"\nCourse:MATH182 CRN:54231 Credits:4 Instructor:Jason Lee Room:Distance-Learning");
		assertEquals(list.get(1),"\nCourse:ENGL102 CRN:91283 Credits:3 Instructor:Megan Howard Room:Distance-Learning");
		assertEquals(list.get(2),"\nCourse:CHEM135 CRN:12345 Credits:4 Instructor:Abner Mintz Room:Distance-Learning");
		assertEquals(list.get(3), "\nCourse:ENES100 CRN:33456 Credits:3 Instructor:Craig Garrison-Mogren Room:Distance-Learning");
	}

	@Test
	public void testRead() {
		try {
			File inputFile = new File("Test2.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.println("CHEM135 12345 4 Distance-Learning Abner Mintz");
			inFile.println("MATH182 54231 4 Distance-Learning Jason Lee");
			inFile.println("ENES100 33456 3 Distance-Learning Craig Garrison-Mogren");
			inFile.print("ENGL102 91283 3 Distance-Learning Megan Howard");
			
			inFile.close();
			dataMgr.readFile(inputFile);
			ArrayList<String> list = dataMgr.showAll();
			assertEquals(list.get(0),"\nCourse:MATH182 CRN:54231 Credits:4 Instructor:Jason Lee Room:Distance-Learning");
			assertEquals(list.get(1),"\nCourse:ENGL102 CRN:91283 Credits:3 Instructor:Megan Howard Room:Distance-Learning");
			assertEquals(list.get(2),"\nCourse:CHEM135 CRN:12345 Credits:4 Instructor:Abner Mintz Room:Distance-Learning");
			assertEquals(list.get(3), "\nCourse:ENES100 CRN:33456 Credits:3 Instructor:Craig Garrison-Mogren Room:Distance-Learning");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Should not have thrown an exception");
		}
	}
}
