package SymbolTable;

import Typing.ConstantTypeInfo;
import Typing.Types;

public class IntegerSymTabInfo extends SymTabInfo {
    public int value;

    public IntegerSymTabInfo(int value) {
        super(new ConstantTypeInfo(Types.INTEGER));
        this.value = value;
    }

    @Override
    public String IdentifiertoString() {
        return Integer.toString(value);
    }

    public String toString()
    {
        return this.value + "";
    }
}
