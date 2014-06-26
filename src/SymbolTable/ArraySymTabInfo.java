package SymbolTable;

import Expressions.Expressions.Expression;
import Typing.ConstantTypeInfo;
import Typing.TypeInfo;
import Typing.Types;

/**
 * Created by christophe on 20.06.14.
 */
public class ArraySymTabInfo extends VariableSymTabInfo {

    private final Expression size;
    private       int        height;
    private       int        width;

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

    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/
    public Expression getSize() {
        return size;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public IntegerSymTabInfo getHeigthExpression()
    {
        ConstantTypeInfo ti = new ConstantTypeInfo(Types.INTEGER);
        IntegerSymTabInfo csti = new IntegerSymTabInfo(this.getHeight());
        return csti;
    }
}
