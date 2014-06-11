package Assembly.x86;

import Assembly.BasicBlock;
import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import SymbolTable.IntegerSymTabInfo;
import SymbolTable.SymTabInfo;

import java.util.HashMap;

/**
 * Created by christophe on 11.06.14.
 */
public class BasicBlockToX86Generator
{
    private StringBuilder prologue = new StringBuilder();
    private StringBuilder globl    = new StringBuilder();

    //Variables per block
    private int parameterOffset     = 8;  // first offset for parameter is 4.
    private int localVariableOffset = -4; // first offset for local variables is -4.
    private HashMap<SymTabInfo, String> variableAddresses = new HashMap<SymTabInfo, String>();

    private void Prologue()
    {
        prologue.append(".section .data");
        prologue.append(".section .text");
        globl.append(".globl _start");
    }

    public void Compile(BasicBlock block)
    {
        for(ThreeAddressCode tac : block.getTacs())
        {
            if(tac.getOpCode() == OpCodes.LABEL)
                PrintLabel(tac);
            if(tac.getOpCode() == OpCodes.GETPARAM)
                PutParameterAddress(tac);
            if(tac.getOpCode() == OpCodes.A0)
            {
                System.out.println(String.format("movl %s, %s", GetAddress(tac.getArg1()), "%eax"));
                System.out.println(String.format("movl %s, %s", "%eax", GetAddress(tac.getResult())));
            }
            if(tac.getOpCode() == OpCodes.A2PLUS || tac.getOpCode() == OpCodes.A2MINUS)
            {
                System.out.println(String.format("movl %s, %%eax", GetAddress(tac.getArg1())));
                System.out.println(String.format("movl %s, %%ebx", GetAddress(tac.getArg2())));
                System.out.println(String.format("%s %%eax, %%ebx", tac.getOpCode() == OpCodes.A2MINUS ? "subl" : "addl"));
                System.out.println(String.format("movl %%ebx, %s", GetAddress(tac.getResult())));
            }
        }
    }

    private void PrintLabel(ThreeAddressCode tac)
    {
        if(tac.getArg1().IdentifiertoString().equals("function_main"))
            System.out.println("_start");
        else
            System.out.println(tac.getArg1().IdentifiertoString());
    }

    private void PutParameterAddress(ThreeAddressCode param)
    {
        variableAddresses.put(param.getArg1(), String.format("%d(%%ebp)", parameterOffset));
        parameterOffset += 4;
    }
    private String GetAddress(SymTabInfo variable)
    {
        if (variable instanceof IntegerSymTabInfo)
            return String.format("$%d", ((IntegerSymTabInfo) variable).value);

        if (variableAddresses.get(variable) == null)
        {
            variableAddresses.put(variable, String.format("%d(%%ebp)", localVariableOffset));
            localVariableOffset -= 4;
        }
        return variableAddresses.get(variable);

    }

}
