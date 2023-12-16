package Exceptions.JudgeExceptions;


/**
 * @author 赵龙淳
 * @version 1.0
 * @Description: TODO
 * @date 2023/12/11 12:36
 */
public class ClientNotOnlineException extends Exception{
    private static final String message = "client is not online";
    public ClientNotOnlineException(){
        super(message);
    }
}
