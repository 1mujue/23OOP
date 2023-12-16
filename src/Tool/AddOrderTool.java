package Tool;

import Exceptions.JudgeExceptions.OrderNotExistException;
import Judges.IsOrderExist;
import Orders.IOrder;
import Servers.Server;

public class AddOrderTool implements ITool{
    IOrder order = null;

    public AddOrderTool(IOrder order){
        this.order = order;
    }
    @Override
    public Object executeTool() {
        try {
            new IsOrderExist(this.order).executeJudge();
        } catch (OrderNotExistException e) {
            throw new RuntimeException(e);
        }
        new Thread(new addOrderThread(this.order)).start();
        return null;
    }

    class addOrderThread implements Runnable{

        private IOrder order;

        private final Object lock = Lock.ServerLock.getLock();

        public addOrderThread(IOrder order){

            this.order = order;
        }

        @Override
        public void run() {
            while(true) {
                synchronized (lock) {
                    if (Server.getServer().max_order_num == Server.getServer().order_num) {
                        try {
                            System.out.println("AddOrder is blocked!");
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    Server.getServer().addOrder(this.order);
                    lock.notify();
                    System.out.println("AddOrder notifies the ServerThread!");
                    break;
                }
            }
        }
    }
}
