package Expressions.Expressions;

import CodeGeneration.ThreeAddressCode;
import SymbolTable.SymTabInfo;
import Typing.Types;

import java.util.ArrayList;

/**
 * Created by christophe on 05.06.14.
 */
public class ConstantExpression extends Expression {

    public ConstantExpression(SymTabInfo identifier, Types type) {
        super(identifier, type);
    }

    public ArrayList<ThreeAddressCode> ToThreeAddressCode()
    {
        return new ArrayList<ThreeAddressCode>(0);
    }
}
