package Expressions.Statement;

import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import Expressions.Expressions.Expression;
import Typing.Types;

import java.util.ArrayList;

/**
 * Created by christophe on 24.06.14.
 */
public class PrintStatement extends Statement {
    private final boolean    newline;
    private       Expression printExpression;
    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public PrintStatement(Expression printExpression) {
        this.printExpression = printExpression;
        this.newline = false;
    }

    public PrintStatement(Expression e, boolean newline) {
        this.printExpression = e;
        this.newline = newline;
    }

    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    public ArrayList<ThreeAddressCode> toThreeAddressCode()
    {

        ArrayList<ThreeAddressCode> rv = new ArrayList<ThreeAddressCode>();

        if(printExpression.getExpressionType() == Types.CHAR)
        {
            OpCodes op = OpCodes.WRITECHAR;
            if(newline) op = OpCodes.WRITECHARLN;
            rv.addAll(printExpression.ToThreeAddressCode());
            rv.add(new ThreeAddressCode(op, printExpression.getIdentifier()));
        }
        else
        if(printExpression.getIdentifier().getTypeInfo().ActualType() == Types.INTEGER)
        {
            OpCodes op = OpCodes.WRITEINT;
            if(newline) op = OpCodes.WRITEINTLN;
            rv.addAll(printExpression.ToThreeAddressCode());
            rv.add(new ThreeAddressCode(op, printExpression.getIdentifier()));
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
    public Expression getPrintExpression() {
        return printExpression;
    }

    public void setPrintExpression(Expression printExpression) {
        this.printExpression = printExpression;
    }
}
