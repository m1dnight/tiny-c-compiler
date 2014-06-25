package Expressions.Expressions;

import CodeGeneration.OpCodes;
import SymbolTable.SymTabInfo;
import Typing.Types;

public class ArithmeticExpession extends Expression {
    protected OpCodes operation;

    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public ArithmeticExpession(SymTabInfo identifier, Types expressionType, OpCodes operation) {
        super(identifier, expressionType);
        this.operation = operation;
    }
    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/
    public OpCodes getOperation() {
        return operation;
    }

    public void setOperation(OpCodes operation) {
        this.operation = operation;
    }
}
