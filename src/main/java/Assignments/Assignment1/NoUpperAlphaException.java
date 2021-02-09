package Assignments.Assignment1;

/**
 * Thrown to notify other classes that a password does not have an uppercase character
 *
 * @author James Pham
 */
public class NoUpperAlphaException extends RuntimeException{

    public NoUpperAlphaException(){
        super("The password must contain at least one uppercase alphabetic character");
    }

}
