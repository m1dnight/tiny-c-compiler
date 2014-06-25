package Expressions.Expressions;

import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import SymbolTable.SymTabInfo;
import Typing.Types;

import java.util.ArrayList;

/**
 * Created by christophe on 05.06.14.
 */
public class BinaryArithmeticExpression extends ArithmeticExpession {
    private Expression operand1;
    private Expression operand2;

    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public BinaryArithmeticExpression(Types expressionType, OpCodes operation, Expression operand1, Expression operand2, SymTabInfo result) {
        super(result, expressionType, operation);
        this.operand1       = operand1;
        this.operand2       = operand2;
    }
    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    @Override
    public ArrayList<ThreeAddressCode> ToThreeAddressCode() {
        ArrayList<ThreeAddressCode> output = new ArrayList<ThreeAddressCode>();
        output.addAll(this.operand1.ToThreeAddressCode());
        output.addAll(this.operand2.ToThreeAddressCode());
        output.add( new ThreeAddressCode(this.operation, this.operand1.getIdentifier(), this.operand2.getIdentifier(), this.identifier));
        return output;
    }
    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/

    public void setOperand1(Expression operand1) {
        this.operand1 = operand1;
    }

    public void setOperand2(Expression operand2) {
        this.operand2 = operand2;
    }

    public Expression getOperand1() {
        return operand1;
    }

    public Expression getOperand2() {
        return operand2;
    }
}
