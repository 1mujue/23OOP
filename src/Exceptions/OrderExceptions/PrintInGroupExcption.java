package Exceptions.OrderExceptions;

public class PrintInGroupExcption extends Exception{
    private static final String message = "print globally failed";

    public PrintInGroupExcption(){
        super(message);
    }
}
