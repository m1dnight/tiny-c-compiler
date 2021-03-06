package SymbolTable;

import Typing.FunctionTypeInfo;
import Utils.StringUtils;

import java.util.ArrayList;

public class SymbolTable {

    public SymTabInfo function;   // Name of the function that holds this scope
    public SymbolTable parent;// The parent of this scope so we can create frames.
    public ArrayList<SymbolTable> children;

    private ArrayList<VariableSymTabInfo> symbolList = new ArrayList<VariableSymTabInfo>(); // The list that will hold all the identifiers in this scope
    private int level = 0; // Value indicating how deep we are in the scope

    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public SymbolTable() {
        this.parent = null;
    }

    public SymbolTable(int level) {
        this.parent = null;
        this.level = level;
    }
    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Level: " + this.level + StringUtils.repeat("*", 32) + "\n");
        for(VariableSymTabInfo var : this.symbolList)
        {

            sb.append(StringUtils.repeat("-", this.level) + " ");
            sb.append(var.toString() + "\n");
        }
        if(this.children != null)
        for(SymbolTable st : children)
        {
            sb.append(st.toString());
        }
        return sb.toString();
    }

    public SymbolTable NewScope() {
        // Create a new scope frame
        SymbolTable newScope = new SymbolTable(this.level + 1);
        newScope.parent = this;
        if(this.children == null) this.children = new ArrayList<SymbolTable>();
        this.children.add(newScope);
        // Instantiate the function of the new
        // scope to the older one. Just in case
        // this new scope is not created by a function
        // definition but perhaps by a forloop.
        newScope.function = this.function;
        return newScope;
    }

    public SymTabInfo Insert(VariableSymTabInfo sti) {
        symbolList.add(sti);
        return sti;
    }
    /******************************************************************************************************************/
    /************************************ LOOKUP VALUES ***************************************************************/
    /******************************************************************************************************************/
    public VariableSymTabInfo Lookup(String name) {
        // Check to see if we can find in this current scope
        for (VariableSymTabInfo si : symbolList) {
            //We need to check if the entry is a VariableSymTabInfo!
            //TODO Is this needed?
            if (si.getName().equals(name))
                return si;
        }
        // If we have a parent, look it up there
        if (this.parent != null)
            return parent.Lookup(name);
        return null;
    }
    public FunctionSymTabInfo LookupFunction(String name)
    {
        VariableSymTabInfo func = Lookup(name);
        boolean f = func instanceof VariableSymTabInfo;
        if(func == null || !(func instanceof FunctionSymTabInfo))
            return null;
        return (FunctionSymTabInfo) func;
    }
    public VariableSymTabInfo LookupVariable(String name)
    {
        VariableSymTabInfo var = Lookup(name);
        if(var == null || var.getTypeInfo() instanceof FunctionTypeInfo)
            return null;
        return var;
    }
    public ArraySymTabInfo LookupArray(String name)
    {
        VariableSymTabInfo array = Lookup(name);
        if(array == null || !(array instanceof ArraySymTabInfo))
            return null;
        return (ArraySymTabInfo) array;
    }


    public boolean ContainsName(String n) {
        if(Lookup(n) != null)
            return true;
        return false;
    }
}