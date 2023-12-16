package Orders;

import Clients.ClientInformation;
import Exceptions.JudgeExceptions.ClientNotExistException;
import Exceptions.JudgeExceptions.ClientNotOnlineException;
import Exceptions.JudgeExceptions.WrongPasswordException;
import Exceptions.OrderExceptions.LoginException;
import Judges.IsClientExist;
import Judges.IsClientOnline;
import Judges.IsPasswordRight;
import Servers.Server;

import java.util.ArrayList;

/**
 * @description: this class is used for logging in.
 * @author 赵龙淳
 * @date 2023/12/9 23:08
 * @version 1.0
 */
public class Login implements IOrder{

    public static String order_name = "Login";
    private ClientInformation clientInformation;

    public Login(){}
    public Login(ClientInformation clientInformation){
        this.clientInformation = clientInformation;
    }

    @Override
    public String getOrderName() {
        return order_name;
    }

    @Override
    public Object executeOrder() throws LoginException {
        if(this.clientInformation.getTag() == 2) {
            try {
                ClientInformation temp_client_information = (ClientInformation)
                        new IsClientExist(this.clientInformation.getName(),
                                Server.getServer().getStatic_client_informations()).executeJudge();
                new IsClientOnline(Server.getServer().getDynamic_client_informations(),temp_client_information).executeJudge();
                new IsPasswordRight(temp_client_information, this.clientInformation.getPassword()).executeJudge();

            } catch (ClientNotExistException | WrongPasswordException e) {
                System.out.println("It is not permitted to log in!");
                throw new LoginException();
            } catch (ClientNotOnlineException e){
                System.out.println("It is permitted to log in!");
            }
        }
        return null;
    }
}
