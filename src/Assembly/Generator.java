package Assembly;

import CodeGeneration.CodeContainer;
import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;

import java.util.ArrayList;

/**
 * This class is the main entry point for generating assembly code from three address code.
 */
public class Generator {

    public ArrayList<BasicBlock> SplitToBlocks(CodeContainer cc)
    {
        ArrayList<BasicBlock> blocks = new ArrayList<BasicBlock>();
        // We loop over all the TAC's in the CodeContainer.
        // As long as they are leaders we add them to a CodeContainer.
        int counter = 0;
        while(counter < cc.codeList.size())
        {
            BasicBlock block = new BasicBlock();
            while(IsLeader(cc.codeList, counter) && counter < cc.codeList.size())
            {
                block.AppendCode(cc.codeList.get(counter));
                counter++;
            }
            blocks.add(block);
        }
        return blocks;

    }
    private boolean IsLeader(ArrayList<ThreeAddressCode> cc, int tacIndex)
    {
        /**
         * The instruction is a leader if:
         * - It is the first
         * - It is the target of a conditional or unconditional jump
         * - It follows a conditional or a jump
         */
        if(tacIndex == 0)
            return true;

        // Check to see if it is a label.
        // If it is a label we will jump to it.
        ThreeAddressCode tac = cc.get(tacIndex);
        if(tac.opCode == OpCodes.LABEL)
            return true;

        tac = cc.get(tacIndex - 1);
        if(tac.opCode == OpCodes.IFFALSE || tac.opCode == OpCodes.GOTO)
            return true;

        return false;
    }

}
