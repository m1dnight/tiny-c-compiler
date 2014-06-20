package Expressions;

import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import SymbolTable.IntegerSymTabInfo;
import SymbolTable.SymTabInfo;
import Typing.Types;

import java.util.ArrayList;

/**
 * Created by christophe on 06.06.14.
 */
public class BooleanExpression extends ArithmeticExpession {
    protected Expression    operand1;
    protected Expression    operand2;
    private   SymTabInfo    trueLabel;
    private   SymTabInfo    falseLabel;
    private   SymTabInfo    endLabel;
    private   StatementList trueCode;
    private   StatementList falseCode;


    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public BooleanExpression(Types expressionType, OpCodes operation, Expression operand1, Expression operand2, SymTabInfo result) {
        super(result, expressionType, operation);
        this.operand1       = operand1;
        this.operand2       = operand2;
    }

    @Override
    public ArrayList<ThreeAddressCode> ToThreeAddressCode() {
        // Return value
        ArrayList<ThreeAddressCode> rv = new ArrayList<ThreeAddressCode>(2);
        boolean swap = false;
        OpCodes emittedOperation = this.operation;
        if(this.operation == OpCodes.A2NEQ)
        {
            emittedOperation = OpCodes.A2EQ;
            swap = true;
        }
        if(this.falseCode == null)
        {
            ThreeAddressCode falseLabelTAC = new ThreeAddressCode(OpCodes.LABEL, this.falseLabel);
            ThreeAddressCode trueLabelTAC  = new ThreeAddressCode(OpCodes.LABEL, trueLabel);



            ThreeAddressCode iff  = new ThreeAddressCode(swap ? emittedOperation : this.operation, this.operand1.getIdentifier(), this.operand2.getIdentifier(), this.trueLabel);
            ThreeAddressCode iff2 = new ThreeAddressCode(OpCodes.GOTO, this.falseLabel);
            rv.addAll(operand1.ToThreeAddressCode());
            rv.addAll(operand2.ToThreeAddressCode());
            rv.add(iff);
            rv.add(iff2);
            rv.add(swap? falseLabelTAC : trueLabelTAC);
            //rv.add(trueLabelTAC);
            rv.add(new ThreeAddressCode(OpCodes.A0, new IntegerSymTabInfo(1), null, this.identifier));
            rv.add(new ThreeAddressCode(OpCodes.GOTO, this.endLabel));
            //rv.add(falseLabelTAC);
            rv.add(swap? trueLabelTAC : falseLabelTAC);
            rv.add(new ThreeAddressCode(OpCodes.A0, new IntegerSymTabInfo(0), null, this.identifier));
            rv.add(new ThreeAddressCode(OpCodes.LABEL, this.endLabel));
        }
        else
        {
            ThreeAddressCode iff  = new ThreeAddressCode(swap ? emittedOperation : this.operation, this.operand1.getIdentifier(), this.operand2.getIdentifier(), this.trueLabel);
            ThreeAddressCode iff2 = new ThreeAddressCode(OpCodes.GOTO, this.falseLabel);

            rv.add(iff);
            rv.add(iff2);
            rv.add(new ThreeAddressCode(OpCodes.LABEL, this.trueLabel));
            rv.addAll(trueCode.toThreeAddressCode());
            rv.add(new ThreeAddressCode(OpCodes.GOTO, this.endLabel));
            rv.add(new ThreeAddressCode(OpCodes.LABEL, this.falseLabel));
            rv.addAll(falseCode.toThreeAddressCode());
            rv.add(new ThreeAddressCode(OpCodes.LABEL, this.endLabel));
        }

        return rv;
    }

    public ArrayList<ThreeAddressCode> ToCondition()
    {
        //TODO Can we remove this?
        // Change to the if operation so the ThreeAddressCode knows what to generate.
        if(this.operation == OpCodes.A2EQ)
            this.operation = OpCodes.A2EQIF;
        if(this.operation == OpCodes.A2NEQ)
            this.operation = OpCodes.A2NEQIF;
        if(this.operation == OpCodes.A2GT)
            this.operation = OpCodes.A2GTIF;
        if(this.operation == OpCodes.A2LT)
            this.operation = OpCodes.A2LTIF;
        // Return value
        ArrayList<ThreeAddressCode> rv = new ArrayList<ThreeAddressCode>(2);
        if(this.falseCode == null)
        {
            ThreeAddressCode iff  = new ThreeAddressCode(this.operation, this.operand1.getIdentifier(), this.operand2.getIdentifier(), this.trueLabel);
            ThreeAddressCode iff2 = new ThreeAddressCode(OpCodes.GOTO, this.falseLabel);
            rv.addAll(operand1.ToThreeAddressCode());
            rv.addAll(operand2.ToThreeAddressCode());
            rv.add(iff);
            rv.add(iff2);
            rv.add(new ThreeAddressCode(OpCodes.LABEL, trueLabel));
            rv.addAll(trueCode.toThreeAddressCode());
            rv.add(new ThreeAddressCode(OpCodes.LABEL, falseLabel));
        }
        else
        {
            ThreeAddressCode iff  = new ThreeAddressCode(this.operation, this.operand1.getIdentifier(), this.operand2.getIdentifier(), this.trueLabel);
            ThreeAddressCode iff2 = new ThreeAddressCode(OpCodes.GOTO, this.falseLabel);
            rv.addAll(this.operand1.ToThreeAddressCode());
            rv.addAll(this.operand2.ToThreeAddressCode());
            rv.add(iff);
            rv.add(iff2);
            rv.add(new ThreeAddressCode(OpCodes.LABEL, this.trueLabel));
            rv.addAll(trueCode.toThreeAddressCode());
            rv.add(new ThreeAddressCode(OpCodes.GOTO, this.endLabel));
            rv.add(new ThreeAddressCode(OpCodes.LABEL, this.falseLabel));
            rv.addAll(falseCode.toThreeAddressCode());
            rv.add(new ThreeAddressCode(OpCodes.LABEL, this.endLabel));
        }

        return rv;
    }
    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/
    public Expression getOperand1() {
        return operand1;
    }

    public void setOperand1(Expression operand1) {
        this.operand1 = operand1;
    }

    public Expression getOperand2() {
        return operand2;
    }

    public void setOperand2(Expression operand2) {
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

    public StatementList getTrueCode() {
        return trueCode;
    }

    public void setTrueCode(StatementList trueCode) {
        this.trueCode = trueCode;
    }
    public void setTrueCode(Statement trueCode) {
        this.trueCode = new StatementList(trueCode);
    }

    public StatementList getFalseCode() {
        return falseCode;
    }

    public void setFalseCode(StatementList falseCode) {
        this.falseCode = falseCode;
    }

    public void setFalseCode(Statement falseCode) {
        this.trueCode = new StatementList(falseCode);
    }

    public SymTabInfo getEndLabel() {
        return endLabel;
    }

    public void setEndLabel(SymTabInfo endLabel) {
        this.endLabel = endLabel;
    }
}
