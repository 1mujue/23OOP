package Exceptions.OrderExceptions;

/**
 * @author 赵龙淳
 * @version 1.0
 * @Description: TODO
 * @date 2023/12/13 15:07
 */
public class SendMessageToOthersException extends Exception{
    private static final String message ="Send message to others failed";
    public SendMessageToOthersException(){
        super(message);
    }
}
