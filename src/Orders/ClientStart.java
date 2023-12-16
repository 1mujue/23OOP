package Orders;

import Clients.Client;
import Clients.ClientInformation;
import Exceptions.OrderExceptions.StartClientException;

/**
 * @author 赵龙淳
 * @version 1.0
 * @Description: TODO
 * @date 2023/12/11 13:38
 */
public class ClientStart implements IOrder{
    private static final String order_name = "ClientStart";
    private ClientInformation clientInformation;
    public ClientStart(ClientInformation clientInformation){
        this.clientInformation = clientInformation;
    }
    @Override
    public String getOrderName() {
        return order_name;
    }

    @Override
    public Object executeOrder() throws StartClientException {
        Client client = null;
        try {
            client = new Client(clientInformation);
            client.tryConnect();
        } catch (Exception e) {
            e.printStackTrace();
            throw new StartClientException();
        }
        return null;
    }
}
