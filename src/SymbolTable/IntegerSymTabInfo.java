package SymbolTable;

import Typing.ConstantTypeInfo;
import Typing.Types;

public class IntegerSymTabInfo extends SymTabInfo {
    private int value;
    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public IntegerSymTabInfo(int value) {
        super(new ConstantTypeInfo(Types.INTEGER));
        this.value = value;
    }
    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/

    @Override
    public int hashCode() {
        return value;
    }
    @Override
    public boolean equals(Object object)
    {
        boolean sameSame = false;

        if (object != null && object instanceof IntegerSymTabInfo)
        {
            sameSame = this.value ==  ((IntegerSymTabInfo) object).value;
        }

        return sameSame;
    }
    @Override
    public String IdentifiertoString() {
        return Integer.toString(value);
    }

    public String toString()
    {
        return this.value + "";
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
