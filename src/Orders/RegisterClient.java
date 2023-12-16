package Orders;

import Clients.ClientInformation;
import Exceptions.JudgeExceptions.ClientNotExistException;
import Exceptions.JudgeExceptions.IllegalNameException;
import Exceptions.JudgeExceptions.IllegalPasswordException;
import Exceptions.JudgeExceptions.SameClientInformationException;
import Exceptions.OrderExceptions.CreatClientException;
import Judges.IsClientExist;
import Judges.IsLegalName;
import Judges.IsLegalPassword;
import Servers.Server;

import java.util.ArrayList;

/**
 * @description: in fact, this class is used for register.
 * @author 赵龙淳
 * @date 2023/12/9 23:06
 * @version 1.0
 */
public class RegisterClient implements IOrder{
    private ClientInformation clientInformation;
    public static String order_name = "RegisterClient";

    public RegisterClient(){}
    public RegisterClient(ClientInformation clientInformation){
        this.clientInformation = clientInformation;
    }

    @Override
    public String getOrderName() {
        return order_name;
    }

    @Override
    public Object executeOrder() throws CreatClientException {
        if(this.clientInformation.getTag() == 1) {
            try {
                new IsLegalName(clientInformation.getName()).executeJudge();
                new IsLegalPassword(clientInformation.getPassword()).executeJudge();
                new IsClientExist(clientInformation.getName(), Server.getServer().getStatic_client_informations()).executeJudge();
            } catch (IllegalNameException | IllegalPasswordException e) {
                System.out.println("Illegal name or password in register client");
                throw new CreatClientException();
            } catch (ClientNotExistException e) {

                System.out.println("It is permitted to register a client!");

                return null;
            }
            System.out.println("A client already exists!");
            throw new CreatClientException();
        }
        return null;
    }
}
