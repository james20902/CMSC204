package Assignments.Assignment1;

public class UnmatchedException extends RuntimeException{

    public UnmatchedException(){
        super("Passwords do not match");
    }

}
