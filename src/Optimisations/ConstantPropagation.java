package Optimisations;

import Assembly.BasicBlock;
import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import SymbolTable.IntegerSymTabInfo;
import SymbolTable.SymTabInfo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by christophe on 09.06.14.
 */
public class ConstantPropagation
{
    public static BasicBlock Optimize(BasicBlock block)
    {
        ArrayList<ThreeAddressCode>  newTacs         = new ArrayList<ThreeAddressCode>();
        HashMap<SymTabInfo, Boolean> constantMarkers = new HashMap<SymTabInfo, Boolean>();
        HashMap<SymTabInfo, IntegerSymTabInfo> variableValues  = new HashMap<SymTabInfo, IntegerSymTabInfo>();
        boolean changed = false;
        do
        {
            changed = false;
            for(ThreeAddressCode tac : block.getTacs())
            {
                // Check to see if we have a constant assignment
                // A = 20;
                if(IsConstantAssignment(tac))
                {
                    // We know for sure the operand is a constant, so we can add this variable as true.
                    constantMarkers.put(tac.getResult(), true);

                    // Cast is clean because we have checked the type in IsConstantAssignment().
                    variableValues.put(tac.getResult(), (IntegerSymTabInfo) tac.getArg1());
                    // Add the unchanged TAC.
                    newTacs.add(tac);
                    continue;
                }
                // Check to see if we are assigning a variable that is a known constant.
                if(IsConstantVariableAssignment(tac, constantMarkers))
                {
                    // Fetch the constant value
                    IntegerSymTabInfo constant = variableValues.get(tac.getArg1());
                    variableValues.put(tac.getResult(), constant);
                    // Create the new TAC that will assign the constant.
                    ThreeAddressCode newTac = new ThreeAddressCode(tac.getOpCode(), constant, null, tac.getResult());
                    // Add the new TAC instead of the old one.
                    newTacs.add(newTac);
                    changed = true;
                    // Mark the resulting variable as constant.
                    constantMarkers.put(tac.getResult(), true);
                    continue;
                }
                // Simple arithmetic operations can be checked and evaluated as well.
                // First we check if we can even optimize. Check if any of both operands
                // are known constants. If so, we should replace them.
                if(IsOptimizableArithmetic(tac, constantMarkers))
                {
                    // Find out which variable is optimizable
                    ThreeAddressCode newTac = new ThreeAddressCode();
                    if(constantMarkers.get(tac.getArg1()) != null && constantMarkers.get(tac.getArg1()))
                        // The first operand can be optimized, so we fetch the constant value.
                        newTac.setArg1(variableValues.get(tac.getArg1()));
                    else
                        newTac.setArg1(tac.getArg1());
                    if(constantMarkers.get(tac.getArg2()) != null && constantMarkers.get(tac.getArg2()))
                        // The first operand can be optimized as well, so fetch the constant value too.
                        newTac.setArg2(variableValues.get(tac.getArg2()));
                    else
                        newTac.setArg2(tac.getArg2());

                    // We have not evaluated the code yet, so we have to put the resulting variable as not constant.
                    constantMarkers.put(tac.getResult(), false);
                    variableValues.remove(tac.getResult());
                    newTac.setOpCode(tac.getOpCode());
                    newTac.setResult(tac.getResult());
                    newTacs.add(newTac);
                    changed = true;
                    continue;
                }
                // If we have a param TAC we can see if we can replace it with a variable
/*                if(IsOptimizableParam(tac, constantMarkers))
                {
                    ThreeAddressCode newTac = new ThreeAddressCode();
                    newTac.setOpCode(OpCodes.PARAM);
                    newTac.setArg1(variableValues.get(tac.getArg1()));
                    changed = true;
                    newTacs.add(newTac);
                    continue;
                }*/
                if(OptimizableArithmeticOperation(tac))
                {
                    IntegerSymTabInfo op1 = (IntegerSymTabInfo) tac.getArg1();
                    IntegerSymTabInfo op2 = (IntegerSymTabInfo) tac.getArg2();
                    ThreeAddressCode newTac = new ThreeAddressCode();
                    newTac.setOpCode(OpCodes.A0);
                    newTac.setResult(tac.getResult());

                    // Calculate new value
                    if(tac.getOpCode() == OpCodes.A2PLUS)
                        newTac.setArg1(new IntegerSymTabInfo(op1.getValue() + op2.getValue()));
                    if(tac.getOpCode() == OpCodes.A2MINUS)
                        newTac.setArg1(new IntegerSymTabInfo(op1.getValue() - op2.getValue()));
                    if(tac.getOpCode() == OpCodes.A2TIMES)
                        newTac.setArg1(new IntegerSymTabInfo(op1.getValue() * op2.getValue()));
                    if(tac.getOpCode() == OpCodes.A2DIV)
                        newTac.setArg1(new IntegerSymTabInfo(op1.getValue() / op2.getValue()));
                    if(tac.getOpCode() == OpCodes.A2EQ)
                        newTac.setArg1(new IntegerSymTabInfo(op1.getValue() == op2.getValue() ? 1 : 0));
                    if(tac.getOpCode() == OpCodes.A2NEQ)
                        newTac.setArg1(new IntegerSymTabInfo(op1.getValue() != op2.getValue() ? 1 : 0));
                    if(tac.getOpCode() == OpCodes.A2GT)
                        newTac.setArg1(new IntegerSymTabInfo(op1.getValue() > op2.getValue() ? 1 : 0));
                    if(tac.getOpCode() == OpCodes.A2LT)
                        newTac.setArg1(new IntegerSymTabInfo(op1.getValue() < op2.getValue() ? 1 : 0));

                    // We have evaluated an expression, so we need to put the constant vlaue
                    variableValues.put(newTac.getResult(), (IntegerSymTabInfo) newTac.getArg1());
                    newTacs.add(newTac);
                    changed = true;
                    continue;
                }
                newTacs.add(tac);
            }
            block.setTacs((ArrayList<ThreeAddressCode>) newTacs.clone());
            newTacs.clear();
            constantMarkers.clear();
            variableValues.clear();

        }
        while(changed);


        return block;
    }

