package Assembly;

import CodeGeneration.ThreeAddressCode;

import java.util.ArrayList;

/**
 * Created by christophe on 4/20/14.
 */
public class BasicBlock {
    private ArrayList<ThreeAddressCode> block;

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

    public ArrayList<ThreeAddressCode> getTacs() {
        return block;
    }

    private void setTacs(ArrayList<ThreeAddressCode> block) {
        this.block = block;
    }


}
