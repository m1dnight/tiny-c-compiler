package Assembly.x86;

import Assembly.BasicBlock;
import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import SymbolTable.*;
import Typing.Types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by christophe on 11.06.14.
 */
public class BasicBlockToX86Generator {
    private final SymbolTable scope;
    private StringBuilder prologue     = new StringBuilder();
    private StringBuilder data         = new StringBuilder();
    private StringBuilder globl        = new StringBuilder();
    private StringBuilder curCode      = new StringBuilder();
    private StringBuilder funcPrologue = new StringBuilder();
    private StringBuilder program      = new StringBuilder();
    private ArrayList<BasicBlock> blocks;

    //Variables per block
    private int                          parameterOffset     = 8;  // first offset for parameter is 4.
    private int                          localVariableOffset = -4; // first offset for local variables is -4.
    private HashMap<SymTabInfo, String>  variableAddresses   = new HashMap<SymTabInfo, String>();
    private HashMap<SymTabInfo, String>  globalAddresses     = new HashMap<SymTabInfo, String>();
    private HashMap<SymTabInfo, Integer> arrayOffset         = new HashMap<SymTabInfo, Integer>();
    private LinkedList<SymTabInfo>       parametersCurFunc   = new LinkedList<SymTabInfo>();
    private String                       currentFunction     = "";
    private int                          localVariableCount  = 0;


    public BasicBlockToX86Generator(ArrayList<BasicBlock> program, SymbolTable scope) {
        this.blocks = program;
        this.scope = scope;
    }

