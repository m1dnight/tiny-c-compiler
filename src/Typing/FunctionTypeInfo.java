package Typing;

import Main.SymTabInfo;

import java.util.LinkedList;

public class FunctionTypeInfo extends TypeInfo {

    public LinkedList<SymTabInfo> parameters;

    public TypeInfo returnType;
    /**
     * Public construct that takes a returntype.
     * These are defined in the enum 'Types'.
     * The paramList contains a list of SymTabInfo's.
     * These are needed so we know the name and type of each parameter.
     * @param typename
     */
    public FunctionTypeInfo(TypeInfo returnType, LinkedList<SymTabInfo> paramlist) {
        super(Types.FUNCTION);
        this.returnType = returnType;
        this.parameters = paramlist;
    }
}