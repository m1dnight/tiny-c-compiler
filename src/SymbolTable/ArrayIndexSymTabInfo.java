package SymbolTable;

import Expressions.Expression;
import Typing.TypeInfo;

/**
 * Created by christophe on 20.06.14.
 */
public class ArrayIndexSymTabInfo extends VariableSymTabInfo {

    private final Expression position;

    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/

    public ArrayIndexSymTabInfo(TypeInfo typeInfo, String name, Expression position) {
        super(typeInfo, name);
        this.position = position;
    }
    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    @Override
    public String IdentifiertoString() {
        return this.getName();
    }
}
