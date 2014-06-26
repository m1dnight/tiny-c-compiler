package Expressions.Statement;

import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import Expressions.Expressions.Expression;
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
    private final Types              type;
    private       VariableSymTabInfo function;
    private       ParameterList      parameterList;

    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public FunctionCallStatement(SymTabInfo identifier, Types type, VariableSymTabInfo function, ParameterList parameters) {
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
        {
            ArrayList<SymTabInfo> expectedPs = ((FunctionTypeInfo) this.function.getTypeInfo()).getParameters();
            ArrayList<Expression> actualPs = this.parameterList.getParameterList();

            for (int i = 0; i < this.parameterList.getParameterList().size(); i++) {
                // If we need to coerce
                output.addAll(actualPs.get(i).ToThreeAddressCode());

                if (actualPs.get(i).getExpressionType() == Types.INTEGER && expectedPs.get(i).getTypeInfo().ActualType() == Types.CHAR)
                    output.add(new ThreeAddressCode(OpCodes.PARAMTOCHAR, actualPs.get(i).getIdentifier()));
                else
                output.add(new ThreeAddressCode(OpCodes.PARAM, actualPs.get(i).getIdentifier()));
            }
/*            if (this.parameterList != null)
                output.addAll(this.parameterList.ToThreeAddressCode());*/
        }
        output.add(
                new ThreeAddressCode(OpCodes.CALL, this.function,
                        ((FunctionTypeInfo) this.function.getTypeInfo()).NumberOfParams(), null));
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
