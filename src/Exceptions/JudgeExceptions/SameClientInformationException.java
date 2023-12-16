package Exceptions.JudgeExceptions;

public class SameClientInformationException extends Exception{
    private static final String message = "Old name and old password are completely same with " +
            "new name and new password";

    public SameClientInformationException(){
        super(message);
    }
}