    public void Prologue() {
        data.append("\n" + ".section .data");

        prologue.append("\n" + "# Used for printing");
        prologue.append("\n" + "inpfnl: .string \"%d \\n\"");
        prologue.append("\n" + "inpf: .string \"%d \\n\"");
        prologue.append("\n" + "outr:    .string \"%d\"");
        prologue.append("\n" + "inpfc: .string \"%c\\n\"");
        prologue.append("\n" + "inpfcnl: .string \"%c \\n\"");
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
            if(op == OpCodes.A1MINUS)
                CompileUnaryMinus(tac);
            if (op == OpCodes.LABEL)
                CompileLabel(tac);
            if(op == OpCodes.GLOBL)
                DeclareGlobalVariable(tac);
            if(op == OpCodes.GLOBLARR)
                DeclareGlobalArray(tac);
            if(op == OpCodes.ALLOC_ARRAY)
                CompileArrayDeclaration(tac);
            if(op == OpCodes.AAS)
                CompileArrayAssignment(tac);
            if(op == OpCodes.AAC)
                CompileArrayAccess(tac);
            if (op == OpCodes.PARAM || op == OpCodes.PARAMTOCHAR)
                CompileParam(parameters, tac);
            if(op == OpCodes.RETURN || op == OpCodes.RETURNTOCHAR)
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
            if(op == OpCodes.A2LT  || op == OpCodes.A2GT    || op == OpCodes.A2EQ ||
               op == OpCodes.A2NEQ)
                CompileComparison(tac, op);
            if(op == OpCodes.GOTO)
                CompileJump(tac);

            if(op == OpCodes.WRITEINT || op == OpCodes.WRITEINTLN)
                CompileWriteInteger(tac);

            if(op == OpCodes.WRITECHAR || op == OpCodes.WRITECHARLN)
                CompileWriteChar(tac);
            if(op == OpCodes.READINT)
                CompileReadInteger(tac);
            if(op == OpCodes.A2EQIF  || op == OpCodes.A2GTIF || op == OpCodes.A2LTIF || op == OpCodes.A2NEQIF)
                CompileIfStatement(tac, op);
        }

    }

    private void CompileWriteChar(ThreeAddressCode tac) {
        // Get the char from
        String op =  tac.getOpCode() == OpCodes.WRITECHAR ? "$inpfc" : "$inpfcnl";
        curCode.append("\n\t"  + String.format("movl %s, %%ebx", PutAndGetAddress(tac.getArg1())));
        curCode.append("\n\t"  + String.format("movl $0, %%eax")); // Clear eax
        curCode.append("\n\t" + String.format("movb %%bl, %%al"));
        curCode.append("\n\t" + String.format("pushl %%eax"));
        curCode.append("\n\t" + String.format("pushl %s", op));
        curCode.append("\n\t" + String.format("call printf"));
        curCode.append("\n\t" + String.format("addl $4, %%esp"));
    }

    private void CompileUnaryMinus(ThreeAddressCode tac) {
        curCode.append("\n\t" + String.format("movl %s, %%eax", PutAndGetAddress(tac.getArg1())));
        curCode.append("\n\t" + String.format("negl %%eax"));
        curCode.append("\n\t" + String.format("movl %%eax, %s", PutAndGetAddress(tac.getResult())));
    }


    /******************************************************************************************************************/
    /************************************ INDIVIDUAL COMPILATION METHODS **********************************************/
    private void DeclareGlobalArray(ThreeAddressCode tac) {
        if(tac.getArg1().getTypeInfo().ActualType() == Types.INTEGER || tac.getArg1().getTypeInfo().ActualType() == Types.CHAR) {
            // This identifies a gobal variable so we have to add it to the prologue.
            String varname = "global_" + tac.getArg1().IdentifiertoString();
            data.append("\n" + String.format("%s: .long 0", varname));

            for(int i = 1; i < ((IntegerSymTabInfo)tac.getArg2()).getValue(); i++)
                data.append(", 0");
            // Add the address (this can't be done using the function PutAndGetAddress)
            this.globalAddresses.put(tac.getArg1(), varname);
        }
    }

    private void DeclareGlobalVariable(ThreeAddressCode tac) {
        if(tac.getArg1().getTypeInfo().ActualType() == Types.INTEGER || tac.getArg1().getTypeInfo().ActualType() == Types.CHAR) {
            // This identifies a gobal variable so we have to add it to the prologue.
            String varname = "global_" + tac.getArg1().IdentifiertoString();
            data.append("\n" + String.format("%s: .long 0", varname));
            // Add the address (this can't be done using the function PutAndGetAddress)
            this.globalAddresses.put(tac.getArg1(), varname);
        }
    }

    private void CompileIfStatement(ThreeAddressCode tac, OpCodes op) {
        String jumpOperator = "";
        // Determine the jumb operator to use.
        jumpOperator = op == OpCodes.A2EQIF ? "je"   :
                (op == OpCodes.A2GTIF ? "jg"  :
                        (op == OpCodes.A2NEQIF ? "jne" : "jl"));
        AddCodeLine(String.format("movl %s, %%ebx", PutAndGetAddress(tac.getArg1())), curCode);
        AddCodeLine(String.format("movl %s, %%eax", PutAndGetAddress(tac.getArg2())), curCode);
        AddCodeLine(String.format("cmpl %%eax, %%ebx"), curCode);
        AddCodeLine(String.format("%s %s", jumpOperator, tac.getResult().IdentifiertoString()), curCode);
    }

    private void CompileComparison(ThreeAddressCode tac, OpCodes op) {
        String jumpOperator = "";
        jumpOperator = op == OpCodes.A2LT ? "jl" : (op == OpCodes.A2GT ? "jg" : "je");

        AddCodeLine(String.format("movl %s, %%ebx", PutAndGetAddress(tac.getArg1())), curCode);
        AddCodeLine(String.format("movl %s, %%eax", PutAndGetAddress(tac.getArg2())), curCode);
        AddCodeLine(String.format("cmpl %%eax, %%ebx"), curCode);
        AddCodeLine(String.format("%s %s", jumpOperator, tac.getResult().IdentifiertoString()), curCode);
    }
    /******************************************************************************************************************/
    private void CompileDivisionAndTimes(ThreeAddressCode tac) {
        curCode.append("\n\t" + String.format("movl %s, %%eax", PutAndGetAddress(tac.getArg1())));
        curCode.append("\n\t" + String.format("movl %s, %%ebx", PutAndGetAddress(tac.getArg2())));
        curCode.append("\n\t" + String.format("%s %%ebx", tac.getOpCode() == OpCodes.A2DIV ? "idivl" : "imull"));
        CoerceToCharOrStore(tac);
        //curCode.append("\n\t" + String.format("movl %%eax, %s", PutAndGetAddress(tac.getResult())));
    }

    private void CompileSumAndSubstract(ThreeAddressCode tac) {
        curCode.append("\n\t" + String.format("movl %s, %%eax", PutAndGetAddress(tac.getArg1())));
        curCode.append("\n\t" + String.format("movl %s, %%ebx", PutAndGetAddress(tac.getArg2())));
        curCode.append("\n\t" + String.format("%s %%ebx, %%eax", tac.getOpCode() == OpCodes.A2MINUS ? "subl" : "addl"));

        // If the resulting variable is a CHAR we have to coerce the value
        //CoerceToChar(tac.getResult());
        CoerceToCharOrStore(tac);
    }

    private void CoerceToCharOrStore(ThreeAddressCode tac) {
        if(tac.getResult().getTypeInfo().ActualType() == Types.CHAR)
        {
            //curCode.append("\n\t" + String.format("movl %s, %%ebx", PutAndGetAddress(tac.getResult())));
            curCode.append("\n\t" + String.format("movl $0, %%ebx"));
            curCode.append("\n\t" + String.format("movb %%al, %%bl"));
            curCode.append("\n\t" + String.format("movl %%ebx, %s", PutAndGetAddress(tac.getResult())));
        }
        else
        {
            curCode.append("\n\t" + String.format("movl %%eax, %s", PutAndGetAddress(tac.getResult())));
        }
    }

    private void CompileAssignment(ThreeAddressCode tac) {
            // If we are assigning to a char, we have to make sure we trim it.
            if(tac.getResult().getTypeInfo().ActualType() == Types.CHAR && tac.getArg1().getTypeInfo().ActualType() == Types.INTEGER)
            {
                PutAndGetAddress(tac.getArg1());
                PutAndGetAddress(tac.getResult());
                // First store arg1 in %eax, then copy %al to %ebx, and then %ebx to the location
                curCode.append("\n\t" + String.format("movl %s, %%eax", PutAndGetAddress(tac.getArg1())));
                curCode.append("\n\t" + String.format("movl $0, %%ebx"));
                curCode.append("\n\t" + String.format("movb %%al, %%bl"));
                curCode.append("\n\t" + String.format("movl %s, %s", "%ebx", PutAndGetAddress(tac.getResult())));
            }
        else
            {
                PutAndGetAddress(tac.getArg1());
                PutAndGetAddress(tac.getResult());
                curCode.append("\n\t" + String.format("movl %s, %s", PutAndGetAddress(tac.getArg1()), "%eax"));
                curCode.append("\n\t" + String.format("movl %s, %s", "%eax", PutAndGetAddress(tac.getResult())));
            }

    }

    private void CompileFunctioncall(Stack<String> parameters, ThreeAddressCode tac) {
        curCode.append("\n\t# Parameters for function call");
        while(!parameters.isEmpty())
            curCode.append(parameters.pop());
        curCode.append("\n\t" + String.format("call %s", tac.getArg1()));
        if(tac.getParamCount() != 0) // No parameters == no scrubbing
            curCode.append("\n\t" + String.format("addl $%d, %%esp", tac.getParamCount() * 4));
        if(tac.getResult() != null) // No result, no movl
        curCode.append("\n\t" + String.format("movl %%eax, %s", PutAndGetAddress(tac.getResult())));
    }

    private void CompileReturn(ThreeAddressCode tac) {
        curCode.append("\n\t" + String.format("movl %s, %%eax", PutAndGetAddress(tac.getArg1())));
        // We know the returnvalue is in %eax, so we modify it there
        if(tac.getOpCode() == OpCodes.RETURNTOCHAR)
        {
            curCode.append("\n\t" + String.format("movl $0, %%ebx"));
            curCode.append("\n\t" + String.format("movb %%al, %%bl"));
            curCode.append("\n\t" + String.format("movl %%ebx, %%eax"));
        }
        // Jump to the ending of the function
        if(!currentFunction.equals(""))
        curCode.append("\n\t" + String.format("jmp end_%s", currentFunction.replace("function_", "")));
    }

    private void CompileParam(Stack<String> parameters, ThreeAddressCode tac) {
        // If we push the address of an array on the stack as parameters, we have to calculate it first
        if(this.globalAddresses.containsKey(tac.getArg1()))
        {
            parameters.push("\n\t" + String.format("pushl %s", globalAddresses.get(tac.getArg1())));
        }
        else
        if(tac.getArg1() instanceof ArraySymTabInfo)
        {
            int ebpOffset = arrayOffset.get(tac.getArg1());
            parameters.push("\n\t" + String.format("pushl %%eax"));
            parameters.push("\n\t" + String.format("subl %%ebx, %%eax"));
            parameters.push("\n\t" + String.format("movl $%d, %%ebx", ebpOffset));
            parameters.push("\n\t" + String.format("movl %%ebp, %%eax"));
            parameters.push("\n\t" + "# Calculate base address for array");

        }
        else {
            // Push strings in *reverse* order on the stack.

            if(tac.getOpCode() == OpCodes.PARAMTOCHAR)
            {
                // If we need to coerce, do that first and put the result back in %eax
                parameters.push("\n\t" + String.format("pushl %%eax"));
                parameters.push("\n\t" + String.format("movb %%bl, %%al"));
                parameters.push("\n\t" + String.format("movl $0, %%eax"));
                parameters.push("\n\t" + String.format("movl %s, %%ebx", PutAndGetAddress(tac.getArg1())));
                parameters.push("\n\t" + String.format("# Coerce the parameter to CHAR"));

            }
            else
            {
                parameters.push("\n\t" + String.format("pushl %%eax"));
                parameters.push("\n\t" + String.format("movl %s, %%eax", PutAndGetAddress(tac.getArg1())));
            }
        }
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

            funcPrologue.append("\n" + "_start:");
            funcPrologue.append("\n\t" + "movl %esp, %ebp");
            this.currentFunction = tac.getArg1().IdentifiertoString();
        }
        else if (tac.getArg1().IdentifiertoString().contains("function")) {
            // Push previous code to program
            program.append("\n" + curCode.toString());
            curCode = new StringBuilder();

            // Reset the scope variables
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

    private void CompileWriteInteger(ThreeAddressCode tac) {
        String op =  tac.getOpCode() == OpCodes.WRITEINT ? "$inpf" : "$inpfln";
        curCode.append("\n\t"  + String.format("movl %s, %%eax", PutAndGetAddress(tac.getArg1())));
        curCode.append("\n\t" + String.format("pushl %%eax"));
        curCode.append("\n\t" + String.format("pushl %s", op));
        curCode.append("\n\t" + String.format("call printf"));
        curCode.append("\n\t" + String.format("addl $4, %%esp"));
    }

    private void CompileReadInteger(ThreeAddressCode tac) {
        curCode.append("\n\t"  + String.format("subl $4, %%esp"));
        curCode.append("\n\t" + String.format("pushl %%esp"));
        curCode.append("\n\t" + String.format("pushl $outr"));
        curCode.append("\n\t" + String.format("call scanf"));
        curCode.append("\n\t" + String.format("addl $12, %%esp"));
        curCode.append("\n\t"  + String.format("movl -4(%%esp), %%eax"));
        curCode.append("\n\t"  + String.format("movl %%eax, %s", PutAndGetAddress(tac.getArg1())));
    }

    private void CompileArrayAssignment(ThreeAddressCode tac) {
        //result = name of array
        //arg1 = index of array
        //arg2 = value
        ArraySymTabInfo array = (ArraySymTabInfo) ((ArrayIndexSymTabInfo) tac.getResult()).getArray();
        if(this.globalAddresses.containsKey(tac.getResult()) && (tac.getResult().getTypeInfo().ActualType() == Types.INTEGER || tac.getResult().getTypeInfo().ActualType() == Types.CHAR))
        {
            // Again, check if we have to coerce the assignment
            curCode.append("\n\t" + String.format("movl %s, %%edi", PutAndGetAddress(tac.getArg1()))); // Move index to edi
            curCode.append("\n\t" + String.format("movl %s, %%eax", PutAndGetAddress(tac.getArg2()))); // Move value into eax
            if(tac.getResult().getTypeInfo().ActualType() == Types.CHAR)
            {
                //curCode.append("\n\t" + String.format("movl %s, %%ebx", PutAndGetAddress(tac.getArg1())));
                curCode.append("\n\t" + String.format("movl $0, %%ebx"));
                curCode.append("\n\t" + String.format("movb %%al, %%bl"));
                curCode.append("\n\t" + String.format("movl %%ebx, %s(,%%edi, 4)", globalAddresses.get(tac.getResult())));
            }
            else {
                curCode.append("\n\t" + String.format("movl %%eax, %s(,%%edi, 4)", globalAddresses.get(tac.getResult())));
            }
        }
        else if(!IsParameter(array))
        {
            // If it is not a parameter, the array offset wrt %ebp should be stored
            int arrayOffset = -1 * this.arrayOffset.get(tac.getResult());
            //arrayOffset -= (4 * Integer.parseInt(tac.getArg1().IdentifiertoString()));


            curCode.append("\n\t" + String.format("movl %%ebp, %%ecx"));
            curCode.append("\n\t" + String.format("movl $%d, %%eax", Math.abs(arrayOffset)));
            curCode.append("\n\t" + String.format("subl %%eax, %%ecx"));// Array base address is in %ecx

            curCode.append("\n\t" + String.format("movl %s, %%eax", PutAndGetAddress(tac.getArg1())));
            curCode.append("\n\t" + String.format("movl $4, %%ebx"));
            curCode.append("\n\t" + String.format("imull %%ebx")); // Offset is in %eax

            curCode.append("\n\t" + String.format("subl %%eax, %%ecx")); // Actual ebp offset is now in %ecx

            curCode.append("\n\t" + String.format("movl %s, %%eax", PutAndGetAddress(tac.getArg2())));
            curCode.append("\n\t" + String.format("movl %%eax, (%%ecx)"));
        }
        else
        {
            // We are assigning to an array that is a parameter.
            // This means the address is located on the stack.
            String baseAddress = PutAndGetAddress(array);
            //curCode.append("\n\t" + String.format("movl %s, %%ebx", baseAddress));
            // Move the base address to %eax and move the size for each cell to %%ebx
            curCode.append("\n\t" + String.format("movl %s, %%eax", PutAndGetAddress(tac.getArg1())));
            curCode.append("\n\t" + String.format("movl $4, %%ebx"));
            curCode.append("\n\t" + String.format("imull %%ebx")); // Address offset for address is in %eax
            curCode.append("\n\t" + String.format("movl %s, %%ebx", baseAddress)); // Load actual address
            curCode.append("\n\t" + String.format("subl %%eax, %%ebx")); // Actual address is in %eax
            curCode.append("\n\t" + String.format("movl %s, %%eax", PutAndGetAddress(tac.getArg2())));
            curCode.append("\n\t" + String.format("movl %%eax, (%%ebx)"));
        }
    }

    private void CompileArrayAccess(ThreeAddressCode tac) {
        // arg1 = array
        // arg2 = index
        // result = variable..
        // If our array is a parameter the address will be stores in our PutAndGetAddress()
        // Otherwise it is defined in our scope and we have to calculate it.
        if(IsParameter(tac.getArg1()))
        {
            // Determine the address for the array
            // If we are dealing with a parameter array, GetPutAddress wil give us the base
            //int ebpOffset = arrayOffset.get(tac.getArg1());
            String base = PutAndGetAddress(tac.getArg1());
            curCode.append("\n\t" + String.format("# Calculate offset"));
            // We have a parameter, so the base address is our parameter (located at address of getArg2())
            curCode.append("\n\t" + String.format("movl %s, %s", PutAndGetAddress(tac.getArg2()), "%eax")); // Move offset in register
            curCode.append("\n\t" + String.format("movl $4, %%ebx"));
            curCode.append("\n\t" + String.format("imull %%ebx")); // Offset is in %eax

            curCode.append("\n\t" + String.format("# Calculate address"));
            curCode.append("\n\t" + String.format("movl %s, %%ebx", base)); // Move base address in register
            curCode.append("\n\t" + String.format("subl %%eax, %%ebx")); // Calculate address on offset to %eax

            curCode.append("\n\t" + String.format("# Get value from address"));
            curCode.append("\n\t" + String.format("movl (%%ebx), %%eax")); // Move actual value to %eax
            curCode.append("\n\t" + String.format("movl %%eax, %s", PutAndGetAddress(tac.getResult())));
        }
        else if(this.globalAddresses.containsKey(tac.getArg1()) && (tac.getResult().getTypeInfo().ActualType() == Types.INTEGER || tac.getResult().getTypeInfo().ActualType() == Types.CHAR))
        {
            // Create the index in %edi
            curCode.append("\n\t" + String.format("movl %s,%%edi", PutAndGetAddress(tac.getArg2())));
            // Move the value to the a register
            curCode.append("\n\t" + String.format("movl %s(,%%edi, 4), %%eax", globalAddresses.get(tac.getArg1())));
            curCode.append("\n\t" + String.format("movl %%eax, %s", PutAndGetAddress(tac.getResult())));
        }
        // We are not dealing with a parameter. The address for the array is thus on our stack.
        else
        {

            // Calculate the offset for the address
            int arrayOffset = this.arrayOffset.get(tac.getArg1());

            // Move the value of the index to %eax
            curCode.append("\n\t" + String.format("movl %s, %%eax", PutAndGetAddress(tac.getArg2())));
            curCode.append("\n\t" + String.format("movl $4,%%ebx"));
            curCode.append("\n\t" + String.format("imull %%ebx")); // The total offset is now in %eax
            curCode.append("\n\t" + String.format("movl %%ebp, %%ebx"));
            curCode.append("\n\t" + String.format("subl $%d, %%ebx", arrayOffset)); // Base address is now in %edx
            curCode.append("\n\t" + String.format("subl %%eax, %%ebx"));// The actual address is now in %edx
            curCode.append("\n\t" + String.format("movl (%%ebx), %%eax", PutAndGetAddress(tac.getResult())));
            curCode.append("\n\t" + String.format("movl %%eax, %s", PutAndGetAddress(tac.getResult())));
        }
    }

    private void CompileArrayDeclaration(ThreeAddressCode tac) {
        // Store the array %ebp offset, which is currently equal to the stackpointer.
        // The stackpointer is stores in localVariableOffset
        arrayOffset.put(tac.getArg1(), Math.abs(localVariableOffset));

/*        curCode.append("\n\t" + String.format("# Add offset for size of array to alloc"));
        curCode.append("\n\t" + String.format("movl %s, %%eax", PutAndGetAddress(tac.getArg2())));
        curCode.append("\n\t" + String.format("movl $4, %%ebx"));
        curCode.append("\n\t" + String.format("imull %%ebx")); // Actual size required on stack is now in %eax
        curCode.append("\n\t" + String.format("subl %%eax, %%esp"));*/
        // Push the address for this array
        // Address for this array is %ebp minus the
        // We have created an array, so we have to increase our local variable counter as well
        this.localVariableCount  +=  (Integer.parseInt(tac.getArg2().IdentifiertoString()));
        this.localVariableOffset -= 4 * (Integer.parseInt(tac.getArg2().IdentifiertoString()));
    }

    private void CompileJump(ThreeAddressCode tac) {
        AddCodeLine(String.format("jmp %s", tac.getArg1().IdentifiertoString()), curCode);
    }

    /******************************************************************************************************************/
    /************************************ ADDRESSES *******************************************************************/
    /******************************************************************************************************************/
    private void PutParameterAddress(ThreeAddressCode param) {
        this.parametersCurFunc.add(param.getArg1());
        //if(param.getArg1() instanceof ArraySymTabInfo)
        variableAddresses.put(param.getArg1(), String.format("%d(%%ebp)", parameterOffset));
        parameterOffset += 4;
    }

    private String PutAndGetAddress(SymTabInfo variable) {
        // If the variable is an integer, the address is simply $<value>

        // Check to see if it is a global parameter
        if(globalAddresses.containsKey(variable))
        {
            // If we have an array address we have to construct the offset

            return globalAddresses.get(variable);
        }
        if (variable instanceof IntegerSymTabInfo)
            return String.format("$%d", ((IntegerSymTabInfo) variable).getValue());
        // If we are dealing with a variable we have to store the variable offset (atm)
        // E.g., the address string should be like:
        // movl %ebp, %eax
        // subl $<offset>, %eax
        // Now the address is in %eax
        if(variable instanceof ArraySymTabInfo)
        {
            if (variableAddresses.get(variable) == null) {
                localVariableCount++;
                variableAddresses.put(variable, String.format("%d(%%ebp)", localVariableOffset));
                localVariableOffset -= 4;
            }
        }
        if(variable instanceof CharSymTabInfo)
        {
            return String.format("$%d", (int)((CharSymTabInfo) variable).getValue());
        }
        if (variableAddresses.get(variable) == null) {
            localVariableCount++;
            variableAddresses.put(variable, String.format("%d(%%ebp)", localVariableOffset));
            localVariableOffset -= 4;
        }
        return variableAddresses.get(variable);

    }

    /******************************************************************************************************************/
    /************************************ HELPER FUNCTIONS ************************************************************/

    private boolean IsParameter(SymTabInfo var)
    {
        return this.parametersCurFunc.contains(var);
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
                // Add the stack space free code in the prologue of the previous function
                funcPrologue.append("\n  # " + localVariableCount + " local variables required. Ergo, " + localVariableCount * 4 + " space on the stack.");
                funcPrologue.append(String.format("\n\t" + "subl $%d, %%esp", localVariableCount * 4));

                // Paste the body of the function after the prologue
                funcPrologue.append(curCode.toString());

                // Put it back as our current code
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
        data     = new StringBuilder();
        funcPrologue = new StringBuilder();
        parameterOffset     = 8;  // first offset for parameter is 4.
        localVariableOffset = -4; // first offset for local variables is -4.
        variableAddresses   = new HashMap<SymTabInfo, String>();
        currentFunction     = "";
        localVariableCount  = 0;

        // Generate the prologue of the program.
        Prologue();

        // Compile each basic block.
        for(BasicBlock bl : this.blocks)
        {
            if(bl.getTacs() != null && bl.getTacs().size() > 0)
                Compile(bl);
        }

        // Append epilogue.
        //curCode.append("\n" + curCode.toString());
        // curCode contains the body of main
        curCode.append("\n" + "end_main:");
        curCode.append("\n\t" + "movl %eax, %ebx");
        curCode.append("\n  # End of _start");
        curCode.append("\n\t" + "movl $1, %eax");
        curCode.append("\n\t" + "int $0x80");
        // funcPrologue contains the prologue of the main function
        funcPrologue.append("\n  # " + localVariableCount + " local variables required. Ergo, " + localVariableCount * 4 + " space on the stack.");
        funcPrologue.append(String.format("\n\t" + "subl $%d, %%esp", localVariableCount * 4));

        // Paste the body of the main after it's prologue
        funcPrologue.append(curCode.toString());

        // Paste the entire mess after the current program
        program.append("\n" + funcPrologue.toString());
        // Return the code.
        return data.toString() + prologue.toString() + globl.toString() + program.toString();
    }


}
