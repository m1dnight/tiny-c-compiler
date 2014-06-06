package Expressions;

import java.util.ArrayList;

/**
 * Created by christophe on 06.06.14.
 */
public class StatementList {
    private ArrayList<Statement> statements;


    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public StatementList(ArrayList<Statement> statements) {
        this.statements = statements;
    }
    public StatementList(Statement statement) {
        if(this.statements == null) this.statements = new ArrayList<Statement>();
        this.statements.add(statement);
    }
    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    public StatementList AddStatements(StatementList ss)
    {
        if(this.statements == null) this.statements = new ArrayList<Statement>();
        if(ss != null)
            this.statements.addAll(ss.getStatements());
        return this;
    }
    public StatementList AddStatement(Statement s)
    {
        if(this.statements == null) this.statements = new ArrayList<Statement>();
        if(s != null)
            this.statements.add(s);
        return this;
    }
    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/
    public ArrayList<Statement> getStatements() {
        if(null == statements) return new ArrayList<Statement>();
        return statements;
    }

    public void setStatements(ArrayList<Statement> statements) {
        this.statements = statements;
    }
}
