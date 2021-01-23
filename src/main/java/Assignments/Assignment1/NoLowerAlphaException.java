package Assignments.Assignment1;

public class NoLowerAlphaException extends RuntimeException {

    public NoLowerAlphaException(){
        super("The password must contain at least one lower case alphabetic character");
    }
}
