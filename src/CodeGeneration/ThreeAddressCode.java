package CodeGeneration;

import SymbolTable.SymTabInfo;

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
        // Print out the 3AC for debugging purposes
        System.out.println(this.toString());
    }

    @Override
    public String toString()
    {

        if(opCode == OpCodes.A1MINUS)
        {
            return String.format("%s = %s %s", result.IdentifiertoString(), " -", arg1.IdentifiertoString());
        }
        /**
         * Assignment
         */
        if(opCode == OpCodes.A0)
        {
            return String.format("%s = %s", result.IdentifiertoString(), arg1.IdentifiertoString());
        }
        /**
         * Simple arithmetic expressions
         */
        if(opCode == OpCodes.A2PLUS)
        {
            return String.format("%s = %s %s %s", result.IdentifiertoString(), arg1.IdentifiertoString(), " + ", arg2.IdentifiertoString());
        }
        if(opCode == OpCodes.A2MINUS)
        {
            return String.format("%s = %s %s %s", result.IdentifiertoString(), arg1.IdentifiertoString(), " - ", arg2.IdentifiertoString());
        }
        if(opCode == OpCodes.A2TIMES)
        {
            return String.format("%s = %s %s %s", result.IdentifiertoString(), arg1.IdentifiertoString(), " * ", arg2.IdentifiertoString());
        }
        if(opCode == OpCodes.A2DIV)
        {
            return String.format("%s = %s %s %s", result.IdentifiertoString(), arg1.IdentifiertoString(), " / ", arg2.IdentifiertoString());
        }

        // In case we didn't match anything return a string that will show in the output
        return "**ERROR CONVERTING**";
    }
}
