package Assembly;

import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;

import java.util.ArrayList;

/**
 * This class is the main entry point for generating assembly code from three address code.
 */
public class Generator {

    public static  ArrayList<BasicBlock> SplitToBlocks(ArrayList<ThreeAddressCode> cc)
    {
        ArrayList<BasicBlock> blocks = new ArrayList<BasicBlock>();
        int counter = 0;
        while(counter < cc.size())
        {
            BasicBlock block = new BasicBlock();
            // If we have a leader, we create a new block and keep adding the code
            // until we meet a new leader.
            if(IsLeader(cc, counter))
            {
                block.AppendCode(cc.get(counter));
                counter++;
                while(counter < cc.size() && !IsLeader(cc, counter))
                {
                    block.AppendCode(cc.get(counter));
                    counter++;
                }
            }
            blocks.add(block);
        }
        return blocks;

    }
    private static boolean IsLeader(ArrayList<ThreeAddressCode> cc, int tacIndex)
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
        if(tac.getOpCode() == OpCodes.LABEL)
            return true;

        tac = cc.get(tacIndex - 1);
        return tac.getOpCode() == OpCodes.IF || tac.getOpCode() == OpCodes.GOTO;

    }

    public static void PrintBlocks(ArrayList<BasicBlock> blocks)
    {
        System.out.println("*****************************************************");
        System.out.println("*********** BASIC BLOCKS ****************************");
        System.out.println("*****************************************************");
        for(BasicBlock block : blocks)
        {
            System.out.println(block);
            System.out.println("*****************************************************");
        }
        System.out.println("*********** END OF BASIC BLOCKS *********************");
        System.out.println("*****************************************************");
    }

}
