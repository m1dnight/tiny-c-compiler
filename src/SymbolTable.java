import java.util.*; // To use ArrayList

public class SymbolTable {


    // The parent of this scope so we can create frames.
    SymbolTable parent;
    // Name of the function that holds this scope
    String function;
    // The list that will hold all the identifiers in this scope
    ArrayList<Object> symbolList = new ArrayList<Object>();


    public SymbolTable()
    {
        this.parent = null;
    }

    public SymbolTable NewScope(String functionName)
    {
        SymbolTable newScope = new SymbolTable();
        newScope.function = functionName;
        newScope.parent = this;
        return newScope;
    }

    public void Insert(String functionName, Object typeInformation)
    {
        symbolList.add(functionName);
    }
}