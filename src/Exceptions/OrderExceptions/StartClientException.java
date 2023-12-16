package Exceptions.OrderExceptions;

/**
 * @author 赵龙淳
 * @version 1.0
 * @Description: TODO
 * @date 2023/12/11 13:46
 */
public class StartClientException extends Exception{
    private static final String message = "Start client failed";
    public StartClientException(){
        super(message);
    }
}
