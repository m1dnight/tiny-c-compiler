package CodeGeneration;

import SymbolTable.StringSymTabInfo;

/**
 * Created by christophe on 05.06.14.
 */

public class BooleanExpression {
    private StringSymTabInfo trueLabel;
    private StringSymTabInfo falseLabel;
    public  CodeContainer    trueCode;
    public  CodeContainer    falseCode;

    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public BooleanExpression(CodeContainer trueCode, CodeContainer falseCode) {
        this.trueCode = trueCode;
        this.falseCode = falseCode;
    }
    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/
    public StringSymTabInfo getTrueLabel() {
        return trueLabel;
    }

    public void setTrueLabel(StringSymTabInfo trueLabel) {
        this.trueLabel = trueLabel;
    }

    public StringSymTabInfo getFalseLabel() {
        return falseLabel;
    }

    public void setFalseLabel(StringSymTabInfo falseLabel) {
        this.falseLabel = falseLabel;
    }
}
