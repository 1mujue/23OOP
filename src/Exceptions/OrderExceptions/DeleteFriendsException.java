package Exceptions.OrderExceptions;

/**
 * @author 赵龙淳
 * @version 1.0
 * @Description: TODO
 * @date 2023/12/13 15:45
 */
public class DeleteFriendsException extends Exception{
    private static final String message = "Delete friends failed";
    public DeleteFriendsException(){
        super(message);
    }
}
