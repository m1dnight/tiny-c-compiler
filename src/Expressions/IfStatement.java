package Expressions;

import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;

import javax.swing.plaf.nimbus.State;
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
