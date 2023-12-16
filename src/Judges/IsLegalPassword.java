package Judges;

import Exceptions.JudgeExceptions.IllegalPasswordException;
/**
 * @description: this class is judging whether a password is legal.
 * @author 赵龙淳
 * @date 2023/12/9 23:02
 * @version 1.0
 */
public class IsLegalPassword implements IJudge{
    private String password;
    public IsLegalPassword(String password){
        this.password = password;
    }
    @Override
    public Object executeJudge() throws IllegalPasswordException {
        int length = password.length();
        if(length < 6 || length > 24)
            throw new IllegalPasswordException();
        boolean isUpper = false;
        boolean isLower = false;
        boolean isNumber = false;
        boolean isSpecial = false;
        for(char c : password.toCharArray()){
            if(c >= 'a' && c <= 'z'){
                isLower = true;
            }
            else if(c >= 'A' && c <= 'Z'){
                isUpper = true;
            }
            else if(c >= '0' && c <= '9'){
                isNumber = true;
            }
            else if(c == '!' || c == '@' || c == '$' || c == '_'){
                isSpecial = true;
            }
            else
                throw new IllegalPasswordException();
        }
        if(!(isLower && isUpper && isNumber && isSpecial)){
            throw new IllegalPasswordException();
        }
        return null;
    }
}
