package Judges;

import Clients.ClientInformation;
import Clients.ClientList;
import Exceptions.JudgeExceptions.ClientNotExistException;

import java.util.ArrayList;

/**
 * @description: this class is trying to judge whether a client exist in our database.
 * @author 赵龙淳
 * @date 2023/12/9 23:00
 * @version 1.0
 */
public class IsClientExist implements IJudge{
    private String target_name;
    private ClientList source_client_list;
    public IsClientExist(String target_name, ClientList source_client_list){
        this.target_name = target_name;
        this.source_client_list = source_client_list;
    }
    @Override
    public Object executeJudge() throws ClientNotExistException {
        ArrayList<ClientInformation> clientInformations = source_client_list.getClientInformations();
        if(clientInformations == null)
            throw new ClientNotExistException();
        for(ClientInformation clientInformation : clientInformations){
            if(target_name.equals(clientInformation.getName())){
                return clientInformation;
            }
        }
        throw new ClientNotExistException();
    }
}
