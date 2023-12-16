package Orders;

import Clients.ClientInformation;
import Exceptions.OrderExceptions.RemoveClientException;
import Servers.Server;

import java.util.ArrayList;

/**
 * @description: this class would remove the client completely.
 * @author 赵龙淳
 * @date 2023/12/9 23:10
 * @version 1.0
 */
public class RemoveClient implements IOrder{
    private String order_name = "RemoveClient";
    private String client_name;
    private ArrayList<ClientInformation> clientInformations;
    private ArrayList<Server.ClientThread> clientThreads;
    @Override
    public String getOrderName() {
        return order_name;
    }
    public RemoveClient(){}
    public RemoveClient(String client_name, ArrayList<ClientInformation> clientInformations,
                        ArrayList<Server.ClientThread> clientThreads){
        this.client_name = client_name;
        this.clientInformations = clientInformations;
        this.clientThreads = clientThreads;
    }
    @Override
    public Object executeOrder() throws RemoveClientException {
        boolean isRemoved = false;
        for(ClientInformation clientInformation : clientInformations){
            if(client_name.equals(clientInformation.getName())){
                clientInformations.remove(clientInformation);
                isRemoved = true;
                break;
            }
        }
        for(Server.ClientThread clientThread : clientThreads){
            if(clientThread.clientSocket.getClientInformation().getName().equals(client_name)){
                new DisconnectClient(clientThread.clientSocket.getSocket());
                clientThreads.remove(clientThread);
                break;
            }
        }
        if(!isRemoved){
            throw new RemoveClientException();
        }
        return null;
    }
}
