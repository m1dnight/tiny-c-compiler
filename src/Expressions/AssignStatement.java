package Expressions;

import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import SymbolTable.ArrayIndexSymTabInfo;
import SymbolTable.SymTabInfo;
import SymbolTable.VariableSymTabInfo;

import java.util.ArrayList;

/**
 * Created by christophe on 06.06.14.
 */
public class AssignStatement extends Statement {
    private Expression value;
    private SymTabInfo target;

    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    // A = B --> identifier = operand
    public AssignStatement(SymTabInfo target, Expression value) {
        this.target = target;
        this.value = value;
    }
    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    public ArrayList<ThreeAddressCode> toThreeAddressCode()
    {
        /**
         * Array assignment
         * arr[x] = 1
         */
        ArrayList<ThreeAddressCode> output = new ArrayList<ThreeAddressCode>();
        if(this.target instanceof ArrayIndexSymTabInfo)
        {
            this.target = ((ArrayIndexSymTabInfo) this.target);
            if (value != null) // It could be a single integer!
                output.addAll(value.ToThreeAddressCode());

            ThreeAddressCode tac = new ThreeAddressCode(OpCodes.AAS,
                                                        ((ArrayIndexSymTabInfo) this.target).getIndex().getIdentifier(),
                                                        this.value.getIdentifier(),
                                                        this.target);
            output.addAll(((ArrayIndexSymTabInfo) this.target).getIndex().ToThreeAddressCode());
            output.add(tac);
            return output;
        }
        if(this.target instanceof VariableSymTabInfo) {
            if (value != null) // It could be a single integer!
                output.addAll(value.ToThreeAddressCode());
            ThreeAddressCode tac = new ThreeAddressCode(OpCodes.A0, this.value.getIdentifier(), null, this.target);
            output.add(tac);
            return output;
        }
        throw new Error("Unknown target type in AssignmentStatement");
    }

    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/
    public Expression getValue() {
        return value;
    }

    public void setValue(Expression valueExpressions) {
        this.value = valueExpressions;
    }

    public SymTabInfo getTarget() {
        return target;
    }

    public void setTarget(SymTabInfo target) {
        this.target = target;
    }
}
