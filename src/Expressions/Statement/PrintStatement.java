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
    private Expression printExpression;
    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public PrintStatement(Expression printExpression) {
        this.printExpression = printExpression;
    }

    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    public ArrayList<ThreeAddressCode> toThreeAddressCode()
    {

        ArrayList<ThreeAddressCode> rv = new ArrayList<ThreeAddressCode>();

        if(printExpression.getExpressionType() == Types.CHAR)
        {
            rv.addAll(printExpression.ToThreeAddressCode());
            rv.add(new ThreeAddressCode(OpCodes.WRITECHAR, printExpression.getIdentifier()));
        }
        else
        if(printExpression.getIdentifier().getTypeInfo().ActualType() == Types.INTEGER)
        {
            rv.addAll(printExpression.ToThreeAddressCode());
            rv.add(new ThreeAddressCode(OpCodes.WRITEINT, printExpression.getIdentifier()));
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
