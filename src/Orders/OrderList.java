package Orders;

import java.util.ArrayList;
/**
 * @description: this class is quite special, actually it is a insurance of misusing orders.
 * @author 赵龙淳
 * @date 2023/12/9 23:08
 * @version 1.0
 */
public class OrderList {
    private final ArrayList<IOrder> orders = new ArrayList<>();
    private static final OrderList orderList = new OrderList();
    private OrderList(){
        orders.add(new ChangeClientInformation());
        orders.add(new RegisterClient());
        orders.add(new DisconnectClient());
        orders.add(new Login());
        orders.add(new PrintMessageInGroup());
        orders.add(new RemoveClient());
    }

    public static ArrayList<IOrder> getOrders(){
        return orderList.orders;
    }
}
