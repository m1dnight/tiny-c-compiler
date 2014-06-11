package SymbolTable;

import Typing.TypeInfo;

public class VariableSymTabInfo extends SymTabInfo {
    private String name;


    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public VariableSymTabInfo(TypeInfo typeInfo, String name) {
        super(typeInfo);
        this.name = name;

    }
    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    @Override
    public String IdentifiertoString() {
        return name;
    }

    public String toString()
    {
        //return String.format("Name: %10s Live: %5s", name, this.live);
        return String.format(name);
    }

    @Override
    public boolean equals(Object object)
    {
        boolean sameSame = false;
        if(this == object) return true;

        if (object != null && object instanceof VariableSymTabInfo)
        {
            VariableSymTabInfo obj = (VariableSymTabInfo) object;
            sameSame = this.name ==  obj.name &&
                       this.typeInfo.equals(obj.typeInfo);
        }

        return sameSame;
    }

    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
