package Assembly.x86;

import Assembly.BasicBlock;
import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import SymbolTable.IntegerSymTabInfo;
import SymbolTable.SymTabInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created by christophe on 11.06.14.
 */
public class BasicBlockToX86Generator {
    private StringBuilder prologue     = new StringBuilder();
    private StringBuilder globl        = new StringBuilder();
    private StringBuilder curCode      = new StringBuilder();
    private StringBuilder funcPrologue = new StringBuilder();
    private StringBuilder program      = new StringBuilder();
    private ArrayList<BasicBlock> blocks;

    //Variables per block
    private int                         parameterOffset     = 8;  // first offset for parameter is 4.
    private int                         localVariableOffset = -4; // first offset for local variables is -4.
    private HashMap<SymTabInfo, String> variableAddresses   = new HashMap<SymTabInfo, String>();
    private String                      currentFunction     = "";
    private int                         localVariableCount  = 0;


    public BasicBlockToX86Generator(ArrayList<BasicBlock> program) {
        this.blocks = program;
    }

    public void Prologue() {
        prologue.append("\n" + ".section .data");
        prologue.append("\n" + ".section .text");
        prologue.append("\n" + ".globl _start");
    }

    private void Compile(BasicBlock block) {
        Stack<String> parameters = new Stack<String>();

        // If this block is the beginning of a new function,
        // we possibly need to end the previous one.
        CloseLastFunction(block);
        for (ThreeAddressCode tac : block.getTacs()) {
            // Print out original TAC as comment.
            curCode.append("\n  " + "#" + tac.toString());
            OpCodes op = tac.getOpCode();
            if (op == OpCodes.LABEL)
                CompileLabel(tac);

            if (op == OpCodes.PARAM)
                CompileParam(parameters, tac);

            if(op == OpCodes.RETURN)
                CompileReturn(tac);

            if (op == OpCodes.GETPARAM)
                PutParameterAddress(tac);

            if (op == OpCodes.CALL)
                CompileFunctioncall(parameters, tac);

            if (op == OpCodes.A0)
                CompileAssignment(tac);

            if (op == OpCodes.A2PLUS || op == OpCodes.A2MINUS)
                CompileSumAndSubstract(tac);

            if (op == OpCodes.A2DIV || op == OpCodes.A2TIMES)
                CompileDivisionAndTimes(tac);

            if(op == OpCodes.A2LT || op == OpCodes.A2GT || op == OpCodes.A2EQ || op == OpCodes.A2NEQ || op == OpCodes.A2EQIF)
                CompileComparison(tac, op);

            if(op == OpCodes.GOTO)
                CompileJump(tac);

            if(op == OpCodes.AAS)
            {

            }
        }

    }

    private void CompileJump(ThreeAddressCode tac) {
        AddCodeLine(String.format("jmp %s", tac.getArg1().IdentifiertoString()), curCode);
    }

    private void CompileComparison(ThreeAddressCode tac, OpCodes op) {
        String jumpOperator = "";
        jumpOperator = op == OpCodes.A2LT ? "jl" : (op == OpCodes.A2GT ? "jg" : "je");

        AddCodeLine(String.format("movl %s, %%eax", PutAndGetAddress(tac.getArg1())), curCode);
        AddCodeLine(String.format("movl %s, %%ebx", PutAndGetAddress(tac.getArg2())), curCode);
        AddCodeLine(String.format("cmpl %%eax, %%ebx"), curCode);
        AddCodeLine(String.format("%s %s", jumpOperator, tac.getResult().IdentifiertoString()), curCode);
    }

    /******************************************************************************************************************/
    /************************************ INDIVIDUAL COMPILATION METHODS **********************************************/
    /******************************************************************************************************************/
    private void CompileDivisionAndTimes(ThreeAddressCode tac) {
        curCode.append("\n\t" + String.format("movl %s, %%eax", PutAndGetAddress(tac.getArg1())));
        curCode.append("\n\t" + String.format("movl %s, %%ebx", PutAndGetAddress(tac.getArg2())));
        curCode.append("\n\t" + String.format("%s %%ebx", tac.getOpCode() == OpCodes.A2DIV ? "idivl" : "imull"));
        curCode.append("\n\t" + String.format("movl %%eax, %s", PutAndGetAddress(tac.getResult())));
    }

    private void CompileSumAndSubstract(ThreeAddressCode tac) {
        curCode.append("\n\t" + String.format("movl %s, %%eax", PutAndGetAddress(tac.getArg1())));
        curCode.append("\n\t" + String.format("movl %s, %%ebx", PutAndGetAddress(tac.getArg2())));
        curCode.append("\n\t" + String.format("%s %%ebx, %%eax", tac.getOpCode() == OpCodes.A2MINUS ? "subl" : "addl"));
        curCode.append("\n\t" + String.format("movl %%eax, %s", PutAndGetAddress(tac.getResult())));
    }

    private void CompileAssignment(ThreeAddressCode tac) {
            PutAndGetAddress(tac.getArg1());
            PutAndGetAddress(tac.getResult());
            curCode.append("\n\t" + String.format("movl %s, %s", PutAndGetAddress(tac.getArg1()), "%eax"));
            curCode.append("\n\t" + String.format("movl %s, %s", "%eax", PutAndGetAddress(tac.getResult())));
    }

    private void CompileFunctioncall(Stack<String> parameters, ThreeAddressCode tac) {
        while(!parameters.isEmpty())
            curCode.append(parameters.pop());
        curCode.append("\n\t" + String.format("call %s", tac.getArg1()));
        curCode.append("\n\t" + String.format("addl $%d, %%esp", tac.getParamCount() * 4));
        curCode.append("\n\t" + String.format("movl %%eax, %s", PutAndGetAddress(tac.getResult())));
    }

