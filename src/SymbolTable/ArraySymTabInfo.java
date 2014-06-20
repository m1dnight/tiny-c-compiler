package SymbolTable;

import Expressions.Expression;
import Typing.TypeInfo;

/**
 * Created by christophe on 20.06.14.
 */
public class ArraySymTabInfo extends VariableSymTabInfo {

    private final Expression size;

    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    /**
     * @param typeInfo
     * @param name
     */
    public ArraySymTabInfo(TypeInfo typeInfo, String name, Expression size) {
        super(typeInfo, name);
        this.size = size;
    }
    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    @Override
    public String IdentifiertoString() {
        return this.getName();
    }
}
