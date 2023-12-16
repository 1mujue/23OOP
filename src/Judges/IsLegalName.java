package Judges;

import Exceptions.JudgeExceptions.IllegalNameException;
/**
 * @description: this class is judging whether a name is legal.
 * @author 赵龙淳
 * @date 2023/12/9 23:01
 * @version 1.0
 */
public class IsLegalName implements IJudge{
    private String name;
    public IsLegalName(String name){
        this.name = name;
    }
    @Override
    public Object executeJudge() throws IllegalNameException {
        int length = name.length();
        if(length < 5 || length > 15)
            throw new IllegalNameException();
        for(char c : name.toCharArray()){
            if(c == '\\' || c == '/' || c == '-')
                throw new IllegalNameException();
        }
        return null;
    }
}
