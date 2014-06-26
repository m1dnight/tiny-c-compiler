package Expressions;

import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import Expressions.Expressions.Expression;
import SymbolTable.SymTabInfo;

import java.util.ArrayList;

/**
 * This class represents a list of parameters passed to a function.
 *
 * Basicly it is semantic wrapper for a list of Expression classes.
 *
 */
public class ParameterList {
    private ArrayList<Expression> parameterList;

    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public ParameterList()
    {

    }
    public ParameterList(Expression param)
    {
        if(this.parameterList == null) this.parameterList = new ArrayList<Expression>(1);
        this.parameterList.add(param);
    }
    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    public ParameterList AddParameter(Expression param)
    {
        this.parameterList.add(param);
        return this;
    }
    public ParameterList AddParameters(ParameterList params)
    {
        if(params != null)
            this.parameterList.addAll(params.getParameterList());
        return this;
    }

    public ArrayList<ThreeAddressCode> ToThreeAddressCode()
    {
        ArrayList<ThreeAddressCode> output = new ArrayList<ThreeAddressCode>();
        for(Expression e : this.parameterList)
        {
            output.addAll(e.ToThreeAddressCode());
        }
        for(Expression e : this.parameterList)
        {
            output.add(new ThreeAddressCode(OpCodes.PARAM, e.getIdentifier()));
        }
        return output;
    }
    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/
    public ArrayList<Expression> getParameterList() {
        if(this.parameterList == null) this.parameterList = new ArrayList<Expression>();
        return parameterList;
    }
    public ArrayList<SymTabInfo> getIdentifiers()
    {
        ArrayList<SymTabInfo> identifiers = new ArrayList<SymTabInfo>(parameterList.size());
        for(Expression exp : this.parameterList)
            identifiers.add(exp.getIdentifier());
        return identifiers;
    }
}
