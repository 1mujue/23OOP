package Exceptions.OrderExceptions;

/**
 * @author 赵龙淳
 * @version 1.0
 * @Description: TODO
 * @date 2023/12/12 21:48
 */
public class InitiateObjectStreamException extends Exception{
    private static final String message = "Initiate object stream failed";
    public InitiateObjectStreamException(){
        super(message);
    }
}
