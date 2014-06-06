package Expressions;

import CodeGeneration.ThreeAddressCode;
import SymbolTable.SymTabInfo;
import Typing.Types;

/**
 * Created by christophe on 05.06.14.
 */
public class VariableExpression extends Expression {

    public VariableExpression(SymTabInfo identifier, Types type) {
        super(identifier, type);
    }

    public ThreeAddressCode ToThreeAddressCode()
    {
        return null;
    }
}
