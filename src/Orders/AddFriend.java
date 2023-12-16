package Orders;

import Clients.Client;
import Clients.ClientInformation;
import Clients.ClientList;
import Exceptions.JudgeExceptions.ClientNotOnlineException;

/**
 * @author 赵龙淳
 * @version 1.0
 * @Description: TODO
 * @date 2023/12/12 18:12
 */
public class AddFriend implements IOrder{
    private static final String order_name = "AddFriend";
    private ClientList clientList;
    public AddFriend(ClientInformation clientInformation, ClientList clientList){

    }
    @Override
    public String getOrderName() {
        return order_name;
    }

    @Override
    public Object executeOrder() throws Exception {
        return null;
    }
}
