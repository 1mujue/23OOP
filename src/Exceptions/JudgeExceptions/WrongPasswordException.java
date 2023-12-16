package Exceptions.JudgeExceptions;

public class WrongPasswordException extends Exception{
    private static final String message = "wrong password";
    public WrongPasswordException(){
        super(message);
    }
}
