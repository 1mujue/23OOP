package Exceptions.JudgeExceptions;

public class ClientNotExistException extends Exception{
    private static final String message = "client not exist";
    public ClientNotExistException(){
        super(message);
    }
}
