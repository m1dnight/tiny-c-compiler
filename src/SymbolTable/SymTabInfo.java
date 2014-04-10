package SymbolTable;


import Typing.TypeInfo;

public abstract class SymTabInfo {
    public TypeInfo typeInfo;

    public SymTabInfo(TypeInfo typeInfo) {
        this.typeInfo = typeInfo;
    }

    public abstract String IdentifiertoString();
}
