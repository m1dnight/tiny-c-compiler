package Expressions;

import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import SymbolTable.SymTabInfo;
import Typing.Types;

/**
 * Created by christophe on 05.06.14.
 */
public class BinaryArithmeticExpression extends ArithmeticExpession {
    protected SymTabInfo operand1;
    protected SymTabInfo operand2;

    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public BinaryArithmeticExpression(Types expressionType, OpCodes operation, SymTabInfo operand1, SymTabInfo operand2, SymTabInfo result) {
        super(result, expressionType, operation);
        this.operand1       = operand1;
        this.operand2       = operand2;
    }
    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    @Override
    public ThreeAddressCode ToThreeAddressCode() {
        return new ThreeAddressCode(this.operation, this.operand1, this.operand2, this.identifier);
    }
    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/
    public SymTabInfo getOperand1() {
        return operand1;
    }

    public void setOperand1(SymTabInfo operand1) {
        this.operand1 = operand1;
    }

    public SymTabInfo getOperand2() {
        return operand2;
    }

    public void setOperand2(SymTabInfo operand2) {
        this.operand2 = operand2;
    }
}
