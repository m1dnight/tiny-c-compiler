package CodeGeneration;

import Main.SymTabInfo;

/**
 * The class will be an object representing a three address code.
 */
public class ThreeAddressCode {
    private OpCodes opCode;
    private SymTabInfo arg1;
    private SymTabInfo arg2;
    private SymTabInfo result;

    public ThreeAddressCode(OpCodes op, SymTabInfo arg1, SymTabInfo arg2, SymTabInfo result)
    {
        this.opCode = op;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.result = result;
    }

    @Override
    public String toString()
    {
        if(opCode == OpCodes.A1MINUS)
        {
            return String.format("%s = %s %s", result.name, "minus", arg1.name);
        }
        return "didntmatchanycase";
    }
}
