package Judges;

import Clients.ClientInformation;
import Exceptions.JudgeExceptions.SameClientInformationException;

/**
 * @description: this class aims at judging whether information are the same.
 * @author 赵龙淳
 * @date 2023/12/9 23:01
 * @version 1.0
 */
public class IsInformationSame implements IJudge{
    private ClientInformation old_client_information;
    private ClientInformation new_client_information;

    public IsInformationSame(ClientInformation old_client_information,ClientInformation new_client_information){
        this.old_client_information = old_client_information;
        this.new_client_information = new_client_information;
    }
    @Override
    public Object executeJudge() throws SameClientInformationException {
        if(this.old_client_information.equals(this.new_client_information)){
            throw new SameClientInformationException();
        }
        return null;
    }
}
