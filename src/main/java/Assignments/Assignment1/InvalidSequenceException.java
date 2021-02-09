package Assignments.Assignment1;

/**
 * Thrown to notify classes that a password has more than two characters in sequence
 *
 * @author James Pham
 */
public class InvalidSequenceException extends RuntimeException{

    public InvalidSequenceException(){
        super("The password cannot contain more than two of the same character in sequence");
    }

}
