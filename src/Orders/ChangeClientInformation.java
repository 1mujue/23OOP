package Orders;

import Clients.ClientInformation;
import Exceptions.JudgeExceptions.*;
import Exceptions.OrderExceptions.ChangeInformationException;
import Judges.*;
import Servers.Server;

/**
 * @description: this class is used for changing one's information.
 * @author 赵龙淳
 * @date 2023/12/9 23:06
 * @version 1.0
 */
public class ChangeClientInformation implements IOrder{
    private String old_name;
    private String old_password;
    private String new_name;
    private String new_password;

    public static String order_name = "ChangeClientInformation";

    public ChangeClientInformation(){}
    public ChangeClientInformation(String name, String password, String new_name, String new_password){
        this.old_name = name;
        this.old_password = password;
        this.new_name = new_name;
        this.new_password = new_password;
    }

    @Override
    public String getOrderName() {
        return order_name;
    }

    @Override
    public Object executeOrder() throws ChangeInformationException {
        try {
            ClientInformation old_client_information = (ClientInformation)
                    new IsClientExist(old_name,Server.getServer().getStatic_client_informations()).executeJudge();
            new IsPasswordRight(old_client_information, old_password).executeJudge();
            new IsLegalName(new_name).executeJudge();
            new IsLegalPassword(new_password).executeJudge();
            ClientInformation new_client_information = new ClientInformation(new_name,new_password,old_client_information.getTag());
            new IsInformationSame(new_client_information,old_client_information).executeJudge();
            //it is just a judge, not an order!
        } catch (ClientNotExistException e) {
            System.out.println("Client does not exist");
            throw new ChangeInformationException();
        } catch (WrongPasswordException e){
            System.out.println("Password wrong");
            throw new ChangeInformationException();
        } catch (IllegalNameException e){
            System.out.println("New name is illegal");
            throw new ChangeInformationException();
        } catch (IllegalPasswordException e){
            System.out.println("New password is illegal");
            throw new ChangeInformationException();
        } catch (SameClientInformationException e){
            System.out.println("The information are completely same");
            throw new ChangeInformationException();
        }
        return null;
    }
}
