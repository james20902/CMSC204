package Assignments.Assignment2;

/**
 * Thrown if an element in queue is trying to be accessed while empty
 *
 * @author James Pham
 */
public class QueueUnderflowException extends RuntimeException{

    public QueueUnderflowException(){
        super("");
    }

}
