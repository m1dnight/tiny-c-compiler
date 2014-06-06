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
    private ParameterList parameterList;

    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public FunctionCallExpression(SymTabInfo identifier, Types type, VariableSymTabInfo function, ParameterList parameters) {
        super(identifier, type);
        this.function = function;
        this.parameterList = parameters;
    }

    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    public ArrayList<ThreeAddressCode> ToThreeAddressCode() {
        ArrayList<ThreeAddressCode> output = new ArrayList<ThreeAddressCode>();

        output.addAll(this.parameterList.ToThreeAddressCode());
        output.add(
                new ThreeAddressCode(OpCodes.CALL, this.function,
                                     ((FunctionTypeInfo) this.function.typeInfo).NumberOfParams(), this.identifier));
        return output;
    }
    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/
    public ParameterList getParameterList() {
        return parameterList;
    }

    public void setParameterList(ParameterList parameterList) {
        this.parameterList = parameterList;
    }
}
