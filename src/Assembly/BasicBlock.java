package Assembly;

import CodeGeneration.ThreeAddressCode;

import java.util.ArrayList;

/**
 * Created by christophe on 4/20/14.
 */
public class BasicBlock {
    ArrayList<ThreeAddressCode> block;
    public void AppendCode(ThreeAddressCode tac) {
        if(block == null) block = new ArrayList<ThreeAddressCode>();
        block.add(tac);
    }

    public String toString()
    {
        StringBuilder output = new StringBuilder();
        for(ThreeAddressCode tac : block)
        {
            output.append("\n" + tac.toString());
        }
        return output.toString();
    }
}
