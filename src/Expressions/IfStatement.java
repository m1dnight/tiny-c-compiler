package Expressions;

import CodeGeneration.ThreeAddressCode;

import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;

/**
 * Created by christophe on 06.06.14.
 */
public class IfStatement extends Statement {
    private BooleanExpression bexp;

    public BooleanExpression getBexp() {
        return bexp;
    }

    public void setBexp(BooleanExpression bexp) {
        this.bexp = bexp;
    }
}
