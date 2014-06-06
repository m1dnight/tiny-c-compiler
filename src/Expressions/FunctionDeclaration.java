package Expressions;

import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import SymbolTable.StringSymTabInfo;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

/**
 * Created by christophe on 06.06.14.
 */
public class FunctionDeclaration extends Declaration {
    private StatementList body;
    private StringSymTabInfo beginLabel;
    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    public String toString()
    {
        throw new NotImplementedException();
    }
    public ArrayList<ThreeAddressCode> toThreeAddressCode()
    {
        ArrayList<ThreeAddressCode> output = new ArrayList<ThreeAddressCode>();

        // Create the TAC for the label.
        ThreeAddressCode beginFunTac = new ThreeAddressCode(OpCodes.LABEL, beginLabel);
        output.add(beginFunTac);
        output.addAll(body.toThreeAddressCode());
        return output;
    }
    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/
    public StatementList getBody() {
        return body;
    }

    public void setBody(StatementList body) {
        this.body = body;
    }

    public StringSymTabInfo getBeginLabel() {
        return beginLabel;
    }

    public void setBeginLabel(StringSymTabInfo beginLabel) {
        this.beginLabel = beginLabel;
    }
}
