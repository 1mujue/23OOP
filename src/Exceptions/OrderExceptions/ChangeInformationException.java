package Exceptions.OrderExceptions;

public class ChangeInformationException extends Exception{
    private static final String message = "Change information failed";

    public ChangeInformationException(){
        super(message);
    }
}
