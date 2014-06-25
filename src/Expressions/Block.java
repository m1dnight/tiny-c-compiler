package Expressions;

import Expressions.Declarations.DeclarationList;
import Expressions.Statement.Statement;
import Expressions.Statement.StatementList;

/**
 * Created by christophe on 22.06.14.
 */
public class Block {
    private StatementList   statements;
    private DeclarationList declarations;


    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public Block(DeclarationList vds, StatementList sts) {
        this.declarations = vds;
        this.statements = sts;

    }

    public Block(Statement cons) {
        this.statements = new StatementList(cons);
    }

    public Block() {

    }

    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    public void addStatement(Statement cons) {
        if(this.statements == null)
            this.statements = new StatementList(cons);
        else
            this.statements.AddStatement(cons);
    }
    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/
    public StatementList getStatements() {
        if(this.statements == null) {
            return new StatementList();
        }
        return statements;
    }

    public DeclarationList getDeclarations() {
        if(this.declarations == null) return new DeclarationList();
        return declarations;
    }


}
