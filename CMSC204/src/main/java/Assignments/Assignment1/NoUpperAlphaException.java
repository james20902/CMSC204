package Assignments.Assignment1;

public class NoUpperAlphaException extends RuntimeException{

    public NoUpperAlphaException(){
        super("The password must contain at least one uppercase alphabetic character");
    }

}
