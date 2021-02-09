package Assignments.Assignment1;

/**
 * Thrown to notify classes that a password does not have a lowercase character
 *
 * @author James Pham
 */
public class NoLowerAlphaException extends RuntimeException {

    public NoLowerAlphaException(){
        super("The password must contain at least one lower case alphabetic character");
    }
}
