package Clients;

/**
 * @author 赵龙淳
 * @version 1.0
 * @Description: this class is a signal class when we want to make friends with someone.
 * @date 2023/12/12 18:22
 */
public class ApplyFriends {
    private final String text;
    private final ClientInformation clientInformation;
    private final int tag = 1;
    public ApplyFriends(ClientInformation clientInformation,String text){
        this.clientInformation = clientInformation;
        this.text = text;
    }
    public ClientInformation getClientInformation(){
        return this.clientInformation;
    }
    public String getText(){
        return this.text;
    }
    public int getTag(){
        return this.tag;
    }
}
