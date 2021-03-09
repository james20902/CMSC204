package Assignments.Assignment2;

/**
 * Thrown if an element in the stack is trying to be accessed while empty
 *
 * @author James Pham
 */
public class StackUnderflowException extends RuntimeException{

    public StackUnderflowException(){
        super("");
    }

}
