package Expressions;

import CodeGeneration.ThreeAddressCode;
import SymbolTable.SymTabInfo;
import Typing.Types;

public class Expression {
    protected SymTabInfo identifier;
    protected Types      expressionType;

    public ThreeAddressCode ToThreeAddressCode() {
        throw new Error("ToThreeAddressCode() should be overridden by subclasses!");
    }
    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public Expression(SymTabInfo identifier, Types type) {
        this.identifier     = identifier;
        this.expressionType = type;
    }
    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/
    public SymTabInfo getIdentifier() {
        return identifier;

    }

    public void setIdentifier(SymTabInfo identifier) {
        this.identifier = identifier;
    }

    public Types getExpressionType() {
        return expressionType;
    }

    public void setExpressionType(Types expressionType) {
        this.expressionType = expressionType;
    }
}
