package CodeGeneration;

import Main.SymTabInfo;

import java.util.ArrayList;

/**
 * Expressions in CUP will be of this type.
 * They will contain the intermediate code that the parser creates.
 * It acts as a wrapper class because it still needs to contain
 * TypeInformation as well. (I suppose at this point).
 */
public class CodeContainer {
    public  SymTabInfo place;
    public ArrayList<ThreeAddressCode> codeList = new ArrayList<ThreeAddressCode>();

    public CodeContainer(){}

    public CodeContainer(SymTabInfo sti)
    {
        this.place = sti;
    }

    public ArrayList<ThreeAddressCode> AppendCode(ThreeAddressCode code)
    {
        this.codeList.add(code);
        return codeList;
    }

    public ArrayList<ThreeAddressCode> AppendCode(ArrayList<ThreeAddressCode> code)
    {
        this.codeList.addAll(code);
        return this.codeList;
    }

}
