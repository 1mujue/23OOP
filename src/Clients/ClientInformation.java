package Clients;

import java.io.Serializable;

/**
 * @description: this class stocks the fundamental information of a client.
 * @author 赵龙淳
 * @date 2023/12/9 22:49
 * @version 1.0
 */
public class ClientInformation implements Serializable {
    private Client client;
    private String name;
    private String password;
    private int tag;//judge whether we want to register a new client(1) or log in(2).(temporary)
    public ClientInformation(String name,String password,int tag){
        this.name = name;
        this.password = password;
        this.tag = tag;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public int getTag(){
        return this.tag;
    }
    public void setClient(Client client){
        this.client = client;
    }
    public Client getClient(){
        return this.client;
    }
}
