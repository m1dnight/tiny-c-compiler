package Expressions;

import CodeGeneration.ThreeAddressCode;
import Expressions.Declarations.Declaration;
import SymbolTable.SymbolTable;

import java.util.ArrayList;

/**
 * Created by christophe on 06.06.14.
 */
public class Program {
    private ArrayList<Declaration> declarations;
    private SymbolTable            symbolTable;


    public Program() {
    }
    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/

    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    public Program AddDeclaration(Declaration d)
    {
        if(this.declarations == null) this.declarations = new ArrayList<Declaration>();
        if(d != null)
            this.declarations.add(d);
        return this;
    }
    public Program AddDeclarations(Program p)
    {
        if(this.declarations == null) this.declarations = new ArrayList<Declaration>();
        if(p != null)
            this.declarations.addAll(p.getDeclarations());
        return this;
    }
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for(Declaration d : declarations)
        {
            sb.append(d.toString());
        }
        return sb.toString();
    }

    public ArrayList<ThreeAddressCode> toThreeAddressCode()
    {
        ArrayList<ThreeAddressCode> program = new ArrayList<ThreeAddressCode>();
        for(Declaration d : this.declarations)
        {
            program.addAll(d.toThreeAddressCode());
        }
        return program;
    }
    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/
    public ArrayList<Declaration> getDeclarations() {
        return declarations;
    }

    public void setDeclarations(ArrayList<Declaration> declarations) {
        this.declarations = declarations;
    }


    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }
}
