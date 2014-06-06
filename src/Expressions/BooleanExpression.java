package Expressions;

import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import SymbolTable.SymTabInfo;
import Typing.Types;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by christophe on 06.06.14.
 */
public class BooleanExpression extends ArithmeticExpession {
    protected Expression operand1;
    protected Expression operand2;
    private   SymTabInfo trueLabel;
    private   SymTabInfo falseLabel;
    private   SymTabInfo endLabel;
    private   Statement  trueCode;
    private   Statement  falseCode;


    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public BooleanExpression(Types expressionType, OpCodes operation, Expression operand1, Expression operand2, SymTabInfo result) {
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

    public ArrayList<ThreeAddressCode> ToCondition()
    {
        //TODO Can we remove this?
        // Change to the if operation so the ThreeAddressCode knows what to do.
        if(this.operation == OpCodes.A2EQ)
        {
            this.operation = OpCodes.A2EQIF;
        }
        if(this.operation == OpCodes.A2NEQ)
        {
            this.operation = OpCodes.A2NEQIF;
        }
        if(this.operation == OpCodes.A2GT)
        {
            this.operation = OpCodes.A2GTIF;
        }
        if(this.operation == OpCodes.A2LT)
        {
            this.operation = OpCodes.A2LTIF;
        }
        ThreeAddressCode iff  = new ThreeAddressCode(this.operation, this.operand1.getIdentifier(), this.operand2.getIdentifier(), this.trueLabel);
        ThreeAddressCode iff2 = new ThreeAddressCode(OpCodes.GOTO, this.falseLabel);

        ArrayList<ThreeAddressCode> rv = new ArrayList<ThreeAddressCode>(2);
        rv.add(iff);
        rv.add(iff2);
        rv.add(new ThreeAddressCode(OpCodes.LABEL, trueLabel));
        rv.addAll(trueCode.toThreeAddressCode());

        rv.add(new ThreeAddressCode(OpCodes.LABEL, falseLabel));
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

    public SymTabInfo getEndLabel() {
        return endLabel;
    }

    public void setEndLabel(SymTabInfo endLabel) {
        this.endLabel = endLabel;
    }
}
