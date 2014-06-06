package Expressions;

import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import SymbolTable.SymTabInfo;
import Typing.Types;

import java.util.ArrayList;

/**
 * Created by christophe on 06.06.14.
 */
public class ReturnStatement extends Statement {
    private Expression returnExpressions;
    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public ReturnStatement(Expression returnExpressions) {
        this.returnExpressions = returnExpressions;
    }

    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    public ArrayList<ThreeAddressCode> toThreeAddressCode()
    {
        ArrayList<ThreeAddressCode> rv = new ArrayList<ThreeAddressCode>();
        rv.add(new ThreeAddressCode(OpCodes.RETURN, returnExpressions.getIdentifier()));
        return rv;
    }
    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/
    public Expression getReturnExpressions() {
        return returnExpressions;
    }

    public void setReturnExpressions(Expression returnExpressions) {
        this.returnExpressions = returnExpressions;
    }
}
