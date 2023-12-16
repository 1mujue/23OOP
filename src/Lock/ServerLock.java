package Lock;

public class ServerLock {
    private static Object lock = new Object();
    public static Object getLock(){
        return lock;
    }
}
