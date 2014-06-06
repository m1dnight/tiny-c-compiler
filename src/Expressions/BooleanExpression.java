package Expressions;

import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import SymbolTable.SymTabInfo;
import Typing.Types;

import java.util.ArrayList;

/**
 * Created by christophe on 06.06.14.
 */
public class BooleanExpression extends ArithmeticExpession {
    protected SymTabInfo operand1;
    protected SymTabInfo operand2;
    private   SymTabInfo trueLabel;
    private   SymTabInfo falseLabel;
    private   Statement  trueCode;
    private   Statement  falseCode;


    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public BooleanExpression(Types expressionType, OpCodes operation, SymTabInfo operand1, SymTabInfo operand2, SymTabInfo result) {
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

    public ArrayList<ThreeAddressCode> ToCondition()
    {
        ThreeAddressCode iff  = new ThreeAddressCode(OpCodes.IF, this.operand1, this.operand2, this.trueLabel);
        ThreeAddressCode iff2 = new ThreeAddressCode(OpCodes.GOTO, this.falseLabel);
        ArrayList<ThreeAddressCode> rv = new ArrayList<ThreeAddressCode>(2);
        rv.add(iff);
        rv.add(iff2);
        return rv;
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

    public SymTabInfo getTrueLabel() {
        return trueLabel;
    }

    public void setTrueLabel(SymTabInfo trueLabel) {
        this.trueLabel = trueLabel;
    }

    public SymTabInfo getFalseLabel() {
        return falseLabel;
    }

    public void setFalseLabel(SymTabInfo falseLabel) {
        this.falseLabel = falseLabel;
    }

    public Statement getTrueCode() {
        return trueCode;
    }

    public void setTrueCode(Statement trueCode) {
        this.trueCode = trueCode;
    }

    public Statement getFalseCode() {
        return falseCode;
    }

    public void setFalseCode(Statement falseCode) {
        this.falseCode = falseCode;
    }
}
