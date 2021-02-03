package Assignments.Assignment1;

import java.util.ArrayList;

public class PasswordCheckerUtility {

    public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException{
        if(!password.equals(passwordConfirm)){
            throw new UnmatchedException();
        }
    }

    public static boolean comparePasswordsWithReturn(String password, String passwordConfirm){
        return password.equals(passwordConfirm);
    }

    public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords){
        ArrayList<String> badPasswords = new ArrayList<>();
        passwords.forEach(passwd -> {
            try{
                isValidPassword(passwd);
            } catch (RuntimeException e){
                badPasswords.add(passwd + " -> " + e.getMessage());
            }
        });
        return badPasswords;
    }

    public static boolean hasBetweenSixAndNineChars(String password){
        return password.length() >= 6 && password.length() <= 9;
    }

    public static boolean hasDigit(String password) throws NoDigitException{
        int length = password.length();
        if(password.replaceAll("[0-9]", "").length() < length){
            return true;
        } else {
            throw new NoDigitException();
        }
    }

    public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException{
        int length = password.length();
        if(password.replaceAll("[a-z]", "").length() < length){
            return true;
        } else {
            throw new NoLowerAlphaException();
        }
    }

    public static boolean hasSameCharInSequence(String password) throws InvalidSequenceException{
        char[] arr = password.toCharArray();
        for(int i = 0; i < arr.length - 3; i++){
            if(arr[i] == arr[i + 1] && arr[i + 1] == arr[i + 2])
                throw new InvalidSequenceException();
        }
        return true;
    }

    public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException{
        int length = password.length();
        if(password.replaceAll("[^A-Za-z0-9]", "").length() < length){
            return true;
        } else {
            throw new NoSpecialCharacterException();
        }
    }

    public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException{
        int length = password.length();
        if(password.replaceAll("[A-Z]", "").length() < length){
            return true;
        } else {
            throw new NoUpperAlphaException();
        }
    }

    public static boolean isValidLength(String password) throws LengthException{
        if(password.length() > 6){
            return true;
        } else {
            throw new LengthException();
        }
    }

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

    public static boolean isWeakPassword(String password) throws WeakPasswordException{
        if(isValidPassword(password) && hasBetweenSixAndNineChars(password)){
            return true;
        } else {
            throw new WeakPasswordException();
        }
    }

}
