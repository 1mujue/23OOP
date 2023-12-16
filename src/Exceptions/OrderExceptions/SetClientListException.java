package Exceptions.OrderExceptions;

/**
 * @author 赵龙淳
 * @version 1.0
 * @Description: TODO
 * @date 2023/12/12 21:33
 */
public class SetClientListException extends Exception{
    private static final String message = "Set client list failed";
    public SetClientListException(){
        super(message);
    }
}
