package Exceptions.OrderExceptions;

/**
 * @author 赵龙淳
 * @version 1.0
 * @Description: TODO
 * @date 2023/12/13 15:34
 */
public class AddFriendsException extends Exception{
    private static final String message = "Add friend failed";
    public AddFriendsException(){
        super(message);
    }
}
