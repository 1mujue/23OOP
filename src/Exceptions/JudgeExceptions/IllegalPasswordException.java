package Exceptions.JudgeExceptions;

public class IllegalPasswordException extends Exception{
    private static final String message = "Illegal password";
    public IllegalPasswordException(){
        super(message);
    }
}
