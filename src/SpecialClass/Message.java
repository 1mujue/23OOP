package SpecialClass;

import Clients.ClientInformation;
import Clients.ClientTest;

import java.io.Serializable;

/**
 * @author 赵龙淳
 * @version 1.0
 * @Description: TODO
 * @date 2023/12/14 12:06
 */
public class Message implements Serializable {
    private final ClientInformation sender_clientInformation;
    private final String text;
    private final String type;
    private final String tag;
    public Message(String text,String tag,String type,ClientInformation clientInformation){
        this.text = text;
        this.tag = tag;
        this.type = type;
        this.sender_clientInformation = clientInformation;
    }
    public String getText(){
        return this.text;
    }
    public String getType(){
        return this.type;
    }
    public ClientInformation getSender_clientInformation(){
        return this.sender_clientInformation;
    }
    public String getTag(){
        return this.tag;
    }
}
