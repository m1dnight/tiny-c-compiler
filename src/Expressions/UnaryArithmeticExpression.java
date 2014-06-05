package Expressions;

import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import SymbolTable.SymTabInfo;
import Typing.Types;

/**
 * Created by christophe on 05.06.14.
 */
public class UnaryArithmeticExpression extends ArithmeticExpession {

    private SymTabInfo operand;
    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public UnaryArithmeticExpression(Types expressionType, OpCodes operation, SymTabInfo operand, SymTabInfo result) {
        super(result, expressionType, operation);
        this.operand        = operand;
    }
    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    @Override
    public ThreeAddressCode ToThreeAddressCode() {
        //TODO Create subclass for TAC
        return new ThreeAddressCode(super.getOperation(), this.operand, null,  this.identifier);
    }

    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/

    public SymTabInfo getOperand() {
        return operand;
    }

    public void setOperand(SymTabInfo operand) {
        this.operand = operand;
    }
}
