package SymbolTable;

import Expressions.Expressions.Expression;
import Typing.TypeInfo;

/**
 * Created by christophe on 20.06.14.
 */
public class ArrayIndexSymTabInfo extends VariableSymTabInfo {

    private final Expression index;
    private final ArraySymTabInfo array;

    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/

    public ArrayIndexSymTabInfo(TypeInfo typeInfo, String name, Expression position, ArraySymTabInfo array) {
        super(typeInfo, name);
        this.index = position;
        this.array = array;
    }
    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    @Override
    public String IdentifiertoString() {
        return this.getName();
    }

    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/
    public Expression getIndex() {
        return index;
    }

    public ArraySymTabInfo getArray() {
        return array;
    }
}
