package Assignments.Assignment1;

/**
 * Thrown to notify classes that a password does not have any digits
 *
 * @author James Pham
 */
public class NoDigitException extends RuntimeException{

    public NoDigitException(){
        super("The password must contain at least one digit");
    }

}
