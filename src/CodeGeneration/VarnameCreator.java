package CodeGeneration;

import SymbolTable.StringSymTabInfo;
import Typing.ConstantTypeInfo;
import Typing.Types;

/**
 * VarnameCreator is used to generate unique names throughout the parsing process.
 * We create unique names for intermediate variables and for GOTO labels based
 * on counters.
 */
public class VarnameCreator {
    private static VarnameCreator instance = null;
    private int    counter = 0;
    private int    labelCounter = 0;
    protected VarnameCreator()
    {
        //Singleton constructor
    }
    public static VarnameCreator getInstance()
    {
        if(instance == null)
            instance =new VarnameCreator();
        return instance;
    }
    public String CreateName()
    {
        this.counter++;
        return String.format("_var%d", counter);

    }
    public String CreateLabel()
    {
        this.labelCounter++;
        return String.format("_label%d:", labelCounter);
    }
    public String CreateLabel(String labelText)
    {
        return String.format("_label_%s:", labelText);
    }
    public String CreateFunctionLabel(String labelText)
    {
        return String.format("function_%s:", labelText);
    }
}