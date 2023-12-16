package Tool;

import Orders.IOrder;

import java.io.File;

/**
 * @author 赵龙淳
 * @version 1.0
 * @Description: TODO
 * @date 2023/12/12 9:02
 */
public class InitiateFileTool implements ITool {
    private String file_path;
    Object o;
    public InitiateFileTool(Object o,String file_path){
        this.o = o;
        this.file_path = file_path;
    }
    @Override
    public Object executeTool() {
        new SerializeTool(o,file_path).executeTool();
        return null;
    }
}
