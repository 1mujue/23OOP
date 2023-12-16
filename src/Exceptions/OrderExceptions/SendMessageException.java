package Exceptions.OrderExceptions;

/**
 * @author 赵龙淳
 * @version 1.0
 * @Description: TODO
 * @date 2023/12/12 21:44
 */
public class SendMessageException extends Exception{
    private static final String message = "Send message failed";
    public SendMessageException(){
        super(message);
    }
}
