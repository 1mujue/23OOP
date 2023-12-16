package Exceptions.OrderExceptions;

public class LoginException extends Exception{
    private static final String message = "login failed";
    public LoginException(){
        super(message);
    }
}
