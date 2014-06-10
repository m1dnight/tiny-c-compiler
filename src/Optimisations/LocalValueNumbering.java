package Optimisations;

import Assembly.BasicBlock;
import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import SymbolTable.IntegerSymTabInfo;
import SymbolTable.SymTabInfo;
import Utils.MyBiMap;

/**
 * Created by christophe on 09.06.14.
 */
public class LocalValueNumbering
{
    public static BasicBlock Optimize(BasicBlock block)
    {
        int number = 0; // Global counter
        MyBiMap<SymTabInfo, Integer> symbolToNumber = new MyBiMap<SymTabInfo, Integer>();
        MyBiMap<String, Integer> opToNumber = new MyBiMap<String, Integer>();


        for (ThreeAddressCode tac : block.getTacs())
        {
            if (tac.getOpCode() == OpCodes.A2PLUS || tac.getOpCode() == OpCodes.A2TIMES || tac.getOpCode() == OpCodes.A2MINUS ||
                    tac.getOpCode() == OpCodes.A2DIV || tac.getOpCode() == OpCodes.A2NEQ || tac.getOpCode() == OpCodes.A2EQ ||
                    tac.getOpCode() == OpCodes.A2GT || tac.getOpCode() == OpCodes.A2LT)
            {
                // First of all we check if we can apply constant folding.
                // A = x + y will be evaluated if x and y are constants.
                if (tac.getArg1() instanceof IntegerSymTabInfo && tac.getArg2() instanceof IntegerSymTabInfo)
                {

                    IntegerSymTabInfo evaluated = new IntegerSymTabInfo(((IntegerSymTabInfo) tac.getArg1()).value + ((IntegerSymTabInfo) tac.getArg2()).value);
                    if (symbolToNumber.containsKey(evaluated))
                    {
                        int symbolNumberOfArg = symbolToNumber.getValue(evaluated);
                        symbolToNumber.put(tac.getResult(), symbolNumberOfArg);
                    } else
                    {
                        symbolToNumber.put(evaluated, number, true);
                        number++;
                        int symbolNumberOfArg = symbolToNumber.getValue(evaluated);
                        symbolToNumber.put(tac.getResult(), symbolNumberOfArg, true);
                    }
                    tac.setArg1(evaluated);
                    tac.setArg2(null);
                    tac.setResult(tac.getResult());
                    tac.setOpCode(OpCodes.A0);
                    continue;
                } else
                {
                    if (!symbolToNumber.containsKey(tac.getArg1()))
                    {
                        symbolToNumber.put(tac.getArg1(), number);
                        number++;
                    }
                    if (!symbolToNumber.containsKey(tac.getArg2()))
                    {
                        symbolToNumber.put(tac.getArg2(), number);
                        number++;
                    }

                    // Generate hash string
                    int valNumA = symbolToNumber.getValue(tac.getArg1());
                    int valNumB = symbolToNumber.getValue(tac.getArg2());
                    String hash;
                    if (valNumA > valNumB)
                        hash = valNumA + " " + tac.getOpCode() + " " + valNumB;
                    else
                        hash = valNumB + " " + tac.getOpCode() + " " + valNumA;


                    if (opToNumber.containsKey(hash))
                    {
                        int symbolNumber = opToNumber.getValue(hash);
                        SymTabInfo var = symbolToNumber.getKey(symbolNumber);

                        tac.setOpCode(OpCodes.A0);
                        tac.setArg2(null);
                        tac.setArg1(var);
                        symbolToNumber.put(tac.getResult(), symbolNumber);

                    } else
                    {
                        // We have not found the hash key
                        // so we put the resulting variable with the new number.
                        opToNumber.put(hash, number);
                        symbolToNumber.put(tac.getResult(), number);
                        number++;
                    }
                    continue;
                }
            }
            if (tac.getOpCode() == OpCodes.A0)
            {
                // If we are assigning to a variable that is already in our hashtable we need to
                // invalidate that record (remove it).
                if (symbolToNumber.containsKey(tac.getResult()))
                {
                    int indexForSymbol = symbolToNumber.getValue(tac.getResult());
                    opToNumber.removeWithValue(indexForSymbol);
                }
                else
                {
                    symbolToNumber.put(tac.getResult(), number);
                    number++;
                }
                // If the argument does not exist we add a unique number to it.
                if (!symbolToNumber.containsKey(tac.getArg1()))
                {
                    symbolToNumber.put(tac.getArg1(), number);
                    number++;
                }

                int symbolNumberOfArg = symbolToNumber.getValue(tac.getResult());
                symbolToNumber.put(tac.getResult(), symbolNumberOfArg);
                continue;
            }
        }

        return block;
    }
}

