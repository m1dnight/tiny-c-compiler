package Expressions;

import java.util.ArrayList;

/**
 * Created by christophe on 06.06.14.
 */
public class Program {
    private ArrayList<Declaration> declarations;
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
        return "program";
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
}
