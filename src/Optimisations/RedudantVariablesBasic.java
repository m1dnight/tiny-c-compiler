package Optimisations;

import Assembly.BasicBlock;
import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;

import java.util.ArrayList;

/**
 * Created by christophe on 09.06.14.
 */
public class RedudantVariablesBasic {

    public static BasicBlock Optimize(BasicBlock b) {
        boolean changed = false;
        BasicBlock output = new BasicBlock();
        ArrayList<ThreeAddressCode> workingCopy = (ArrayList<ThreeAddressCode>) b.getTacs().clone();
        int currentSize = workingCopy.size();
        do {
            changed = false;
            output.setTacs((ArrayList<ThreeAddressCode>) workingCopy.clone());
            currentSize = workingCopy.size();
            for (int idx = 0; idx < currentSize - 1; idx++) {
                ThreeAddressCode nextRecord = workingCopy.get(idx + 1);
                ThreeAddressCode currentRecord = workingCopy.get(idx);

                if ((nextRecord.getOpCode() == OpCodes.A0)
                        && nextRecord.getArg1().equals(currentRecord.getResult())
                        && currentRecord.getResult().IdentifiertoString().startsWith("_var")) {
                    currentRecord.setResult(nextRecord.getResult());
                    workingCopy.remove(nextRecord);
                    currentSize--;
                    changed = true;
                }
                if ((nextRecord.getOpCode() == OpCodes.WRITEINT || nextRecord.getOpCode() == OpCodes.WRITECHAR)
                        && currentRecord.getOpCode() == OpCodes.A0
                        && currentRecord.getResult() == nextRecord.getArg1()) {
                    nextRecord.setArg1(currentRecord.getArg1());
                    workingCopy.remove(currentRecord);
                    currentSize--;
                    changed = true;
                }
            }
        }
        while (changed);
        return new BasicBlock().setTacs((ArrayList<ThreeAddressCode>) workingCopy.clone());
    }
}
