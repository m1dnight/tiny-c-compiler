package Expressions.Expressions;

import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import SymbolTable.SymTabInfo;
import Typing.Types;

import java.util.ArrayList;

/**
 * Created by christophe on 05.06.14.
 */
public class UnaryArithmeticExpression extends ArithmeticExpession {
    private Expression operand;

    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public UnaryArithmeticExpression(Types expressionType, OpCodes operation, Expression operand, SymTabInfo result) {
        super(result, expressionType, operation);
        this.operand        = operand;
    }
    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    @Override
    public ArrayList<ThreeAddressCode> ToThreeAddressCode() {
        //TODO Create subclass for TAC
        ArrayList<ThreeAddressCode> output = new ArrayList<ThreeAddressCode>();
        output.addAll(this.operand.ToThreeAddressCode());
        output.add(new ThreeAddressCode(super.getOperation(), this.operand.getIdentifier(), null,  this.identifier));
        return output;
    }

    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/

    public Expression getOperand() {
        return operand;
    }

    public void setOperand(Expression operand) {
        this.operand = operand;
    }
}
