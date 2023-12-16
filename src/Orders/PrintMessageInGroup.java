package Orders;

import Exceptions.OrderExceptions.PrintInGroupExcption;
import Servers.Server;

import java.util.ArrayList;
import java.util.Iterator;
/**
 * @description: this is a class used by server to print message.
 * @author 赵龙淳
 * @date 2023/12/9 23:09
 * @version 1.0
 */
public class PrintMessageInGroup implements IOrder{

    public static String order_name = "PrintMessageInGroup";
    private String text;

    private ArrayList<Server.ClientThread> clientThreads;

    public PrintMessageInGroup(){}
    public PrintMessageInGroup(String text, ArrayList<Server.ClientThread> clientThreads){
        this.text = text;
        this.clientThreads = clientThreads;
    }

    @Override
    public String getOrderName() {
        return order_name;
    }

    @Override
    public Object executeOrder() throws PrintInGroupExcption {
        Iterator<Server.ClientThread> ITCSS = clientThreads.iterator();
        Server.ClientThread temp_CS;
        while (ITCSS.hasNext()) {
            temp_CS = ITCSS.next();
            Server.getServer().sendObject(temp_CS.clientSocket, text);
        }
        return null;
    }
}
