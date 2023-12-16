package Exceptions.OrderExceptions;

/**
 * @author 赵龙淳
 * @version 1.0
 * @Description: TODO
 * @date 2023/12/12 21:38
 */
public class ConnectServerException extends Exception{
    private static final String message = "Connect server failed";
    public ConnectServerException(){
        super(message);
    }
}