    private static boolean OptimizableArithmeticOperation(ThreeAddressCode tac)
    {
        if (tac.getOpCode() == OpCodes.A2PLUS || tac.getOpCode() == OpCodes.A2TIMES ||
                tac.getOpCode() == OpCodes.A2MINUS || tac.getOpCode() == OpCodes.A2DIV ||
                tac.getOpCode() == OpCodes.A2NEQ || tac.getOpCode() == OpCodes.A2EQ ||
                tac.getOpCode() == OpCodes.A2GT || tac.getOpCode() == OpCodes.A2LT)
            return tac.getArg1() instanceof IntegerSymTabInfo && tac.getArg2() instanceof IntegerSymTabInfo;
        return false;
    }

    private static boolean IsOptimizableParam(ThreeAddressCode tac, HashMap<SymTabInfo, Boolean> constantMarkers)
    {
        if(tac.getOpCode() == OpCodes.PARAM)
        {
            return constantMarkers.get(tac.getArg1()) != null && constantMarkers.get(tac.getArg1());
        }
        return false;
    }

    private static boolean IsOptimizableArithmetic(ThreeAddressCode tac, HashMap<SymTabInfo, Boolean> constantMarkers)
    {
        if (tac.getOpCode() == OpCodes.A2PLUS      || tac.getOpCode() == OpCodes.A2TIMES ||
                tac.getOpCode() == OpCodes.A2MINUS || tac.getOpCode() == OpCodes.A2DIV   ||
                tac.getOpCode() == OpCodes.A2NEQ   || tac.getOpCode() == OpCodes.A2EQ    ||
                tac.getOpCode() == OpCodes.A2GT    || tac.getOpCode() == OpCodes.A2LT      )
        {
            return (constantMarkers.get(tac.getArg1()) != null && constantMarkers.get(tac.getArg1())) ||
                   (constantMarkers.get(tac.getArg2()) != null && constantMarkers.get(tac.getArg2()));
        }

        return false;
    }

    private static boolean IsConstantAssignment(ThreeAddressCode tac)
    {
        // If it is of the form A = 1 it is obviously true.
        if(tac.getOpCode() == OpCodes.A0 && tac.getArg1() instanceof IntegerSymTabInfo)
            return true;
        return false;

    }
    private static boolean IsConstantVariableAssignment(ThreeAddressCode tac, HashMap<SymTabInfo, Boolean> constantMarkers)
    {
        // Make sure it is a single assignment
        // A = B
        if(tac.getOpCode() == OpCodes.A0)
        {
            return constantMarkers.get(tac.getArg1()) != null && constantMarkers.get(tac.getArg1());
        }
        return false;
    }

    private boolean IsInteger(SymTabInfo sti)
    {
        if(sti instanceof IntegerSymTabInfo) return true;
        return false;
    }
}
