package Typing;

import SymbolTable.FunctionSymTabInfo;
import SymbolTable.VariableSymTabInfo;

/**
 * Created by christophe on 26.06.14.
 */
public class Typing {

    public static boolean EqualOrBroaderType(Types actual, Types expected) {
        if(actual == expected) return true;
        if(actual == Types.CHAR    && expected == Types.INTEGER) return true;
        if(actual == Types.INTEGER && expected == Types.CHAR) return true;
        return false;
    }

    private static boolean IsVariable(VariableSymTabInfo v)
    {
        return !(v instanceof FunctionSymTabInfo);
    }
}
