package CodeGeneration;

import SymbolTable.SymTabInfo;

/**
 * The class will be an object representing a three address code.
 */
public class ThreeAddressCode {
    private OpCodes    opCode;
    private SymTabInfo arg1;
    private SymTabInfo arg2;
    private SymTabInfo result;
    private int        ParamCount;

    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public ThreeAddressCode(OpCodes op, SymTabInfo arg1, SymTabInfo arg2, SymTabInfo result)
    {
        this.opCode = op;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.result = result;
    }

    // Special constructor for function calls
    public ThreeAddressCode(OpCodes op, SymTabInfo arg1, int ParamCount, SymTabInfo result)
    {
        this.opCode = op;
        this.arg1 = arg1;
        this.ParamCount = ParamCount;
        this.result = result;
    }

    public ThreeAddressCode(OpCodes op, SymTabInfo arg)
    {
        this.opCode = op;
        this.arg1 = arg;
    }

    public ThreeAddressCode()
    {

    }
    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    @Override
    public String toString()
    {
        /*****************************************************************************
         * Coercion
         *****************************************************************************/
        if(opCode == OpCodes.PARAMTOCHAR)
        {
            return String.format("PARAM2CHAR %s", arg1.IdentifiertoString());
        }
        /*****************************************************************************
         * Global variable
         *****************************************************************************/
        if(opCode == OpCodes.GLOBL)
            return String.format("GLOBALVAR %s", arg1.IdentifiertoString());
        if(opCode == OpCodes.GLOBLARR)
        {
            return String.format("GLOBALARR %s %s", arg1.IdentifiertoString(), arg2.IdentifiertoString());
        }
        /*****************************************************************************
         * Writing
         *****************************************************************************/
        if(opCode == OpCodes.WRITEINT)
            return String.format("WRITE %s", arg1.IdentifiertoString());
        if(opCode == OpCodes.READINT)
            return String.format("READ %s", arg1.IdentifiertoString());
        if(opCode == OpCodes.WRITECHAR)
            return String.format("WRITECHAR %s", arg1.IdentifiertoString());

        /*****************************************************************************
         * Arrays
         *****************************************************************************/
        if(opCode == OpCodes.AAC)
        {
            return String.format("%s = %s[%s]", result.IdentifiertoString(), arg1.IdentifiertoString(), arg2.IdentifiertoString());
        }
        if(opCode == OpCodes.AAS)
        {
            return String.format("%s[%s] = %s", result.IdentifiertoString(), arg1.IdentifiertoString(), arg2.IdentifiertoString());
        }
        if(opCode == OpCodes.ALLOC_ARRAY)
        {
            return String.format("ALLOC_ARRAY %s %s", arg1.getTypeInfo().ActualType(), arg1.IdentifiertoString());
        }
        /*****************************************************************************
         * Parameters
         *****************************************************************************/
        if(opCode == OpCodes.GETPARAM)
        {
            return String.format("GetParam %s", arg1.IdentifiertoString());
        }
        /*****************************************************************************
         * Return Statements
         *****************************************************************************/
        if(opCode == OpCodes.RETURN)
        {
            return String.format("return %s", arg1.IdentifiertoString());
        }
        if(opCode == OpCodes.RETURNTOCHAR)
        {
            return String.format("return %s", arg1.IdentifiertoString());
        }
        /*****************************************************************************
         * Function Calls
         *****************************************************************************/
        if(opCode == OpCodes.CALL)
        {

            if(this.result == null)
                return String.format("call %s, %s", arg1.IdentifiertoString(), this.ParamCount);
            return String.format("%s = call %s, %s", result.IdentifiertoString(), arg1.IdentifiertoString(), this.ParamCount);
        }
        if(opCode == OpCodes.PARAM)
        {
            return String.format("param %s", arg1.IdentifiertoString());
        }
/*        *//*****************************************************************************
         * Boolean Expressions
         *****************************************************************************//*
        if(opCode == OpCodes.A2GT)
        {
            return String.format("%s = %s %s %s", result.IdentifiertoString(), arg1.IdentifiertoString(), ">", arg2.IdentifiertoString());
        }*/
        /*****************************************************************************
         * Control Flow
         *****************************************************************************/
        if(opCode == OpCodes.GOTO)
        {
            return String.format("GOTO %s", arg1.IdentifiertoString());
        }
        if(opCode == OpCodes.LABEL)
        {
            return String.format("%s", arg1.IdentifiertoString());
        }
        if(opCode == OpCodes.IFFALSE)
        {
            return String.format("ifFalse %s GOTO %s", arg1.IdentifiertoString(), arg2.IdentifiertoString());
        }
        if(opCode == OpCodes.LABEL)
        {
            return String.format("%s", arg1.IdentifiertoString());
        }
        if(opCode == OpCodes.A2GTIF)
        {
            return String.format("IF %s > %s GOTO %s", arg1.IdentifiertoString(), arg2.IdentifiertoString(), result.IdentifiertoString());
        }
        if(opCode == OpCodes.A2LTIF)
        {
            return String.format("IF %s < %s GOTO %s", arg1.IdentifiertoString(), arg2.IdentifiertoString(), result.IdentifiertoString());
        }
        if(opCode == OpCodes.A2EQIF)
        {
            return String.format("IF %s == %s GOTO %s", arg1.IdentifiertoString(), arg2.IdentifiertoString(), result.IdentifiertoString());
        }
        if(opCode == OpCodes.A2NEQIF)
        {
            return String.format("IF %s != %s GOTO %s", arg1.IdentifiertoString(), arg2.IdentifiertoString(), result.IdentifiertoString());
        }

        /******************************************************************************
         * Assignment
         *****************************************************************************/
        if(opCode == OpCodes.A0)
        {
             return String.format("%s = %s", result.IdentifiertoString(), arg1.IdentifiertoString());
        }
        /******************************************************************************
         * Simple arithmetic expressions
         *****************************************************************************/
        if(opCode == OpCodes.A1MINUS)
            return String.format("%s = %s %s", result.IdentifiertoString(), " -", arg1.IdentifiertoString());

        if(opCode == OpCodes.A2PLUS)
            return String.format("%s = %s %s %s", result.IdentifiertoString(), arg1.IdentifiertoString(), " + ", arg2.IdentifiertoString());

        if(opCode == OpCodes.A2MINUS)
            return String.format("%s = %s %s %s", result.IdentifiertoString(), arg1.IdentifiertoString(), " - ", arg2.IdentifiertoString());

        if(opCode == OpCodes.A2TIMES)
            return String.format("%s = %s %s %s", result.IdentifiertoString(), arg1.IdentifiertoString(), " * ", arg2.IdentifiertoString());

        if(opCode == OpCodes.A2DIV)
            return String.format("%s = %s %s %s", result.IdentifiertoString(), arg1.IdentifiertoString(), " / ", arg2.IdentifiertoString());

        if(opCode == OpCodes.A2EQ)
            return String.format("IF %s == %s GOTO %s ", arg1.IdentifiertoString(), arg2.IdentifiertoString(), result.IdentifiertoString());

        if(opCode == OpCodes.A2NEQ)
            return String.format("IF %s != %s GOTO %s ", arg1.IdentifiertoString(), arg2.IdentifiertoString(), result.IdentifiertoString());

        if(opCode == OpCodes.A2GT)
            return String.format("IF %s > %s GOTO %s ", arg1.IdentifiertoString(), arg2.IdentifiertoString(), result.IdentifiertoString());

        if(opCode == OpCodes.A2LT)
            return String.format("IF %s < %s GOTO %s ", arg1.IdentifiertoString(), arg2.IdentifiertoString(), result.IdentifiertoString());


        // In case we didn't match anything return a string that will show in the output
        return String.format("**ERROR CONVERTING TAC TO STRING**\n\tOpCode: %s", opCode.name());
    }

    /*******************************************************************************************************************
     *********************************************** GETTERS AND SETTERS ***********************************************
     ******************************************************************************************************************/
    public OpCodes getOpCode() {
        return opCode;
    }

    public void setOpCode(OpCodes opCode) {
        this.opCode = opCode;
    }

    public SymTabInfo getArg1() {
        return arg1;
    }

    public void setArg1(SymTabInfo arg1) {
        this.arg1 = arg1;
    }

    public SymTabInfo getArg2() {
        return arg2;
    }

    public void setArg2(SymTabInfo arg2) {
        this.arg2 = arg2;
    }

    public SymTabInfo getResult() {
        return result;
    }

    public void setResult(SymTabInfo result) {
        this.result = result;
    }

    public SymTabInfo getResultSymTabInfo() {
        return result;
    }

    public int getParamCount()
    {
        return ParamCount;
    }

    public void setParamCount(int paramCount)
    {
        ParamCount = paramCount;
    }
}
