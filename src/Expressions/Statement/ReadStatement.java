package Expressions.Statement;

import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import SymbolTable.SymTabInfo;
import Typing.Types;

import java.util.ArrayList;

/**
 * Created by christophe on 24.06.14.
 */
public class ReadStatement extends Statement{

    private final Types readType;
    private SymTabInfo result;

    public ReadStatement(SymTabInfo var) {
        this.readType = var.typeInfo.ActualType();
        this.result = var;

    }
    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/

    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    public ArrayList<ThreeAddressCode> toThreeAddressCode()
    {
        ArrayList<ThreeAddressCode> rv = new ArrayList<ThreeAddressCode>();

        if(this.readType == Types.INTEGER)
        {
            rv.add(new ThreeAddressCode(OpCodes.READINT, this.result));
        }
        else
        {
            throw new Error("Printing non-integer value!");
        }

        return rv;
    }
    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/

}
