package Typing;

import SymbolTable.SymTabInfo;

import java.util.ArrayList;
import java.util.LinkedList;

public class FunctionTypeInfo extends TypeInfo {

    public LinkedList<SymTabInfo> parameters;
    public TypeInfo               returnType;

    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    /**
     * Public construct that takes a returntype.
     * These are defined in the enum 'Types'.
     * The paramList contains a list of SymTabInfo's.
     * These are needed so we know the name and type of each parameter.
     */
    public FunctionTypeInfo(TypeInfo returnType, LinkedList<SymTabInfo> paramlist) {
        super(Types.FUNCTION);
        this.returnType = returnType;
        this.parameters = paramlist;
    }
    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    public boolean equals(Object object)
    {
        boolean sameSame = false;

        if (object != null && object instanceof FunctionTypeInfo)
        {
            FunctionTypeInfo obj = (FunctionTypeInfo) object;

            sameSame = this.returnType == obj.returnType;

            sameSame = sameSame && this.type == obj.type;

            if(this.parameters.size() == obj.parameters.size())
            {
                for(int p = 0; p < this.parameters.size(); p++)
                {
                    SymTabInfo p1 = this.parameters.get(p);
                    SymTabInfo p2 = obj.parameters.get(p);
                    if(!p1.equals(p2))
                        return false;
                }
            }
            else
            {
                return false;
            }
        }
        return sameSame;
    }
    public Types ActualType()
    {
        return returnType.ActualType();
    }

    public int NumberOfParams()
    {
        if(this.parameters == null || this.parameters.size() < 1) return 0;
        return parameters.size();
    }

    public ArrayList<SymTabInfo> getParameters()
    {
        ArrayList<SymTabInfo> parameters = new ArrayList<SymTabInfo>();
        for(SymTabInfo p : this.parameters)
            parameters.add(p);
        return parameters;
    }
}