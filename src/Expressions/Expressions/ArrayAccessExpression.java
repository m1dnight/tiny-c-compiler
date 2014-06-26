package Expressions.Expressions;

import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import SymbolTable.ArraySymTabInfo;
import SymbolTable.SymTabInfo;
import SymbolTable.VariableSymTabInfo;
import Typing.Types;

import java.util.ArrayList;

/**
 * Created by christophe on 20.06.14.
 */
public class ArrayAccessExpression extends Expression {
    private final Expression      widthIdx;
    private final Expression      heightIdx;
    private final ArraySymTabInfo array;
    private final VariableSymTabInfo t1;
    private final VariableSymTabInfo idx;

    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public ArrayAccessExpression(SymTabInfo identifier, Types type, ArraySymTabInfo array, Expression idx) {
        super(identifier, type);
        this.widthIdx = idx;
        this.array = array;
        this.heightIdx = null;
        this.t1 = null;
        this.idx = null;
    }
    public ArrayAccessExpression(SymTabInfo identifier, Types type, ArraySymTabInfo array, Expression width, Expression height, VariableSymTabInfo t1, VariableSymTabInfo idx) {
        super(identifier, type);
        this.widthIdx = width;
        this.array = array;
        this.heightIdx = height;
        this.t1 = t1;
        this.idx = idx;
    }
    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    public boolean IsTwoDim()
    {
        return heightIdx != null;
    }
    public ArrayList<ThreeAddressCode> ToThreeAddressCode()
    {
        ArrayList<ThreeAddressCode> output = new ArrayList<ThreeAddressCode>();

        if(!IsTwoDim()) {
            output.addAll(this.widthIdx.ToThreeAddressCode());
            output.add(new ThreeAddressCode(OpCodes.AAC, this.array, this.widthIdx.getIdentifier(), this.identifier));
        }
        else
        {
            output.addAll(this.heightIdx.ToThreeAddressCode());
            output.addAll(this.widthIdx.ToThreeAddressCode());
            // Print out TAC to calculate the address


            output.add(new ThreeAddressCode(OpCodes.A2TIMES, this.widthIdx.getIdentifier(), this.array.getHeigthExpression(), t1));
            output.add(new ThreeAddressCode(OpCodes.A2PLUS, t1, this.heightIdx.getIdentifier(), idx));
            output.add(new ThreeAddressCode(OpCodes.AAC, this.array, idx, this.identifier));
        }

        return output;
    }

    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/
}
