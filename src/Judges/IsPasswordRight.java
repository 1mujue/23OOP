package Judges;

import Clients.ClientInformation;
import Exceptions.JudgeExceptions.WrongPasswordException;
/**
 * @description: this class is judging whether a password matches.
 * @author 赵龙淳
 * @date 2023/12/9 23:03
 * @version 1.0
 */
public class IsPasswordRight implements IJudge{
    ClientInformation clientInformation;
    String password;
    public IsPasswordRight(ClientInformation clientInformation, String password){
        this.clientInformation = clientInformation;
        this.password = password;
    }
    @Override
    public Object executeJudge() throws WrongPasswordException {
        if(!password.equals(clientInformation.getPassword())){
            throw new WrongPasswordException();
        }
        return null;
    }
}
