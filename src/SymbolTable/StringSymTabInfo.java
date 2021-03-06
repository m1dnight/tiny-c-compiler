package SymbolTable;

import Typing.TypeInfo;

public class StringSymTabInfo extends SymTabInfo {
    public String name;

    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public StringSymTabInfo(TypeInfo typeInfo, String value) {
        super(typeInfo);
        this.name = value;
    }
    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    @Override
    public String IdentifiertoString() {
        return name;
    }

    public boolean equals(Object object)
    {
        boolean sameSame = false;

        if (object != null && object instanceof StringSymTabInfo)
        {
            StringSymTabInfo obj = (StringSymTabInfo) object;
            sameSame = this.getTypeInfo().equals(obj.getTypeInfo());
        }
        return sameSame;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
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