package Tool;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SerializeTool implements ITool{
    private String file_path;

    private FileOutputStream FOS = null;

    private ObjectOutputStream OOS = null;

    private Object object = null;
    public SerializeTool(Object object,String file_path){
        this.object = object;
        this.file_path = file_path;
    }
    @Override
    public Object executeTool() {
        try {
            FOS = new FileOutputStream(this.file_path);
            OOS = new ObjectOutputStream(FOS);
            OOS.writeObject(this.object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                OOS.close();
                FOS.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
