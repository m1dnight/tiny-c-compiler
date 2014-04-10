package SymbolTable;

import Typing.TypeInfo;

import java.util.ArrayList;

public class SymbolTable {

    public SymTabInfo function;   // Name of the function that holds this scope
    public SymbolTable parent;// The parent of this scope so we can create frames.

    private ArrayList<VariableSymTabInfo> symbolList = new ArrayList<VariableSymTabInfo>(); // The list that will hold all the identifiers in this scope
    private int level = 0; // Value indicating how deep we are in the scope


    public SymbolTable() {
        this.parent = null;
    }

    public SymbolTable(int level) {
        this.parent = null;
        this.level = level;
    }

    public SymbolTable NewScope() {
        // Create a new scope frame
        SymbolTable newScope = new SymbolTable(this.level + 1);
        newScope.parent = this;
        // Instantiate the function of the new
        // scope to the older one. Just in case
        // this new scope is not created by a function
        // definition but perhaps by a forloop.
        newScope.function = this.function;
        return newScope;
    }

    /**
     * This function will create a new SymTabInfo and add this
     * to the list of the current scope.
     * This function also creates the SymTabInfo object for each
     * identifier. We need this e.g. for labeling a scope.
     *
     * @param name            : name of the function or variable
     * @param typeInformation : Object holding the type info
     */
    public SymTabInfo Insert(String name, TypeInfo typeInformation) {
        VariableSymTabInfo si = new VariableSymTabInfo(typeInformation, name);
        symbolList.add(si);

        /*
         * Debug printing and stuff
         */
        if (typeInformation != null)
            System.out.printf("Inserted \"%5s\" with type %10s in current scope.\n", name, typeInformation/**/.type.name());
        else
            System.out.printf("Inserted \"%5s\" in current scope.\n", name);

        return si;
    }

    public SymTabInfo Insert(VariableSymTabInfo sti) {
        symbolList.add(sti);
        return sti;
    }

    public SymTabInfo Lookup(String name) {
        // Check to see if we can find in this current scope
        for (VariableSymTabInfo si : symbolList) {
            //We need to check if the entry is a VariableSymTabInfo!
            //TODO Is this needed?
            if (si.name.equals(name))
                return si;
        }
        // If we have a parent, look it up there
        if (this.parent != null)
            return parent.Lookup(name);
        // It can't be found..
        return null;
    }
}