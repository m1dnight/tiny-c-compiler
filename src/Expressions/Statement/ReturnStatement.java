package Expressions.Statement;

import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import Expressions.Expressions.Expression;
import Typing.Types;

import java.util.ArrayList;

/**
 * Created by christophe on 06.06.14.
 */
public class ReturnStatement extends Statement {
    private Expression returnExpressions;
    private Types returnType;
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
        rv.addAll(returnExpressions.ToThreeAddressCode());

        // If we have to coerce to a char, we have to add the TAC
        if(this.returnExpressions.getExpressionType() != returnType && returnType == Types.CHAR)
            rv.add(new ThreeAddressCode(OpCodes.RETURNTOCHAR, returnExpressions.getIdentifier()));
        else
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

    public Types getReturnType() {
        return returnType;
    }

    public void setReturnType(Types returnType) {
        this.returnType = returnType;
    }
}
