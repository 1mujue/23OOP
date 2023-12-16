package Exceptions.OrderExceptions;

public class CreatClientException extends Exception{
    private static final String message = "Create client failed";
    public CreatClientException(){
        super(message);
    }
}
