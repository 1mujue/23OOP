package Orders;
/**
 * @description: this is the interface of all orders.
 * @author 赵龙淳
 * @date 2023/12/9 23:07
 * @version 1.0
 */
public interface IOrder {
    String getOrderName();
    Object executeOrder() throws Exception;
}
