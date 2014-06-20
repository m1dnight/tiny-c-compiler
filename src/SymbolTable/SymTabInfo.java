package SymbolTable;


import Typing.TypeInfo;

public abstract class SymTabInfo {
    public TypeInfo   typeInfo;
    protected boolean live     = true;

    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public SymTabInfo(TypeInfo typeInfo) {
        this.typeInfo = typeInfo;
    }

    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    public abstract String IdentifiertoString();

    public boolean equals(Object obj)
    {
        throw new Error("equals() should be implemented in subclasses!");
    }

    public int hashCode()
    {
        throw new Error("hashCode() should be implemented in subclasses!");
    }
    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/
    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }
}
