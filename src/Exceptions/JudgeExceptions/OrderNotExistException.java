package Exceptions.JudgeExceptions;

public class OrderNotExistException extends Exception{
    private static final String message = "The order doesn't exist";

    public OrderNotExistException(){
        super(message);
    }
}
