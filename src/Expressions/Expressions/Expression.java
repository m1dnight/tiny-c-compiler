package Expressions.Expressions;

import CodeGeneration.ThreeAddressCode;
import SymbolTable.SymTabInfo;
import Typing.Types;

import java.util.ArrayList;

public class Expression {
    private   ArrayList<Expression> codeList;
    protected SymTabInfo            identifier;
    protected Types                 expressionType;
    private   ArrayList<Expression> cedeList;

    public ArrayList<ThreeAddressCode> ToThreeAddressCode() {
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

    public ArrayList<Expression> getCodeList() {
        return codeList;
    }

    public void setCodeList(ArrayList<Expression> codeList) {
        this.codeList = codeList;
    }

}
