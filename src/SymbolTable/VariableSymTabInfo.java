package SymbolTable;

import Typing.TypeInfo;

public class VariableSymTabInfo extends SymTabInfo {
    public String name;

    public VariableSymTabInfo(TypeInfo typeInfo, String name) {
        super(typeInfo);
        this.name = name;

    }

    @Override
    public String IdentifiertoString() {
        return name;
    }
}
