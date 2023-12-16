package Judges;

import Exceptions.JudgeExceptions.OrderNotExistException;
import Orders.IOrder;
import Orders.OrderList;

import java.util.ArrayList;
/**
 * @description: this class may be temporary, it is a guarantee that we add a non-existent order when coding.
 * @author 赵龙淳
 * @date 2023/12/9 23:02
 * @version 1.0
 */
public class IsOrderExist implements IJudge{
    IOrder order;

    public IsOrderExist(IOrder order){
        this.order = order;
    }
    @Override
    public Object executeJudge() throws OrderNotExistException {
        int flag = 0;
        ArrayList<IOrder> orders = OrderList.getOrders();
        for(IOrder iorder : orders){
            if(this.order.getOrderName().equals(iorder.getOrderName())){
                flag = 1;
                break;
            }
        }
        if(flag == 0){
            throw new OrderNotExistException();
        }
        return null;
    }
}
