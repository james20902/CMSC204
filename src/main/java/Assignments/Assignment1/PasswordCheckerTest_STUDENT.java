package Assignments.Assignment1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * STUDENT tests for the methods of PasswordChecker
 * @author James Pham
 *
 */
public class PasswordCheckerTest_STUDENT {

	ArrayList<String> startingList;
	ArrayList<String> filteredList;
	String failLength = "abcde";
	String passLengthFailUpper = "abcdefghij";
	String passUpperFailLower = "ABCDE";
	String passLowerFailWeak = "4bc~deFGHI";
	String passWeak = "4BCd~eF";
	String failSequence =  "aaaBceeeee1~";
	String passSequenceFailDigit = "abcdef";
	String passDigitFailValid = "1234ABc";
	String passValid = "th1sIsASecureP4ssw.rd";

	@Before
	public void setUp() throws Exception {
		startingList = new ArrayList<>(
				Arrays.asList(failLength,
						passValid,
						"AnotherSecur3~pword",
						"anothersecur3~pword",
						"ANOTHERSECUR3~PWORD",
						"Someth1ngPasswordR*lated",
						"AbAbAbAb",
						passDigitFailValid,
						"YayyAnother~~longPassw0rd123",
						failSequence)
		);

		filteredList = new ArrayList<>(
			Arrays.asList(failLength + " -> The password must be at least 6 characters long",
					"anothersecur3~pword -> The password must contain at least one uppercase alphabetic character",
					"ANOTHERSECUR3~PWORD -> The password must contain at least one lower case alphabetic character",
					"AbAbAbAb -> The password must contain at least one digit",
					passDigitFailValid + " -> The password must contain at least one special character",
					failSequence + " -> The password cannot contain more than two of the same character in sequence")
		);
	}

	@After
	public void tearDown() throws Exception {
		startingList = null;
		filteredList = null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort() {
		assertThrows("The password must be at least 6 characters long",
				LengthException.class,
				() -> PasswordCheckerUtility.isValidLength(failLength));

		assertTrue(PasswordCheckerUtility.isValidLength(passLengthFailUpper));
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha() {
		assertThrows("The password must contain at least one uppercase alphabetic character",
				NoUpperAlphaException.class,
				() -> PasswordCheckerUtility.hasUpperAlpha(passLengthFailUpper));

		assertTrue(PasswordCheckerUtility.hasUpperAlpha(passUpperFailLower));
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha() {
		assertThrows("The password must contain at least one lowercase alphabetic character",
				NoLowerAlphaException.class,
				() -> PasswordCheckerUtility.hasLowerAlpha(passUpperFailLower));

		assertTrue(PasswordCheckerUtility.hasLowerAlpha(passLowerFailWeak));
	}
	/**
	 * Test if the password is valid but is no longer than nine characters
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword() {
		assertThrows("The password is OK but weak - it contains fewer than 10 characters.",
				WeakPasswordException.class,
				() -> PasswordCheckerUtility.isWeakPassword(passLowerFailWeak));

		assertTrue(PasswordCheckerUtility.isWeakPassword(passWeak));
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence() {
		assertThrows("The password cannot contain more than two of the same character in sequence",
				InvalidSequenceException.class,
				() -> PasswordCheckerUtility.hasSameCharInSequence(failSequence));

		assertTrue(PasswordCheckerUtility.hasSameCharInSequence(passSequenceFailDigit));
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit() {
		assertThrows("The password must contain at least one digit",
				NoDigitException.class,
				() -> PasswordCheckerUtility.hasDigit(passSequenceFailDigit));

		assertTrue(PasswordCheckerUtility.hasDigit(passDigitFailValid));
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		assertThrows(RuntimeException.class,
				() -> PasswordCheckerUtility.isValidPassword(passDigitFailValid));

		assertTrue(PasswordCheckerUtility.isValidPassword(passValid));
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		assertEquals(PasswordCheckerUtility.getInvalidPasswords(startingList), filteredList);
		filteredList.remove(0);
		assertNotEquals(PasswordCheckerUtility.getInvalidPasswords(startingList), filteredList);
	}
	
}
