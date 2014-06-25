package SymbolTable;

import Typing.ConstantTypeInfo;
import Typing.Types;

/**
 * Created by christophe on 25.06.14.
 */
public class CharSymTabInfo extends SymTabInfo {
    private char value;

    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public CharSymTabInfo(char value) {
        super(new ConstantTypeInfo(Types.CHAR));
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

        if (object != null && object instanceof CharSymTabInfo)
        {
            sameSame = this.value ==  ((CharSymTabInfo) object).value;
        }

        return sameSame;
    }
    @Override
    public String IdentifiertoString() {
        return String.valueOf(this.value);
    }

    public String toString()
    {
        return this.value + "";
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }
}
