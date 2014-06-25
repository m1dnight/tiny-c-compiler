package Expressions.Expressions;

import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import SymbolTable.ArraySymTabInfo;
import SymbolTable.SymTabInfo;
import Typing.Types;

import java.util.ArrayList;

/**
 * Created by christophe on 20.06.14.
 */
public class ArrayAccessExpression extends Expression {
    private final Expression index;
    private final ArraySymTabInfo array;

    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public ArrayAccessExpression(SymTabInfo identifier, Types type, ArraySymTabInfo array, Expression idx) {
        super(identifier, type);
        this.index = idx;
        this.array = array;
    }
    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    public ArrayList<ThreeAddressCode> ToThreeAddressCode()
    {
        ArrayList<ThreeAddressCode> output = new ArrayList<ThreeAddressCode>();
        output.addAll(this.index.ToThreeAddressCode());
        output.add(new ThreeAddressCode(OpCodes.AAC, this.array, this.index.getIdentifier(), this.identifier));

        return output;
    }

    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/
}
