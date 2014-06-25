package Expressions.Statement;

import CodeGeneration.ThreeAddressCode;
import Expressions.Expressions.BooleanExpression;

import java.util.ArrayList;

/**
 * Created by christophe on 06.06.14.
 */
public class IfStatement extends Statement {
    private BooleanExpression bexp;

    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/

    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    public ArrayList<ThreeAddressCode> toThreeAddressCode()
    {
        ArrayList<ThreeAddressCode> output = new ArrayList<ThreeAddressCode>();
        output.addAll(this.bexp.ToCondition());
        return output;
    }
    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/
    public BooleanExpression getBexp() {
        return bexp;
    }

    public void setBexp(BooleanExpression bexp) {
        this.bexp = bexp;
    }
}
