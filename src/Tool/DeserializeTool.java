package Tool;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeserializeTool implements ITool{

    File file = null;
    private ObjectInputStream OIS = null;
    private FileInputStream FIS = null;

    public DeserializeTool(String file_path){
        file = new File(file_path);
    }
    @Override
    public Object executeTool() {
        try {
            FIS = new FileInputStream(file);
            OIS = new ObjectInputStream(FIS);
            Object object = OIS.readObject();
            return object;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
