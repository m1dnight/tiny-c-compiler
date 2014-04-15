package SymbolTable;

import Typing.TypeInfo;

public class StringSymTabInfo extends SymTabInfo {
    public String name;

    public StringSymTabInfo(TypeInfo typeInfo, String value) {
        super(typeInfo);
        this.name = value;
    }
    @Override
    public String IdentifiertoString() {
        return name;
    }
}