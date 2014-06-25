package Expressions.Declarations;

import CodeGeneration.ThreeAddressCode;
import SymbolTable.SymTabInfo;
import SymbolTable.VariableSymTabInfo;

import java.util.ArrayList;

/**
 * Created by christophe on 06.06.14.
 */
public class Declaration {
    protected SymTabInfo variable;
    private   boolean    global;
    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/

    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    public String toString()
    {
        throw new Error("toString() should be declared in subclasses of Declaration");
    }

    public ArrayList<ThreeAddressCode> toThreeAddressCode()
    {
        throw new Error("toThreeAddressCode() should be declared in subclasses of Declaration");
    }




    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/
    public void setVariable(VariableSymTabInfo variable) {
        this.variable = variable;
    }

    public SymTabInfo getVariable() {
        return variable;
    }

    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }
}
