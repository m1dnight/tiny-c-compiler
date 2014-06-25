package Expressions.Statement;

import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import Expressions.ParameterList;
import SymbolTable.SymTabInfo;
import SymbolTable.VariableSymTabInfo;
import Typing.FunctionTypeInfo;
import Typing.Types;

import java.util.ArrayList;

/**
 * Created by christophe on 25.06.14.
 */
public class FunctionCallStatement extends Statement {
    private final SymTabInfo         identifier;
    private final Types              type;
    private       VariableSymTabInfo function;
    private       ParameterList      parameterList;

    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public FunctionCallStatement(SymTabInfo identifier, Types type, VariableSymTabInfo function, ParameterList parameters) {
        this.identifier = identifier;
        this.type = type;
        this.function = function;
        this.parameterList = parameters;
    }

    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    public ArrayList<ThreeAddressCode> toThreeAddressCode() {
        ArrayList<ThreeAddressCode> output = new ArrayList<ThreeAddressCode>();
        if(this.parameterList != null)
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
