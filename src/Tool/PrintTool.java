package Tool;

public class PrintTool implements ITool{
    private Object object;
    public PrintTool(Object object){
        this.object = object;
    }
    @Override
    public Object executeTool() {
        System.out.println(object.toString());
        return null;
    }
}
