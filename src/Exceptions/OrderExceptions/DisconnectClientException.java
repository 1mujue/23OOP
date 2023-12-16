package Exceptions.OrderExceptions;


public class DisconnectClientException extends Exception{
    private static final String message = "disconnect a client failed";

    public DisconnectClientException(){
        super(message);
    }
}
