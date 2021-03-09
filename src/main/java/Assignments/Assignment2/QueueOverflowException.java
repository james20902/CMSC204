package Assignments.Assignment2;

/**
 * Thrown if a queue is trying to add an element while full
 *
 * @author James Pham
 */
public class QueueOverflowException extends RuntimeException{

    public QueueOverflowException(){
        super("");
    }

}
