package Expressions;

import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import SymbolTable.SymTabInfo;

/**
 * Created by christophe on 06.06.14.
 */
public class AssignStatement extends Statement {
    private Expression valueExpressions;
    private SymTabInfo target;

    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    // A = B --> identifier = operand
    public AssignStatement(SymTabInfo target, Expression valueExpressions) {
        this.target = target;
        this.valueExpressions = valueExpressions;
    }
    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/
    public Expression getValue() {
        return valueExpressions;
    }

    public void setValue(Expression valueExpressions) {
        this.valueExpressions = valueExpressions;
    }

    public SymTabInfo getTarget() {
        return target;
    }

    public void setTarget(SymTabInfo target) {
        this.target = target;
    }
}

