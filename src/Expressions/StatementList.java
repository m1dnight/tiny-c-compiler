package Expressions;

import CodeGeneration.ThreeAddressCode;

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

    public StatementList() {
        this.statements = new ArrayList<Statement>();
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

    public ArrayList<ThreeAddressCode> toThreeAddressCode() {

        ArrayList<ThreeAddressCode> output = new ArrayList<ThreeAddressCode>();
        for(Statement s : this.statements)
        {
           output.addAll(s.toThreeAddressCode());
        }
        return output;
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
