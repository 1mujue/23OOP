package Exceptions.OrderExceptions;

/**
 * @author 赵龙淳
 * @version 1.0
 * @Description: TODO
 * @date 2023/12/12 18:48
 */
public class ClientListNotFoundException extends Exception{
    private static final String message = "client list not found";
    public ClientListNotFoundException(){
        super(message);
    }
}
