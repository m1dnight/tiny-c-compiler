package Expressions;

import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import SymbolTable.SymTabInfo;
import SymbolTable.VariableSymTabInfo;
import Typing.FunctionTypeInfo;
import Typing.Types;

import java.util.ArrayList;

/**
 * Created by christophe on 05.06.14.
 */
public class FunctionCallExpression extends Expression {
    private VariableSymTabInfo function;

    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public FunctionCallExpression(SymTabInfo identifier, Types type, VariableSymTabInfo function) {
        super(identifier, type);
        this.function = function;
    }

    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    public ArrayList<ThreeAddressCode> ToThreeAddressCode() {
        ArrayList<ThreeAddressCode> output = new ArrayList<ThreeAddressCode>();
        output.add(new ThreeAddressCode(OpCodes.CALL, this.function, ((FunctionTypeInfo) this.function.typeInfo).NumberOfParams(), this.identifier));
        return output;
    }

}
