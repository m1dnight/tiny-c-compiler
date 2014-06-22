package Expressions;

import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import SymbolTable.ArraySymTabInfo;

import java.util.ArrayList;

/**
 * Created by christophe on 22.06.14.
 */
public class ArrayDeclaration extends VariableDeclaration {
    private ArraySymTabInfo variable;

    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    public ArrayList<ThreeAddressCode> toThreeAddressCode()
    {
        ArrayList<ThreeAddressCode> output = new ArrayList<ThreeAddressCode>();
        output.addAll(this.variable.getSize().ToThreeAddressCode());

        output.add(new ThreeAddressCode(OpCodes.ALLOC_ARRAY, this.variable, this.variable.getSize().getIdentifier(), null));
        return output;
    }
    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/
    public void setVariable(ArraySymTabInfo variable) {
        this.variable = variable;
    }

    public ArraySymTabInfo getVariable() {
        return variable;
    }

}
