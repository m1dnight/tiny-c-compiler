package Main;

import java_cup.symbol;

import java.util.*; // To use ArrayList

public class SymbolTable {

    public SymTabInfo function;   // Name of the function that holds this scope
    public SymbolTable parent;// The parent of this scope so we can create frames.

    private ArrayList<SymTabInfo> symbolList = new ArrayList<SymTabInfo>(); // The list that will hold all the identifiers in this scope
    private int level = 0; // Value indicating how deep we are in the scope


    public SymbolTable()
    {
        this.parent = null;
    }

    public SymbolTable(int level)
    {
        this.parent = null;
        this.level = level;
    }

    public SymbolTable NewScope()
    {
        // Create a new scope frame
        SymbolTable newScope = new SymbolTable(this.level + 1);
        newScope.parent = this;
        // Instantiate the function of the new
        // scope to the older one. Just in case
        // this new scope is not created by a function
        // definition.
        newScope.function = this.function;
        return newScope;
    }

    /**
     * This function will create a new SymTabInfo and add this
     * to the list of the current scope.
     * This function also creates the SymTabInfo object for each
     * identifier. We need this e.g. for labeling a scope.
     * @param name : name of the function or variable
     * @param typeInformation : Object holding the type info
     */
    public SymTabInfo Insert(String name, TypeInfo typeInformation)
    {
        SymTabInfo si = new SymTabInfo(name, typeInformation);
        symbolList.add(si);

        /*
         * Debug printing and stuff
         */
        if(typeInformation != null)
            System.out.printf("Inserted \"%5s\" with type %10s in current scope.\n", name, typeInformation.type.name());
        else
            System.out.printf("Inserted \"%5s\" in current scope.\n", name);

        return si;
    }

    public SymTabInfo Insert(SymTabInfo sti)
    {
        symbolList.add(sti);

        /*
         * Debug printing and stuff
         */
        if(sti.typeInfo != null)
            System.out.printf("Inserted %5s with type %10s in current scope.\n", sti.name, sti.typeInfo.type.name());
        else
            System.out.printf("Inserted %5s in current scope.\n", sti.name);

        return sti;
    }

    public void Print()
    {
        // Print scope information
        System.out.println("-------------------------------");
        if(this.function != null)
            System.out.printf("Defining function: %8s%n", this.function.name);
        else
            System.out.printf("Defining function: %8s%n", "root scope");
        System.out.println("-------------------------------");

        // Map print over the symbol list
        for(SymTabInfo si : symbolList)
        {
            si.Print();
        }

        if(this.parent != null)
        {
            System.out.println("**** Parent");
            this.parent.Print();
        }


    }
}