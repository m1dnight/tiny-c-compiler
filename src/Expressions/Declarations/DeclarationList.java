package Expressions.Declarations;

import CodeGeneration.ThreeAddressCode;

import java.util.ArrayList;

/**
 * Created by christophe on 22.06.14.
 */
public class DeclarationList {
    private ArrayList<Declaration> declarations;

    public DeclarationList(Declaration v) {
        this.declarations = new ArrayList<Declaration>(1);
        this.declarations.add(v);
    }

    public DeclarationList() {
        this.declarations = new ArrayList<Declaration>();
    }
    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    public void addDeclarations(DeclarationList vs) {
        if(vs != null) {
            if (this.declarations == null)
                this.declarations = new ArrayList<Declaration>();
            if (vs.getDeclarations() != null)
                this.declarations.addAll(vs.getDeclarations());
        }
    }
    public void addDeclaration(Declaration v)
    {
        if(this.declarations == null) this.declarations = new ArrayList<Declaration>();
        this.declarations.add(v);
    }

    public ArrayList<ThreeAddressCode> toThreeAddressCode() {
        ArrayList<ThreeAddressCode> output = new ArrayList<ThreeAddressCode>();
        for(Declaration s : this.declarations)
        {
            output.addAll(s.toThreeAddressCode());
        }
        return output;
    }
    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/
    public ArrayList<Declaration> getDeclarations() {

        return declarations;
    }


}
