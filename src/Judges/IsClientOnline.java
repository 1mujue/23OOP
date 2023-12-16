package Judges;

import Clients.ClientInformation;
import Clients.ClientList;
import Exceptions.JudgeExceptions.ClientNotOnlineException;

import java.util.ArrayList;

/**
 * @author 赵龙淳
 * @version 1.0
 * @Description: TODO
 * @date 2023/12/11 12:35
 */
public class IsClientOnline implements IJudge{
    private ClientList online_client_list;
    private ClientInformation target_client_information;
    public IsClientOnline(ClientList online_client_list, ClientInformation clientInformation){
        this.online_client_list = online_client_list;
        this.target_client_information = clientInformation;
    }
    @Override
    public Object executeJudge() throws ClientNotOnlineException {
        ArrayList<ClientInformation> online_client_informations = online_client_list.getClientInformations();
        if(online_client_informations == null){
            throw new ClientNotOnlineException();
        }
        for(ClientInformation clientInformation : online_client_informations){
            if(target_client_information.getName().equals(clientInformation.getName())){
                return null;
            }
        }
        throw new ClientNotOnlineException();
    }
}
