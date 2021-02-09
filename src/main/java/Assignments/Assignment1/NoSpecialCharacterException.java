package Assignments.Assignment1;

/**
 * Thrown to notify other classes that a password does not have a special character
 *
 * @author James Pham
 */
public class NoSpecialCharacterException extends RuntimeException{

    public NoSpecialCharacterException(){
        super("The password must contain at least one special character");
    }

}
