package SymbolTable;

import Expressions.Expressions.Expression;
import Typing.TypeInfo;

/**
 * Created by christophe on 20.06.14.
 */
public class ArrayIndexSymTabInfo extends VariableSymTabInfo {

    private final Expression         widthIndex;
    private final Expression         heigthIndex;
    private final ArraySymTabInfo    array;
    private final VariableSymTabInfo t1;
    private final VariableSymTabInfo idx;

    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/

    public ArrayIndexSymTabInfo(TypeInfo typeInfo, String name, Expression position, ArraySymTabInfo array) {
        super(typeInfo, name);
        this.widthIndex = position;
        this.array = array;
        this.heigthIndex = null;
        this.t1 = null;
        this.idx = null;
    }

    public ArrayIndexSymTabInfo(TypeInfo typeInfo, String name, Expression wi, Expression hi, ArraySymTabInfo array, VariableSymTabInfo t1, VariableSymTabInfo idx) {
        super(typeInfo, name);
        this.widthIndex = wi;
        this.heigthIndex = hi;
        this.array = array;
        this.t1 = t1;
        this.idx = idx;
    }

    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    @Override
    public String IdentifiertoString() {
        return this.getName();
    }

    public boolean IsTwoDim()
    {
        return this.heigthIndex != null;
    }
    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/
    public Expression getWidthIndex() {
        return widthIndex;
    }

    public ArraySymTabInfo getArray() {
        return array;
    }

    public Expression getHeigthIndex() {
        return heigthIndex;
    }

    public VariableSymTabInfo getT1() {
        return t1;
    }

    public VariableSymTabInfo getIdx() {
        return idx;
    }
}
