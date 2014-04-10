package SymbolTable;

import Typing.TypeInfo;

public class IntegerSymTabInfo extends SymTabInfo {
    public int value;

    public IntegerSymTabInfo(TypeInfo typeInfo, int value) {
        super(typeInfo);
        this.value = value;
    }

    @Override
    public String IdentifiertoString() {
        return Integer.toString(value);
    }
}
