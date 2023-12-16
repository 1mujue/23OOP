package Exceptions.JudgeExceptions;

public class IllegalNameException extends Exception{
    private static final String message = "Illegal name";
    public IllegalNameException(){
        super(message);
    }
}
