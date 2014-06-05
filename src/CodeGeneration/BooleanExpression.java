package CodeGeneration;

import SymbolTable.StringSymTabInfo;
import SymbolTable.SymTabInfo;

/**
 * Created by christophe on 05.06.14.
 */

public class BooleanExpression extends CodeContainer {
    private StringSymTabInfo trueLabel;
    private StringSymTabInfo falseLabel;

    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public BooleanExpression(OpCodes op, SymTabInfo arg1, SymTabInfo arg2, SymTabInfo result) {
        super(op, arg1, arg2, result);
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

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        /*****************************************************************************
         * Single If
         *****************************************************************************/
        if(this.opCode == OpCodes.A2GT)
        {
            sb.append(String.format("if %s %s %s GOTO %s", arg1.IdentifiertoString(), ">", arg2.IdentifiertoString(), trueLabel.IdentifiertoString()));
            sb.append(String.format("GOTO %s", falseLabel.IdentifiertoString()));
            return sb.toString();
        }
        if(opCode == OpCodes.A2LT)
        {
            sb.append(String.format("if %s %s %s GOTO %s", arg1.IdentifiertoString(), "<", arg2.IdentifiertoString(), trueLabel.IdentifiertoString()));
            sb.append(String.format("GOTO %s", falseLabel.IdentifiertoString()));
            return sb.toString();
        }
        return String.format("**ERROR CONVERTING BEXP TO STRING**\n\tOpCode: %s", opCode.name());

    }
}
