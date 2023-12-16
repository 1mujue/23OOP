package Exceptions.OrderExceptions;

/**
 * @author 赵龙淳
 * @version 1.0
 * @Description: TODO
 * @date 2023/12/12 21:53
 */
public class GetMessageException extends Exception{
    private static final String message = "Get message failed";
    public GetMessageException(){
        super(message);
    }
}
