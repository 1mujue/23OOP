package Clients;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @description: this is the friend list of a client.
 * @author 赵龙淳
 * @date 2023/12/9 22:36
 * @version 1.0
 */
public class ClientList implements Serializable {//this list represents the list of client_information.
    private ArrayList<ClientInformation> clientInformations;
    private ClientInformation owner_client_information;
    private String type;
    private String text;
    public ClientList(String type){
        owner_client_information = null;
        clientInformations = new ArrayList<>();
        this.type = type;
    }
    public ClientList(ClientInformation clientInformation,String type,String text) {
        owner_client_information = clientInformation;
        this.text = text;
        this.type = type;
        clientInformations = new ArrayList<>();
    }
    public ClientList(ClientInformation clientInformation,String type){
        this(clientInformation, type, "new list");
    }
    public ClientInformation getOwner_client_information(){
        return this.owner_client_information;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    public void setText(String text){
        this.text = text;
    }
    public String getText(){
        return this.text;
    }
    public ArrayList<ClientInformation> getClientInformations(){
        return this.clientInformations;
    }
    public void addClientInformation(ClientInformation clientInformation){
        this.clientInformations.add(clientInformation);
    }
    public void deleteClientInformation(ClientInformation clientInformation){
        this.clientInformations.remove(clientInformation);
    }
    public void setClientInformations(ArrayList<ClientInformation> clientInformations){
        this.clientInformations = clientInformations;
    }
    public void refreshClientInformation(ClientInformation old_client_information,ClientInformation new_client_information){
        for(ClientInformation clientInformation : this.clientInformations){
            if(clientInformation.getName().equals(old_client_information.getName())){
                clientInformations.remove(old_client_information);
                clientInformations.add(new_client_information);
                break;
            }
        }
    }
}
