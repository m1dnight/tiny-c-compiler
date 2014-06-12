package Assembly.x86;

import Assembly.BasicBlock;
import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import SymbolTable.IntegerSymTabInfo;
import SymbolTable.SymTabInfo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by christophe on 11.06.14.
 */
public class BasicBlockToX86Generator {
    private StringBuilder prologue = new StringBuilder();
    private StringBuilder globl    = new StringBuilder();
    private StringBuilder program  = new StringBuilder();
    private ArrayList<BasicBlock> blocks;

    //Variables per block
    private int                         parameterOffset     = 8;  // first offset for parameter is 4.
    private int                         localVariableOffset = -4; // first offset for local variables is -4.
    private HashMap<SymTabInfo, String> variableAddresses   = new HashMap<SymTabInfo, String>();
    private String currentFunction = "";


    public BasicBlockToX86Generator(ArrayList<BasicBlock> program)
    {
        this.blocks = program;
    }

    public void Prologue() {
        prologue.append("\n" + ".section .data");
        prologue.append("\n" + ".section .text");
        prologue.append("\n" + ".globl _start");
    }

    private void Compile(BasicBlock block) {

        // If this block is the beginning of a new function,
        // we possibly need to end the previous one.
        CloseLastFunction(block);
        for (ThreeAddressCode tac : block.getTacs()) {
            program.append("\n  " + "#" + tac.toString());
            if (tac.getOpCode() == OpCodes.LABEL)
                PrintLabel(tac);
            if (tac.getOpCode() == OpCodes.PARAM) {
                program.append("\n\t" + String.format("movl %s, %%eax", PutAndGetAddress(tac.getArg1())));
                program.append("\n\t" + String.format("pushl %%eax"));
            }
            if(tac.getOpCode() == OpCodes.RETURN)
            {
                program.append("\n\t" + String.format("movl %s, %%eax", PutAndGetAddress(tac.getArg1())));
            }
            if (tac.getOpCode() == OpCodes.GETPARAM)
                PutParameterAddress(tac);
            if (tac.getOpCode() == OpCodes.CALL) {
                program.append("\n\t" + String.format("call %s", tac.getArg1()));
                program.append("\n\t" + String.format("addl $%d, %%esp", tac.getParamCount() * 4));
                if(HasNoAddress(tac.getResult()))
                {
                    program.append("\n\t" + String.format("pushl %%eax"));
                    PutAndGetAddress(tac.getResult());
                }
                else
                {
                    program.append("\n\t" + String.format("movl %%eax, %s", PutAndGetAddress(tac.getResult())));
                }
            }

            if (tac.getOpCode() == OpCodes.A0) {
                if(HasNoAddress(tac.getResult()))
                {
                    PutAndGetAddress(tac.getArg1());
                    PutAndGetAddress(tac.getResult());
                    program.append("\n\t" + String.format("movl %s, %s", PutAndGetAddress(tac.getArg1()), "%eax"));
                    program.append("\n\t" + String.format("pushl %s", "%eax"));
                }
                else
                {
                    PutAndGetAddress(tac.getArg1());
                    PutAndGetAddress(tac.getResult());
                    program.append("\n\t" + String.format("movl %s, %s", PutAndGetAddress(tac.getArg1()), "%eax"));
                    program.append("\n\t" + String.format("movl %s, %s", "%eax", PutAndGetAddress(tac.getResult())));
                }
            }
            if (tac.getOpCode() == OpCodes.A2PLUS || tac.getOpCode() == OpCodes.A2MINUS) {
                program.append("\n\t" + String.format("movl %s, %%eax", PutAndGetAddress(tac.getArg1())));
                program.append("\n\t" + String.format("movl %s, %%ebx", PutAndGetAddress(tac.getArg2())));
                program.append("\n\t" + String.format("%s %%ebx, %%eax", tac.getOpCode() == OpCodes.A2MINUS ? "subl" : "addl"));
                program.append("\n\t" + String.format("movl %%eax, %s", PutAndGetAddress(tac.getResult())));
            }
            if (tac.getOpCode() == OpCodes.A2DIV) {
                program.append("\n\t" + String.format("movl %s, %%eax", PutAndGetAddress(tac.getArg1())));
                program.append("\n\t" + String.format("movl %s, %%ebx", PutAndGetAddress(tac.getArg2())));
                program.append("\n\t" + String.format("idivl %%ebx"));
                program.append("\n\t" + String.format("movl %%eax, %s", PutAndGetAddress(tac.getResult())));
            }
        }

    }

    private boolean HasNoAddress(SymTabInfo result) {
        return this.variableAddresses.get(result) == null;
    }

    private void CloseLastFunction(BasicBlock block) {
        ThreeAddressCode firstInBlock = block.getTacs().get(0);

        if(!this.currentFunction.equals("") && firstInBlock.getOpCode() == OpCodes.LABEL)
        {
            String label = firstInBlock.getArg1().IdentifiertoString();
            if(label.contains("function"))
            {
                program.append("\n  " + "#function ending");
                program.append("\n" + currentFunction.replace("function", "end") + ":");
                program.append("\n\t" + "leave");
                program.append("\n\t" + "ret");
            }
        }
    }

    /**
     * Function labels need to be printed differently than
     * regular labels.
     *
     * Each function label needs to be printed in the .globl part as well.
     * @param tac
     */
    private void PrintLabel(ThreeAddressCode tac) {
        // Main is a special case.
        if (tac.getArg1().IdentifiertoString().equals("function_main")) {
            variableAddresses.clear();
            localVariableOffset = -4;
            parameterOffset = 8;
            program.append("\n" + "_start:");
            program.append("\n\t" + "movl %esp, %ebp");
            this.currentFunction = "";
        }
        else if (tac.getArg1().IdentifiertoString().contains("function")) {
            variableAddresses.clear();
            localVariableOffset = -4;
            parameterOffset = 8;
            String functionLabel = tac.getArg1().IdentifiertoString();
            program.append("\n" + String.format(".type %s, @function", functionLabel.replace("function_", "")));
            program.append("\n" + String.format(functionLabel.replace("function_", "") + ":"));
            globl.append("\n"   + String.format(".globl %s", functionLabel.replace("function_", "")));
            program.append("\t  # Function init");
            program.append("\n\tpushl %ebp");
            program.append("\n\tmovl %esp, %ebp");
            this.currentFunction = functionLabel;

        } else
            program.append("\n" + tac.getArg1().IdentifiertoString() + ":");
    }

    private void PutParameterAddress(ThreeAddressCode param) {
        variableAddresses.put(param.getArg1(), String.format("%d(%%ebp)", parameterOffset));
        parameterOffset += 4;
    }

    private String PutAndGetAddress(SymTabInfo variable) {
        if (variable instanceof IntegerSymTabInfo)
            return String.format("$%d", ((IntegerSymTabInfo) variable).value);

        if (variableAddresses.get(variable) == null) {
            variableAddresses.put(variable, String.format("%d(%%ebp)", localVariableOffset));
            localVariableOffset -= 4;
        }
        return variableAddresses.get(variable);

    }

    public String toString()
    {
        program = new StringBuilder();
        prologue = new StringBuilder();
        globl = new StringBuilder();

        Prologue();
        for(BasicBlock b : this.blocks) Compile(b);
        // Append epiloggue
        program.append("\n\t" + "movl %eax, %ebx");
        program.append("\n  # End of _start");
        program.append("\n\t" + "movl $1, %eax");
        program.append("\n\t" + "int $0x80");
        return prologue.toString() + globl.toString() + program.toString();
    }


}
