package SpecialClass;

import Clients.ClientInformation;

/**
 * @author 赵龙淳
 * @version 1.0
 * @Description: TODO
 * @date 2023/12/14 11:34
 */
public class InformationChange {
    private final ClientInformation old_client_information;
    private final ClientInformation new_client_information;
    public InformationChange(ClientInformation old_client_information,ClientInformation new_client_information){
        this.old_client_information = old_client_information;
        this.new_client_information = new_client_information;
    }
    public ClientInformation getOld_client_information(){
        return this.old_client_information;
    }
    public ClientInformation getNew_client_information(){
        return this.new_client_information;
    }
}
