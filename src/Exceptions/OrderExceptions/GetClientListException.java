package Exceptions.OrderExceptions;

/**
 * @author 赵龙淳
 * @version 1.0
 * @Description: TODO
 * @date 2023/12/12 21:34
 */
public class GetClientListException extends Exception{
    private static final String message = "Get Client list failed";
    public GetClientListException(){
        super(message);
    }
}
