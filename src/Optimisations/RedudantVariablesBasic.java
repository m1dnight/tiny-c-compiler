package Optimisations;

import Assembly.BasicBlock;
import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;

import java.util.ArrayList;

/**
 * Created by christophe on 09.06.14.
 */
public class RedudantVariablesBasic
{

    public static BasicBlock Optimize(BasicBlock b)
    {
        ArrayList<ThreeAddressCode> workingCopy = (ArrayList<ThreeAddressCode>) b.getTacs().clone();
        int currentSize = workingCopy.size();
        for(int idx = 0; idx < currentSize - 1; idx++)
        {
            ThreeAddressCode nextRecord    = workingCopy.get(idx + 1);
            ThreeAddressCode currentRecord = workingCopy.get(idx);

            if(nextRecord.getOpCode() == OpCodes.A0 && nextRecord.getArg1().equals(currentRecord.getResult()) && currentRecord.getResult().IdentifiertoString().startsWith("_var"))
            {
                currentRecord.setResult(nextRecord.getResult());
                workingCopy.remove(nextRecord);
                currentSize--;
            }

        }
        return new BasicBlock().setTacs(workingCopy);
    }
}
