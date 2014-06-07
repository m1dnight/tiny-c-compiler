package Assembly;

import CodeGeneration.ThreeAddressCode;
import SymbolTable.SymbolTable;
import SymbolTable.SymTabInfo;

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
        // Traverse the statements in reverse
        for(int index = block.getTacs().size() - 1; index >= 0; index--)
        {
            ThreeAddressCode tac = block.getTac(index);

            // Lookup all the variables in the Symboltable.
            SymTabInfo result   = tac.getResult();
            SymTabInfo operand1 = tac.getArg1();
            SymTabInfo operand2 = tac.getArg2();

        }
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
