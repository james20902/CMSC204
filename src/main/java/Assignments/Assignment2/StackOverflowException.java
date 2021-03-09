package Assignments.Assignment2;

/**
 * Thrown if a stack is trying to add an element while full
 *
 * @author James Pham
 */
public class StackOverflowException extends RuntimeException{

    public StackOverflowException(){
        super("");
    }

}
