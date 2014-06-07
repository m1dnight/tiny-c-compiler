package Assembly;

import CodeGeneration.ThreeAddressCode;
import SymbolTable.SymbolTable;
import SymbolTable.SymTabInfo;
import com.sun.org.apache.xpath.internal.operations.Variable;

import java.util.ArrayList;

/**
 * Created by christophe on 4/20/14.
 */
public class BasicBlock {
    private ArrayList<ThreeAddressCode> block;


    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    public void AppendCode(ThreeAddressCode tac) {
        if (getTacs() == null) setTacs(new ArrayList<ThreeAddressCode>());
        getTacs().add(tac);
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        for (ThreeAddressCode tac : getTacs()) {
            output.append("\n" + tac.toString());
        }
        return output.toString();
    }

    public static void DetermineLiveness(SymbolTable symTab, BasicBlock block)
    {
/*        // Traverse the statements in reverse
        for(int index = block.getTacs().size() - 1; index >= 0; index--)
        {

            ThreeAddressCode tac = block.getTac(index);
            // Check to see if we are dealing with an assignment or an operation.
            if(tac.getOpCode() == OpCodes.)
            // Lookup all the variables in the Symboltable.

            SymTabInfo result   = tac.getResult();
            SymTabInfo operand1 = tac.getArg1();
            SymTabInfo operand2 = tac.getArg2();
            if(null != result)
            result.setLive(false);
            if(null != operand1)
            operand1.setLive(false);
            if(null != operand2)
            operand2.setLive(false);
        }*/
    }
    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/
    public ArrayList<ThreeAddressCode> getTacs() {
        return block;
    }

    private void setTacs(ArrayList<ThreeAddressCode> block) {
        this.block = block;
    }

    public ThreeAddressCode getTac(int idx)
    {
        return block.get(idx);
    }


}
