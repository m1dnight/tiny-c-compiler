package CodeGeneration;

import SymbolTable.SymTabInfo;

import java.util.ArrayList;

/**
 * Expressions in CUP will be of this type.
 * They will contain the intermediate code that the parser creates.
 * It acts as a wrapper class because it still needs to contain
 * TypeInformation as well. (I suppose at this point).
 */
public class CodeContainer {
    public  SymTabInfo symTabInfo;
    public ArrayList<ThreeAddressCode> codeList = new ArrayList<ThreeAddressCode>();
    public CodeContainer(){}

    public CodeContainer(SymTabInfo sti)
    {
        this.symTabInfo = sti;
    }

    public SymTabInfo ResultVariable()
    {
        return codeList.get(0).getResultSymTabInfo();
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

    public ArrayList<ThreeAddressCode> AddFirst(ThreeAddressCode code)
    {
        this.codeList.add(0, code);
        return this.codeList;
    }

    public void PrintCode()
    {
        for(int i = 0; i < codeList.size(); i++)
            System.out.println(codeList.get(i).toString());
    }

    public void addDeclarations(CodeContainer vs) {

    }
}
