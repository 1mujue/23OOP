package Lock;


/**
 * @author 赵龙淳
 * @version 1.0
 * @Description: TODO
 * @date 2023/12/12 18:51
 */
public class ClientListLock {
    private static final Object lock = new Object();
    public static Object getLock(){
        return lock;
    }
}
