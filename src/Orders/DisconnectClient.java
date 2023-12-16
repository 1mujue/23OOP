package Orders;

import Exceptions.OrderExceptions.DisconnectClientException;

import java.io.IOException;
import java.net.Socket;
/**
 * @description: this class merely disconnect a client from the server, but it will not delete the client.
 * @author 赵龙淳
 * @date 2023/12/9 23:07
 * @version 1.0
 */
public class DisconnectClient implements IOrder{

    public static String order_name = "DisconnectClient";
    private Socket socket;
    public DisconnectClient(){}
    public DisconnectClient(Socket socket){
        this.socket = socket;
    }

    @Override
    public String getOrderName() {
        return order_name;
    }

    @Override
    public Object executeOrder() throws DisconnectClientException {
        try {
            if(socket == null){
                System.out.println("Socket is null in disconnect client order");
                throw new DisconnectClientException();
            }
            socket.shutdownInput();
            socket.shutdownOutput();
        } catch (IOException e) {
            System.out.println("Socket shut stream failed in disconnect client order");
            throw new RuntimeException(e);
        }
        return null;
    }

}
