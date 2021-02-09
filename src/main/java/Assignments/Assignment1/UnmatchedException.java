package Assignments.Assignment1;

/**
 * Thrown to notify that two passwords do not match
 *
 * @author James Pham
 */
public class UnmatchedException extends RuntimeException{

    public UnmatchedException(){
        super("Passwords do not match");
    }

}
