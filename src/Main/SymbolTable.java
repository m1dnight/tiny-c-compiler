package Main;

import java.util.*; // To use ArrayList

public class SymbolTable {


    // The parent of this scope so we can create frames.
    SymbolTable parent;
    // Name of the function that holds this scope
    String function;
    // The list that will hold all the identifiers in this scope
    ArrayList<Object> symbolList = new ArrayList<Object>();

    private int level = 0;


    public SymbolTable()
    {
        this.parent = null;
    }

    public SymbolTable(int level)
    {
        this.parent = null;
        this.level = level;
    }

    public SymbolTable NewScope(String functionName)
    {
        // Create a new scope and add the functioname
        // as the label of the scope
        //TODO Anything else..?

        SymbolTable newScope = new SymbolTable();
        newScope.function = functionName;
        newScope.parent = this;
        return newScope;
    }

    public void Insert(String functionName, TypeInfo typeInformation)
    {
        if(typeInformation != null)
            System.out.printf("Inserted %6s with type %10s in current scope.\n", functionName, typeInformation.type.name());
        else
            System.out.printf("Inserted %10s in current scope.\n", functionName);
        symbolList.add(functionName);
    }
}