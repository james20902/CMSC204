package Assignments.Assignment1;

/**
 * Thrown to notify classes that a password does not exceed the length requirement,
 * in this case being six characters
 *
 * @author James Pham
 */
public class LengthException extends RuntimeException{

    public LengthException(){
        super("The password must be at least 6 characters long");
    }

}
