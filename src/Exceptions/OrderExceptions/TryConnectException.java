package Exceptions.OrderExceptions;

/**
 * @author 赵龙淳
 * @version 1.0
 * @Description: TODO
 * @date 2023/12/12 21:36
 */
public class TryConnectException extends Exception{
    private static final String message = "Try to connect failed";
    public TryConnectException(){
        super(message);
    }
}
