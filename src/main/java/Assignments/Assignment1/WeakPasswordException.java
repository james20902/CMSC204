package Assignments.Assignment1;

/**
 * Thrown to notify that a password is valid, but could contain more characters for security
 *
 * @author James Pham
 */
public class WeakPasswordException extends RuntimeException{

    public WeakPasswordException(){
        super("The password is OK but weak - it contains fewer than 10 characters.");
    }

}
