package Assignments.Assignment1;

import java.util.ArrayList;

/**
 * Utility class for checking if a password conforms to certain requirements
 * to ensure security
 *
 * @author James Pham
 */
public class PasswordCheckerUtility {

    /**
     * Compare two raw password strings and ensure they match, otherwise throw
     * an exception indicating mismatch
     * @param password The original password
     * @param passwordConfirm The second password string for comparison
     * @throws UnmatchedException If strings do not equal, exception is thrown to notify any other classes
     */
    public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException{
        if(!password.equals(passwordConfirm)){
            throw new UnmatchedException();
        }
    }

    /**
     * Compare two raw password strings and ensure they match, but returning a boolean instead
     * @param password The original password
     * @param passwordConfirm The second password string for comparison
     * @return Whether or not the original and second password are the same
     */
    public static boolean comparePasswordsWithReturn(String password, String passwordConfirm){
        return password.equals(passwordConfirm);
    }

    /**
     * Process a list of passwords and return a list of passwords considered invalid
     * @param passwords List of passwords
     * @return List of passwords that are considered invalid, with reasoning appended
     */
    public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords){
        ArrayList<String> badPasswords = new ArrayList<>();

        //pass through list
        passwords.forEach(passwd -> {
            try{
                //check if password is valid
                isValidPassword(passwd);
            } catch (RuntimeException e){
                //if any exception is thrown, assume bad password and append reasoning
                badPasswords.add(passwd + " -> " + e.getMessage());
            }
        });
        return badPasswords;
    }

    /**
     * Check that a raw password string has between six and nine characters
     * @param password Raw password string to check
     * @return Whether or not the given password has between six and nine characters
     */
    public static boolean hasBetweenSixAndNineChars(String password){
        return password.length() >= 6 && password.length() <= 9;
    }

    /**
     * Check that a raw password string contains a digit
     * @param password Raw password string to check
     * @return True if the password contains a digit
     * @throws NoDigitException Notify any class using this utility class that the password has no digit
     */
    public static boolean hasDigit(String password) throws NoDigitException{
        int length = password.length();
        if(password.replaceAll("[0-9]", "").length() < length){
            return true;
        } else {
            throw new NoDigitException();
        }
    }

    /**
     * Check that a raw password string contains a lower case character
     * @param password Raw password string to check
     * @return True if the password contains a lower case character
     * @throws NoLowerAlphaException Notify any class using this utility class that the password has no
     * lower case character
     */
    public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException{
        int length = password.length();
        if(password.replaceAll("[a-z]", "").length() < length){
            return true;
        } else {
            throw new NoLowerAlphaException();
        }
    }

    /**
     * Check that a raw password string doesn't contain three of the same characters in sequence
     * @param password Raw password string to check
     * @return True if the password remains unique throughout
     * @throws InvalidSequenceException Notify any classes using this utility class that there are
     * three or more matching characters in sequence
     */
    public static boolean hasSameCharInSequence(String password) throws InvalidSequenceException{
        //convert to array for iteration
        char[] arr = password.toCharArray();
        //for every character until the third to last one
        for(int i = 0; i < arr.length - 3; i++){
            //if a pair of three characters match, throw exception to notify user classes
            if(arr[i] == arr[i + 1] && arr[i + 1] == arr[i + 2])
                throw new InvalidSequenceException();
        }
        return true;
    }

    /**
     * Check that a raw password string contains a special character
     * @param password Raw password string to check
     * @return True if the password contains a special character
     * @throws NoSpecialCharacterException Notify any class using this utility class that the password has no
     * special characters
     */
    public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException{
        int length = password.length();
        if(password.replaceAll("[^A-Za-z0-9]", "").length() < length){
            return true;
        } else {
            throw new NoSpecialCharacterException();
        }
    }

    /**
     * Check that a raw password string contains an upper case character
     * @param password Raw password string to check
     * @return True if the password contains an upper case character
     * @throws NoDigitException Notify any class using this utility class that the password has no upper case
     * character
     */
    public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException{
        int length = password.length();
        if(password.replaceAll("[A-Z]", "").length() < length){
            return true;
        } else {
            throw new NoUpperAlphaException();
        }
    }

    /**
     * Check that a raw password string is considered a valid length
     * @param password Raw password string to check
     * @return True if the password is considered valid length wise, in this implementation six characters
     * @throws LengthException Notify any class using this utility class that the password is too short
     */
    public static boolean isValidLength(String password) throws LengthException{
        if(password.length() > 6){
            return true;
        } else {
            throw new LengthException();
        }
    }

    /**
     * Check that a raw password conforms to all standards to be considered secure
     * @param password Raw password string to check
     * @return True assuming no checks throw exceptions
     * @throws LengthException Length check failed
     * @throws NoUpperAlphaException Uppercase character check failed
     * @throws NoLowerAlphaException Lowercase character check failed
     * @throws NoDigitException Digit check failed
     * @throws NoSpecialCharacterException Special character check failed
     * @throws InvalidSequenceException Character sequence check failed
     */
    public static boolean isValidPassword(String password) throws LengthException,
            NoUpperAlphaException,
            NoLowerAlphaException,
            NoDigitException,
            NoSpecialCharacterException,
            InvalidSequenceException {
        return isValidLength(password)
                && hasUpperAlpha(password)
                && hasLowerAlpha(password)
                && hasDigit(password)
                && hasSpecialChar(password)
                && hasSameCharInSequence(password);
    }

    /**
     * Check that raw password string is valid but too short
     * @param password Raw password string to check
     * @return True if the password is valid, but also has between six and nine characters
     * @throws WeakPasswordException Notify any class using this utility class that the password is not weak or password is invalid
     */
    public static boolean isWeakPassword(String password) throws WeakPasswordException{
        if(isValidPassword(password) && hasBetweenSixAndNineChars(password)){
            return true;
        } else {
            throw new WeakPasswordException();
        }
    }

}
