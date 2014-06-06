package Expressions;

import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import SymbolTable.SymTabInfo;
import Typing.Types;

/**
 * Created by christophe on 06.06.14.
 */
public class ReturnStatement extends Statement {
    private Expression returnExpressions;
    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public ReturnStatement(Expression returnExpressions) {
        this.returnExpressions = returnExpressions;
    }


    public Expression getReturnExpressions() {
        return returnExpressions;
    }

    public void setReturnExpressions(Expression returnExpressions) {
        this.returnExpressions = returnExpressions;
    }
}