    private void CompileReturn(ThreeAddressCode tac) {
        curCode.append("\n\t" + String.format("movl %s, %%eax", PutAndGetAddress(tac.getArg1())));
        // Jump to the ending of the function
        if(!currentFunction.equals(""))
        curCode.append("\n\t" + String.format("jmp end_%s", currentFunction.replace("function_", "")));
    }

    private void CompileParam(Stack<String> parameters, ThreeAddressCode tac) {
        // Push strings in reverse order on the stack.
        parameters.push("\n\t" + String.format("pushl %%eax"));
        parameters.push("\n\t" + String.format("movl %s, %%eax", PutAndGetAddress(tac.getArg1())));
        //curCode.append("\n\t" + String.format("movl %s, %%eax", PutAndGetAddress(tac.getArg1())));
        //curCode.append("\n\t" + String.format("pushl %%eax"));
    }

    private void CompileLabel(ThreeAddressCode tac) {
        // Main is a special case.
        if (tac.getArg1().IdentifiertoString().equals("function_main")) {
            program.append("\n" + curCode.toString());
            curCode = new StringBuilder();

            currentFunction = tac.getArg1().IdentifiertoString();
            variableAddresses.clear();
            localVariableOffset = -4;
            parameterOffset = 8;

            curCode.append("\n" + "_start:");
            curCode.append("\n\t" + "movl %esp, %ebp");
            this.currentFunction = "";
        }
        else if (tac.getArg1().IdentifiertoString().contains("function")) {
            // Push previous code to program
            program.append("\n" + curCode.toString());
            curCode = new StringBuilder();

            variableAddresses.clear();
            localVariableOffset = -4;
            parameterOffset = 8;
            String functionLabel = tac.getArg1().IdentifiertoString();
            funcPrologue.append("\n" + String.format(".type %s, @function", functionLabel.replace("function_", "")));
            funcPrologue.append("\n" + String.format(functionLabel.replace("function_", "") + ":"));
            globl.append("\n"   + String.format(".globl %s", functionLabel.replace("function_", "")));
            funcPrologue.append("\n  # Function init");
            funcPrologue.append("\n\tpushl %ebp");
            funcPrologue.append("\n\tmovl %esp, %ebp");
            this.currentFunction = functionLabel;

        } else
            curCode.append("\n\n\t" + tac.getArg1().IdentifiertoString() + ":");
    }


    /******************************************************************************************************************/
    /************************************ ADDRESSES *******************************************************************/
    /******************************************************************************************************************/
    private boolean HasNoAddress(SymTabInfo result) {
        return this.variableAddresses.get(result) == null;
    }

    /******************************************************************************************************************/
    /************************************ HELPER FUNCTIONS ************************************************************/
    /******************************************************************************************************************/
    private void PutParameterAddress(ThreeAddressCode param) {
        variableAddresses.put(param.getArg1(), String.format("%d(%%ebp)", parameterOffset));
        parameterOffset += 4;
    }

    private String PutAndGetAddress(SymTabInfo variable) {
        if (variable instanceof IntegerSymTabInfo)
            return String.format("$%d", ((IntegerSymTabInfo) variable).value);

        if (variableAddresses.get(variable) == null) {
            localVariableCount++;
            variableAddresses.put(variable, String.format("%d(%%ebp)", localVariableOffset));
            localVariableOffset -= 4;
        }
        return variableAddresses.get(variable);

    }

    private void AddCodeLine(String code, StringBuilder sb)
    {
        sb.append("\n\t" + code);
    }

    private void CloseLastFunction(BasicBlock block) {
        ThreeAddressCode firstInBlock = block.getTacs().get(0);

        if(!this.currentFunction.equals("") && firstInBlock.getOpCode() == OpCodes.LABEL)
        {
            // If the new basic block is the beginning of a new function.
            String label = firstInBlock.getArg1().IdentifiertoString();
            if(label.contains("function"))
            {
                // Cur code contains the code for our previous functions' body.
                curCode.append("\n  " + "#function ending");
                curCode.append("\n" + currentFunction.replace("function", "end") + ":");
                curCode.append("\n\t" + "leave");
                curCode.append("\n\t" + "ret");
                funcPrologue.append("\n  # " + localVariableCount + " local variables required. Ergo, " + localVariableCount * 4 + " space on the stack.");
                funcPrologue.append(String.format("\n\t" + "subl $%d, %%esp", localVariableCount * 4));
                // Prepend the prologue to the body.
                funcPrologue.append(curCode.toString());
                curCode = funcPrologue;
                funcPrologue = new StringBuilder();
                localVariableCount = 0;
                //TODO CLEAR?
            }

        }
    }
    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    public String getCurCode()
    {
        // Clear previous compilation.
        curCode  = new StringBuilder();
        prologue = new StringBuilder();
        globl    = new StringBuilder();
        program  = new StringBuilder();
        parameterOffset     = 8;  // first offset for parameter is 4.
        localVariableOffset = -4; // first offset for local variables is -4.
        variableAddresses   = new HashMap<SymTabInfo, String>();
        currentFunction     = "";
        localVariableCount  = 0;

        // Generate the prologue of the program.
        Prologue();

        // Compile each basic block.
        for(BasicBlock b : this.blocks) Compile(b);

        // Append epilogue.
        program.append("\n" + curCode.toString());
        program.append("\n\t" + "movl %eax, %ebx");
        program.append("\n  # End of _start");
        program.append("\n\t" + "movl $1, %eax");
        program.append("\n\t" + "int $0x80");

        // Return the code.
        return prologue.toString() + globl.toString() + program.toString();
    }


}
