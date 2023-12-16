package Servers;

import Clients.ClientInformation;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
/**
 * @description: this class stocks client information, including his Socket and object stream.
 * @author 赵龙淳
 * @date 2023/12/9 23:10
 * @version 1.0
 */
public class ClientSocket {
    private ClientInformation clientInformation;
    private final Socket socket;
    private final ObjectInputStream OIS;
    private final ObjectOutputStream OOS;

    public ClientSocket(ClientInformation clientInformation, Socket socket,ObjectInputStream OIS,ObjectOutputStream OOS){
        this.clientInformation = clientInformation;
        this.socket = socket;
        this.OIS = OIS;
        this.OOS = OOS;
    }

    public ClientInformation getClientInformation(){
        return this.clientInformation;
    }

    public Socket getSocket(){
        return this.socket;
    }

    public void setClientInformation(ClientInformation clientInformation){
        this.clientInformation = clientInformation;
    }
    public ObjectInputStream getOIS(){
        return this.OIS;
    }
    public ObjectOutputStream getOOS(){
        return this.OOS;
    }
}
