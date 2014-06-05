package SymbolTable;

import Typing.TypeInfo;

public class VariableSymTabInfo extends SymTabInfo {
    public String name; // Type of this variable.

    public VariableSymTabInfo(TypeInfo typeInfo, String name) {
        super(typeInfo);
        this.name = name;

    }

    @Override
    public String IdentifiertoString() {
        return name;
    }
}
