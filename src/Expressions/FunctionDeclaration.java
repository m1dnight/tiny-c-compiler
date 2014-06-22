package Expressions;

import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import SymbolTable.StringSymTabInfo;
import SymbolTable.SymTabInfo;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by christophe on 06.06.14.
 */
public class FunctionDeclaration extends Declaration {
    private Block    body;
    private StringSymTabInfo beginLabel;
    private LinkedList<SymTabInfo> parameters;
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
        //Emit TAC for the parameters
        if(parameters != null && parameters.size() > 0)
            for(SymTabInfo parameter : parameters)
                output.add(new ThreeAddressCode(OpCodes.GETPARAM, parameter));
        if(body != null) {
            output.addAll(body.getDeclarations().toThreeAddressCode());
            output.addAll(body.getStatements().toThreeAddressCode());
        }
        return output;
    }
    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/
    public Block getBody() {
        return body;
    }

    public void setBody(Block body) {
        this.body = body;
    }

    public StringSymTabInfo getBeginLabel() {
        return beginLabel;
    }

    public void setBeginLabel(StringSymTabInfo beginLabel) {
        this.beginLabel = beginLabel;
    }

    public void setParameters(LinkedList<SymTabInfo> parameters)
    {
        this.parameters = parameters;
    }
    public LinkedList<SymTabInfo> getParameters()
    {
        return this.parameters;
    }
}
