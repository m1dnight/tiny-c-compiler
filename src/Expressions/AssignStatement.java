package Expressions;

import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import SymbolTable.SymTabInfo;

import java.util.ArrayList;

/**
 * Created by christophe on 06.06.14.
 */
public class AssignStatement extends Statement {
    private Expression valueExpressions;
    private SymTabInfo target;

    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    // A = B --> identifier = operand
    public AssignStatement(SymTabInfo target, Expression valueExpressions) {
        this.target = target;
        this.valueExpressions = valueExpressions;
    }
    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    public ArrayList<ThreeAddressCode> toThreeAddressCode()
    {
        ArrayList<ThreeAddressCode> output = new ArrayList<ThreeAddressCode>();
        if(valueExpressions != null) // It could be a single integer!
            output.add(valueExpressions.ToThreeAddressCode());
        ThreeAddressCode tac = new ThreeAddressCode(OpCodes.A0, this.valueExpressions.getIdentifier(), null, this.target);
        output.add(tac);
        return output;
    }

    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/
    public Expression getValue() {
        return valueExpressions;
    }

    public void setValue(Expression valueExpressions) {
        this.valueExpressions = valueExpressions;
    }

    public SymTabInfo getTarget() {
        return target;
    }

    public void setTarget(SymTabInfo target) {
        this.target = target;
    }
}

