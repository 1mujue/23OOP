package Clients;

/**
 * @author 赵龙淳
 * @version 1.0
 * @Description: this class is a signal class when we have agreed to add friends.
 * @date 2023/12/12 18:36
 */
public class AgreeFriends {
    private final ClientInformation clientInformation;
    private final int tag;
    public AgreeFriends(ClientInformation clientInformation,int tag){
        this.clientInformation = clientInformation;
        this.tag = tag;
    }
    public ClientInformation getClientInformation(){
        return this.clientInformation;
    }
    public int getTag(){
        return this.tag;
    }
}
