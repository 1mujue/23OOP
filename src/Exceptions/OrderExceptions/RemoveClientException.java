package Exceptions.OrderExceptions;

/**
 * @author 赵龙淳
 * @version 1.0
 * @Description: TODO
 * @date 2023/12/9 22:15
 */
public class RemoveClientException extends Exception{
    private static final String message = "remove client failed";

    public RemoveClientException(){
        super(message);
    }
}
